package utils

import grails.converters.JSON

class JsonUtils {
    
    /**
     * Returns the JSON string representation of an object in a pretty format.
     *
     * @param obj
     * @return
     */
    static String toPrettyJsonString(obj) {
        def prettyPrint = true
        (obj as JSON).toString prettyPrint
    }
}
