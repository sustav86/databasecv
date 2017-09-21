<%@ page import="ua.sustav.databasecv.web.HtmlUtil" %>
<%@ page import="ua.sustav.databasecv.model.ContactType" %><%--
  Created by IntelliJ IDEA.
  User: SUSTAVOV
  Date: 21.09.2017
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:useBean id="resume" type="ua.sustav.databasecv.model.Resume" scope="request"/>
    <title>Обзор резюме: ${resume.fullName}</title>
</head>
<body>
<h2>Обзор резюме: ${resume.fullName}</h2>
<section>
    <table>
        <tr>
            <td>${resume.fullName}</td>
        </tr>
        <tr>
            <td>${resume.location}</td>
        </tr>
        <tr>
            <td>${resume.homePage}</td>
        </tr>
        <%--<tr>${resume.location}</tr>--%>
        <%--<tr>${resume.homePage}</tr>--%>
        <%--<c:forEach items="${resumeList}" var="resume">--%>
            <%--<jsp:useBean id="resume" type="ua.sustav.databasecv.model.Resume"/>--%>
            <%--<tr>--%>
                <%--<td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>--%>
                <%--<td>${resume.location}</td>--%>
                <%--<td><%=HtmlUtil.getContact(resume, ContactType.EMAIL)%></td>--%>
                <%--<td><a href="resume?uuid=${resume.uuid}&action=delete"><img src="img/del.png"></a></td>--%>
                <%--<td><a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/edit.png"></a></td>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>
    </table>
</section>
<button onclick="window.history.back()">OK</button>
</body>
</html>
