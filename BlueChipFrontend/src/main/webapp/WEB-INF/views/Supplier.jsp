<%@ page language="java" contentType="text/html"%>
<%@include file="Header.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">

<form action="<c:url value="/addSupplier"/>" method="post">
<table align="center" class="table table-bordered">
	<tr bgcolor="grey">
		<td colspan="3"><h3><center> Add Supplier Detail</center></h3></td>
	</tr>
	<tr>
		
		<td><h4>Supplier Name</h4></td>
		<td><h4>Supplier Address</h4></td>
		<td><h4>Operation</h4></td>
	</tr>
	<tr>
		
		<td><input type="text" name="supName" id="supName"/></td>
		<td><textarea cols=30 rows="1" name="supAddr"></textarea></td>
		<td><input type="submit" value="Add Supplier"/></td>
	</tr>
	
</table>
</form>



<table align="center" class="table table-bordered">

<tr>
	<td bgcolor="grey" colspan="4"><h3><center>Suppier Detail</center></h3></td>
</tr>

<tr>

	<td><h4>ID</h4></td>
	
	<td><h4>Name</h4></td>
	
	<td><h4>Address</h4></td>
	
	<td><h4>Operation</h4></td>
</tr>

<c:forEach items="${listSuppliers}" var="supplier">
<tr>
	
	<td>${supplier.supplierId}</td>
	
	<td>${supplier.supplierName}</td>
	
	<td>${supplier.supplierAddr}</td>
	
	<td>
		<a href="<c:url value="/editSupplier/${supplier.supplierId}"/>">
		
		<img src="<c:url value="resources/images/edit.png"/>" width="70" height="25"/></a>
		
		<a href="<c:url value="/deleteSupplier/${supplier.supplierId}"/>">
		
		<img src="<c:url value="resources/images/delete.png"/>" width="50" height="20"/></a>
		
	</td>
</tr>
</c:forEach>
</table>

</div>