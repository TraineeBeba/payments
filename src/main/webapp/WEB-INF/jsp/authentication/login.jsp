<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.payments.command.constant.WebPathConstants"%>
<%@ page import="com.epam.payments.command.constant.WebUrlConstants"%>
<%@ page import="com.epam.payments.command.constant.ParamNames"%>

<fmt:setLocale value="${sessionScope[ParamNames.LANGUAGE]}"/>
<fmt:setBundle basename="locale.authentication.login"/>

<html class="h-100">

<head>
	<jsp:include page="${WebPathConstants.HEAD_PATH}"/>
</head>

<header class="mb-15">
	<%@ include file="/WEB-INF/jsp/jspf/header.jspf" %>
</header>

<body class="h-100 text-white bg-dark">
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
	<main class="px-3">
		<div class="form-container">
			<%@ include file="/WEB-INF/jsp/jspf/alerts.jspf" %>

			<div class="categories p-3">
				<h3> <fmt:message key="title"/> </h3>
			</div>

			<form style="padding: 2%" action="${WebUrlConstants.COMMON_URL_PREFIX}" method="post">
				<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.LOGIN}">
				<input type="text" name="${ParamNames.USERNAME}" class="form-control" placeholder="<fmt:message key="enter"/> <fmt:message key="username"/>" required><br>
				<input type="password" name="${ParamNames.PASSWORD}" class="form-control" placeholder="<fmt:message key="enter"/> <fmt:message key="password"/>" required><br>

				<button type="submit" class="btn btn-primary"> <fmt:message key="login"/> </button>
			</form>

			<a class="nav-link" href='${WebUrlConstants.GO_REGISTER_PAGE_URL}' onclick='this.parentNode.submit(); '> <fmt:message key="go_register"/> </a>

		</div>
	</main>
</div>
</body>
</html>