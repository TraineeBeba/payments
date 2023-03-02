<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
		<%@ include file="/WEB-INF/jspf/alerts.jspf" %>

		<h3>${requestScope.currWallet.name}  №${requestScope.currWallet.bill_number}  ${requestScope.currWallet.balance} грн </h3><br>

		<div>
			<h4>Дії:</h4>
			<c:choose>
				<c:when test="${requestScope.currWallet.getState() eq 'blocked'  and sessionScope.userDTO.getRole() eq 'role_admin'}">
					<form action="<n:Namespace value="controller.name"/>" method="post">
						<input type="hidden" name="command" value="<n:Namespace value="command.unblock-wallet"/>">
						<input type="hidden" name="walletId" value="${requestScope.currWallet.getId()}">

						<a class="pr-2" href='#' onclick='this.parentNode.submit();'>Розблокувати</a>
					</form>
				</c:when>
				<c:when test="${requestScope.currWallet.getState() eq 'blocked'  and sessionScope.userDTO.getRole() eq 'role_user'}">
					<form action="<n:Namespace value="controller.name"/>" method="post">
						<input type="hidden" name="command" value="<n:Namespace value="command.wallet-request"/>">
						<input type="hidden" name="request_type" value="unblock_wallet">
                        <input type="hidden" name="walletId" value="${requestScope.currWallet.getId()}">

						<a class="pr-2" href='#' onclick='this.parentNode.submit();'>Розблокувати</a>
					</form>
				</c:when>
				<c:when test="${requestScope.currWallet.getState() eq 'unblocked'}">
					<form action="<n:Namespace value="controller.name"/>" method="post">
						<input type="hidden" name="command" value="<n:Namespace value="command.block-wallet"/>">
						<input type="hidden" name="walletId" value="${requestScope.currWallet.getId()}">

						<a class="pr-2" href='#' onclick='this.parentNode.submit();'>Заблокувати</a>
					</form>
				</c:when>
			</c:choose>

			<form action="<n:Namespace value="controller.name"/>" method="get">
				<input type="hidden" name="command" value="<n:Namespace value="command.go.user-wallets"/>">

				<a class="pr-2" href='#' onclick='this.parentNode.submit();'>Назад</a>
			</form>

		</div>
		<br>

		<h4>Історія переказів:</h4><br>

		<div class="left-block col-4">

			<form>
				<a class="pr-2">Сортувати:</a>
			</form>

			<form action="<n:Namespace value="controller.name"/>" method="get">
				<input type="hidden" name="command" value="<n:Namespace value="command.go.wallet-details"/>">
				<input type="hidden" name="transferSort" value="transfer.id DESC">
				<input type="hidden" name="page" value="${requestScope.page}">
				<input type="hidden" name="bill_number" value="${requestScope.currWallet.bill_number}">

				<a class="pr-2" href='#' onclick='this.parentNode.submit(); '>За Id</a>
			</form>

			<form action="<n:Namespace value="controller.name"/>" method="get">
				<input type="hidden" name="command" value="<n:Namespace value="command.go.wallet-details"/>">
				<input type="hidden" name="transferSort" value="transfer.date ASC, transfer.id ASC">
				<input type="hidden" name="page" value="${requestScope.page}">
				<input type="hidden" name="bill_number" value="${requestScope.currWallet.bill_number}">

				<a class="pr-2" href='#' onclick='this.parentNode.submit(); '>Old to New</a>
			</form>

			<form action="<n:Namespace value="controller.name"/>" method="get">
				<input type="hidden" name="command" value="<n:Namespace value="command.go.wallet-details"/>">
				<input type="hidden" name="transferSort" value="transfer.date DESC, transfer.id DESC">
				<input type="hidden" name="page" value="${requestScope.page}">
				<input type="hidden" name="bill_number" value="${requestScope.currWallet.bill_number}">

				<a class="pr-2" href='#' onclick='this.parentNode.submit();'>New to Old</a>
			</form>
		</div>
		<br>


		<table class="table table-dark table-bordered table-hover text-center">
			<thead>
			<tr class="row ml-3">
				<th class="col-2"><b>Номер</b></th>
				<th class="col-3"><b>Номер гаманця відправника</b></th>
				<th class="col-3"><b>Номер гаманця отримувача</b></th>
				<th class="col-2">Сума</th>
				<th class="col-2">Дата</th>
			</tr>
			</thead>
			<tbody>
			<tr class="row ml-3" >
				<c:forEach items="${requestScope.walletTransfers}" var="transfer" >
					<td class="col-2"> <c:out value="${transfer.id}"></c:out> </td>
					<td class="col-3"> <c:out value="${transfer.sender_bill_number}"></c:out> </td>
					<td class="col-3"> <c:out value="${transfer.recipient_bill_number}"></c:out> </td>
					<c:choose>
						<c:when test="${transfer.sender_bill_number eq requestScope.currWallet.bill_number}">
							<td class="col-2"> <c:out value="-${transfer.sum}"></c:out> </td>
						</c:when>
						<c:otherwise>
							<td class="col-2"> <c:out value="+${transfer.sum}"></c:out> </td>
						</c:otherwise>
					</c:choose>
					<td class="col-2"> <c:out value="${transfer.date}"></c:out> </td>
				</c:forEach>
			</tr>
			</tbody>
		</table>

		<table border="1" cellpadding="5" cellspacing="5">
			<tr>
				<c:if test="${requestScope.page != 1}">
					<td>
					<form action="<n:Namespace value="controller.name"/>" method="get">
						<input type="hidden" name="command" value="<n:Namespace value="command.go.wallet-details"/>">
						<input type="hidden" name="page" value="${requestScope.page - 1}">
						<input type="hidden" name="bill_number" value="${requestScope.currWallet.bill_number}">

						<a class="pr-2" href='#' onclick='this.parentNode.submit();'>Previous</a>
					</form>
					</td>
				</c:if>

				<c:forEach begin="1" end="${requestScope.noOfTransferPages}" var="i">
					<c:choose>
						<c:when test="${requestScope.page eq i}">
							<td>${i}</td>
						</c:when>
						<c:otherwise>
							<td>
							<form action="<n:Namespace value="controller.name"/>" method="get">
								<input type="hidden" name="command" value="<n:Namespace value="command.go.wallet-details"/>">
								<input type="hidden" name="page" value="${i}">
								<input type="hidden" name="bill_number" value="${requestScope.currWallet.bill_number}">

								<a class="pr-2" href='#' onclick='this.parentNode.submit();'>${i}</a>
							</form>
							</td>
						</c:otherwise>
					</c:choose>
				</c:forEach>

			<c:if test="${requestScope.page lt requestScope.noOfTransferPages}">
				<td>
				<form action="<n:Namespace value="controller.name"/>" method="get">
					<input type="hidden" name="command" value="<n:Namespace value="command.go.wallet-details"/>">
					<input type="hidden" name="page" value="${requestScope.page + 1}">
					<input type="hidden" name="bill_number" value="${requestScope.currWallet.bill_number}">

					<a class="pr-2" href='#' onclick='this.parentNode.submit();'>Next</a>
				</form>
				</td>
			</c:if>
			</tr>
		</table>

	</main>
</div>
</body>
</html>