<%@ page import="ua.sustav.databasecv.web.HtmlUtil" %>
<%@ page import="ua.sustav.databasecv.storage.XmlStorage" %>
<%@ page import="ua.sustav.databasecv.model.ContactType" %>
<%@ page import="ua.sustav.databasecv.storage.MapStorage" %>
<%@ page import="java.util.Collection" %>
<%@ page import="ua.sustav.databasecv.model.Resume" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml" %>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>--%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Список резюме</title>
</head>
<body>

<section>

    <table>

        <tr>
            <td colspan="5" style="text-align: right"><a href="resume/action=create">Добавить резюме</a></td>
        </tr>

        <tr>
            <td>

                <table border="1" cellpadding="8" cellspacing="0">

                    <tr>
                       <th>Имя:</th>
                       <th>Адрес:</th>
                       <th>Email:</th>
                       <th>&nbsp;</th>
                       <th>&nbsp;</th>
                       <%--<th><%=HtmlUtil.EMPTY_TD%></th>--%>
                    </tr>

                    <%
                        XmlStorage storage = new XmlStorage("C:\\Users\\SUSTAVOV\\Documents\\myProject\\databasecv\\file_storage");
                        Collection<Resume> resumeList = storage.getAllSorted();
                        request.setAttribute("resumeList", resumeList);
                    %>

                    <c:forEach items="${resumeList}" var="resume">
                        <jsp:useBean id="resume" type="ua.sustav.databasecv.model.Resume"/>
                        <tr>
                            <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                            <td>${resume.location}</td>
                            <td><%=HtmlUtil.getContact(resume, ContactType.EMAIL)%></td>
                            <td><a href="resume?uuid=${resume.uuid}&action=delete"><img src="img/del.png"></a></td>
                            <td><a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/edit.png"></a></td>
                        </tr>
                    </c:forEach>

                </table>

            </td>
        </tr>

    </table>

</section>

</body>
</html>
