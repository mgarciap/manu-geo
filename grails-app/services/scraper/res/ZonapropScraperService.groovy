package scraper.res

import org.apache.http.client.HttpClient
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.ResponseHandler
import org.apache.http.impl.client.BasicResponseHandler

class ZonapropScraperService extends AbstractScrapService {

    static transactional = false
    static site = "http://www.zonaprop.com.ar"

    def parseDptosAbasto() {
        def resultOffSet = 100
        def url = "http://propiedades.zonaprop.com.ar/venta-departamentos-capital-federal-abasto-3-amb/ncZ1_opZtipo-operacion-venta_lnZ3661_caZcantidad-ambientes-3_miZ" + resultOffSet
        Set parsedLinks = new HashSet() // Lo hago un Set porque me trae links repetidos y me da fiaca refinar la regex
        def adLinkRegex = /"http:\/\/aviso.zonaprop.com.ar\/\d+-\S+"/

        log.debug 'Scraping Ad Links for "Departamento de Abasto". Url: ' + url
        parse(getHTML(url), adLinkRegex, parsedLinks);
        log.debug 'Ad Links scrapping finished'
        parsedLinks.each {
            def adLink = new AdLink(site: getSite(), fromUrl: url, url: it, status: ScrapStatus.PENDING)
            adLink.save(flush: true)
        }
    }

    def scrapAdLinksFromUrl(url){
        def resultOffSet = 100
        Set parsedLinks = new HashSet() // Lo hago un Set porque me trae links repetidos y me da fiaca refinar la regex
        def adLinkRegex = /"http:\/\/aviso.zonaprop.com.ar\/\d+-\S+"/

        log.debug 'Scraping Ad Links from URL: ' + url
        parse(getHTML(url), adLinkRegex, parsedLinks);
        log.debug 'Ad Links scrapping finished'
        parsedLinks.each {
            def adLinkUrl = ((String) it).replaceAll('"', '')
            adLinkUrl = adLinkUrl.substring(0, adLinkUrl.indexOf('-'))
            def adLink = new AdLink(site: getSite(), fromUrl: url, url: adLinkUrl, status: ScrapStatus.PENDING)
            log.debug 'Saving AdLink: ' + adLink
            if (adLink.save(flush: true)) {
                log.debug '-----> Saved'
            } else {
                adLink.errors.each {
                    println it
                }
            }
        }
    }


    @Override
    String getSiteURL() {
        return site
    }
}