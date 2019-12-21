package com.kata.tennis.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.kata.tennis.model.TennisPlayer;

public class TennisGameScoreManagerTest {
	TennisPlayer nadal = new TennisPlayer("Player 1");
	TennisPlayer thiem = new TennisPlayer("Player 2");

	ITennisGameScoreManager scoreManager = new TennisGameScoreManager(nadal, thiem);

	@DisplayName("Start the game")
	@Test
	public void testStartTheGameShouldReturn0_0()
	{
		final String score = scoreManager.getScore();

		assertEquals("Game Score -> 0 - 0 | Set Score -> 0 - 0", score);
	}

	@DisplayName("Player 1 wins 1 point And Player 2 win 0 point")
	@Test
	public void testPlayer1WinsFirstBall()
	{
		scoreManager.player1Win1Point();

		final String score = scoreManager.getScore();

		assertEquals("Game Score -> 15 - 0 | Set Score -> 0 - 0", score);
	}

	@DisplayName("Player 1 wins 2 points And Player 2 win 0 point")
	@Test
	public void testPlayer1WinsSecondetBall()
	{
		scoreManager.player1Win1Point();
		scoreManager.player1Win1Point();

		final String score = scoreManager.getScore();

		assertEquals("Game Score -> 30 - 0 | Set Score -> 0 - 0", score);
	}

	@DisplayName("Player 1 wins 2 points and Player 2 wins 1 point")
	@Test
	public void testPlayer1WinsSecondetBallAndPlayer2WinsOneBall()
	{
		scoreManager.player1Win1Point();
		scoreManager.player1Win1Point();
		scoreManager.player2Win1Point();

		final String score = scoreManager.getScore();

		assertEquals("Game Score -> 30 - 15 | Set Score -> 0 - 0", score);
	}

	@DisplayName("Player 1 wins 3 points and Player 2 wins 1 point")
	@Test
	public void testPlayer1Wins3tBallAndPlayer2Wins1Ball()
	{
		scoreManager.player1Win1Point();
		scoreManager.player1Win1Point();
		scoreManager.player1Win1Point();
		scoreManager.player2Win1Point();

		final String score = scoreManager.getScore();

		assertEquals("Game Score -> 40 - 15 | Set Score -> 0 - 0", score);
	}

	@DisplayName("Player 1 wins 3 points and Player 2 wins 2 points")
	@Test
	public void testPlayer1Wins3tBallAndPlayer2Wins2Ball()
	{
		scoreManager.player1Win1Point();
		scoreManager.player1Win1Point();
		scoreManager.player1Win1Point();
		scoreManager.player2Win1Point();
		scoreManager.player2Win1Point();

		final String score = scoreManager.getScore();

		assertEquals("Game Score -> 40 - 30 | Set Score -> 0 - 0", score);
	}

	@DisplayName("Player 1 wins 3 points and Player 2 wins 3 points")
	@Test
	public void testPlayer1Wins3tBallAndPlayer2Wins3Ball()
	{
		scoreManager.player1Win1Point();
		scoreManager.player1Win1Point();
		scoreManager.player1Win1Point();
		scoreManager.player2Win1Point();
		scoreManager.player2Win1Point();
		scoreManager.player2Win1Point();

		final String score = scoreManager.getScore();

		assertEquals("Game Score -> 40 - 40 | Set Score -> 0 - 0", score);
	}

	@DisplayName("ADV rule : Player 1 wins 3 points and Player 2 wins 4 points and get ADV")
	@Test
	public void testPlayer1Wins2tBallAndPlayer2Wins4Ball()
	{
		scoreManager.player1Win1Point();
		scoreManager.player1Win1Point();
		scoreManager.player1Win1Point();
		scoreManager.player2Win1Point();
		scoreManager.player2Win1Point();
		scoreManager.player2Win1Point();
		scoreManager.player2Win1Point();

		final String score = scoreManager.getScore();

		assertEquals("Game Score -> 40 - ADV | Set Score -> 0 - 0", score);
	}

	@DisplayName("DEUCE rule: Player 1 wins 4 points and Player 2 wins 4 point")
	@Test
	public void testPlayer1Wins4BallAndPlayer2Wins4Ball()
	{
		scoreManager.player1Win1Point();
		scoreManager.player1Win1Point();
		scoreManager.player2Win1Point();
		scoreManager.player1Win1Point();
		scoreManager.player2Win1Point();
		scoreManager.player2Win1Point();
		scoreManager.player2Win1Point();
		scoreManager.player1Win1Point();

		final String score = scoreManager.getScore();

		assertEquals("Game Score -> DEUCE - DEUCE | Set Score -> 0 - 0", score);
	}

