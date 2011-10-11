package geo

/**
 * Created by IntelliJ IDEA.
 * User: manu
 * Date: 10/2/11
 * Time: 9:54 PM
 * To change this template use File | Settings | File Templates.
 */
public enum PlaceType {

    BANK('banco'),
    SUBWAY('subte')


    final String id

    static list() {
        [BANK, SUBWAY]
    }

    PlaceType(id) {
        this.id = id
    }

    String toString() {
        this.id.toString()
    }
}

