<%@ page import="scraper.res.AdSearchCriteria" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'adSearchCriteria.label', default: 'AdSearchCriteria')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
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
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
            <tbody>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="adSearchCriteria.id.label" default="Id"/></td>

                <td valign="top" class="value">${fieldValue(bean: adSearchCriteriaInstance, field: "id")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="adSearchCriteria.operationType.label"
                                                         default="Operation Type"/></td>

                <td valign="top" class="value">${adSearchCriteriaInstance?.operationType?.encodeAsHTML()}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="adSearchCriteria.status.label" default="Status"/></td>

                <td valign="top" class="value">${adSearchCriteriaInstance?.status?.encodeAsHTML()}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="adSearchCriteria.realStateType.label"
                                                         default="Real State Type"/></td>

                <td valign="top" class="value">${adSearchCriteriaInstance?.realStateType?.encodeAsHTML()}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="adSearchCriteria.geographicArea.label"
                                                         default="Geographic Area"/></td>

                <td valign="top"
                    class="value">${fieldValue(bean: adSearchCriteriaInstance, field: "geographicArea")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="adSearchCriteria.adLinks.label" default="Ad Links"/></td>

                <td valign="top" style="text-align: left;" class="value">
                    <ul>
                        <g:each in="${adSearchCriteriaInstance.adLinks}" var="a">
                            <li><g:link controller="adLink" action="show"
                                        id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
                        </g:each>
                    </ul>
                </td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="adSearchCriteria.dateCreated.label"
                                                         default="Date Created"/></td>

                <td valign="top" class="value"><g:formatDate date="${adSearchCriteriaInstance?.dateCreated}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="adSearchCriteria.lastUpdated.label"
                                                         default="Last Updated"/></td>

                <td valign="top" class="value"><g:formatDate date="${adSearchCriteriaInstance?.lastUpdated}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="adSearchCriteria.link.label" default="Link"/></td>

                <td valign="top" class="value">${fieldValue(bean: adSearchCriteriaInstance, field: "link")}</td>

            </tr>

            </tbody>
        </table>
    </div>

    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${adSearchCriteriaInstance?.id}"/>
            <span class="button"><g:actionSubmit class="edit" action="edit"
                                                 value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
            <span class="button"><g:actionSubmit class="delete" action="delete"
                                                 value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                                 onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
        </g:form>
    </div>
</div>
</body>
</html>
