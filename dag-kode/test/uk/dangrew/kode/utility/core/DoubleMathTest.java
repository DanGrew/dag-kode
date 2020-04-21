package uk.dangrew.kode.utility.core;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DoubleMathTest {

    private DoubleMath systemUnderTest;

    @Before public void initialiseSystemUnderTest(){
        systemUnderTest = new DoubleMath(1000);
    }

    @Test
    public void shouldCalculateModulus(){
        assertThat(systemUnderTest.doubleModulus(0.2, 0.5), is(0.2));
        assertThat(systemUnderTest.doubleModulus(0.5, 0.2), is(0.1));
        assertThat(systemUnderTest.doubleModulus(0.02, 0.5), is(0.02));
        assertThat(systemUnderTest.doubleModulus(0.5, 0.02), is(0.0));
    }

    @Test
    public void shouldCalculateDivision(){
        assertThat(systemUnderTest.doubleDivision(0.5, 0.1), is(5.0));
        assertThat(systemUnderTest.doubleDivision(0.2, 0.5), is(0.4));
        assertThat(systemUnderTest.doubleDivision(0.49999, 0.5), is(1.0));
        assertThat(systemUnderTest.doubleDivision(0.50001, 0.5), is(closeTo(1.0, 0.01)));
    }

}