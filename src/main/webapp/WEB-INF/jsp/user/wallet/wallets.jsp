<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

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
			<h3> <i18n:Locale value="page.user.title"/>  ${sessionScope.selectedUser.getUsername()}</h3>

			<c:if test="${not empty sessionScope.selectedUser}">
				<a class="nav-link active" aria-current="page" href="/<n:Namespace value="controller.name"/>?command=<n:Namespace value="command.go.admin-users"/>"> Назад </a>
			</c:if>

			<c:choose>
				<c:when test="${sessionScope.userDTO.getRole() eq 'role_user'}">
					<form action="/<n:Namespace value="controller.name"/>" method="get">
						<input type="hidden" name="command" value="<n:Namespace value="command.go.create-wallet"/>">

						<a class="nav-link" href='#' onclick='this.parentNode.submit(); '> <i18n:Locale value="page.user.add_new"/> </a>
					</form>
				</c:when>
			</c:choose>

		</div>

		<div class="left-block col-4">

			<form style="padding: 2%">
				<a class="pr-2"><i18n:Locale value="page.user.sort"/></a>
			</form>

			<form style="padding: 2%" action="<n:Namespace value="controller.name"/>" method="get">
				<input type="hidden" name="command" value="<n:Namespace value="command.go.user-wallets"/>">
				<input type="hidden" name="walletSort" value="wallet.bill_number ASC">

				<a class="pr-2" href='#' onclick='this.parentNode.submit(); return false;'> <i18n:Locale value="page.user.by_number"/> </a>
			</form>

			<form style="padding: 2%" action="<n:Namespace value="controller.name"/>" method="get">
				<input type="hidden" name="command" value="<n:Namespace value="command.go.user-wallets"/>">
				<input type="hidden" name="walletSort" value="wallet.name ASC">

				<a class="pr-2" href='#' onclick='this.parentNode.submit(); '> <i18n:Locale value="page.user.by_name"/> </a>
			</form>

			<form style="padding: 2%" action="<n:Namespace value="controller.name"/>" method="get">
				<input type="hidden" name="command" value="<n:Namespace value="command.go.user-wallets"/>">
				<input type="hidden" name="walletSort" value="wallet.balance ASC">

				<a class="pr-2" href='#' onclick='this.parentNode.submit(); return false;'> <i18n:Locale value="page.user.by_balance"/> </a>
			</form>
		</div>
		<br>

		<table class="table table-dark table-bordered table-hover text-center">
			<thead>
			<tr class="row ml-3">
				<th class="col-2"><b> <i18n:Locale value="page.user.table.name"/> </b></th>
				<th class="col-3"><b> <i18n:Locale value="page.user.table.bill_number"/> </b></th>
				<th class="col-3"><b> <i18n:Locale value="page.user.table.balance"/> </b></th>
				<th class="col-2"> <i18n:Locale value="page.user.table.status"/> </th>
				<th class="col-2"> <i18n:Locale value="page.user.table.details"/> </th>
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
						<form style="padding: 2%" action="<n:Namespace value="controller.name"/>" method="get">
							<input type="hidden" name="command" value="<n:Namespace value="command.go.wallet-details"/>">
							<input type="hidden" name="bill_number" value="${wallet.bill_number}">

							<a class="pr-2" href='#' onclick='this.parentNode.submit();'>
								<i18n:Locale value="page.user.table.details"/></a>
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