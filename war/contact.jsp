<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set var="pageTitle" scope="action" ><s:text name="contact.title"/></s:set>

<html>

<head></head>

<body>

	<h1><s:text name="contact.title" /></h1>
	
	<s:actionerror/>
	<s:actionmessage/>
	
	<s:form action="contact">
	
		<s:if test="%{senderEmail == null}">
			<s:textfield key="senderEmail" required="true"></s:textfield>
		</s:if>
		<s:textarea key="message"></s:textarea>
		<s:submit key="global.send"></s:submit>
	</s:form>
	
</body>

</html>