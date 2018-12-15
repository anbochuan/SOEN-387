<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<json:object>
  <json:object name="deck">
    <json:property name="id" value="${deckId}"></json:property>
    <json:array name="cards" var="card" items="${cards}">
      <json:object>
        <json:property name="t" value="${card.cardType}"/>
        <json:property name="n" value="${card.cardName}"/>
      </json:object>
    </json:array>
  </json:object>
</json:object>