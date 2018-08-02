<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="message"/>
<html lang="${language}">
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <title><fmt:message key="label.wagon.list"/></title>
</head>
<body>
<div class="container">
    <form>
        <select id="language" name="language" onchange="submit()">
            <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message key="label.lang.en"/></option>
            <option value="uk" ${language == 'uk' ? 'selected' : ''}><fmt:message key="label.lang.uk"/></option>

        </select>
    </form>

    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/"><fmt:message key="label.home"/></a></li>
            <li class="breadcrumb-item "><a href="/wagonRandomList"><fmt:message key="label.wagon.list.random"/></a>
            </li>
            <li class="breadcrumb-item active" aria-current="page"><fmt:message key="label.wagon.list.jdbc"/></li>
            <li class="breadcrumb-item"><a href="/wagonFileList"><fmt:message key="label.wagon.list.file"/></a></li>

        </ol>
    </nav>
    <center>

        <h2>
            <a href="/newWagon"><fmt:message key="label.wagon.addNew"/></a>
            &nbsp;&nbsp;&nbsp;
            <a href="/listWagons"><fmt:message key="label.wagon.list"/></a>
            &nbsp;&nbsp;&nbsp;
            <a href="/search"><fmt:message key="label.wagon.search"/></a>

        </h2>
    </center>
    <div align="center">
        <table class="table table-striped" border="1" cellpadding="5">
            <h2><fmt:message key="label.wagon.list"/></h2>
            <tr>
            <tr>
                <th width="120"><fmt:message key="label.number.wagon"/></th>
                <th width="120"><fmt:message key="label.type"/></th>
                <th width="120"><fmt:message key="label.number.of.passenger"/></th>
                <th width="120"><fmt:message key="label.amount.of.luggage"/></th>
                <th width="120"><fmt:message key="label.max.passengers"/></th>
                <th width="120"><fmt:message key="label.free.passengers"/></th>
            </tr>
            </tr>
            <c:forEach var="wagon" items="${listWagon}">
                <tr>
                    <td><c:out value="${wagon.id}"/></td>
                    <td><c:out value="${wagon.type}"/></td>
                    <td><c:out value="${wagon.numberOfPassengers}"/></td>
                    <td><c:out value="${wagon.amountOfLuggage}"/></td>
                    <td><c:out value="${wagon.maxPassengers}"/></td>
                    <td><c:out value="${wagon.maxPassengers - wagon.numberOfPassengers}"/></td>
                    <td>
                        <a href="/edit?id=<c:out value='${wagon.id}' />"><fmt:message key="label.button.edit"/></a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<c:out value='${wagon.id}' />"><fmt:message key="label.button.delete"/></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div align="right">
        <p><fmt:message key="label.total.passengers"/> :
            <mark> ${TotalOfPassengers} </mark>
        </p>
        <p><fmt:message key="label.total.luggage"/> :
            <mark> ${TotalOfLuggage} </mark>
        </p>
    </div>
</div>
</body>
</html>