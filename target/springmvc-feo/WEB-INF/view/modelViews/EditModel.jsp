<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <form:form modelAttribute="model" action="/springmvc-feo/addModel" method="post">
     <p>
        <label for="id">id:</label>
        <form:input id="id" path="id" />
     </p>
     <p>
        <label for="name">name:</label>
        <form:input id="name" path="name" />
      </p>
     <p>
        <label for="description">description:</label>
        <form:input id="description" path="description" />
     </p>
     <p>
        <input id="submit" type="submit" value="Edit" />
     </p>
  </form:form>
</body>
</html>