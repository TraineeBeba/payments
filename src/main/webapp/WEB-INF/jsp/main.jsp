<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" uri="/WEB-INF/locale.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html class="h-100">

<head>
	<c:import url="blocks/head.jsp" />
</head>

<header class="mb-15">
	<div>
		<nav class="nav nav-masthead justify-content-right float-md-end row">
			<div class="right-block col-1">
				<form action="controller" method="post">
					<input type="hidden" name="redirect" value="?command=goToLoginCommand">
					<input type="hidden" name="command" value="languageCommand">
					<input type="hidden" name="language" value="en">
					<input type="hidden" name=url value="${requestScope['javax.servlet.forward.query_string']}">

					<input type="image" src="/img/us.png" alt="Submit">

				</form>
				<form action="controller" method="post">
					<input type="hidden" name="redirect" value="?command=goToLoginCommand">
					<input type="hidden" name="command" value="languageCommand">
					<input type="hidden" name="language" value="uk">
					<input type="hidden" name=url value="${requestScope['javax.servlet.forward.query_string']}">

					<input type="image" src="/img/ua.png" alt="Submit">
				</form>
			</div>
		</nav>
	</div>
</header>

<body class="h-100 text-white bg-dark">
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
	<main class="px-3">
		<div class="form-container">

			<div id = "flags">

			</div>

			<div class="categories p-3">
				<h3> Привіт ${sessionScope.username} </h3>
			</div>

		</div>
	</main>
</div>
</body>
</html>