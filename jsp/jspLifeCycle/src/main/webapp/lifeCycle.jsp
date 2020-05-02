<%--
  - Author(s): Nishant Raut (nishant30197@gmail.com)
  - Description: This example demonstrates life cycle methods of JSP
  --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP life cycle example</title>
    </head>
    <body>
		<%!
		// define a instance variable by defining it in a declaration tag
		int visitorCounter ;
		// override jspInit() method
		public void jspInit()
		{
		/*
		initialize counter by dummy value say 0. Ideally this should be grabbed
		from some data source but for the sake of example I am  hardcoding it
		*/
		visitorCounter=0;
		}
		// override destroy method which will set the value of counter back to 0
		public void jspDestroy()
		{
		visitorCounter=0;
		}
		%>
		<%
		/*
		increment the counter on each visit using scriplet. All code written inside scriplet goes under service()
		 so will be executed on each request
		*/
		visitorCounter = visitorCounter+1;
		%>
		<strong>
		Thanks for visiting the page. You are <%=visitorCounter %> visitor of the page.
		</strong>
    </body>
</html>