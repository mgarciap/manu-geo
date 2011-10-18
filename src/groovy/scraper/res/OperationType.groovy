package scraper.res

public enum OperationType {

    SALE('VENTA'),
    RENT('ALQUILER'),
    TEMPORAL_RENT('ALQUILER TEMPORAL')

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

