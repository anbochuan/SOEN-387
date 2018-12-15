<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<json:object>
  <json:object name="board">
    <json:property name = "id" value="${board.id}"/>
    <json:array name="players" items="${board.players}"/>
    <json:array name="decks" items="${board.decks}"/>
    <json:object name ="play">
      <json:object name ="${board.players[0]}">
        <json:property name = "status" value="${board.challenger.status}"/>
        <json:property name = "handsize" value="${board.challenger.handsize}"/>
        <json:property name = "decksize" value="${board.challenger.decksize}"/>
        <json:property name = "discardsize" value="${board.challenger.discardsize}"/>
        <json:array name="bench" items="${board.challenger.bench}"/>
      </json:object>
      <json:object name ="${board.players[1]}">
        <json:property name = "status" value="${board.challengee.status}"/>
        <json:property name = "handsize" value="${board.challengee.handsize}"/>
        <json:property name = "decksize" value="${board.challengee.decksize}"/>
        <json:property name = "discardsize" value="${board.challengee.discardsize}"/>
        <json:array name="bench" items="${board.challengee.bench}"/>
      </json:object>
    </json:object>
  </json:object>
</json:object>