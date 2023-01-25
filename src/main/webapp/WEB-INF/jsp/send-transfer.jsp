<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" uri="/WEB-INF/tld/locale.tld" %>
<%@ taglib prefix="с" uri="/WEB-INF/tld/locale.tld" %>
<%@ taglib prefix="n" uri="/WEB-INF/tld/name.tld" %>
<%@ taglib prefix="select" uri="/WEB-INF/tld/select-wallet.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
		<div class="form-container">

			<c:import url="blocks/warnings.jsp" />

			<div class="categories p-3">
				<h3>Підтвердити здійснення переказу</h3>
			</div>

			<div class="categories p-3">
				<h3> Твій гаманець: ${sessionScope.transferDTO.sender_bill_number} </h3>
			</div>

			<div class="categories p-3">
				<h3> Його гаманець: ${sessionScope.transferDTO.recipient_bill_number} </h3>
			</div>

			<div class="categories p-3">
				<h3> Сума переказу: ${sessionScope.transferDTO.sum} грн</h3>
			</div>

			<form style="padding: 2%" action="/<n:Name value="controller.name"/>" method="post">
				<input type="hidden" name="redirect" value="<n:Name value="redirect.main"/>">
				<input type="hidden" name="command" value="<n:Name value="command.send-transfer"/>">

				<button type="submit" class="btn btn-primary">От прям точно треба відправити!</button>
			</form>

			<form style="padding: 2%" action="/<n:Name value="controller.name"/>" method="post">
				<input type="hidden" name="redirect" value="<n:Name value="redirect.main"/>">
				<input type="hidden" name="command" value="<n:Name value="command.cancel-transfer"/>">

				<a class="nav-link" href='#' onclick='this.parentNode.submit(); '>Я передумав</a>
			</form>

			<div class="categories p-3">
				<h3> Привіт ${sessionScope.username} </h3>
			</div>


		</div>
	</main>
</div>
</body>
</html>