	@DisplayName("Player 1 wins 5 points and get ADV and Player 2 wins 4 point")
	@Test
	public void testPlayer1Wins5BallAndPlayer2Wins4Ball()
	{
		scoreManager.player1Win1Point();
		scoreManager.player1Win1Point();
		scoreManager.player2Win1Point();
		scoreManager.player1Win1Point();
		scoreManager.player2Win1Point();
		scoreManager.player2Win1Point();
		scoreManager.player2Win1Point();
		scoreManager.player1Win1Point();
		scoreManager.player1Win1Point();

		final String score = scoreManager.getScore();

		assertEquals("Game Score -> ADV - 40 | Set Score -> 0 - 0", score);
	}

	@DisplayName("Player 1 wins 6 points and Player 2 wins 4 point")
	@Test
	public void testPlayer1Wins6BallAndPlayer2Wins4Ball()
	{
		scoreManager.player1Win1Point();
		scoreManager.player1Win1Point();
		scoreManager.player2Win1Point();
		scoreManager.player1Win1Point();
		scoreManager.player2Win1Point();
		scoreManager.player2Win1Point();
		scoreManager.player2Win1Point();
		scoreManager.player1Win1Point();
		scoreManager.player1Win1Point();
		scoreManager.player1Win1Point();

		final String score = scoreManager.getScore();

		assertEquals("Game Score -> 0 - 0 | Set Score -> 1 - 0\nPlayer 1 wins the game", score);
	}

	@DisplayName("Player 1 wins 1 Set and Player 2 wins 1 Set")
	@Test
	public void testPlayer1Wins1SetAndPlayer2Wins1Set()
	{

		scoreManager.setGameScores(6, 3);
		scoreManager.setGameScores(5, 7);

		final String score = scoreManager.getScore();

		assertEquals("Game Score -> 0 - 0 | Set Score -> 1 - 1", score);
	}

	@DisplayName("Player 1 wins 1 Set and Player 2 wins 2 Set")
	@Test
	public void testPlayer1Wins1SetAndPlayer2Wins2Set()
	{

		scoreManager.setGameScores(6, 3);
		scoreManager.setGameScores(5, 7);
		scoreManager.setGameScores(3, 6);

		final String score = scoreManager.getScore();

		assertEquals("Game Score -> 0 - 0 | Set Score -> 1 - 2", score);
	}

	@DisplayName("Player 1 wins 1 Set and Player 2 wins 3 Set")
	@Test
	public void testPlayer1Wins1SetAndPlayer2Wins3Set()
	{

		scoreManager.setGameScores(6, 3);
		scoreManager.setGameScores(5, 7);
		scoreManager.setGameScores(3, 6);
		scoreManager.setGameScores(1, 6);

		final String score = scoreManager.getScore();

		assertEquals("Game Score -> 0 - 0 | Set Score -> 1 - 3", score);
	}

	@DisplayName("Player 1 wins 1 Set and Player 2 wins 4 Set")
	@Test
	public void testPlayer1Wins1SetAndPlayer2Wins4Set()
	{

		scoreManager.setGameScores(6, 3);
		scoreManager.setGameScores(5, 7);
		scoreManager.setGameScores(3, 6);
		scoreManager.setGameScores(1, 6);
		scoreManager.setGameScores(5, 7);

		final String score = scoreManager.getScore();

		assertEquals("Game Score -> 0 - 0 | Set Score -> 1 - 4", score);
	}
	@DisplayName("Player 1 wins 1 Set and Player 2 wins 4 Set")
	@Test
	public void testPlayer1Wins1SetAndPlayer2Wins5Set()
	{

		scoreManager.setGameScores(6, 3);
		scoreManager.setGameScores(5, 7);
		scoreManager.setGameScores(3, 6);
		scoreManager.setGameScores(1, 6);
		scoreManager.setGameScores(5, 7);
		scoreManager.setGameScores(5, 7);

		final String score = scoreManager.getScore();

		assertEquals("Game Score -> 0 - 0 | Set Score -> 1 - 5", score);
	}

	@DisplayName("Player 1 wins 1 Set and Player 2 wins 6 Set")
	@Test
	public void testPlayer1Wins1SetAndPlayer2Wins6Set()
	{

		scoreManager.setGameScores(6, 3);
		scoreManager.setGameScores(5, 7);
		scoreManager.setGameScores(3, 6);
		scoreManager.setGameScores(1, 6);
		scoreManager.setGameScores(5, 7);
		scoreManager.setGameScores(5, 7);
		scoreManager.setGameScores(1, 6);

		final String score = scoreManager.getScore();

		assertEquals("Game Score -> 0 - 0 | Set Score -> 1 - 6\nPlayer 2 wins the set", score);
	}
}
