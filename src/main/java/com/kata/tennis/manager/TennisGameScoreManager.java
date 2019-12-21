package com.kata.tennis.manager;

import org.apache.commons.lang3.StringUtils;

import com.kata.tennis.model.TennisPlayer;

public class TennisGameScoreManager implements ITennisGameScoreManager {
	private final TennisPlayer player1;
	private int player1GameScore;
	private final TennisPlayer player2;
	private int player2GameScore;

	public TennisGameScoreManager(TennisPlayer player1, TennisPlayer player2) {
		super();
		this.player1 = player1;
		this.player2 = player2;
	}

	public String getScore() {
		if (hasWinner()) {
			final TennisPlayer winner = getWinner();
			winner.isTheWinner();
			resetGameScores();
			return StringUtils.defaultString(translateScore(this.player1GameScore) + " - " + translateScore(this.player2GameScore)) + "\n" + winner.getName() + " win the game";
		}

		return StringUtils.defaultString(translateScore(this.player1GameScore) + " - " + translateScore(this.player2GameScore));
	}

	public void player1Win1Point() {
		this.player1GameScore++;
	}

	public void player2Win1Point() {
		this.player2GameScore++;
	}

	private boolean hasWinner() {

		return (this.player1GameScore >= 4 || this.player2GameScore >= 4);
	}

	private TennisPlayer getWinner() {
		if (this.player1GameScore > this.player2GameScore) {
			return this.player1;
		}

		return this.player2;
	}

	private void resetGameScores() {
		this.player1GameScore = 0;
		this.player2GameScore = 0;
	}

	private int translateScore(int score) {
		switch (score) {
		case 3:
			return 40;
		case 2:
			return 30;
		case 1:
			return 15;
		case 0:
			return 0;
		}
		throw new IllegalArgumentException("Illegal score: " + score);
	}

}
