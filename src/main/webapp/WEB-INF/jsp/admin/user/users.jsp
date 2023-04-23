<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.payments.command.constant.WebUrlConstants"%>
<%@ page import="com.epam.payments.command.constant.ParamNames"%>
<%@ page errorPage="/WEB-INF/jsp/error/error.jsp" %>

<fmt:setLocale value="${sessionScope[ParamNames.LANGUAGE]}"/>
<fmt:setBundle basename="locale.admin.user.users"/>

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

        <c:set var="page" value="${requestScope[ParamNames.PAGE]}"/>
        <c:set var="noOfTransferPages" value="${requestScope[ParamNames.NO_OF_TRANSFER_PAGES]}"/>

		<div class="сinemas p-3">
			<h3> <fmt:message key="title"/> </h3>
		</div>

		<div class="left-block col-4">
			<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
				<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_ADMIN_USERS_PAGE}">
				<input type="hidden" name="${ParamNames.USER_SORT}" value="user.username DESC">
				<input type="hidden" name="${ParamNames.PAGE}" value="${page}">

				<a class="pr-2" href='#' onclick='this.parentNode.submit(); '><fmt:message key="sort.username"/></a>
			</form>

			<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
				<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_ADMIN_USERS_PAGE}">
				<input type="hidden" name="${ParamNames.USER_SORT}" value="user.state_id DESC">
				<input type="hidden" name="${ParamNames.PAGE}" value="${page}">


				<a class="pr-2" href='#' onclick='this.parentNode.submit();'><fmt:message key="sort.status"/></a>
			</form>

		</div>
		<br>

		<table class="table table-dark table-bordered table-hover text-center">
			<thead>
			<tr class="row ml-3">
                <th class="col-3"><b> <fmt:message key="username"/> </b></th>
				<th class="col-3"><b> <fmt:message key="status"/> </b></th>
				<th class="col-3"><b> <fmt:message key="change-status"/> </b></th>
				<th class="col-3"><b> <fmt:message key="details"/> </b></th>
			</tr>
			</thead>
			<tbody>
			<tr class="row ml-3" >
				<c:forEach items="${requestScope.users}" var="user" >

                    <td class="col-3"> <c:out value="${user.getUsername()}"/> </td>
					<td class="col-3"> <c:out value="${user.getState()}"/> </td>
					<td class="pupa href-container col-3">
						<c:choose>
							<c:when test="${user.getState().getName() eq 'blocked'}">
								<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="post">
									<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.UNBLOCK_USER}">
									<input type="hidden" name="${ParamNames.SELECTED_USER_NAME}" value="${user.getUsername()}">

									<a class="pr-2" href='#' onclick='this.parentNode.submit(); return false;'><fmt:message key="unblock"/></a>
								</form>
							</c:when>
							<c:otherwise>
								<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="post">
									<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.BLOCK_USER}">
									<input type="hidden" name="${ParamNames.SELECTED_USER_NAME}" value="${user.getUsername()}">

									<a class="pr-2" href='#' onclick='this.parentNode.submit(); return false;'><fmt:message key="block"/></a>
								</form>
							</c:otherwise>
						</c:choose>
					</td>
					<td class="pupa href-container col-3">
						<form style="padding: 2%" action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
							<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_ADMIN_USER_WALLETS_PAGE}">
							<input type="hidden" name="${ParamNames.SELECTED_USER_NAME}" value="${user.getUsername()}">

							</a><a class="pr-2" href='#' onclick='this.parentNode.submit();'><fmt:message key="details"/></a>
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