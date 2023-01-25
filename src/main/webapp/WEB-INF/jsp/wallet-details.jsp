<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" uri="/WEB-INF/tld/locale.tld" %>
<%@ taglib prefix="n" uri="/WEB-INF/tld/name.tld" %>
<%@ taglib prefix="transfers" uri="/WEB-INF/tld/transfers.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%--<c:set var = "currTransferPage" scope = "session" value = "1"/>--%>
<%--<c:set var = "noOfTransferPages" scope = "session" value = "2"/>--%>

<html class="h-100">

<head>
	<c:import url="blocks/head.jsp" />
</head>

<body class="h-100 text-white bg-dark">
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">

	<header class="mb-15">
		<c:import url="blocks/header.jsp" />
	</header>

	<main class="px-3">
		<c:import url="blocks/warnings.jsp" />

		<div class="left-block col-4">

			<form style="padding: 2%">
				<a class="pr-2">Сортувати:</a>
			</form>

			<form style="padding: 2%" action="<n:Name value="controller.name"/>" method="post">
				<input type="hidden" name="redirect" value="<n:Name value="redirect.${sessionScope.currentPage}"/>">
				<input type="hidden" name="command" value="<n:Name value="command.sort-transfers"/>">
                <input type="hidden" name="currTransferPage" value="${sessionScope.currTransferPage}">
				<input type="hidden" name="currWalletBill" value="${sessionScope.currWalletBill}">
				<input type="hidden" name="transferSort" value="transfer.id DESC">

				<a class="pr-2" href='#' onclick='this.parentNode.submit(); return false;'>За Id</a>
			</form>

			<form style="padding: 2%" action="<n:Name value="controller.name"/>" method="post">
				<input type="hidden" name="redirect" value="<n:Name value="redirect.${sessionScope.currentPage}"/>">
				<input type="hidden" name="command" value="<n:Name value="command.sort-transfers"/>">
                <input type="hidden" name="currTransferPage" value="${sessionScope.currTransferPage}">
				<input type="hidden" name="currWalletBill" value="${sessionScope.currWalletBill}">
				<input type="hidden" name="transferSort" value="transfer.date ASC, transfer.id ASC">

				<a class="pr-2" href='#' onclick='this.parentNode.submit(); '>Old to New</a>
			</form>

			<form style="padding: 2%" action="<n:Name value="controller.name"/>" method="post">
				<input type="hidden" name="redirect" value="<n:Name value="redirect.${sessionScope.currentPage}"/>">
				<input type="hidden" name="command" value="<n:Name value="command.sort-transfers"/>">
                <input type="hidden" name="currTransferPage" value="${sessionScope.currTransferPage}">
				<input type="hidden" name="currWalletBill" value="${sessionScope.currWalletBill}">
				<input type="hidden" name="transferSort" value="transfer.date DESC, transfer.id DESC">

				<a class="pr-2" href='#' onclick='this.parentNode.submit(); return false;'>New to Old</a>
			</form>
		</div>
		<br>

		<transfers:Transfers value="${sessionScope.currWalletBill}"/>

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
				<c:forEach items="${sessionScope.walletTransfers}" var="transfer" >
					<td class="col-2"> <c:out value="${transfer.id}"></c:out> </td>
					<td class="col-3"> <c:out value="${transfer.sender_bill_number}"></c:out> </td>
					<td class="col-3"> <c:out value="${transfer.recipient_bill_number}"></c:out> </td>
					<c:choose>
						<c:when test="${transfer.sender_bill_number eq sessionScope.currWalletBill}">
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

		<%--For displaying Previous link except for the 1st page --%>
		<c:if test="${sessionScope.currTransferPage != 1}">
			<form style="padding: 2%" action="<n:Name value="controller.name"/>" method="post">
				<input type="hidden" name="redirect" value="<n:Name value="redirect.${sessionScope.currentPage}"/>">
				<input type="hidden" name="command" value="<n:Name value="command.sort-transfers"/>">
                <input type="hidden" name="transferSort" value="${sessionScope.transferSort}">
				<input type="hidden" name="currTransferPage" value="${sessionScope.currTransferPage - 1}">
				<input type="hidden" name="currWalletBill" value="${sessionScope.currWalletBill}">

				<td><a class="pr-2" href='#' onclick='this.parentNode.submit(); return false;'>Previous</a></td>
			</form>
		</c:if>

		<%--For displaying Page numbers.
    	The when condition does not display a link for the current page--%>
		<table border="1" cellpadding="5" cellspacing="5">
			<tr>
				<c:forEach begin="1" end="${sessionScope.noOfTransferPages}" var="i">
					<c:choose>
						<c:when test="${sessionScope.currTransferPage eq i}">
							<td>${i}</td>
						</c:when>
						<c:otherwise>
							<td>
							<form style="padding: 2%" action="<n:Name value="controller.name"/>" method="post">
								<input type="hidden" name="redirect" value="<n:Name value="redirect.${sessionScope.currentPage}"/>">
								<input type="hidden" name="command" value="<n:Name value="command.sort-transfers"/>">
								<input type="hidden" name="transferSort" value="${sessionScope.transferSort}">
								<input type="hidden" name="currWalletBill" value="${sessionScope.currWalletBill}">
								<input type="hidden" name="currTransferPage" value="${i}">

								<a class="pr-2" href='#' onclick='this.parentNode.submit(); return false;'>${i}</a>
							</form>
							</td>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</tr>
		</table>

		<%--For displaying Next link --%>
		<c:if test="${sessionScope.currTransferPage lt sessionScope.noOfTransferPages}">
			<form style="padding: 2%" action="<n:Name value="controller.name"/>" method="post">
				<input type="hidden" name="redirect" value="<n:Name value="redirect.${sessionScope.currentPage}"/>">
				<input type="hidden" name="command" value="<n:Name value="command.sort-transfers"/>">
                <input type="hidden" name="transferSort" value="${sessionScope.transferSort}">
				<input type="hidden" name="currWalletBill" value="${sessionScope.currWalletBill}">
				<input type="hidden" name="currTransferPage" value="${sessionScope.currTransferPage + 1}">

				<td><a class="pr-2" href='#' onclick='this.parentNode.submit(); return false;'>Next</a></td>
			</form>
		</c:if>

		<div class="categories p-3">
			<h3> Привіт ${sessionScope.username} </h3>
			<h3> Cортування ${sessionScope.transferSort} </h3>
			<h3> currTransferPage ${sessionScope.currTransferPage} </h3>
			<h3> noOfTransferPages ${sessionScope.noOfTransferPages} </h3>
		</div>
	</main>
</div>
</body>
</html>