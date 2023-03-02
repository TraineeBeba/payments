<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.epam.payments.command.constant.WebPathConstants"%>
<%@ page import="com.epam.payments.command.constant.WebUrlConstants"%>
<%@ page import="com.epam.payments.command.constant.ParamNames"%>
<%@ page import="com.epam.payments.i18n.RegisterPage"%>

<html class="h-100">

<head>
	<jsp:include page="${WebPathConstants.HEAD_PATH}"/>
</head>

<header class="mb-15">
<%--	<jsp:include page="${WebPathConstants.HEADER_PATH}"/>--%>
	<%@ include file="/WEB-INF/jsp/jspf/header.jspf" %>
</header>

<body class="h-100 text-white bg-dark">
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
	<main class="px-3">
		<div class="form-container">
<%--			<jsp:include page="${WebPathConstants.ALERTS_PATH}"/>--%>

			<div class="categories p-3">
				<h3> fdsf </h3>

			</div>

			<form style="padding: 2%" action="${WebUrlConstants.LOGIN_URL}" method="post">
				<input type="text" name="${ParamNames.USERNAME}" class="form-control" placeholder="ddsffs" required><br>
				<input type="password" name="${ParamNames.PASSWORD}" class="form-control" placeholder="fdfsdf" required><br>

				<button type="submit" class="btn btn-primary"> fdsfdsf </button>
			</form>

			<a class="nav-link" href='${WebUrlConstants.GO_REGISTER_PAGE_URL}' onclick='this.parentNode.submit(); '> dsds </a>

		</div>
	</main>
</div>
</body>
</html>