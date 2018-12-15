<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<json:object>
  <json:array name="games" var="game" items="${gamesList}">
    <json:object>
      <json:property name="id" value="${game.id}"/>
      <json:property name="version" value="${game.version}"/>
      <json:array name="players">
      	  <json:property value="${game.challenger}"/>
      	  <json:property value="${game.challengee}"/>
  	  </json:array>
    </json:object>
  </json:array>
</json:object>



