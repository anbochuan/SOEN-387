<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<json:object>
  <json:object name="game">
	  <json:property name="id" value="${board.game.id}"/>
	  <json:property name="version" value="${board.game.version}"/>
      <json:array name="players">
      	  <json:property value="${board.game.challenger}"/>
      	  <json:property value="${board.game.challengee}"/>
  	  </json:array>
	  <json:property name="current" value="${board.current}"/>
	  <json:array name="deck">
      	  <json:property value="${board.game.challengerDeck}"/>
      	  <json:property value="${board.game.challengeeDeck}"/>
  	  </json:array>
	  <json:object name="play">
		  <json:object name="${board.game.challenger}">
		  	<json:property name="status" value="${board.statusChallenger}"/>
		  	<json:property name="handsize" value="${board.handSizeChallenger}"/>
		  	<json:property name="decksize" value="${board.deckSizeChallenger}"/>
		  	<json:property name="discardsize" value="${board.discardSizeChallenger}"/>
		  	<json:array name="bench" var="b" items="${board.benchChallenger}">	
		  		<json:property value="${b.id}"/>
		  	</json:array>  	
		  </json:object>
		  <json:object name="${board.game.challengee}">
			  <json:property name="status" value="${board.statusChallengee}"/>
			  	<json:property name="handsize" value="${board.handSizeChallengee}"/>
			  	<json:property name="decksize" value="${board.deckSizeChallengee}"/>
			  	<json:property name="discardsize" value="${board.discardSizeChallengee}"/>
			  	<json:array name="bench" var="b" items="${board.benchChallengee}">	
			  		<json:object>
			  			<json:property name="id" value="${b.card.id}"/>
			  		</json:object>
			  	</json:array>
		  </json:object>
	  </json:object>  
  </json:object>
</json:object>