<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" uri="/WEB-INF/tld/locale.tld" %>
<%@ taglib prefix="com" uri="/WEB-INF/tld/command.tld" %>
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
					<input type="hidden" name="redirect" value="<com:Command value="redirect.login"/>">
					<input type="hidden" name="command" value="<com:Command value="command.language"/>">
					<input type="hidden" name="language" value="en">
					<input type="image" src="/img/us.png" alt="Submit">

				</form>
				<form action="controller" method="post">
					<input type="hidden" name="redirect" value="<com:Command value="redirect.login"/>">
					<input type="hidden" name="command" value="<com:Command value="command.language"/>">
					<input type="hidden" name="language" value="uk">
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

			<c:if test="${sessionScope.register eq true}">
				<div class="alertWarning">
					<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
					<strong>Попередження: </strong> <text> <my:Locale value="page.login.warning"/> </text>
					<text> <br>(づ ◕‿◕ )づ</text>
				</div>
				<c:remove var="register" scope="session" />
			</c:if>

			<c:if test="${not empty sessionScope.wrongData}">
				<div class="alertError">
					<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
					<strong>Помилка: </strong> <text> <my:Locale value="${sessionScope.wrongData}"/> </text>
<%--					<strong>Помилка: </strong> <text> ХРІНЬКА </text>--%>
					<text> <br>(╯ ° □ °) ╯ (┻━┻)</text>
				</div>
				<c:remove var="wrongData" scope="session" />
			</c:if>

			<div class="categories p-3">
				<h3> <my:Locale value="page.login.title"/> </h3>
			</div>

			<form style="padding: 2%" action="controller" method="post">
				<input type="hidden" name="redirect" value=?command=goMainCommand>
				<input type="hidden" name="command" value="loginCommand">
				<input type="text" name="username" class="form-control" placeholder="<my:Locale value="page.login.enter"/> <my:Locale value="page.login.username"/>" required><br>
				<input type="text" name="password" class="form-control" placeholder="<my:Locale value="page.login.enter"/> <my:Locale value="page.login.password"/>" required><br>
				<button type="submit" class="btn btn-primary"> <my:Locale value="page.login.login"/> </button>
			</form>

			<a class="nav-link active"  href="/controller?command=goRegisterCommand"> <my:Locale value="page.login.go_register"/></a>

		</div>
	</main>
</div>
</body>
</html>