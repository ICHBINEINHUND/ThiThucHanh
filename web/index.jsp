<%@page import="clienttttt.Saaa"%>
<%@page import="clienttttt.Saaa_Service"%>
<%@page import="clientttttt.*"%>
<%
    Saaa_Service service = new Saaa_Service();
    Saaa port = service.getSaaaPort();
    String result = port.hello("World");
%>
<html>
<body>
    <h1>K?t qu?: <%= result %></h1>
</body>
</html>