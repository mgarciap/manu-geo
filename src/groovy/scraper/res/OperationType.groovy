package scraper.res

public enum OperationType {

    SALE('venta'),
    RENT('alquiler'),
    TEMPORAL_RENT('alquiler temporal')

    final String id

    static list() {
        [SALE, RENT, TEMPORAL_RENT]
    }

    OperationType(id) {
        this.id = id
    }

    String toString() {
        this.id.toString()
    }
}

