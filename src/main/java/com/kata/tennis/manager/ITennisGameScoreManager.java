package com.kata.tennis.manager;

public interface ITennisGameScoreManager {

	public String getScore() ;

	public void player1Win1Point();

	public void player2Win1Point();

	public String setGameScores(int player1GameScore, int player2GameScore);
}
