package ml.api

import static utils.JsonUtils.toPrettyJsonString

import groovyx.net.http.HttpResponseException
import net.sf.json.JSONNull
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.codehaus.groovy.grails.web.json.JSONObject
import static groovyx.net.http.ContentType.JSON

abstract class AbstractAPIService {

//    def cacheService

    def getWithRest(path, query, Closure unmarshall = {it}) {
        def value
        log.debug "Sending REST request to $serviceURL$path ${query ? "with query $query" : ''}"
        withRest(uri: this.serviceURL) {
            def response = get(path: path.toString(), query: query)
            value = response.responseData
            convertJsonNulltoPrimitiveNull(value)
        }
        value = unmarshall(value)
        log.debug "Response unmarshalled"
        return value
    }

    def getWithRest(path, Closure unmarshall = {it}) {
        getWithRest(path, [:], unmarshall)
    }

    // TODO Tests!!!

    def postWithRest(path, Map body, query) throws HttpResponseException {
        postWithRest(path: path, body: body, query: query)
    }

    def postWithRest(path, Map body) throws HttpResponseException {
        postWithRest(path, body, [:])
    }

    def postWithRest(Map params) throws HttpResponseException {
        params.requestContentType = params.requestContentType ?: JSON

        log.debug "Posting REST request to $serviceURL$params.path with query $params.query and body:\n${-> util.JsonUtils.toPrettyJsonString params.body}"
        def response = null
        withRest(uri: this.serviceURL) {
            response = post(params)
            convertJsonNulltoPrimitiveNull(response.responseData)
        }
        return response
    }

    def getServiceURL() {
        ConfigurationHolder.config.mlAPI.url
    }

    static void convertJsonNulltoPrimitiveNull(object) {

        if (object in Collection) {
            object.each {
                convertJsonNulltoPrimitiveNull(it)
            }
        }

        if (object in Map) {
            for (tuple in object) {
                if (tuple.value.getClass() == net.sf.json.JSONNull ||
                        JSONNull.instance == tuple.value || JSONObject.NULL == tuple.value) {
                    tuple.value = null
                } else if (tuple.value in Map || tuple.value in Collection) {
                    convertJsonNulltoPrimitiveNull(tuple.value)
                }
            }
        }
    }
}
