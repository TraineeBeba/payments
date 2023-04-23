<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.payments.command.constant.WebUrlConstants"%>
<%@ page import="com.epam.payments.command.constant.ParamNames"%>
<%@ page errorPage="/WEB-INF/jsp/error/error.jsp" %>
<%@ page errorPage="/WEB-INF/jsp/error/error.jsp" %>

<fmt:setLocale value="${sessionScope[ParamNames.LANGUAGE]}"/>
<fmt:setBundle basename="locale.user.transfer.transfer-send"/>

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
			<c:set var="transferDTO" value="${sessionScope[ParamNames.TRANSFER_DTO]}"/>

			<%@ include file="/WEB-INF/jsp/jspf/alerts.jspf" %>

			<div class="categories p-3">
				<h3><fmt:message key="confirm"/></h3>
			</div>

			<div class="categories p-3">
				<h3> <fmt:message key="sender-wallet"/> ${transferDTO.sender_bill_number} </h3>
			</div>

			<div class="categories p-3">
				<h3> <fmt:message key="recipient-wallet"/> ${transferDTO.recipient_bill_number} </h3>
			</div>

			<div class="categories p-3">
				<h3> <fmt:message key="sum"/> ${transferDTO.sum} грн</h3>
			</div>

			<form style="padding: 2%" action="${WebUrlConstants.COMMON_URL_PREFIX}" method="post">
				<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.SEND_TRANSFER}">

				<button type="submit" class="btn btn-primary"><fmt:message key="send"/></button>
			</form>

			<form style="padding: 2%" action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
				<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.CANCEL_TRANSFER}">

				<a class="nav-link" href='#' onclick='this.parentNode.submit(); '><fmt:message key="back"/></a>
			</form>

		</div>
	</main>
</div>
</body>
</html>