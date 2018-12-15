package org.poke.util;

import java.util.ArrayList;

public class Player {
	
	private String status;
	private int handsize;
	private int decksize;
	private int discardsize;
	private ArrayList<Long> bench;
	
	public Player(String status, int handsize, int decksize, int discardsize, ArrayList<Long> bench) {
		super();
		this.status = status;
		this.handsize = handsize;
		this.decksize = decksize;
		this.discardsize = discardsize;
		this.bench = bench;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getHandsize() {
		return handsize;
	}

	public void setHandsize(int handsize) {
		this.handsize = handsize;
	}

	public int getDecksize() {
		return decksize;
	}

	public void setDecksize(int decksize) {
		this.decksize = decksize;
	}

	public int getDiscardsize() {
		return discardsize;
	}

	public void setDiscardsize(int discardsize) {
		this.discardsize = discardsize;
	}

	public ArrayList<Long> getBench() {
		return bench;
	}

	public void setBench(ArrayList<Long> bench) {
		this.bench = bench;
	}

	

}
