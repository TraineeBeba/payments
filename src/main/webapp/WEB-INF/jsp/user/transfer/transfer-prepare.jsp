<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.payments.command.constant.WebUrlConstants"%>
<%@ page import="com.epam.payments.command.constant.ParamNames"%>
<%@ page errorPage="/WEB-INF/jsp/error/error.jsp" %>


<fmt:setLocale value="${sessionScope[ParamNames.LANGUAGE]}"/>
<fmt:setBundle basename="locale.user.transfer.transfer-prepare"/>

<html class="h-100">

<head>
	<%@ include file="/WEB-INF/jsp/jspf/head.jspf" %>
</head>

<body class="h-100 text-white bg-dark">
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">

	<header class="mb-15">
		<%@ include file="/WEB-INF/jsp/jspf/header.jspf" %>
	</header>

	<main class="px-3">
		<div class="form-container">

			<%@ include file="/WEB-INF/jsp/jspf/alerts.jspf" %>

			<div class="categories p-3">
				<h3><fmt:message key="title"/></h3>
			</div>

			<form style="padding: 2%" action="${WebUrlConstants.COMMON_URL_PREFIX}" method="post">
				<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.PREPARE_TRANSFER}">
				<input type="number" step="0.01" min="0.00" name="${ParamNames.SUM}" placeholder="<fmt:message key="placeholder.enter-sum"/>" class="form-control" required><br>
				<input id="uintTextBox" name="${ParamNames.RECIPIENT_BILL_NUMBER}" minlength="6" maxlength="6" placeholder="<fmt:message key="placeholder.enter-bill"/>" class="form-control"><br>

				<select name="${ParamNames.SENDER_BILL_NUMBER}" class="form-select">
					<c:forEach var="wallet" items="${requestScope.wallets}">
						<option value="${wallet.getBill_number()}" >
							<c:out value="${wallet.getName()} ${wallet.getBill_number()} ${wallet.getBalance()}"/> <fmt:message key="uah"/>
						</option>
					</c:forEach>
				</select>
				<br>

				<button type="submit" class="btn btn-primary"> <fmt:message key="send"/></button>
			</form>

			<a class="nav-link active" aria-current="page" href="${WebUrlConstants.GO_USER_WALLETS_PAGE_URL}"> <fmt:message key="back"/> </a>

		</div>

		<script> type="text/javascript"
			function setInputFilter(textbox, inputFilter, errMsg) {
				["input", "keydown", "keyup", "mousedown", "mouseup", "select", "contextmenu", "drop", "focusout"].forEach(function(event) {
					textbox.addEventListener(event, function(e) {
						if (inputFilter(this.value)) {
							// Accepted value
							if (["keydown","mousedown","focusout"].indexOf(e.type) >= 0){
								this.classList.remove("input-error");
								this.setCustomValidity("");
							}
							this.oldValue = this.value;
							this.oldSelectionStart = this.selectionStart;
							this.oldSelectionEnd = this.selectionEnd;
						} else if (this.hasOwnProperty("oldValue")) {
							// Rejected value - restore the previous one
							this.classList.add("input-error");
							this.setCustomValidity(errMsg);
							this.reportValidity();
							this.value = this.oldValue;
							this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd);
						} else {
							// Rejected value - nothing to restore
							this.value = "";
						}
					});
				});
			}

			setInputFilter(document.getElementById("uintTextBox"), function(value) {
				return /^\d*$/.test(value); }, "Must be an unsigned integer");
		</script>

	</main>
</div>
</body>
</html>