package ml.api

import groovyx.net.http.ParserRegistry
import net.sf.json.JSON
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient
import org.springframework.web.multipart.MultipartFile

/* Actually should be called FileService or AnythingService...
 * doesn't expect for files but anything, and identifies "picture" lists by String id
 * TODO Improve type checking if necessary
 */
class PicturesAPIService extends ml.api.AbstractAPIService {

    static transactional = true

    synchronized Map<?, List> pictureLists = new HashMap<?, List>()

    /** Adds a picture file to a flow
     *
     * @param pictureFile picture to upload
     * @param callerId id of user who is uploading the picture
     * @return a Picture representing the uploaded picture
     */
    ml.api.APIPicture save(pictureFile, callerId) {
        def filename = pictureFile.fileItem.fileName
        def result = uploadPicture(callerId, pictureFile, filename)
        if (!result.thumbnailUrl || !result.id) {
            throw new ml.api.exceptions.UnknownMlApiException("Picture upload result invalid: $result")
        }
        result
    }

    /**
     * Sube una imagen a la api de mercado libre
     * @param callerId : id del usuario logueado
     * @param siteId : sitio para el cual se sube la imagen
     * @param file : imagen que se desea subir al sitio
     * @return Una Picture que representa la imagen subida
     */
    protected uploadPicture(callerId, MultipartFile file, String filename) {
        String url = "${this.serviceURL}/pictures?caller.id=$callerId"
        log.debug "About to upload image to url $url"

        HttpResponse response = postMultipartRequestByteArray(file, filename, url)

        ParserRegistry registry = new ParserRegistry()
        JSON result = registry.parseJSON(response)
        log.debug "Upload result as JSON: \n${-> def indent = 4; result.toString indent}"

        if (!(response.statusLine.statusCode in 200..299))
            throw new ml.api.exceptions.UnknownMlApiException("Unable to post picture. Result: $result")
        return selectPictureVariantInfo(result)
    }

    /**
     * Selects a picture variant from the post's json result.
     * @param jsonResult
     * @return
     */
    protected selectPictureVariantInfo(jsonResult) {
        def variants = jsonResult.variations
        if (variants.empty)
            throw new ml.api.exceptions.UnknownMlApiException("Image has no variants")
        // There should always be a variant with 120x120 size. But if none comes, take the first one.
        def variant = variants.find { it.size == "120x120" } ?: variants[0]
        new ml.api.APIPicture(id: jsonResult.id, thumbnailUrl: variant.url)
    }

    protected HttpResponse postMultipartRequestByteArray(MultipartFile file, String filename, String url) {
        // Build method
        HttpPost method = new HttpPost(url)
        org.apache.http.entity.mime.MultipartEntity entity = new org.apache.http.entity.mime.MultipartEntity()
        entity.addPart("file", new ml.api.InputStreamKnownSizeBody(file.inputStream, file.size, file.contentType, filename))
        method.setEntity(entity)
        // The execution:
        DefaultHttpClient httpclient = new DefaultHttpClient()
        return httpclient.execute(method)
    }
}
