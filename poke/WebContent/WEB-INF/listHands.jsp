<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<json:object>
  <json:array name="hand" var="hand" items="${hands}">
      <json:property value="${hand.cardId}"/>
  </json:array>
</json:object>