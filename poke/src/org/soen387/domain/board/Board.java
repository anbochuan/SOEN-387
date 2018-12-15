package org.soen387.domain.board;

import java.util.List;

import org.soen387.domain.card.Card;
import org.soen387.domain.game.IGame;

public class Board {

	private IGame game;
	private Long current;
	private String statusChallenger;
	private String statusChallengee;
	private int handSizeChallenger;
	private int deckSizeChallenger;
	private int discardSizeChallenger;
	private int handSizeChallengee;
	private int deckSizeChallengee;
	private int discardSizeChallengee;	
	private List<Card> benchChallenger;
	private List<Card> benchChallengee;
	public Board(IGame game, Long current, String statusChallenger, String statusChallengee, int handSizeChallenger,
			int cardSizeChallenger, int discardSizeChallenger, int handSizeChallengee, int cardSizeChallengee,
			int discardSizeChallengee, List<Card> benchChallenger, List<Card> benchChallengee) {
		super();
		this.game = game;
		this.current = current;
		this.statusChallenger = statusChallenger;
		this.statusChallengee = statusChallengee;
		this.handSizeChallenger = handSizeChallenger;
		this.deckSizeChallenger = cardSizeChallenger;
		this.discardSizeChallenger = discardSizeChallenger;
		this.handSizeChallengee = handSizeChallengee;
		this.deckSizeChallengee = cardSizeChallengee;
		this.discardSizeChallengee = discardSizeChallengee;
		this.benchChallenger = benchChallenger;
		this.benchChallengee = benchChallengee;
	}
	public IGame getGame() {
		return game;
	}
	public void setGame(IGame game) {
		this.game = game;
	}
	public Long getCurrent() {
		return current;
	}
	public void setCurrent(Long current) {
		this.current = current;
	}
	public String getStatusChallenger() {
		return statusChallenger;
	}
	public void setStatusChallenger(String statusChallenger) {
		this.statusChallenger = statusChallenger;
	}
	public String getStatusChallengee() {
		return statusChallengee;
	}
	public void setStatusChallengee(String statusChallengee) {
		this.statusChallengee = statusChallengee;
	}
	public int getHandSizeChallenger() {
		return handSizeChallenger;
	}
	public void setHandSizeChallenger(int handSizeChallenger) {
		this.handSizeChallenger = handSizeChallenger;
	}
	public int getDeckSizeChallenger() {
		return deckSizeChallenger;
	}
	public void setDeckSizeChallenger(int cardSizeChallenger) {
		this.deckSizeChallenger = cardSizeChallenger;
	}
	public int getDiscardSizeChallenger() {
		return discardSizeChallenger;
	}
	public void setDiscardSizeChallenger(int discardSizeChallenger) {
		this.discardSizeChallenger = discardSizeChallenger;
	}
	public int getHandSizeChallengee() {
		return handSizeChallengee;
	}
	public void setHandSizeChallengee(int handSizeChallengee) {
		this.handSizeChallengee = handSizeChallengee;
	}
	public int getDeckSizeChallengee() {
		return deckSizeChallengee;
	}
	public void setDeckSizeChallengee(int cardSizeChallengee) {
		this.deckSizeChallengee = cardSizeChallengee;
	}
	public int getDiscardSizeChallengee() {
		return discardSizeChallengee;
	}
	public void setDiscardSizeChallengee(int discardSizeChallengee) {
		this.discardSizeChallengee = discardSizeChallengee;
	}
	public List<Card> getBenchChallenger() {
		return benchChallenger;
	}
	public void setBenchChallenger(List<Card> benchChallenger) {
		this.benchChallenger = benchChallenger;
	}
	public List<Card> getBenchChallengee() {
		return benchChallengee;
	}
	public void setBenchChallengee(List<Card> benchChallengee) {
		this.benchChallengee = benchChallengee;
	}
	
	
	
	

}
