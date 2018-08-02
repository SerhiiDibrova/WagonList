<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="ua.training.model.WagonComfortType" %>
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
            <li class="breadcrumb-item"><a href="/wagonRandomList"><fmt:message key="label.wagon.list.random"/></a></li>
            <li class="breadcrumb-item"><a href="/wagonJDBCList"><fmt:message key="label.wagon.list.jdbc"/></a></li>
            <li class="breadcrumb-item"><a href="/wagonFileList"><fmt:message key="label.wagon.list.file"/></a></li>

        </ol>
    </nav>
    <div align="center">

        <form action="searchWagonCSV" method="post">

            <table class="table table-striped" border="1" cellpadding="5">

                <h2>

                    <fmt:message key="label.wagon.search"/>

                </h2>

                <tr>
                    <th><fmt:message key="label.quantity.passanger"/>:
                    </th>
                    <td>
                        <input type="text" class="form-control" name="id" size="45" placeholder=
                                "<fmt:message key="label.quantity.passanger"/>"
                               value="<c:out value="0" />"/>
                    </td>
                </tr>

                <tr>
                    <td colspan="2" align="center">
                        <button type="submit" class="btn btn-primary"> Search</button>
                    </td>
                </tr>
            </table>

        </form>

    </div>
</div>
</body>
</html>
