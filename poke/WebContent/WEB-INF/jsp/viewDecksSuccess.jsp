<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<json:object>
 <json:array name="decks" var="deck" items="${decksList}">
     <json:property value="${deck.id}"/>
 </json:array>
</json:object>