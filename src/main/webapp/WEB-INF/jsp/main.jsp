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
			<h3>Гаманці</h3>
			<a href="/<n:Name value="controller.name"/><n:Name value="redirect.create-wallet"/>"> Додати новий </a><br>


		</div>

		<div class="left-block col-4">

			<form style="padding: 2%">
				<a class="pr-2">Сортувати:</a>
			</form>

			<form style="padding: 2%" action="<n:Name value="controller.name"/>" method="post">
				<input type="hidden" name="redirect" value="<n:Name value="redirect.main"/>">
				<input type="hidden" name="command" value="<n:Name value="command.sort-wallets"/>">
				<input type="hidden" name="walletSort" value="wallet.bill_number ASC">

				<a class="pr-2" href='#' onclick='this.parentNode.submit(); return false;'>За номером</a>
			</form>

			<form style="padding: 2%" action="<n:Name value="controller.name"/>" method="post">
				<input type="hidden" name="redirect" value="<n:Name value="redirect.main"/>">
				<input type="hidden" name="command" value="<n:Name value="command.sort-wallets"/>">
				<input type="hidden" name="walletSort" value="wallet.name ASC">

				<a class="pr-2" href='#' onclick='this.parentNode.submit(); '>За назвою</a>
			</form>

			<form style="padding: 2%" action="<n:Name value="controller.name"/>" method="post">
				<input type="hidden" name="redirect" value="<n:Name value="redirect.main"/>">
				<input type="hidden" name="command" value="<n:Name value="command.sort-wallets"/>">
				<input type="hidden" name="walletSort" value="wallet.balance ASC">

				<a class="pr-2" href='#' onclick='this.parentNode.submit(); return false;'>За балансом</a>
			</form>
		</div>
		<br>

		<wallets:Wallets/>

		<div class="categories p-3">
			<h3> Привіт ${sessionScope.username} </h3>
			<h3> Cортування ${sessionScope.walletSort} </h3>
		</div>
	</main>
</div>
</body>
</html>