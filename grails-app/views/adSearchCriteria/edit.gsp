<%@ page import="scraper.res.AdSearchCriteria" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'adSearchCriteria.label', default: 'AdSearchCriteria')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                           args="[entityName]"/></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label"
                                                                               args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${adSearchCriteriaInstance}">
        <div class="errors">
            <g:renderErrors bean="${adSearchCriteriaInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form method="post">
        <g:hiddenField name="id" value="${adSearchCriteriaInstance?.id}"/>
        <g:hiddenField name="version" value="${adSearchCriteriaInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="operationType"><g:message code="adSearchCriteria.operationType.label"
                                                              default="Operation Type"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: adSearchCriteriaInstance, field: 'operationType', 'errors')}">
                        <g:select name="operationType" from="${scraper.res.OperationType?.values()}"
                                  keys="${scraper.res.OperationType?.values()*.name()}"
                                  value="${adSearchCriteriaInstance?.operationType?.name()}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="status"><g:message code="adSearchCriteria.status.label" default="Status"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: adSearchCriteriaInstance, field: 'status', 'errors')}">
                        <g:select name="status" from="${scraper.res.ScrapStatus?.values()}"
                                  keys="${scraper.res.ScrapStatus?.values()*.name()}"
                                  value="${adSearchCriteriaInstance?.status?.name()}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="realStateType"><g:message code="adSearchCriteria.realStateType.label"
                                                              default="Real State Type"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: adSearchCriteriaInstance, field: 'realStateType', 'errors')}">
                        <g:select name="realStateType" from="${scraper.res.RealStateType?.values()}"
                                  keys="${scraper.res.RealStateType?.values()*.name()}"
                                  value="${adSearchCriteriaInstance?.realStateType?.name()}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="geographicArea"><g:message code="adSearchCriteria.geographicArea.label"
                                                               default="Geographic Area"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: adSearchCriteriaInstance, field: 'geographicArea', 'errors')}">
                        <g:textField name="geographicArea" value="${adSearchCriteriaInstance?.geographicArea}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="adLinks"><g:message code="adSearchCriteria.adLinks.label"
                                                        default="Ad Links"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: adSearchCriteriaInstance, field: 'adLinks', 'errors')}">

                        <ul>
                            <g:each in="${adSearchCriteriaInstance?.adLinks?}" var="a">
                                <li><g:link controller="adLink" action="show"
                                            id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
                            </g:each>
                        </ul>
                        <g:link controller="adLink" action="create"
                                params="['adSearchCriteria.id': adSearchCriteriaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'adLink.label', default: 'AdLink')])}</g:link>

                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="link"><g:message code="adSearchCriteria.link.label" default="Link"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: adSearchCriteriaInstance, field: 'link', 'errors')}">
                        <g:textField name="link" value="${adSearchCriteriaInstance?.link}"/>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button"><g:actionSubmit class="save" action="update"
                                                 value="${message(code: 'default.button.update.label', default: 'Update')}"/></span>
            <span class="button"><g:actionSubmit class="delete" action="delete"
                                                 value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                                 onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
