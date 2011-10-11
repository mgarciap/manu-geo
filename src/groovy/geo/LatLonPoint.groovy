package geo

/**
 * Created by IntelliJ IDEA.
 * User: manu
 * Date: 10/2/11
 * Time: 9:55 PM
 * To change this template use File | Settings | File Templates.
 */
class LatLonPoint {
    BigDecimal latitude
    BigDecimal longitude

    static constraints = {
        latitude(nullable: true, max: new BigDecimal(999), min: new BigDecimal(-100), scale: 10)
        longitude(nullable: true, max: new BigDecimal(999), min: new BigDecimal(-100), scale: 10)
    }

    String toString() {
        return "(Latitude: $latitude. Longitude: $longitude)"
    }

    public LatLonPoint() {

    }

    public LatLonPoint(lat, lon) {
        latitude = toBigDecimal(lat)
        longitude = toBigDecimal(lon)
    }

    BigDecimal toBigDecimal(def value) {
        def valueToString = value.toString()

        return new BigDecimal(valueToString)
    }

    public LatLonPoint(BigDecimal lat, BigDecimal lon) {
        latitude = lat
        longitude = lon
    }

}

