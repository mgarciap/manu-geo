package scraper.res

class AdSearchCriteriaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [adSearchCriteriaInstanceList: AdSearchCriteria.list(params), adSearchCriteriaInstanceTotal: AdSearchCriteria.count()]
    }

    def create = {
        def adSearchCriteriaInstance = new AdSearchCriteria()
        adSearchCriteriaInstance.properties = params
        return [adSearchCriteriaInstance: adSearchCriteriaInstance]
    }

    def save = {
        def adSearchCriteriaInstance = new AdSearchCriteria(params)
        if (adSearchCriteriaInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'adSearchCriteria.label', default: 'AdSearchCriteria'), adSearchCriteriaInstance.id])}"
            redirect(action: "show", id: adSearchCriteriaInstance.id)
        }
        else {
            render(view: "create", model: [adSearchCriteriaInstance: adSearchCriteriaInstance])
        }
    }

    def show = {
        def adSearchCriteriaInstance = AdSearchCriteria.get(params.id)
        if (!adSearchCriteriaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'adSearchCriteria.label', default: 'AdSearchCriteria'), params.id])}"
            redirect(action: "list")
        }
        else {
            [adSearchCriteriaInstance: adSearchCriteriaInstance]
        }
    }

    def edit = {
        def adSearchCriteriaInstance = AdSearchCriteria.get(params.id)
        if (!adSearchCriteriaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'adSearchCriteria.label', default: 'AdSearchCriteria'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [adSearchCriteriaInstance: adSearchCriteriaInstance]
        }
    }

    def update = {
        def adSearchCriteriaInstance = AdSearchCriteria.get(params.id)
        if (adSearchCriteriaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (adSearchCriteriaInstance.version > version) {

                    adSearchCriteriaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'adSearchCriteria.label', default: 'AdSearchCriteria')] as Object[], "Another user has updated this AdSearchCriteria while you were editing")
                    render(view: "edit", model: [adSearchCriteriaInstance: adSearchCriteriaInstance])
                    return
                }
            }
            adSearchCriteriaInstance.properties = params
            if (!adSearchCriteriaInstance.hasErrors() && adSearchCriteriaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'adSearchCriteria.label', default: 'AdSearchCriteria'), adSearchCriteriaInstance.id])}"
                redirect(action: "show", id: adSearchCriteriaInstance.id)
            }
            else {
                render(view: "edit", model: [adSearchCriteriaInstance: adSearchCriteriaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'adSearchCriteria.label', default: 'AdSearchCriteria'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def adSearchCriteriaInstance = AdSearchCriteria.get(params.id)
        if (adSearchCriteriaInstance) {
            try {
                adSearchCriteriaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'adSearchCriteria.label', default: 'AdSearchCriteria'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'adSearchCriteria.label', default: 'AdSearchCriteria'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'adSearchCriteria.label', default: 'AdSearchCriteria'), params.id])}"
            redirect(action: "list")
        }
    }
}
