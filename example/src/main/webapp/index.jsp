<%@ taglib prefix="asset" uri="https://github.com/hoverruan/servlet-asset-fingerprinting" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <asset:resource path="/css/example.css" var="example_css"/>
    <link rel="stylesheet" href="${example_css}">
</head>
<body>
<div class="header">Header</div>
</body>
</html>
