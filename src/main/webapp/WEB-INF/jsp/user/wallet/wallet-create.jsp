<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.payments.command.constant.WebUrlConstants"%>
<%@ page import="com.epam.payments.command.constant.ParamNames"%>
<%@ page errorPage="/WEB-INF/jsp/error/error.jsp" %>

<fmt:setLocale value="${sessionScope[ParamNames.LANGUAGE]}"/>
<fmt:setBundle basename="locale.user.wallet.wallet-create"/>

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
				<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.CREATE_WALLET}">
				<input type="text" 	 name="${ParamNames.WALLET_NAME}" placeholder="<fmt:message key="placeholder.create"/>" class="form-control" required><br>
				<button type="submit" class="btn btn-primary"><fmt:message key="create"/></button>
			</form>

			<a class="nav-link active" aria-current="page" href="${WebUrlConstants.GO_USER_WALLETS_PAGE_URL}"> <fmt:message key="back"/> </a>
		</div>

	</main>
</div>
</body>
</html>