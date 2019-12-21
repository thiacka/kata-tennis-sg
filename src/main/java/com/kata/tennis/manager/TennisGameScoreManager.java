package com.kata.tennis.manager;

import com.kata.tennis.model.TennisPlayer;
import com.kata.tennis.utils.ScoreUtils;

public class TennisGameScoreManager implements ITennisGameScoreManager {
	private final TennisPlayer player1;
	private int player1GameScore;
	private int player1SetScore;
	private int player1TieBreakScore;
	private final TennisPlayer player2;
	private int player2GameScore;
	private int player2SetScore;
	private int player2TieBreakScore;

	public TennisGameScoreManager(TennisPlayer player1, TennisPlayer player2) {
		super();
		this.player1 = player1;
		this.player2 = player2;
	}

	public String getScore() {
		if (hasWinner()) {
			final TennisPlayer winner = getWinner();

			if (hasSetWinner()) {
				winner.isTheWinner();
				resetGameScores();

				return ScoreUtils.formatScores(ScoreUtils.translateScore(this.player1GameScore), ScoreUtils.translateScore(this.player2GameScore),
						this.player1SetScore, this.player2SetScore) + "\n" + winner.getName() + " wins the set";
			}

			if(hasTieBreakWinner()) {
				updateSetScores();
				resetGameScores();
				resetTieBreakScores();

				return ScoreUtils.formatTieBreakScores(ScoreUtils.translateScore(this.player1GameScore), ScoreUtils.translateScore(this.player2GameScore),
						this.player1SetScore, this.player2SetScore, this.player1TieBreakScore, this.player2TieBreakScore) + "\n" + winner.getName() + " wins the set";
			}

			updateSetScores();
			resetGameScores();

			return ScoreUtils.formatScores(ScoreUtils.translateScore(this.player1GameScore), ScoreUtils.translateScore(this.player2GameScore),
					this.player1SetScore, this.player2SetScore) + "\n" + winner.getName() + " wins the game";
		}

		if(IsTieBreak()) {
			return ScoreUtils.formatTieBreakScores(ScoreUtils.translateScore(this.player1GameScore), ScoreUtils.translateScore(this.player2GameScore),
					this.player1SetScore, this.player2SetScore, this.player1TieBreakScore, this.player2TieBreakScore);
		}

		if (isDEUCE()) {
			return ScoreUtils.formatScores("DEUCE", "DEUCE", this.player1SetScore, this.player2SetScore);
		}

		if (IsADV()) {
			if (this.player1GameScore > this.player2GameScore) {
				return ScoreUtils.formatScores("ADV", "40", this.player1SetScore, this.player2SetScore);
			}

			return ScoreUtils.formatScores("40", "ADV", this.player1SetScore, this.player2SetScore);
		}

		return ScoreUtils.formatScores(ScoreUtils.translateScore(this.player1GameScore), ScoreUtils.translateScore(this.player2GameScore),
				this.player1SetScore, this.player2SetScore);
	}

	public void player1Win1Point() {
		if(IsTieBreakSetScore()) {
			this.player1TieBreakScore++;
		}else {
			this.player1GameScore++;
		}
	}

	public void player2Win1Point() {
		if(IsTieBreakSetScore()) {
			this.player2TieBreakScore++;
		}else {
			this.player2GameScore++;
		}
	}

	public String setGameScores(int player1GameScore, int player2GameScore) {
		this.player1GameScore = player1GameScore;
		this.player2GameScore = player2GameScore;

		return getScore();
	}

	public String setSetScores(int player1SetScore, int player2SetScore) {
		this.player1SetScore = player1SetScore;
		this.player2SetScore = player2SetScore;
		resetGameScores();

		return getScore();
	}

	private boolean hasWinner() {
		return hasGameWinner() || hasSetWinner() || hasTieBreakWinner();
	}

	private boolean hasSetWinner() {
		return (this.player1SetScore == 6 && this.player2SetScore <= 4)
				|| (this.player2SetScore == 6 && this.player1SetScore <= 4)
				|| this.player1SetScore == 7
				|| this.player2SetScore == 7;
	}

	private boolean hasGameWinner() {
		return (this.player1GameScore >= 4 && this.player1GameScore >= this.player2GameScore + 2)
				|| (this.player2GameScore >= 4 && this.player2GameScore >= this.player1GameScore + 2);
	}

	private boolean IsADV() {
		return (this.player1GameScore >= 4 && this.player1GameScore == this.player2GameScore + 1)
				|| (this.player2GameScore >= 4 && this.player2GameScore == this.player1GameScore + 1);
	}

	private boolean isDEUCE() {
		return this.player1GameScore >= 4 && this.player1GameScore == this.player2GameScore;
	}

	private boolean hasTieBreakWinner() {
		return  IsTieBreak() && (this.player1TieBreakScore >= 7 || this.player2TieBreakScore >= 7);
	}

	private boolean IsTieBreak() {
		return IsTieBreakSetScore() && (this.player1TieBreakScore > 0 || this.player2TieBreakScore > 0) ;
	}

	private boolean IsTieBreakSetScore() {
		return this.player1SetScore == 6 &&  this.player1SetScore == this.player2SetScore;
	}

	private TennisPlayer getWinner() {
		if ((this.player1GameScore > this.player2GameScore) || (this.player1SetScore > this.player2SetScore) || (this.player1TieBreakScore > this.player2TieBreakScore)) {
			return this.player1;
		}

		return this.player2;
	}

	private void resetGameScores() {
		this.player1GameScore = 0;
		this.player2GameScore = 0;
	}

	private void resetTieBreakScores() {
		this.player1TieBreakScore = 0;
		this.player2TieBreakScore = 0;
	}

	private void updateSetScores() {
		if ((player1GameScore > player2GameScore)|| (IsTieBreak() && this.player1TieBreakScore > this.player2TieBreakScore)) {
			this.player1SetScore++;
		} else {
			this.player2SetScore++;
		}

	}

}
