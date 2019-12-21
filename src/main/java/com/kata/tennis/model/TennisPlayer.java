package com.kata.tennis.model;

public class TennisPlayer {
	private String name;
	private String country;
	private int numberOfVictories;

	public TennisPlayer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getNumberOfVictories() {
		return numberOfVictories;
	}

	public void setNumberOfVictories(int numberOfVictories) {
		this.numberOfVictories = numberOfVictories;
	}

	public void isTheWinner() {
		this.numberOfVictories++;

	}
}
