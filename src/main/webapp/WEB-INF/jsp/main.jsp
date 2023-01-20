<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" uri="/WEB-INF/tld/locale.tld" %>
<%@ taglib prefix="n" uri="/WEB-INF/tld/name.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<%--<sql:setDataSource var="db" driver="com.mysql.cj.jdbc.Driver" url="jdbc:mysql://localhost:3306/payments" user="root" password=""/>--%>
<%--<sql:query dataSource="${db}" var="wallets"> SELECT * FROM wallet WHERE wallet.user_id = ${sessionScope.user_id} </sql:query>--%>

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

		<div class="сinemas p-3">
			<h3>Гаманці</h3>
			<a href="/<n:Name value="controller.name"/><n:Name value="redirect.create-wallet"/>"> Додати новий </a>
		</div>

		<table class="table table-dark table-bordered table-hover text-center">
			<thead>
			<tr class="row ml-3">
				<th class="col-2"><b>Назва</b></th>
				<th class="col-3"><b>Номер рахунку</b></th>
				<th class="col-3"><b>Баланс</b></th>
				<th class="col-2">Статус</th>
				<th class="col-2">Детальніше</th>
			</tr>
			</thead>
			<tbody>
			<tr class="row ml-3" >
				<c:forEach items="${sessionScope.wallets}" var="wallet" >
					<td class="col-2"> <c:out value="${wallet.name}"></c:out> </td>
					<td class="col-3"> <c:out value="${wallet.bill_number}"></c:out> </td>
					<td class="col-3"> <c:out value="${wallet.balance}"></c:out> </td>
					<td class="col-2"> <c:out value="${wallet.getState()}"></c:out> </td>
					<td class="pupa href-container col-2">
						<a class="nav-link" href="/<n:Name value="controller.name"/>?command=goDetails-WalletCommand">Детальніше</a>
					</td>
				</c:forEach>
			</tr>
			</tbody>
		</table>

		<div class="categories p-3">
			<h3> Привіт ${sessionScope.username} </h3>
		</div>

	</main>
</div>
</body>
</html>