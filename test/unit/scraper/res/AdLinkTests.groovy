package scraper.res

import grails.test.*

class AdLinkTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {

    }

    void testSaveAndRetrieve() {
        def adLink = new AdLink(site: 'http://site.com', fromUrl: 'http://site.com/search', url: 'http://site.com/url', status: ScrapStatus.PENDING)
        adLink.save(flush: true)
        assertNotNull AdLink.get(1)
    }
}
