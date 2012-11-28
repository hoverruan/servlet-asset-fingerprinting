# Asset Fingerprinting for Java Servlet

Add fingerprinting supports for static resource in java servlet

## How to use

### Import to your project using maven

	<dependency>
	    <groupId>com.github.hoverruan</groupId>
        <artifactId>servlet-asset-fingerprinting</artifactId>
        <version>0.1</version>
    </dependency>

### Config AssetServlet in web.xml

	<servlet>
		<servlet-name>assetServlet</servlet-name>
		<servlet-class>com.github.hoverruan.assetfingerprinting.AssetServlet</servlet-class>
	</servlet>

	<servlet-mapping>
		<servlet-name>assetServlet</servlet-name>
		<url-pattern>/asset/*</url-pattern>
	</servlet-mapping>

### Using the asset tag in your JSP files

    <%@ taglib prefix="asset" uri="https://github.com/hoverruan/servlet-asset-fingerprinting" %>
    <%@ page isELIgnored="false" %>

    <asset:resource path="/css/example.css" var="example_css"/>
    <link rel="stylesheet" href="${example_css}">

    <asset:resource path="/js/example.js" var="example_js"/>
    <script type="javascript" src="${example_js}"></script>

    <%-- using resource function in EL --%>
    <script type="javascript" src="${asset:resource(pageContext, '/js/another.js')}"></script>

### Example

You can check out example project to getting more details.