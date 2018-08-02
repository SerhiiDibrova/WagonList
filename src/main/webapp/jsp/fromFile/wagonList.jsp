<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="message" />
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
    <title><fmt:message key="label.wagon.list" /></title>
</head>
<body>
<div class="container">
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message key="label.lang.en" /></option>
        <option value="uk" ${language == 'uk' ? 'selected' : ''}><fmt:message key="label.lang.uk" /></option>

    </select>
</form>
<nav aria-label="breadcrumb">
    <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href ="/"><fmt:message key="label.home"/></a></li>
        <li class="breadcrumb-item"><a href="/wagonRandomList"><fmt:message key="label.wagon.list.random"/></a></li>
        <li class="breadcrumb-item"><a href="/wagonJDBCList"><fmt:message key="label.wagon.list.jdbc"/></a></li>
        <li class="breadcrumb-item active" aria-current="page"><fmt:message key="label.wagon.list.file"/></li>

    </ol>
</nav>
    <div align="center">
    <h2>
        <a href="/newWagonCSV"><fmt:message key="label.wagon.addNew"/></a>
        &nbsp;&nbsp;&nbsp;
        <a href="/searchCSV"><fmt:message key="label.wagon.search"/></a>

    </h2>
    </div>
    <div align="center">
        <h1><fmt:message key="label.wagon.list"/></h1>

        <c:if test="${!empty listWagon}">
            <table class="table table-striped">

                <tr>
                    <th width="120"><fmt:message key="label.number.wagon"/></th>
                    <th width="120" align="center"><fmt:message key="label.type"/></th>
                    <th width="120"><fmt:message key="label.number.of.passenger"/></th>
                    <th width="120"><fmt:message key="label.amount.of.luggage"/></th>
                    <th width="120"><fmt:message key="label.max.passengers"/></th>
                    <th width="120"><fmt:message key="label.free.passengers"/></th>
                </tr>

                <c:forEach items="${listWagon}" var="wagon">
                    <tr>
                        <td align="center">${wagon.id}</td>
                        <td align="center">${wagon.type}</td>
                        <td align="center">${wagon.numberOfPassengers}</td>
                        <td align="center">${wagon.amountOfLuggage}</td>
                        <td align="center">${wagon.maxPassengers}</td>
                        <td align="center">${wagon.maxPassengers - wagon.numberOfPassengers}</td>
                        <td>
                            <a href="/editCSV?id=<c:out value='${wagon.id}' />"><fmt:message key="label.button.edit"/></a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/deleteCSV?id=<c:out value='${wagon.id}' />"><fmt:message key="label.button.delete"/></a>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </c:if>
    </div>
    <div align="right">
        <p> <fmt:message key="label.total.passengers"/> : <mark> ${TotalOfPassengers} </mark></p>
        <p> <fmt:message key="label.total.luggage"/> : <mark> ${TotalOfLuggage} </mark></p>
    </div>
</div>

</body>
</html>

