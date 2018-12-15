<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<json:object>
  <json:property name="status" value="${status}"></json:property>
  <json:array name="games" var="game" items="${games}">
    <json:object>
      <json:property name="id" value="${game.id}"/>
      <json:array name="players">
        <json:property value="${game.challengerId}"/>
        <json:property value="${game.challengeeId}"/>
      </json:array>
    </json:object>
  </json:array>
</json:object>