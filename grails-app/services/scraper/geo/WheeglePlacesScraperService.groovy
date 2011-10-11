package scraper.geo

import geo.PlaceStatus
import geo.LatLonPoint
import net.sf.json.JSONObject

class WheeglePlacesScraperService {

    static transactional = false;

    //http://www.wheegle.com/api/v1/shop/search/?term=bancos&format=json
    static source = 'www.wheegle.com'
    static scheme = 'http'
    static serviceDomain = 'www.wheegle.com'
    static servicePath = '/api/v1/shop/search'

    def scrap(String type) {
        def value
        def queryParams = this.getQueryParams(type)
        withRest(uri: this.getURItoScrap()) {
            def response = get(path: servicePath, format: "json", query: this.getQueryParams(type))
            value = response?.responseData
        }
        return value
    }

    def getURItoScrap() {
        return scheme + '://' + serviceDomain
    }

    def getQueryParams(String type) {
        return [term: type, format: "json"]
    }

    def getQueryString(String type) {
        def mapParams = this.getQueryParams(type)
        def queryString = mapParams.collect { key, value -> "$key=" + URLEncoder.encode(value) }.join("&")
        return "?" + queryString
    }

    def printURL(String type) {
        println 'URL to scrap: ' + getURItoScrap() + servicePath + getQueryString(type)
    }


    def scrapCategory(String categ) {
        this.consoleLog("Comenzando proceso de geocodificacion")

        def jsonResults = JSONObject.fromObject(this.scrap(categ))
        def results = []
        this.consoleLog("Cantidad de resultados: " + jsonResults.info_id)
        results = jsonResults.objects
        //println "values: " + jsonResults.objects
        results.each {it ->
            def place = new Place(name: it["name"], address: it["address"], type: categ, status: PlaceStatus.ACTIVE, description: 'not yet implemented')
            place.point = new LatLonPoint(((String) it["latitude"]).toBigDecimal(), ((String) it["longitude"]).toBigDecimal())
            place.save(flush: true)
            println "Stored Place: " + place
        }
//        results.each {result ->
        //            println "key: " + result
        //            println "address: " + result["address"]
        //        }

        this.consoleLog "FIN proceso de geocodificacion: "
    }


    private void consoleLog(String message) {
        log.debug message
        print "$message \n"
    }
}



