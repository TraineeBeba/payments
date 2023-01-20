
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" uri="/WEB-INF/tld/locale.tld" %>
<%@ taglib prefix="n" uri="/WEB-INF/tld/name.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html class="h-100">

<head>
	<c:import url="blocks/head.jsp" />
</head>

<header class="mb-15">
	<c:import url="blocks/header.jsp" />
</header>

<body class="h-100 text-white bg-dark">
	<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
		<main class="px-3">
			<div class="form-container">
				<c:import url="blocks/warnings.jsp" />

				<div class="categories p-3">
					<h3> <my:Locale value="page.register.title"/> </h3>
				</div>

				<form style="padding: 2%" action="<n:Name value="controller.name"/>" method="post">
					<input type="hidden" name="redirect" value="<n:Name value="redirect.login"/>">
					<input type="hidden" name="command" value="<n:Name value="command.register"/>">
					<input type="text" name="username" class="form-control" placeholder="<my:Locale value="page.register.enter"/> <my:Locale value="page.register.username"/>" required><br>
					<input type="text" name="password" class="form-control" placeholder="<my:Locale value="page.register.enter"/> <my:Locale value="page.register.password"/>" required><br>
					<button type="submit" class="btn btn-primary"> <my:Locale value="page.register.register"/> </button>
				</form>

				<a class="nav-link active"  href="/<n:Name value="controller.name"/><n:Name value="redirect.login"/>"> <my:Locale value="page.register.go_login"/></a>

			</div>
		</main>
	</div>
</body>
</html>