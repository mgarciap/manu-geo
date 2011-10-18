package scraper.res

class AdLink {
    String site
    String fromUrl
    String url
    ScrapStatus status
    OperationType operation

    //ToDo: Ad category

    //Automatic Timestamps
    Date dateCreated
    Date lastUpdated

    static constraints = {
        site(url: true)
        fromUrl(url: true, nullable: true)
        url(url: true)//, unique: true)
        status(inList: ScrapStatus.list())
        operation(inList: OperationType.list(), nullable: true)
    }

    static belongsTo = [adSearchCriteria: AdSearchCriteria]

    public String toString ( ) {
        return "AdLink{" +
                "site='" + site + '\'' +
                ", fromUrl='" + fromUrl + '\'' +
                ", url='" + url + '\'' +
                '}' ;
    }}
