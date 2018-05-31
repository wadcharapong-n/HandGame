package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HandGameTest {

	@Test
	void TestInputPlayer1EqualsOO() {
		assertEquals(true, HandGame.validateInput("OO", false));
	}

	@Test
	void TestInputPlayer1EqualsCO() {

		assertEquals(true, HandGame.validateInput("CO", false));
	}

	@Test
	void TestInputPlayer1EqualsOC() {
		assertEquals(true, HandGame.validateInput("OC", false));
	}

	@Test
	void TestInputPlayer1EqualsCC() {
		assertEquals(true, HandGame.validateInput("CC", false));
	}

	@Test
	void TestInputPlayer1EqualsOORolePlayTrue() {
		assertEquals(true, HandGame.validateInput("OO1", true));
	}

	@Test
	void TestInputPlayer1EqualsCORolePlayTrue() {

		assertEquals(true, HandGame.validateInput("CO0", true));
	}

	@Test
	void TestInputPlayer1EqualsOCRolePlayTrue() {
		assertEquals(true, HandGame.validateInput("OC2", true));
	}

	@Test
	void TestInputPlayer1EqualsCCRolePlayTrue() {
		assertEquals(true, HandGame.validateInput("CC4", true));
	}

	@Test
	void TestInputPlayer1EqualsOORolePlay() {
		assertEquals(false, HandGame.validateInput("OO", true));
	}

	@Test
	void TestInputPlayer1EqualsCORolePlay() {

		assertEquals(false, HandGame.validateInput("CO", true));
	}

	@Test
	void TestInputPlayer1EqualsOCRolePlay() {
		assertEquals(false, HandGame.validateInput("OC", true));
	}

	@Test
	void TestInputPlayer1EqualsCCRolePlay() {
		assertEquals(false, HandGame.validateInput("CC", true));
	}

	@Test
	void TestInputPlayer1EqualsNoWrong1() {
		assertEquals(false, HandGame.validateInput("CCCC", false));
	}

	@Test
	void TestInputPlayer1EqualsNoWrong2() {
		assertEquals(false, HandGame.validateInput("CC44", false));
	}

	@Test
	void TestInputPlayer1EqualsNoWrong3() {
		assertEquals(false, HandGame.validateInput("C", false));
	}

	@Test
	void TestInputPlayer1EqualsNoWrong4() {
		assertEquals(false, HandGame.validateInput("CC5", false));
	}

	@Test
	void TestchallengeRigthRoleHuman1() {
		String human = "CO3";
		String ai = "OO";
		assertEquals(true, HandGame.challenge(human, ai, true));
	}

	@Test
	void TestchallengeRigthRoleHuman2() {
		String human = "CO2";
		String ai = "OC";
		assertEquals(true, HandGame.challenge(human, ai, true));
	}

	@Test
	void TestchallengeNotRigthRoleHuman1() {
		String human = "CO4";
		String ai = "CC";
		assertEquals(false, HandGame.challenge(human, ai, true));
	}

	@Test
	void TestchallengeNotRigthRoleHuman2() {
		String human = "CO0";
		String ai = "CC";
		assertEquals(false, HandGame.challenge(human, ai, true));
	}

	@Test
	void TestchallengeRigthRoleAI1() {
		String human = "OO";
		String ai = "OO4";
		assertEquals(true, HandGame.challenge(human, ai, false));
	}

	@Test
	void TestchallengeRigthRoleAI2() {
		String human = "CO";
		String ai = "OC2";
		assertEquals(true, HandGame.challenge(human, ai, false));
	}

	@Test
	void TestchallengeNotRigthRoleAI1() {
		String human = "CC";
		String ai = "CC2";
		assertEquals(false, HandGame.challenge(human, ai, false));
	}

	@Test
	void TestchallengeNotRigthRoleAI2() {
		String human = "OO";
		String ai = "CC3";
		assertEquals(false, HandGame.challenge(human, ai, false));
	}

	// simulate
	@Test
	void TestSimulateAIRoleNotplay() {
		boolean act = false;
		String value = HandGame.simulateAI(false);
		if (value.equalsIgnoreCase("OO") || value.equalsIgnoreCase("CO") || value.equalsIgnoreCase("OC")
				|| value.equalsIgnoreCase("CC")) {
			act = true;
		}
		assertEquals(true, act);

	}

	@Test
	void TestSimulateAIRolePlay() {
		String value = HandGame.simulateAI(true);
		assertEquals(true, value.matches("[CO]{2}[01234]{1}"));

	}

	@Test
	void TestHardAIRolePlay1() {
		Integer value = HandGame.randomDigit(true,"CC");
		boolean act = false;
		if(value >= 0 && value <= 2 ) {
			act = true;
		}
		assertEquals(true,act);
	}
	
	@Test
	void TestHardAIRolePlay2() {
		Integer value = HandGame.randomDigit(true,"CC");
		boolean act = false;
		if(value < 0 || value > 2 ) {
			act = true;
		}
		assertEquals(false,act);
	}
}
