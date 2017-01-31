package com.tests;

import com.datacontrol.DataReader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DataReaderTest {
    private DataReader dataReader;

    @Before
    public void setUp() {
        dataReader = new DataReader();
    }

    private void simulateInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        dataReader.input = new Scanner(System.in);
    }

    @Test
    public void validateBetWithCorrectNumber() {
        simulateInput("100\n");
        assertThat(dataReader.validateBet(), is(100));
    }

    @Test
    public void validateBetWithIncorrectNumber() {
        simulateInput("-100\n");
        assertThat(dataReader.validateBet(), is(-100));
    }

    @Test
    public void validateBetWithZero() {
        simulateInput("0\n");
        assertThat(dataReader.validateBet(), is(0));
    }

    @Test
    public void validateBetWithString() {
        simulateInput("test string\n");
        assertThat(dataReader.validateBet(), is(0));
    }

    @Test
    public void isHitChosenInput1Test() {
        simulateInput("1\n");
        assertThat(dataReader.isHitChosen(), is(true));
    }

    @Test
    public void isHitChosenInputHit() {
        simulateInput("Hit\n");
        assertThat(dataReader.isHitChosen(), is(true));
    }

    @Test
    public void isHitChosenInputHIT() {
        simulateInput("HIT\n");
        assertThat(dataReader.isHitChosen(), is(true));
    }

    @Test
    public void isHitChosenInputhit() {
        simulateInput("hit\n");
        assertThat(dataReader.isHitChosen(), is(true));
    }

    @Test
    public void isHitChosenInput2Test() {
        simulateInput("2");
        assertThat(dataReader.isHitChosen(), is(false));
    }

    @Test
    public void isHitChosenInputStand() {
        simulateInput("Stand");
        assertThat(dataReader.isHitChosen(), is(false));
    }

    @Test
    public void isHitChosenInputSTAND() {
        simulateInput("STAND");
        assertThat(dataReader.isHitChosen(), is(false));
    }

    @Test
    public void isHitChosenInputstand() {
        simulateInput("stand");
        assertThat(dataReader.isHitChosen(), is(false));
    }

    @After
    public void resetSystemIn() {
        dataReader.input.close();
    }
}
