<%@ page import="ua.sustav.databasecv.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:useBean id="resume" type="ua.sustav.databasecv.model.Resume" scope="request"/>
    <title>Реэюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>

<section>

    <form id="resume" method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="name" size="50" value="${resume.fullName}"></dd>
        </dl>

        <dl>
            <dt>Проживание:</dt>
            <dd><input type="text" name="location" size="50" value="${resume.location}"></dd>
        </dl>

        <dl>
            <dt>Домашняя страничка:</dt>
            <dd><input type="text" name="home_page" size="50" value="${resume.homePage}"></dd>
        </dl>

        <h2>Контакты:</h2>
        <p/>

        <c:forEach items="<%=ContactType.values()%>" var="type">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size="30" value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>

        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>

</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
