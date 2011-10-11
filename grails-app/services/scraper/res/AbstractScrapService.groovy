package scraper.res

import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.ResponseHandler
import org.apache.http.impl.client.BasicResponseHandler

abstract class AbstractScrapService {

    static transactional = false

    protected def getHTML(url){
        log.debug 'Getting HTML source code from URL: ' + url
        def httpClient = new DefaultHttpClient()
        def responseBody
        try {
            def httpGet = new HttpGet(url)
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            responseBody = httpClient.execute(httpGet, responseHandler);
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpClient.getConnectionManager().shutdown();
        }
        return responseBody
    }



    def parse(html, regex, Collection results){
        html.eachMatch(regex) {
            results.add(it)
            log.debug('Scraped URL: ' + it)
        }
        log.debug("URLs de VIPs encontrados: ${results.size()}")
        return results
    }


    // Template Methods
    abstract def String getSiteURL()
}
