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

        <h2>
            <a href="/newWagon"><fmt:message key="label.wagon.addNew"/></a>
            &nbsp;&nbsp;&nbsp;
            <a href="/listWagons"><fmt:message key="label.wagon.list"/></a>

        </h2>
    </div>
    <div align="center">

        <c:if test="${wagon != null}">
        <form action="update" method="post">
            </c:if>
            <c:if test="${wagon == null}">
            <form action="insert" method="post">
                </c:if>

                <table class="table table-striped" border="1" cellpadding="5">

                    <h2>
                        <c:if test="${wagon != null}">
                            <fmt:message key="label.wagon.edit"/>
                        </c:if>
                        <c:if test="${wagon == null}">
                            <fmt:message key="label.wagon.addNew"/>
                        </c:if>
                    </h2>

                    <tr>
                        <th><fmt:message key="label.number.wagon"/>:
                        </th>
                        <td>
                            <input type="text" class="form-control" name="id" size="45" placeholder=
                                "<fmt:message key="label.number.wagon"/>"
                                   value="<c:out value='${wagon.id}' />"/>
                        </td>
                    </tr>
                    <tr>

                        <th><fmt:message key="label.type"/>:</th>
                        <td>
                            <select class="form-control" name="type">
                                <c:forEach items="<%=WagonComfortType.values()%>" var="type">
                                    <option>${type.name() }</option>
                                </c:forEach>
                            </select>

                        </td>
                    </tr>
                    <tr>
                        <th><fmt:message key="label.number.of.passenger"/>:</th>
                        <td>
                            <input type="text" class="form-control" name="numberOfPassengers" size="45" placeholder=
                                    "<fmt:message key="label.number.of.passenger"/> "
                                   value="<c:out value='${wagon.numberOfPassengers}' />"/>
                        </td>

                    </tr>
                    <tr>
                        <th><fmt:message key="label.amount.of.luggage"/>:</th>
                        <td>
                            <input type="text" class="form-control" name="amountOfLuggage" size="45" placeholder=
                                    "<fmt:message key="label.amount.of.luggage"/>"
                                   value="<c:out value='${wagon.amountOfLuggage}' />"/>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="2" align="center">
                            <button type="submit" class="btn btn-primary"> Save</button>
                        </td>
                    </tr>
                </table>

            </form>
        </form>
    </div>
</div>
</body>
</html>