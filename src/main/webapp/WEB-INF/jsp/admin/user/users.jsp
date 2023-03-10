<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="i18n" uri="/WEB-INF/tld/locale.tld" %>
<%@ taglib prefix="n" uri="/WEB-INF/tld/namespace.tld" %>
<%@ taglib prefix="load-users" uri="/WEB-INF/tld/load-users.tld" %>

<html class="h-100">

<head>
	<%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>

<body class="h-100 text-white bg-dark">
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">

	<header class="mb-15">
		<%@ include file="/WEB-INF/jspf/header.jspf" %>
	</header>

	<main class="px-3">
		<%@ include file="/WEB-INF/jspf/alerts.jspf" %>

		<div class="сinemas p-3">
<%--			<h3> <my:Locale value="page.user.title"/> </h3>--%>
			<h3> Корситувачі </h3>
<%--			<a href="/<n:Name value="controller.name"/><n:Name value="redirect.user-wallets-create-wallet"/>"> <my:Locale value="page.user.add_new"/> </a><br>--%>
		</div>

		<div class="left-block col-4">
			<form action="<n:Namespace value="controller.name"/>" method="get">
				<input type="hidden" name="command" value="<n:Namespace value="command.go.admin-users"/>">
				<input type="hidden" name="userSort" value="user.id DESC">
				<input type="hidden" name="page" value="${requestScope.page}">

				<a class="pr-2" href='#' onclick='this.parentNode.submit(); '>За Id</a>
			</form>

			<form action="<n:Namespace value="controller.name"/>" method="get">
				<input type="hidden" name="command" value="<n:Namespace value="command.go.admin-users"/>">
				<input type="hidden" name="userSort" value="user.username DESC">
				<input type="hidden" name="page" value="${requestScope.page}">

				<a class="pr-2" href='#' onclick='this.parentNode.submit(); '>За юзернеймом</a>
			</form>

			<form action="<n:Namespace value="controller.name"/>" method="get">
				<input type="hidden" name="command" value="<n:Namespace value="command.go.admin-users"/>">
				<input type="hidden" name="userSort" value="user.state_id DESC">
				<input type="hidden" name="page" value="${requestScope.page}">


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

					<td class="col-1"> <c:out value="${user.id}"></c:out> </td>
					<td class="col-3"> <c:out value="${user.username}"></c:out> </td>
					<td class="col-2"> <c:out value="${user.getState()}"></c:out> </td>
					<td class="pupa href-container col-3">
						<c:choose>
							<c:when test="${user.getState() eq 'blocked'}">
								<form action="<n:Namespace value="controller.name"/>" method="post">
									<input type="hidden" name="command" value="<n:Namespace value="command.unblock-user"/>">
									<input type="hidden" name="currUserId" value="${user.id}">

									<a class="pr-2" href='#' onclick='this.parentNode.submit(); return false;'>Розблокувати</a>
								</form>
							</c:when>
							<c:otherwise>
								<form action="<n:Namespace value="controller.name"/>" method="post">
									<input type="hidden" name="command" value="<n:Namespace value="command.block-user"/>">
									<input type="hidden" name="currUserId" value="${user.id}">
									<input type="hidden" name="currUserId" value="${requestScope[com.epam.payments.command.constant.ParameterNames.USERNAME]}">

									<a class="pr-2" href='#' onclick='this.parentNode.submit(); return false;'>Заблокувати</a>
								</form>
							</c:otherwise>
						</c:choose>
					</td>
					<td class="pupa href-container col-3">
						<form style="padding: 2%" action="<n:Namespace value="controller.name"/>" method="get">
							<input type="hidden" name="command" value="<n:Namespace value="command.go.user-wallets"/>">
							<input type="hidden" name="selectedUserName" value="${user.getUsername()}">

							</a><a class="pr-2" href='#' onclick='this.parentNode.submit();'>Детальніше</a>
						</form>
					</td>
				</c:forEach>
			</tr>
			</tbody>
		</table>

		<table border="1" cellpadding="5" cellspacing="5">
			<tr>
				<c:if test="${requestScope.page != 1}">
					<td>
						<form action="<n:Namespace value="controller.name"/>" method="get">
							<input type="hidden" name="command" value="<n:Namespace value="command.go.admin-users"/>">
							<input type="hidden" name="page" value="${requestScope.page - 1}">

							<a class="pr-2" href='#' onclick='this.parentNode.submit();'>Previous</a>
						</form>
					</td>
				</c:if>

				<c:forEach begin="1" end="${requestScope.noOfTransferPages}" var="i">
					<c:choose>
						<c:when test="${requestScope.page eq i}">
							<td>${i}</td>
						</c:when>
						<c:otherwise>
							<td>
								<form action="<n:Namespace value="controller.name"/>" method="get">
									<input type="hidden" name="command" value="<n:Namespace value="command.go.admin-users"/>">
									<input type="hidden" name="page" value="${i}">

									<a class="pr-2" href='#' onclick='this.parentNode.submit();'>${i}</a>
								</form>
							</td>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${requestScope.page lt requestScope.noOfTransferPages}">
					<td>
						<form action="<n:Namespace value="controller.name"/>" method="get">
							<input type="hidden" name="command" value="<n:Namespace value="command.go.admin-users"/>">
							<input type="hidden" name="page" value="${requestScope.page + 1}">

							<a class="pr-2" href='#' onclick='this.parentNode.submit();'>Next</a>
						</form>
					</td>
				</c:if>
			</tr>
		</table>
	</main>
</div>
</body>
</html>