<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.payments.command.constant.WebUrlConstants"%>
<%@ page import="com.epam.payments.command.constant.ParamNames"%>
<%@ page errorPage="/WEB-INF/jsp/error/error.jsp" %>

<fmt:setLocale value="${sessionScope[ParamNames.LANGUAGE]}"/>
<fmt:setBundle basename="locale.user.wallet.wallet-top-up"/>

<html class="h-100">

<head>
	<%@ include file="/WEB-INF/jsp/jspf/head.jspf" %>
</head>

<body class="h-100 text-white bg-dark">
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">

	<header class="mb-15">
		<%@ include file="/WEB-INF/jsp/jspf/header.jspf" %>
	</header>

	<main class="px-3">
		<div class="form-container">

			<%@ include file="/WEB-INF/jsp/jspf/alerts.jspf" %>

			<div class="categories p-3">
				<h3><fmt:message key="title"/></h3>
			</div>

			<form style="padding: 2%" action="${WebUrlConstants.COMMON_URL_PREFIX}" method="post">
				<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.TOP_UP_BALANCE}">
				<input type="number" step="0.01" min="0.00" name="sum" placeholder="<fmt:message key="placeholder.sum"/>" class="form-control" required><br>
				<select name="${ParamNames.BILL_NUMBER}" class="form-select">
					<c:forEach var="wallet" items="${requestScope.wallets}">
						<option value="${wallet.getBill_number()}" >
							<c:out value="${wallet.getName()} ${wallet.getBill_number()} ${wallet.getBalance()} грн" />
						</option>
					</c:forEach>
				</select>
				<br>

				<button type="submit" class="btn btn-primary"><fmt:message key="top-up"/></button>
			</form>

			<a class="nav-link active" aria-current="page" href="${WebUrlConstants.GO_USER_WALLETS_PAGE_URL}"> <fmt:message key="back"/> </a>
		</div>
	</main>
</div>
</body>
</html>