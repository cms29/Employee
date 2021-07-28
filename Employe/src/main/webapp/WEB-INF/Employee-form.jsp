<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Employee Details</title>
</head>
<body>
	<center>
		<h1>Employee Details</h1>
        <h2>
        	<a href="new">Add New Employee</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="list">List All Employee</a>
        	
        </h2>
	</center>
    <div align="center">
		<c:if test="${Employee != null}">
			<form action="update" method="post">
        </c:if>
        <c:if test="${Employee == null}">
			<form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		<c:if test="${Employee != null}">
            			Edit Employee
            		</c:if>
            		<c:if test="${Employee == null}">
            			Add New Employee
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${Employee != null}">
        			<input type="hidden" name="id" value="<c:out value='${Employee.id}' />" />
        		</c:if>            
            <tr>
                <th>Employee Name: </th>
                <td>
                	<input type="text" name="name" size="45"
                			value="<c:out value='${Employee.name}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Employee Address: </th>
                <td>
                	<input type="text" name="Address" size="45"
                			value="<c:out value='${Employee.addres}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>Phone number: </th>
                <td>
                	<input type="text" name="phonenum" size="15"
                			value="<c:out value='${Employee.phonenum}' />"
                	/>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Save" />
            	</td>
            </tr>
        </table>
        </form>
    </div>	
</body>
</html>
