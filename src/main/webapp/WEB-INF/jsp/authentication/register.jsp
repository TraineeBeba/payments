<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.payments.command.constant.WebPathConstants"%>
<%@ page import="com.epam.payments.command.constant.WebUrlConstants"%>
<%@ page import="com.epam.payments.command.constant.ParamNames"%>
<%@ page import="com.epam.payments.command.i18n.constant.Locale"%>
<%@ page import="com.epam.payments.i18n.RegisterPage"%>

<fmt:setBundle basename="${RegisterPage.BUNDLE_NAME}" />
<%--<fmt:setLocale value="en" scope="session"/>--%>

<html class="h-100">

<head>
	<jsp:include page="${WebPathConstants.HEAD_PATH}"/>
</head>

<header class="mb-15">
<%--	<jsp:include page="${WebPathConstants.HEADER_PATH}"/>--%>
<%--	<%@ include file="${WebPathConstants.HEADER_PATH}" %>--%>
	<%@ include file="/WEB-INF/jsp/jspf/header.jspf" %>
</header>

<body class="h-100 text-white bg-dark">
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
	<main class="px-3">
		<div class="form-container">
<%--			<jsp:include page="${WebPathConstants.ALERTS_PATH}"/>--%>

				<div class="categories p-3">
					<h3> ${RegisterPage.TITLE} </h3>
				</div>

                <c:out value="${WebPathConstants.print()}"/>

				<form style="padding: 2%" action="${WebUrlConstants.REGISTER_URL}" method="post">
					<input type="text" name="${ParamNames.USERNAME}" class="form-control" placeholder="${RegisterPage.ENTER_LABEL} ${RegisterPage.USERNAME_PLACEHOLDER}"  required><br>
					<input type="text" name="${ParamNames.PASSWORD}" class="form-control" placeholder="${RegisterPage.ENTER_LABEL} ${RegisterPage.PASSWORD_PLACEHOLDER}" required><br>

					<button type="submit" class="btn btn-primary"> ${RegisterPage.REGISTER_BUTTON} </button>
				</form>

				<a class="nav-link" href='${WebUrlConstants.GO_LOGIN_PAGE_URL}' onclick='this.parentNode.submit(); '> ${RegisterPage.GO_LOGIN_BUTTON} </a>

			</div>
		</main>
	</div>
</body>
</html>