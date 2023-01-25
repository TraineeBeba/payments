<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" uri="/WEB-INF/tld/locale.tld" %>
<%@ taglib prefix="n" uri="/WEB-INF/tld/name.tld" %>
<%@ taglib prefix="wallets" uri="/WEB-INF/tld/wallets.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<html class="h-100">

<head>
	<c:import url="blocks/head.jsp" />
</head>

<body class="h-100 text-white bg-dark">
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">

	<header class="mb-15">
		<c:import url="blocks/header.jsp" />
	</header>

	<main class="px-3">
		<c:import url="blocks/warnings.jsp" />

		<div class="сinemas p-3">
			<h3> <my:Locale value="page.main.title"/> </h3>
			<a href="/<n:Name value="controller.name"/><n:Name value="redirect.create-wallet"/>"> <my:Locale value="page.main.add_new"/> </a><br>


		</div>

		<div class="left-block col-4">

			<form style="padding: 2%">
				<a class="pr-2"><my:Locale value="page.main.sort"/></a>
			</form>

			<form style="padding: 2%" action="<n:Name value="controller.name"/>" method="post">
				<input type="hidden" name="redirect" value="<n:Name value="redirect.main"/>">
				<input type="hidden" name="command" value="<n:Name value="command.sort-wallets"/>">
				<input type="hidden" name="walletSort" value="wallet.bill_number ASC">

				<a class="pr-2" href='#' onclick='this.parentNode.submit(); return false;'> <my:Locale value="page.main.by_number"/> </a>
			</form>

			<form style="padding: 2%" action="<n:Name value="controller.name"/>" method="post">
				<input type="hidden" name="redirect" value="<n:Name value="redirect.main"/>">
				<input type="hidden" name="command" value="<n:Name value="command.sort-wallets"/>">
				<input type="hidden" name="walletSort" value="wallet.name ASC">

				<a class="pr-2" href='#' onclick='this.parentNode.submit(); '> <my:Locale value="page.main.by_name"/> </a>
			</form>

			<form style="padding: 2%" action="<n:Name value="controller.name"/>" method="post">
				<input type="hidden" name="redirect" value="<n:Name value="redirect.main"/>">
				<input type="hidden" name="command" value="<n:Name value="command.sort-wallets"/>">
				<input type="hidden" name="walletSort" value="wallet.balance ASC">

				<a class="pr-2" href='#' onclick='this.parentNode.submit(); return false;'> <my:Locale value="page.main.by_balance"/> </a>
			</form>
		</div>
		<br>

		<wallets:Wallets/>

		<table class="table table-dark table-bordered table-hover text-center">
			<thead>
			<tr class="row ml-3">
				<th class="col-2"><b> <my:Locale value="page.main.table.name"/> </b></th>
				<th class="col-3"><b> <my:Locale value="page.main.table.bill_number"/> </b></th>
				<th class="col-3"><b> <my:Locale value="page.main.table.balance"/> </b></th>
				<th class="col-2"> <my:Locale value="page.main.table.status"/> </th>
				<th class="col-2"> <my:Locale value="page.main.table.details"/> </th>
			</tr>
			</thead>
			<tbody>
			<tr class="row ml-3" >
				<c:forEach items="${sessionScope.wallets}" var="wallet" >
					<td class="col-2"> <c:out value="${wallet.name}"></c:out> </td>
					<td class="col-3"> <c:out value="${wallet.bill_number}"></c:out> </td>
					<td class="col-3"> <c:out value="${wallet.balance}"></c:out> </td>
					<td class="col-2"> <c:out value="${wallet.getState()}"></c:out> </td>
					<td class="pupa href-container col-2">
						<form style="padding: 2%" action="<n:Name value="controller.name"/>" method="post">
							<input type="hidden" name="redirect" value="<n:Name value="redirect.wallet-details"/>">
							<input type="hidden" name="command" value="<n:Name value="command.sort-transfers"/>">
							<input type="hidden" name="currWalletBill" value="${wallet.bill_number}">

							<a class="pr-2" href='#' onclick='this.parentNode.submit(); return false;'>Детальніше</a>
						</form>
					</td>
				</c:forEach>
			</tr>
			</tbody>
		</table>

		<div class="categories p-3">
			<h3> Привіт ${sessionScope.username} </h3>
			<h3> Cортування ${sessionScope.walletSort} </h3>
		</div>
	</main>
</div>
</body>
</html>