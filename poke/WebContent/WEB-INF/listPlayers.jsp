<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<json:object>
  <json:property name="status" value="${status}"></json:property>
  <json:array name="players" var="player" items="${players}">
    <json:object>
      <json:property name="id" value="${player.id}"/>
      <json:property name="user" value="${player.username}"/>
    </json:object>
  </json:array>
</json:object>