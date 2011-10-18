package ml.api

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

class ItemsAPIService extends ml.api.AbstractAPIService {

    private static final ITEMS_API_PATH = '/items'
    private static final DESCRIPTION_API_PATH = '/descriptions'

    /**
     *
     * @param callerId
     * @param itemData The item data to post.
     * @param description
     * @return A map with the values:
     *         paymentRequired: Whether the user should pay for the item publication.
     *         item: The item data response.
     */
    def postItem(callerId, itemData, description) {
        validateItemData(itemData)
        log.debug "Posting item with JSON:\n${-> util.JsonUtils.toPrettyJsonString itemData}"

        def http = new HTTPBuilder()
        def response = http.request(this.serviceURL, Method.POST, ContentType.JSON) {
            uri.path = ITEMS_API_PATH
            uri.query = ["caller.id": callerId]
            body = itemData

            response.success = { resp, item ->
                log.debug "Status: $resp.statusLine\nItem: ${-> util.JsonUtils.toPrettyJsonString item}"
                [paymentRequired: false, item: item]
            }

            response.'402' = { resp, item ->
                log.debug "Status: $resp.statusLine\nItem: ${-> util.JsonUtils.toPrettyJsonString item}"
                [paymentRequired: true, item: item]
            }
        }
        if (description) {
            def descriptionPostPath = "$ITEMS_API_PATH/${response.item.id}$DESCRIPTION_API_PATH"
            postWithRest(descriptionPostPath, [text: description], ["caller.id": callerId])
        }

        return response
    }

    def getItem(itemId) {
        def path = "$ITEMS_API_PATH/$itemId"
        getWithRest(path)
    }

    // TODO Validamos algo? Aunque sea presencia de parametros?

    def validateItemData(itemData) {
        true
    }
}
