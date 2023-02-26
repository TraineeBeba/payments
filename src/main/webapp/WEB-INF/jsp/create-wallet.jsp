<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="с" uri="/WEB-INF/tld/locale.tld" %>
<%@ taglib prefix="i18n" uri="/WEB-INF/tld/locale.tld" %>
<%@ taglib prefix="n" uri="/WEB-INF/tld/namespace.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
		<div class="form-container">

			<%@ include file="/WEB-INF/jspf/alerts.jspf" %>

			<div class="categories p-3">
				<h3>Створити новий гаманець</h3>
			</div>

			<form style="padding: 2%" action="/<n:Namespace value="controller.name"/>" method="post">
				<input type="hidden" name="command" value="<n:Namespace value="command.create-wallet"/>">
				<input type="text" 	 name="name" placeholder="Введіть назву гаманця" class="form-control" required><br>
				<button type="submit" class="btn btn-primary">Додати гаманець</button>
			</form>

			<a class="nav-link active" aria-current="page" href="/<n:Namespace value="controller.name"/>?command=<n:Namespace value="command.go.user-wallets"/>"> Я передумав </a>
<%--			<a href="/<n:Namespace value="controller.name"/><n:Namespace value="redirect.user-wallets"/>"> Я передумав </a>--%>

		</div>

	</main>
</div>
</body>
</html>