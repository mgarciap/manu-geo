package scraper.res

class AdSearchCriteria {

    RealStateType realStateType
    OperationType operationType
    String link
    ScrapStatus status
    String geographicArea

    //Automatic Timestamps
    Date dateCreated
    Date lastUpdated

    static constraints = {
        operationType(inList: OperationType.list())
        status(inList: ScrapStatus.list())
        realStateType(inList: RealStateType.list())
        geographicArea(nullable: true)
    }

    static hasMany = [adLinks: AdLink]

    def beforeInsert() {
        status = ScrapStatus.PENDING
    }
}
