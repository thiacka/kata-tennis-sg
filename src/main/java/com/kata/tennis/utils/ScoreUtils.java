package com.kata.tennis.utils;

import org.apache.commons.lang3.StringUtils;

public class ScoreUtils {

	public static String formatScores(String player1GameScore, String player2GameScore, int player1SetScore,int player2SetScore) {

		return StringUtils.defaultString(
				"Game Score -> "
						+ player1GameScore + " - " + player2GameScore
						+ " | Set Score -> " + player1SetScore + " - " + player2SetScore);
	}

	public static String translateScore(int score) {
		switch (score) {
		case 3:
			return "40";
		case 2:
			return "30";
		case 1:
			return "15";
		case 0:
			return "0";
		}
		throw new IllegalArgumentException("Illegal score: " + score);
	}
}
