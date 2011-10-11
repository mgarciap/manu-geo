package geo

/**
 * Created by IntelliJ IDEA.
 * User: manu
 * Date: 10/2/11
 * Time: 9:54 PM
 * To change this template use File | Settings | File Templates.
 */
public enum PlaceStatus {

    ACTIVE('A'),
    INACTIVE('I'),
    WAITING('P');

    final String id

    static list() {
        [ACTIVE, INACTIVE, WAITING]
    }

    PlaceStatus(id) {
        this.id = id
    }

    String toString() {
        this.id.toString()
    }
}

