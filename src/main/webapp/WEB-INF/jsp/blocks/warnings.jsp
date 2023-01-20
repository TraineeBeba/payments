<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="n" uri="/WEB-INF/tld/name.tld" %>
<%@ taglib prefix="my" uri="/WEB-INF/tld/locale.tld" %>


<c:if test="${sessionScope.registerSuccess eq true}">
    <div class="alertWarning">
        <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
        <strong><my:Locale value="alertWarning.alert"/></strong>
        <text> <my:Locale value="alertWarning.sucess.register"/> </text>
        <text> <br><my:Locale value="alertWarning.emodji"/></text>
    </div>
    <c:remove var="registerSuccess" scope="session" />
</c:if>

<c:if test="${not empty sessionScope.walletCreationSuccess eq true}">
    <div class="alertWarning">
        <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
        <strong><my:Locale value="alertWarning.alert"/></strong>
        <text> <my:Locale value="alertWarning.sucess.walletCreation"/> </text>
        <text> <br><my:Locale value="alertWarning.emodji"/></text>
    </div>
    <c:remove var="walletCreationSuccess" scope="session" />
</c:if>

<c:if test="${not empty sessionScope.wrongData}">
    <div class="alertError">
        <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
        <strong><my:Locale value="alertError.error"/></strong> <text> <my:Locale value="${sessionScope.wrongData}"/> </text>
        <text> <br><my:Locale value="alertError.emodji"/></text>
    </div>
    <c:remove var="wrongData" scope="session" />
</c:if>