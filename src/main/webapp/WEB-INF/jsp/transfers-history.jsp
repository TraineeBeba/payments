<%--<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<%@ taglib prefix="my" uri="/WEB-INF/tld/locale.tld" %>--%>
<%--<%@ taglib prefix="n" uri="/WEB-INF/tld/name.tld" %>--%>
<%--<%@ taglib prefix="transfers" uri="/WEB-INF/tld/transfers.tld" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>--%>
<%--&lt;%&ndash;<c:set var = "currTransferPage" scope = "session" value = "1"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;<c:set var = "noOfTransferPages" scope = "session" value = "2"/>&ndash;%&gt;--%>

<%--<html class="h-100">--%>

<%--<head>--%>
<%--	<c:import url="blocks/head.jsp" />--%>
<%--</head>--%>

<%--<body class="h-100 text-white bg-dark">--%>
<%--<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">--%>

<%--	<header class="mb-15">--%>
<%--		<c:import url="blocks/header.jsp" />--%>
<%--	</header>--%>

<%--	<main class="px-3">--%>
<%--		<c:import url="blocks/warnings.jsp" />--%>

<%--		<div class="left-block col-4">--%>

<%--			<form style="padding: 2%">--%>
<%--				<a class="pr-2">Сортувати:</a>--%>
<%--			</form>--%>

<%--			<form style="padding: 2%" action="<n:Name value="controller.name"/>" method="post">--%>
<%--				<input type="hidden" name="redirect" value="<n:Name value="redirect.transfers-history"/>">--%>
<%--				<input type="hidden" name="command" value="<n:Name value="command.sort-transfers"/>">--%>
<%--                <input type="hidden" name="currTransferPage" value="${sessionScope.currTransferPage}">--%>
<%--				<input type="hidden" name="transferSort" value="transfer.id DESC">--%>

<%--				<a class="pr-2" href='#' onclick='this.parentNode.submit(); return false;'>За Id</a>--%>
<%--			</form>--%>

<%--			<form style="padding: 2%" action="<n:Name value="controller.name"/>" method="post">--%>
<%--				<input type="hidden" name="redirect" value="<n:Name value="redirect.transfers-history"/>">--%>
<%--				<input type="hidden" name="command" value="<n:Name value="command.sort-transfers"/>">--%>
<%--                <input type="hidden" name="currTransferPage" value="${sessionScope.currTransferPage}">--%>
<%--                <input type="hidden" name="transferSort" value="transfer.date ASC, transfer.id ASC">--%>

<%--				<a class="pr-2" href='#' onclick='this.parentNode.submit(); '>Old to New</a>--%>
<%--			</form>--%>

<%--			<form style="padding: 2%" action="<n:Name value="controller.name"/>" method="post">--%>
<%--				<input type="hidden" name="redirect" value="<n:Name value="redirect.transfers-history"/>">--%>
<%--				<input type="hidden" name="command" value="<n:Name value="command.sort-transfers"/>">--%>
<%--                <input type="hidden" name="currTransferPage" value="${sessionScope.currTransferPage}">--%>
<%--				<input type="hidden" name="transferSort" value="transfer.date DESC, transfer.id DESC">--%>

<%--				<a class="pr-2" href='#' onclick='this.parentNode.submit(); return false;'>New to Old</a>--%>
<%--			</form>--%>
<%--		</div>--%>
<%--		<br>--%>

<%--		<transfers:Transfers/>--%>

<%--		&lt;%&ndash;For displaying Previous link except for the 1st page &ndash;%&gt;--%>
<%--		<c:if test="${sessionScope.currTransferPage != 1}">--%>
<%--			<form style="padding: 2%" action="<n:Name value="controller.name"/>" method="post">--%>
<%--				<input type="hidden" name="redirect" value="<n:Name value="redirect.transfers-history"/>">--%>
<%--				<input type="hidden" name="command" value="<n:Name value="command.sort-transfers"/>">--%>
<%--                <input type="hidden" name="transferSort" value="${sessionScope.transferSort}">--%>
<%--				<input type="hidden" name="currTransferPage" value="${sessionScope.currTransferPage - 1}">--%>

<%--				<td><a class="pr-2" href='#' onclick='this.parentNode.submit(); return false;'>Previous</a></td>--%>
<%--			</form>--%>
<%--		</c:if>--%>

<%--		&lt;%&ndash;For displaying Page numbers.--%>
<%--    	The when condition does not display a link for the current page&ndash;%&gt;--%>
<%--		<table border="1" cellpadding="5" cellspacing="5">--%>
<%--			<tr>--%>
<%--				<c:forEach begin="1" end="${sessionScope.noOfTransferPages}" var="i">--%>
<%--					<c:choose>--%>
<%--						<c:when test="${sessionScope.currTransferPage eq i}">--%>
<%--							<td>${i}</td>--%>
<%--						</c:when>--%>
<%--						<c:otherwise>--%>
<%--							<td><a href="<n:Name value="controller.name"/>?page=${i}">${i}</a></td>--%>
<%--						</c:otherwise>--%>
<%--					</c:choose>--%>
<%--				</c:forEach>--%>
<%--			</tr>--%>
<%--		</table>--%>

<%--		&lt;%&ndash;For displaying Next link &ndash;%&gt;--%>
<%--		<c:if test="${sessionScope.currTransferPage lt sessionScope.noOfTransferPages}">--%>
<%--			<form style="padding: 2%" action="<n:Name value="controller.name"/>" method="post">--%>
<%--				<input type="hidden" name="redirect" value="<n:Name value="redirect.transfers-history"/>">--%>
<%--				<input type="hidden" name="command" value="<n:Name value="command.sort-transfers"/>">--%>
<%--                <input type="hidden" name="transferSort" value="${sessionScope.transferSort}">--%>
<%--				<input type="hidden" name="currTransferPage" value="${sessionScope.currTransferPage + 1}">--%>

<%--				<td><a class="pr-2" href='#' onclick='this.parentNode.submit(); return false;'>Next</a></td>--%>
<%--			</form>--%>
<%--		</c:if>--%>

<%--		<div class="categories p-3">--%>
<%--			<h3> Привіт ${sessionScope.username} </h3>--%>
<%--			<h3> Cортування ${sessionScope.transferSort} </h3>--%>
<%--			<h3> currTransferPage ${sessionScope.currTransferPage} </h3>--%>
<%--			<h3> noOfTransferPages ${sessionScope.noOfTransferPages} </h3>--%>
<%--		</div>--%>
<%--	</main>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>