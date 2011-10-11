package scraper.res

public enum ScrapStatus {

    SCRAPED('scraped'),
    PENDING('pending')

    final String id

    static list() {
        [SCRAPED, PENDING]
    }

    ScrapStatus(id) {
        this.id = id
    }

    String toString() {
        this.id.toString()
    }
}

