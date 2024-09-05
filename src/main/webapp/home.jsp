<%@ page import="java.util.Random" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Mohsen
  Date: 9/5/2024
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>


<h1>This is Heading 1</h1>
<h2>This is Heading 2</h2>
<p>This is first paragraph</p>
<p>this is my name ${myName}</p>

<%

    Random random = new Random();
    if (random.nextInt() % 2 == 0) {
        out.print("<p>even number</p>");
    } else {
        out.print("<p>odd number</p>");
    }

    List<String> names = (List<String>) request.getAttribute("names");
    if (names != null && !names.isEmpty()) {
        out.print("<ul>");

        for (String name : names) {
            out.print("<li>");
            out.print(name);
            out.print("</li>");
        }

        out.print("</ul>");

    }

%>

<jsp:include page="footer.jsp"/>

