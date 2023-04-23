<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.payments.command.constant.WebUrlConstants"%>
<%@ page import="com.epam.payments.command.constant.ParamNames"%>
<%@ page errorPage="/WEB-INF/jsp/error/error.jsp" %>

<fmt:setLocale value="${sessionScope[ParamNames.LANGUAGE]}"/>
<fmt:setBundle basename="locale.user.wallet.wallet-details"/>

<html class="h-100">
<head>
	<%@ include file="/WEB-INF/jsp/jspf/head.jspf" %>
</head>

<body class="h-100 text-white bg-dark">
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
	<c:set var="userDTO" value="${sessionScope[ParamNames.USER_DTO]}"/>
	<c:set var="currWallet" value="${requestScope[ParamNames.CURR_WALLET]}"/>
	<c:set var="page" value="${requestScope[ParamNames.PAGE]}"/>
	<c:set var="walletTransfers" value="${requestScope[ParamNames.WALLET_TRANSFERS]}"/>
	<c:set var="noOfTransferPages" value="${requestScope[ParamNames.NO_OF_TRANSFER_PAGES]}"/>

	<header class="mb-15">
		<%@ include file="/WEB-INF/jsp/jspf/header.jspf" %>
	</header>

	<main class="px-3">
		<%@ include file="/WEB-INF/jsp/jspf/alerts.jspf" %>

		<h3>${currWallet.name}  №${currWallet.bill_number}  ${currWallet.balance} <fmt:message key="title.uah"/> </h3><br>

		<div>
			<h4><fmt:message key="actions"/></h4>
			<c:choose>
				<%--				<c:when test="${requestScope.currWallet.getState() eq 'blocked'  and sessionScope.userDTO.getRole() eq 'role_admin'}">--%>
				<%--					<form action="<n:Namespace value="controller.name"/>" method="post">--%>
				<%--						<input type="hidden" name="command" value="<n:Namespace value="command.unblock-wallet"/>">--%>
				<%--						<input type="hidden" name="walletId" value="${requestScope.currWallet.getId()}">--%>

				<%--						<a class="pr-2" href='#' onclick='this.parentNode.submit();'>Розблокувати</a>--%>
				<%--					</form>--%>
				<%--				</c:when>--%>
				<c:when test="${currWallet.getState().getName() eq 'blocked'}">
					<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="post">
						<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.ADMIN_UNBLOCK_WALLET}">
						<input type="hidden" name="${ParamNames.REQUEST_TYPE}" value="unblock_wallet">
						<input type="hidden" name="${ParamNames.BILL_NUMBER}" value="${currWallet.getBill_number()}">
						<input type="hidden" name="${ParamNames.SELECTED_USER_NAME}" value="${requestScope[ParamNames.SELECTED_USER_NAME]}">

						<a class="pr-2" href='#' onclick='this.parentNode.submit();'><fmt:message key="unblock"/></a>
					</form>
				</c:when>
				<c:when test="${currWallet.getState().getName() eq 'unblocked'}">
					<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="post">
						<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.ADMIN_BLOCK_WALLET}">
						<input type="hidden" name="${ParamNames.BILL_NUMBER}" value="${currWallet.getBill_number()}">
						<input type="hidden" name="${ParamNames.SELECTED_USER_NAME}" value="${requestScope[ParamNames.SELECTED_USER_NAME]}">

						<a class="pr-2" href='#' onclick='this.parentNode.submit();'><fmt:message key="block"/></a>
					</form>
				</c:when>
			</c:choose>

			<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
				<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_ADMIN_USER_WALLETS_PAGE}">
				<input type="hidden" name="${ParamNames.SELECTED_USER_NAME}" value="${requestScope[ParamNames.SELECTED_USER_NAME]}">

				<a class="pr-2" href='#' onclick='this.parentNode.submit();'><fmt:message key="back"/></a>
			</form>

		</div>
		<br>

		<h4><fmt:message key="history"/></h4><br>

		<div class="left-block col-4">

			<form>
				<a class="pr-2"><fmt:message key="sort"/></a>
			</form>

			<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
				<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_WALLET_DETAILS_PAGE}">
				<input type="hidden" name="${ParamNames.TRANSFER_SORT}" value="transfer.id DESC">
				<input type="hidden" name="${ParamNames.PAGE}" value="${page}">
				<input type="hidden" name="${ParamNames.BILL_NUMBER}" value="${currWallet.bill_number}">

				<a class="pr-2" href='#' onclick='this.parentNode.submit(); '>За Id</a>
			</form>

			<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
				<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_WALLET_DETAILS_PAGE}">
				<input type="hidden" name="${ParamNames.TRANSFER_SORT}" value="transfer.date ASC, transfer.id ASC">
				<input type="hidden" name="${ParamNames.PAGE}" value="${page}">
				<input type="hidden" name="${ParamNames.BILL_NUMBER}" value="${currWallet.bill_number}">

				<a class="pr-2" href='#' onclick='this.parentNode.submit(); '>Old to New</a>
			</form>

			<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
				<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_WALLET_DETAILS_PAGE}">
				<input type="hidden" name="${ParamNames.TRANSFER_SORT}" value="transfer.date DESC, transfer.id DESC">
				<input type="hidden" name="${ParamNames.PAGE}" value="${page}">
				<input type="hidden" name="${ParamNames.BILL_NUMBER}" value="${currWallet.bill_number}">

				<a class="pr-2" href='#' onclick='this.parentNode.submit();'>New to Old</a>
			</form>
		</div>
		<br>

		<table class="table table-dark table-bordered table-hover text-center">
			<thead>
			<tr class="row ml-3">
				<%--				<th class="col-2"><b>Номер</b></th>--%>
				<th class="col-3"><b><fmt:message key="sender_bill_number"/></b></th>
				<th class="col-3"><b><fmt:message key="recipient_bill_number"/></b></th>
				<th class="col-2"><fmt:message key="sum"/></th>
				<th class="col-2"><fmt:message key="date"/></th>
			</tr>
			</thead>
			<tbody>
			<tr class="row ml-3" >
				<c:forEach items="${walletTransfers}" var="transfer" varStatus="status" >
					<%--					<td class="col-2"> <c:out value="${status.index + 1}  ${page} ${noOfTransferPages}"></c:out> </td>--%>
					<td class="col-3"> <c:out value="${transfer.sender_bill_number}"></c:out> </td>
					<td class="col-3"> <c:out value="${transfer.recipient_bill_number}"></c:out> </td>
					<c:choose>
						<c:when test="${transfer.sender_bill_number eq currWallet.bill_number}">
							<td class="col-2"> <c:out value="-${transfer.sum}"></c:out> </td>
						</c:when>
						<c:otherwise>
							<td class="col-2"> <c:out value="+${transfer.sum}"></c:out> </td>
						</c:otherwise>
					</c:choose>
					<td class="col-2"> <c:out value="${transfer.date}"></c:out></td>
				</c:forEach>
			</tr>
			</tbody>
		</table>

		<table border="1" cellpadding="5" cellspacing="5">
			<tr>
				<c:if test="${page != 1}">
					<td>
						<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
							<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_WALLET_DETAILS_PAGE}">
							<input type="hidden" name="${ParamNames.PAGE}" value="${page - 1}">
							<input type="hidden" name="${ParamNames.BILL_NUMBER}" value="${currWallet.bill_number}">

							<a class="pr-2" href='#' onclick='this.parentNode.submit();'><fmt:message key="prev"/></a>
						</form>
					</td>
				</c:if>

				<c:forEach begin="1" end="${noOfTransferPages}" var="i">
					<c:choose>
						<c:when test="${page eq i}">
							<td>${i}</td>
						</c:when>
						<c:otherwise>
							<td>
								<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
									<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_WALLET_DETAILS_PAGE}">
									<input type="hidden" name="${ParamNames.PAGE}" value="${i}">
									<input type="hidden" name="${ParamNames.BILL_NUMBER}" value="${currWallet.bill_number}">

									<a class="pr-2" href='#' onclick='this.parentNode.submit();'>${i}</a>
								</form>
							</td>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${page lt noOfTransferPages}">
					<td>
						<form action="${WebUrlConstants.COMMON_URL_PREFIX}" method="get">
							<input type="hidden" name="${ParamNames.COMMAND}" value="${CommandNames.GO_WALLET_DETAILS_PAGE}">
							<input type="hidden" name="${ParamNames.PAGE}" value="${page + 1}">
							<input type="hidden" name="${ParamNames.BILL_NUMBER}" value="${currWallet.bill_number}">

							<a class="pr-2" href='#' onclick='this.parentNode.submit();'><fmt:message key="next"/></a>
						</form>
					</td>
				</c:if>
			</tr>
		</table>

	</main>
</div>
</body>
</html>