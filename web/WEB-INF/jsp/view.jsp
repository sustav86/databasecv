<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ua.sustav.databasecv.web.HtmlUtil" %>
<%@ page import="ua.sustav.databasecv.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:useBean id="resume" type="ua.sustav.databasecv.model.Resume" scope="request"/>
    <title>Обзор резюме: ${resume.fullName}</title>
</head>
<body>
<h2>Обзор резюме: ${resume.fullName}</h2>
<section>
    <p>
        <c:if test="${not empty resume.location}">
            Проживание: ${resume.location}
        </c:if>
    </p>
    <p>
        <c:if test="${not empty resume.homePage}">
            Site: ${resume.homePage}
        </c:if>
    </p>
    <p>
        <c:forEach var="type" items="${resume.contacts.keySet()}">
            <jsp:useBean id="type" type="ua.sustav.databasecv.model.ContactType"/>
            <%=HtmlUtil.getContact(resume, type)%><br>
        </c:forEach>
    </p>

    <table>

        <c:forEach var="entry" items="${resume.sections.entrySet()}">
            <jsp:useBean id="entry" type="java.util.Map.Entry"/>
            <%
                SectionType sectionType = (SectionType) entry.getKey();
                Section section = (Section) entry.getValue();
            %>

            <tr>
                <td><h2><a name="<%=sectionType.name()%>"><%=sectionType.getTitle()%>
                </a></h2></td>
                <%
                    switch (sectionType) {
                        case OBJECTIVE:
                %>
                <td><h2><%=((TextSection) entry.getValue()).getTitle()%>
                </h2></td>
            </tr>
            <%
                    break;
                case ACHIEVEMENTS:
                case QUALIFICATION:
            %>
            <td>
                <%--<c:forEach var="item" items="<%=((MultiTextSection) entry.getValue()).getValues()%>">--%>
                    <%--<li>${item}</li>--%>
                <%--</c:forEach>--%>
            </td>
            <tr>
                <%
                        break;
                    case EXPIRIENCE:
                    case EDUCATION:
                %>
            </tr>
            <c:forEach var="org" items="<%=((OrganizationSection) entry.getValue()).getValues()%>">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${empty org.link.url}">
                                ${org.link.name}
                            </c:when>
                            <c:otherwise>
                                <a href="${org.link.url}">${org.link.name}</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <c:forEach var="period" items="${org.periods}">
                    <jsp:useBean id="period" type="ua.sustav.databasecv.model.Organization.Period"/>
                    <tr>
                        <td><b>${period.position}</b><br>${period.content}</td>
                    </tr>
                </c:forEach>
            </c:forEach>
            <%
                }
            %>
        </c:forEach>
    </table>
</section>
<button onclick="window.history.back()">OK</button>
</body>
</html>
