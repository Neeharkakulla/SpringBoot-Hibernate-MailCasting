<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<h2>Error Page</h2>

<p>Something went wrong please head back to home page</p>
<p>Status code : ${error.status }</p>
<p>Message : ${error.message }</p>
<p>TimeStamp : ${error.dateAndTime } </p>
<a href="index">click here to home</a>
</center>
</body>
</html>