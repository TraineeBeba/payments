<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.payments.command.constant.WebUrlConstants"%>
<%@ page import="com.epam.payments.command.constant.ParamNames"%>
<%@ page errorPage="/WEB-INF/jsp/error/error.jsp" %>

<fmt:setLocale value="${sessionScope[ParamNames.LANGUAGE]}"/>
<fmt:setBundle basename="locale.admin.user.transfer-prepare"/>

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
<%--			<h3> <my:Locale value="page.user.title"/> </h3>--%>
			<h3> Корситувачі </h3>
			<a href="${WebUrlConstants.GO_ADMIN_USERS_PAGE_URL}"> <my:Locale value="page.user.add_new"/> </a><br>
		</div>

		<div class="left-block col-4">
			<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
				<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_ADMIN_USERS_PAGE}">
				<input type="hidden" name="${ParamNames.USER_SORT}" value="user.id DESC">
				<input type="hidden" name="${ParamNames.PAGE}" value="${requestScope.page}">

				<a class="pr-2" href='#' onclick='this.parentNode.submit(); '>За Id</a>
			</form>

			<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
				<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_ADMIN_USERS_PAGE}">
				<input type="hidden" name="${ParamNames.USER_SORT}" value="user.username DESC">
				<input type="hidden" name="${ParamNames.PAGE}" value="${requestScope.page}">

				<a class="pr-2" href='#' onclick='this.parentNode.submit(); '>За юзернеймом</a>
			</form>

			<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
				<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_ADMIN_USERS_PAGE}">
				<input type="hidden" name="${ParamNames.USER_SORT}" value="user.state_id DESC">
				<input type="hidden" name="${ParamNames.PAGE}" value="${requestScope.page}">


				<a class="pr-2" href='#' onclick='this.parentNode.submit();'>За статусом</a>
			</form>

		</div>
		<br>

		<table class="table table-dark table-bordered table-hover text-center">
			<thead>
			<tr class="row ml-3">
<%--				<th class="col-2"><b> <my:Locale value="page.user.table.name"/> </b></th>--%>
                <th class="col-1"><b> ID користувача </b></th>
                <th class="col-3"><b> Ім'я користувача </b></th>
				<th class="col-2"><b> Статус </b></th>
				<th class="col-3"><b> Змінити статус </b></th>
				<th class="col-3"><b> Детальніше </b></th>

			</tr>
			</thead>
			<tbody>
			<tr class="row ml-3" >
				<c:forEach items="${requestScope.users}" var="user" >

<%--					<td class="col-1"> <c:out value="${user.id}"></c:out> </td>--%>
					<td class="col-3"> <c:out value="${user.username}"></c:out> </td>
					<td class="col-2"> <c:out value="${user.getState()}"></c:out> </td>
					<td class="pupa href-container col-3">
						<c:choose>
							<c:when test="${user.getState() eq 'blocked'}">
								<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="post">
									<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.UNBLOCK_USER}">
									<input type="hidden" name="${ParamNames.CURR_USER_ID}" value="${user.id}">

									<a class="pr-2" href='#' onclick='this.parentNode.submit(); return false;'>Розблокувати</a>
								</form>
							</c:when>
							<c:otherwise>
								<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="post">
									<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.BLOCK_USER}">
									<input type="hidden" name="${ParamNames.CURR_USER_ID}" value="${user.id}">
<%--									<input type="hidden" name="currUserId" value="${requestScope[com.epam.payments.command.constant.ParameterNames.USERNAME]}">--%>

									<a class="pr-2" href='#' onclick='this.parentNode.submit(); return false;'>Заблокувати</a>
								</form>
							</c:otherwise>
						</c:choose>
					</td>
					<td class="pupa href-container col-3">
						<form style="padding: 2%" action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
							<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_USER_WALLETS_PAGE}">
							<input type="hidden" name="${ParamNames.SELECTED_USER_NAME}" value="${user.getUsername()}">

							</a><a class="pr-2" href='#' onclick='this.parentNode.submit();'>Детальніше</a>
						</form>
					</td>
				</c:forEach>
			</tr>
			</tbody>
		</table>

		<table border="1" cellpadding="5" cellspacing="5">
			<tr>
				<c:if test="${page != 1}">
					<td>
						<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
							<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_ADMIN_USERS_PAGE}">
							<input type="hidden" name="${ParamNames.PAGE}" value="${page - 1}">

							<a class="pr-2" href='#' onclick='this.parentNode.submit();'><fmt:message key="prev"/></a>
						</form>
					</td>
				</c:if>

				<c:forEach begin="1" end="${noOfTransferPages}" var="i">
					<c:choose>
						<c:when test="${page eq i}">
							<td>${i}</td>
						</c:when>
						<c:otherwise>
							<td>
								<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
									<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_ADMIN_USERS_PAGE}">
									<input type="hidden" name="${ParamNames.PAGE}" value="${i}">

									<a class="pr-2" href='#' onclick='this.parentNode.submit();'>${i}</a>
								</form>
							</td>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${page lt noOfTransferPages}">
					<td>
						<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
							<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_ADMIN_USERS_PAGE}">
							<input type="hidden" name="${ParamNames.PAGE}" value="${page + 1}">

							<a class="pr-2" href='#' onclick='this.parentNode.submit();'><fmt:message key="next"/></a>
						</form>
					</td>
				</c:if>
			</tr>
		</table>
	</main>
</div>
</body>
</html>