<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Transport List</title>
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
            <li class="breadcrumb-item active" aria-current="page"><fmt:message key="label.home"/></li>
            <li class="breadcrumb-item"><a href="/wagonRandomList"><fmt:message key="label.wagon.list.random"/></a></li>
            <li class="breadcrumb-item"><a href="/wagonJDBCList"><fmt:message key="label.wagon.list.jdbc"/></a></li>
            <li class="breadcrumb-item"><a href="/wagonFileList"><fmt:message key="label.wagon.list.file"/></a></li>

        </ol>
    </nav>
<div class="jumbotron">
    <h1 class="display-4">Hello, world!</h1>
    <hr class="my-4">
</div>

</div>
</body>
</html>
