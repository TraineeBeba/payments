<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" uri="/WEB-INF/tld/locale.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html class="h-100">

<head>
	<c:import url="blocks/head.jsp" />
</head>

<body class="h-100 text-white bg-dark">
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">

	<header class="mb-15">
		<div>
			<nav class="nav nav-masthead justify-content-right float-md-end row">
				<div class="left-block col-6">
					<a class="nav-link active" aria-current="page" href="/controller?command=goMainCommand">Гаманці</a>
					<%--				<a class="nav-link active" aria-current="page" href="/cinema">Поповнення ігор</a>--%>
					<%--				<a class="nav-link active" aria-current="page" href="/cinema">Перекази</a>--%>

				</div>

				<div class="right-block col-6">
					<form action="controller" method="post">
						<input type="hidden" name="redirect" value="?command=goMainCommand">
						<input type="hidden" name="command" value="languageCommand">
						<input type="hidden" name="language" value="en">
						<input type="image" src="/img/us.png" alt="Submit">

					</form>
					<form action="controller" method="post">
						<input type="hidden" name="redirect" value="?command=goMainCommand">
						<input type="hidden" name="command" value="languageCommand">
						<input type="hidden" name="language" value="uk">
						<input type="image" src="/img/ua.png" alt="Submit">
					</form>

					<form style="padding: 2%" action="controller" method="post">
						<input type="hidden" name="redirect" value="?command=goLoginCommand">
						<input type="hidden" name="command" value="logoutCommand">
						<a class="nav-link" href='#' onclick='this.parentNode.submit(); return false;'>Вийти</a>
					</form>
				</div>

			</nav>
		</div>
	</header>

	<main class="px-3">
		<div class="сinemas p-3">
			<h3>Гаманці</h3>
			<a href="/controller?command=goCreate-WalletCommand"> Додати новий </a>
		</div>

		<table class="table table-dark table-bordered table-hover text-center">
			<thead>
			<tr class="row ml-3">
				<th class="col-2"><b>Назва</b></th>
				<th class="col-3"><b>Номер рахунку</b></th>
				<th class="col-3"><b>Баланс</b></th>
				<th class="col-2">Статус</th>
				<th class="col-2">Детальніше</th>
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
						<a class="nav-link" href="/controller?command=goDetails-WalletCommand">Детальніше</a>
					</td>
				</c:forEach>
			</tr>
			</tbody>
		</table>

		<div class="categories p-3">
			<h3> Привіт ${sessionScope.username} </h3>
		</div>

	</main>
</div>
</body>
</html>