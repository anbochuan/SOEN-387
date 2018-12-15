<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<json:object>
  <json:property name="status" value="${status}"></json:property>
  <json:array name="challenges" var="challenge" items="${challenges}">
    <json:object>
      <json:property name="id" value="${challenge.id}"/>
      <json:property name="challenger" value="${challenge.challenger}"/>
      <json:property name="challengee" value="${challenge.challengee}"/>
      <json:property name="status" value="${challenge.status}"/>
    </json:object>
  </json:array>
</json:object>