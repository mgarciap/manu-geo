package geo

import grails.test.*
import scraper.geo.Place

class PlaceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {
        def place = new Place(name: "name", address:"address", type: "lalala", status: PlaceStatus.ACTIVE,
                description: 'not yet implemented')
        place.point = new LatLonPoint(("31").toBigDecimal(), ("31").toBigDecimal())
        place.save(flush: true)
        println "Stored Place: " + place

        assertNotNull Place.get(1)

    }
}
