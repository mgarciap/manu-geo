<%@ page import="scraper.res.AdLink" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'adDetailURL.label', default: 'AdLink')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label"
                                                                               args="[entityName]"/></g:link></span>
    <span class="menuButton"><g:link class="create" action="scrapAdLinks">ScrapAdLinks</g:link> </span>
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

                <g:sortableColumn property="id" title="${message(code: 'adDetailURL.id.label', default: 'Id')}"/>

                <g:sortableColumn property="site" title="${message(code: 'adDetailURL.site.label', default: 'Site')}"/>

                <g:sortableColumn property="searchUrl"
                                  title="${message(code: 'adDetailURL.fromUrl.label', default: 'Search Url')}"/>

                <g:sortableColumn property="url" title="${message(code: 'adDetailURL.url.label', default: 'Url')}"/>

                <g:sortableColumn property="status"
                                  title="${message(code: 'adDetailURL.status.label', default: 'Status')}"/>

                <g:sortableColumn property="dateCreated"
                                  title="${message(code: 'adDetailURL.dateCreated.label', default: 'Date Created')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${adDetailURLInstanceList}" status="i" var="adDetailURLInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show"
                                id="${adDetailURLInstance.id}">${fieldValue(bean: adDetailURLInstance, field: "id")}</g:link></td>

                    <td>${fieldValue(bean: adDetailURLInstance, field: "site")}</td>

                    <td>${fieldValue(bean: adDetailURLInstance, field: "fromUrl")}</td>

                    <td><a href="${fieldValue(bean: adDetailURLInstance, field: "url")}">${fieldValue(bean: adDetailURLInstance, field: "url")}</a></td>

                    <td>${fieldValue(bean: adDetailURLInstance, field: "status")}</td>

                    <td><g:formatDate date="${adDetailURLInstance.dateCreated}"/></td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${adDetailURLInstanceTotal}"/>
    </div>
</div>
</body>
</html>
