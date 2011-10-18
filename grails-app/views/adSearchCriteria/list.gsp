<%@ page import="scraper.res.AdSearchCriteria" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'adSearchCriteria.label', default: 'AdSearchCriteria')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label"
                                                                               args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
            <tr>

                <g:sortableColumn property="id" title="${message(code: 'adSearchCriteria.id.label', default: 'Id')}"/>

                <g:sortableColumn property="operationType"
                                  title="${message(code: 'adSearchCriteria.operationType.label', default: 'Operation Type')}"/>

                <g:sortableColumn property="status"
                                  title="${message(code: 'adSearchCriteria.status.label', default: 'Status')}"/>

                <g:sortableColumn property="realStateType"
                                  title="${message(code: 'adSearchCriteria.realStateType.label', default: 'Real State Type')}"/>

                <g:sortableColumn property="geographicArea"
                                  title="${message(code: 'adSearchCriteria.geographicArea.label', default: 'Geographic Area')}"/>

                <g:sortableColumn property="dateCreated"
                                  title="${message(code: 'adSearchCriteria.dateCreated.label', default: 'Date Created')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${adSearchCriteriaInstanceList}" status="i" var="adSearchCriteriaInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show"
                                id="${adSearchCriteriaInstance.id}">${fieldValue(bean: adSearchCriteriaInstance, field: "id")}</g:link></td>

                    <td>${fieldValue(bean: adSearchCriteriaInstance, field: "operationType")}</td>

                    <td>${fieldValue(bean: adSearchCriteriaInstance, field: "status")}</td>

                    <td>${fieldValue(bean: adSearchCriteriaInstance, field: "realStateType")}</td>

                    <td>${fieldValue(bean: adSearchCriteriaInstance, field: "geographicArea")}</td>

                    <td><g:formatDate date="${adSearchCriteriaInstance.dateCreated}"/></td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${adSearchCriteriaInstanceTotal}"/>
    </div>
</div>
</body>
</html>
