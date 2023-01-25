<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="n" uri="/WEB-INF/tld/name.tld" %>
<%@ taglib prefix="my" uri="/WEB-INF/tld/locale.tld" %>

<header class="mb-15">
    <div>
        <nav class="nav nav-masthead justify-content-right float-md-end row">
            <div class="left-block col-6">
                <c:if test="${not empty sessionScope.user_id}">
                    <a class="nav-link active" aria-current="page" href="/<n:Name value="controller.name"/><n:Name value="redirect.main"/>"> <my:Locale value="header.wallets"/> </a>
                    <a class="nav-link active" aria-current="page" href="/<n:Name value="controller.name"/><n:Name value="redirect.prepare-transfer"/>"> <my:Locale value="header.transfers"/> </a>
<%--                    <a class="nav-link active" aria-current="page" href="/<n:Name value="controller.name"/><n:Name value="redirect.transfers-history"/>">Історія переказів</a>--%>
                </c:if>
            </div>

            <div class="right-block col-6">
                <div class="right-block col-2">
                    <c:set var = "currentPage" scope = "session" value = "${sessionScope.currentPage}"/>
                    <form action="<n:Name value="controller.name"/>" method="post">
                        <input type="hidden" name="redirect" value="<n:Name value="redirect.${currentPage}"/>">
                        <input type="hidden" name="command" value="<n:Name value="command.language"/>">
                        <input type="hidden" name="language" value="en">
                        <input type="image" src="/img/us.png" alt="Submit">

                    </form>
                    <form action="<n:Name value="controller.name"/>" method="post">
                        <input type="hidden" name="redirect" value="<n:Name value="redirect.${currentPage}"/>">
                        <input type="hidden" name="command" value="<n:Name value="command.language"/>">
                        <input type="hidden" name="language" value="uk">
                        <input type="image" src="/img/ua.png" alt="Submit">
                    </form>
                </div>

                <div class="right-block col-2">
                    <c:if test="${not empty sessionScope.user_id}">
                        <form style="padding: 2%" action="<n:Name value="controller.name"/>" method="post">
                            <input type="hidden" name="redirect" value="<n:Name value="redirect.login"/>">
                            <input type="hidden" name="command" value="<n:Name value="command.logout"/>">
                            <a class="nav-link" href='#' onclick='this.parentNode.submit();'> <my:Locale value="header.logout"/> </a>
                        </form>
                    </c:if>
                </div>
            </div>

        </nav>
    </div>
</header>