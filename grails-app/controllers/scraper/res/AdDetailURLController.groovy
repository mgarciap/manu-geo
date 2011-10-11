package scraper.res

class AdDetailURLController {
    ZonapropScraperService zonapropScraperService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [adDetailURLInstanceList: AdLink.list(params), adDetailURLInstanceTotal: AdLink.count()]
    }

    def create = {
        def adDetailURLInstance = new AdLink()
        adDetailURLInstance.properties = params
        return [adDetailURLInstance: adDetailURLInstance]
    }

    def scrapAdLinks = {
        def String searchUrl = params.searchUrl

        if (searchUrl == null || searchUrl.isEmpty()) {
            return
        } else {
            zonapropScraperService.scrapAdLinksFromUrl(searchUrl)
            redirect(action: 'list')
        }
    }

    def save = {
        def adDetailURLInstance = new AdLink(params)
        if (adDetailURLInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'adDetailURL.label', default: 'AdLink'), adDetailURLInstance.id])}"
            redirect(action: "show", id: adDetailURLInstance.id)
        }
        else {
            render(view: "create", model: [adDetailURLInstance: adDetailURLInstance])
        }
    }

    def show = {
        def adDetailURLInstance = AdLink.get(params.id)
        if (!adDetailURLInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'adDetailURL.label', default: 'AdLink'), params.id])}"
            redirect(action: "list")
        }
        else {
            [adDetailURLInstance: adDetailURLInstance]
        }
    }

    def edit = {
        def adDetailURLInstance = AdLink.get(params.id)
        if (!adDetailURLInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'adDetailURL.label', default: 'AdLink'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [adDetailURLInstance: adDetailURLInstance]
        }
    }

    def update = {
        def adDetailURLInstance = AdLink.get(params.id)
        if (adDetailURLInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (adDetailURLInstance.version > version) {

                    adDetailURLInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'adDetailURL.label', default: 'AdLink')] as Object[], "Another user has updated this AdLink while you were editing")
                    render(view: "edit", model: [adDetailURLInstance: adDetailURLInstance])
                    return
                }
            }
            adDetailURLInstance.properties = params
            if (!adDetailURLInstance.hasErrors() && adDetailURLInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'adDetailURL.label', default: 'AdLink'), adDetailURLInstance.id])}"
                redirect(action: "show", id: adDetailURLInstance.id)
            }
            else {
                render(view: "edit", model: [adDetailURLInstance: adDetailURLInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'adDetailURL.label', default: 'AdLink'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def adDetailURLInstance = AdLink.get(params.id)
        if (adDetailURLInstance) {
            try {
                adDetailURLInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'adDetailURL.label', default: 'AdLink'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'adDetailURL.label', default: 'AdLink'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'adDetailURL.label', default: 'AdLink'), params.id])}"
            redirect(action: "list")
        }
    }
}
