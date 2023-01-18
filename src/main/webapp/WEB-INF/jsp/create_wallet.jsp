<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" uri="/WEB-INF/locale.tld" %>
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
					<a class="nav-link active" aria-current="page" href="/controller?command=goToMainCommand">Гаманці</a>
					<%--				<a class="nav-link active" aria-current="page" href="/cinema">Поповнення ігор</a>--%>
					<%--				<a class="nav-link active" aria-current="page" href="/cinema">Перекази</a>--%>

				</div>

				<div class="right-block col-6">
					<form action="controller" method="post">
						<input type="hidden" name="redirect" value="?command=goToWalletCreateCommand">
						<input type="hidden" name="command" value="languageCommand">
						<input type="hidden" name="language" value="en">
						<input type="image" src="/img/us.png" alt="Submit">

					</form>
					<form action="controller" method="post">
						<input type="hidden" name="redirect" value="?command=goToWalletCreateCommand">
						<input type="hidden" name="command" value="languageCommand">
						<input type="hidden" name="language" value="uk">
						<input type="image" src="/img/ua.png" alt="Submit">
					</form>

					<form style="padding: 2%" action="controller" method="post">
						<input type="hidden" name="redirect" value="?command=goToLoginCommand">
						<input type="hidden" name="command" value="logoutCommand">
						<a class="nav-link" href='#' onclick='this.parentNode.submit(); return false;'>Вийти</a>
					</form>
				</div>

			</nav>
		</div>
	</header>

	<main class="px-3">
		<div class="form-container">

<%--			<div th:if="${wrongData}" class="alertError">--%>
<%--				<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>--%>
<%--				<strong>Помилка: </strong><text th:text="${wrongData}"> </text>--%>
<%--				<text> <br>(╯ ° □ °) ╯ (┻━┻)</text>--%>
<%--			</div>--%>

			<div class="categories p-3">
				<h3>Створити новий гаманець</h3>
			</div>

			<form style="padding: 2%" action="/controller" method="post">
				<input type="hidden" name="redirect" value="?command=goToWalletCreateCommand">
				<input type="hidden" name="command" value="createWalletCommand">
				<input type="text" 	 name="name" placeholder="Введіть назву гаманця" class="form-control" required><br>
				<button type="submit" class="btn btn-primary">Додати гаманець</button>
			</form>

			<a href="/controller?command=goToMainCommand"> Я передумав </a>

			<div class="categories p-3">
				<h3> Привіт ${sessionScope.username} </h3>
			</div>

		</div>

	</main>
</div>
</body>
</html>