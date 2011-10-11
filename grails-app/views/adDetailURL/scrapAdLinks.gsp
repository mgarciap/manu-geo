<%@ page import="scraper.res.AdLink" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'adDetailURL.label', default: 'AdLink')}"/>
    <title>Zonaprop Scraper</title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                           args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:form action="scrapAdLinks">
        <span class="name">Zonaprop Scrapping</span>
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="searchUrl"><g:message code="adDetailURL.searchUrl.label"
                                                          default="Search Url"/></label>
                    </td>
                    <td valign="top"
                        class="value">
                        <g:textField name="searchUrl" size="100"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button"><g:submitButton name="scrap" class="save"
                                                 value="${message(code: 'default.button.scrap.label', default: 'Scrap')}"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
