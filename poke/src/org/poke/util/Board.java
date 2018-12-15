package org.poke.util;

import java.util.ArrayList;

public class Board {

	private long id;
	private ArrayList<Long> players;
	private ArrayList<Long> decks;
	private Player challenger;
	private Player challengee;
	public Board(long id, ArrayList<Long> players, ArrayList<Long> decks, Player challenger, Player challengee) {
		super();
		this.id = id;
		this.players = players;
		this.decks = decks;
		this.challenger = challenger;
		this.challengee = challengee;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public ArrayList<Long> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<Long> players) {
		this.players = players;
	}
	public ArrayList<Long> getDecks() {
		return decks;
	}
	public void setDecks(ArrayList<Long> decks) {
		this.decks = decks;
	}
	public Player getChallenger() {
		return challenger;
	}
	public void setChallenger(Player challenger) {
		this.challenger = challenger;
	}
	public Player getChallengee() {
		return challengee;
	}
	public void setChallengee(Player challengee) {
		this.challengee = challengee;
	}
	
	
}
