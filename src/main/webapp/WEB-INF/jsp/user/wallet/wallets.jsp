<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.payments.command.constant.SortConstants"%>
<%@ page import="com.epam.payments.command.constant.WebUrlConstants"%>
<%@ page import="com.epam.payments.command.constant.ParamNames"%>
<%@ page errorPage="/WEB-INF/jsp/error/error.jsp" %>

<fmt:setLocale value="${sessionScope[ParamNames.LANGUAGE]}"/>
<fmt:setBundle basename="locale.user.wallet.wallets"/>

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
		<%@ include file="/WEB-INF/jsp/jspf/alerts.jspf" %>

		<div class="сinemas p-3">
			<h3> <fmt:message key="page.user.title"/>  <%--${sessionScope.selectedUser.getUsername()}--%></h3>

			<%--<c:if test="${not empty sessionScope.selectedUser}">
				<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
					<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_ADMIN_USERS_PAGE}">

					<a class="nav-link" href='#' onclick='this.parentNode.submit(); '> Назад </a>
				</form>
			</c:if>--%>

			<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
				<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_CREATE_WALLET_PAGE}">

				<a class="nav-link" href='#' onclick='this.parentNode.submit(); '><fmt:message key="page.user.add_new"/> </a>
			</form>

		</div>

		<div class="left-block col-4">

			<form style="padding: 2%">
				<a class="pr-2"><fmt:message key="page.user.sort"/></a>
			</form>

			<form style="padding: 2%" action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
				<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_USER_WALLETS_PAGE}">
				<input type="hidden" name="${ParamNames.WALLET_SORT}" value="${SortConstants.DEFAULT_WALLET_SORT}">

				<a class="pr-2" href='#' onclick='this.parentNode.submit();'> <fmt:message key="page.user.by_number"/> </a>
			</form>

			<form style="padding: 2%" action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
				<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_USER_WALLETS_PAGE}">
				<input type="hidden" name="${ParamNames.WALLET_SORT}" value="${SortConstants.WALLET_SORT_BY_NAME_ASC}">

				<a class="pr-2" href='#' onclick='this.parentNode.submit(); '> <fmt:message key="page.user.by_name"/> </a>
			</form>

			<form style="padding: 2%" action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
				<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_USER_WALLETS_PAGE}">
				<input type="hidden" name="${ParamNames.WALLET_SORT}" value="${SortConstants.WALLET_SORT_BY_BALANCE_ASC}">

				<a class="pr-2" href='#' onclick='this.parentNode.submit();'> <fmt:message key="page.user.by_balance"/> </a>
			</form>
		</div>
		<br>

		<table class="table table-dark table-bordered table-hover text-center">
			<thead>
			<tr class="row ml-3">
				<th class="col-2"><b> <fmt:message key="page.user.table.name"/> </b></th>
				<th class="col-3"><b> <fmt:message key="page.user.table.bill_number"/> </b></th>
				<th class="col-3"><b> <fmt:message key="page.user.table.balance"/> </b></th>
				<th class="col-2"> <fmt:message key="page.user.table.status"/> </th>
				<th class="col-2"> <fmt:message key="page.user.table.details"/> </th>
			</tr>
			</thead>
			<tbody>
			<tr class="row ml-3" >
				<c:forEach items="${requestScope.wallets}" var="wallet" >
					<td class="col-2"> <c:out value="${wallet.name}"></c:out> </td>
					<td class="col-3"> <c:out value="${wallet.bill_number}"></c:out> </td>
					<td class="col-3"> <c:out value="${wallet.balance}"></c:out> </td>
					<td class="col-2"> <c:out value="${wallet.getState()}"></c:out> </td>
					<td class="pupa href-container col-2">
						<form style="padding: 2%" action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
							<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_WALLET_DETAILS_PAGE}">
							<input type="hidden" name="${ParamNames.BILL_NUMBER}" value="${wallet.bill_number}">

							<a class="pr-2" href='#' onclick='this.parentNode.submit();'>
								<fmt:message key="page.user.table.details"/></a>
						</form>
					</td>
				</c:forEach>
			</tr>
			</tbody>
		</table>
	</main>
</div>
</body>
</html>