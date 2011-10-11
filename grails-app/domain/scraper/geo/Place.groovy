package scraper.geo

import geo.PlaceStatus
import geo.LatLonPoint

class Place {
    String name
    String address //ToDo: extraer en clase con pais, provincia, etc
    String description


    String category
    PlaceStatus status = PlaceStatus.ACTIVE

    //Where does it come from
    String source

    // Geocoding
    LatLonPoint point

    static embedded = ['point']
    static constraints = {
        category(nullable: true)
        status(inList: PlaceStatus.list(), nullable: true)
        description(nullable: true)
        source(nullable: true)
        point(nullable: true)
    }

    public String toString() {
        return "[id: '${id}', name: '${name}', address: '${address}']"
    }
}
