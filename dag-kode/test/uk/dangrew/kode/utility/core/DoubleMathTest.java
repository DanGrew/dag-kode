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
        assertThat(systemUnderTest.doubleModulus(0.5, 0.0), is(0.5));
    }

    @Test
    public void shouldCalculateDivision(){
        assertThat(systemUnderTest.doubleDivision(0.5, 0.1), is(5.0));
        assertThat(systemUnderTest.doubleDivision(0.2, 0.5), is(0.4));
        assertThat(systemUnderTest.doubleDivision(0.49999, 0.5), is(1.0));
        assertThat(systemUnderTest.doubleDivision(0.50001, 0.5), is(closeTo(1.0, 0.01)));
        assertThat(systemUnderTest.doubleDivision(0.5, 0.0), is(Double.POSITIVE_INFINITY));
    }

    @Test public void shouldCalculateSubtraction(){
        assertThat(systemUnderTest.doubleSubtraction(0.5, 0.1), is(0.4));
        assertThat(systemUnderTest.doubleSubtraction(0.5, 0.11), is(0.39));
        assertThat(systemUnderTest.doubleSubtraction(0.6, 0.1), is(0.5));
        assertThat(systemUnderTest.doubleSubtraction(0.2, 0.3), is(-0.1));
    }

    @Test public void shouldCalculateAddition(){
        assertThat(systemUnderTest.doubleAddition(0.3, 0.04), is(0.34));
    }

}