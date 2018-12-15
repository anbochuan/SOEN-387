<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<json:object>
	<json:array name="challenges" var="challenge" items="${challengesList}">
	  <json:object>
	    <json:property name="id" value="${challenge.id}"/>
	    <json:property name="version" value="${challenge.version}"/>
	    <json:property name="challenger" value="${challenge.challenger}"/>
	    <json:property name="challengee" value="${challenge.challengee}"/>
	    <json:property name="status" value="${challenge.status}"/>
	    <json:property name="deck" value="${challenge.deck}"/>
	  </json:object>
	</json:array>
</json:object>