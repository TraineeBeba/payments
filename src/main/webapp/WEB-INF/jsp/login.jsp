<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="i18n" uri="/WEB-INF/tld/locale.tld" %>
<%@ taglib prefix="n" uri="/WEB-INF/tld/namespace.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html class="h-100">

<head>
	<%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>

<header class="mb-15">
	<%@ include file="/WEB-INF/jspf/header.jspf" %>
</header>

<body class="h-100 text-white bg-dark">
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
	<main class="px-3">
		<div class="form-container">
			<%@ include file="/WEB-INF/jspf/alerts.jspf" %>

			<div class="categories p-3">
				<h3> <i18n:Locale value="page.login.title"/> </h3>
			</div>

			<form style="padding: 2%" action="<n:Namespace value="controller.name"/>" method="post">
				<input type="hidden" name="command" value="<n:Namespace value="command.login"/>">
				<input type="text" name="username" class="form-control" placeholder="<i18n:Locale value="page.login.enter"/> <i18n:Locale value="page.login.username"/>" required><br>
				<input type="password" name="password" class="form-control" placeholder="<i18n:Locale value="page.login.enter"/> <i18n:Locale value="page.login.password"/>" required><br>

				<button type="submit" class="btn btn-primary"> <i18n:Locale value="page.login.login"/> </button>
			</form>

			<form action="/<n:Namespace value="controller.name"/>" method="get">
				<input type="hidden" name="command" value="<n:Namespace value="command.go.register"/>">

				<a class="nav-link" href='#' onclick='this.parentNode.submit(); '> <i18n:Locale value="page.login.go_register"/> </a>
			</form>

		</div>
	</main>
</div>
</body>
</html>