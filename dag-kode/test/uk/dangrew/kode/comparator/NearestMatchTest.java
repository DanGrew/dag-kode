package uk.dangrew.kode.comparator;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class NearestMatchTest {

    private NearestMatch systemUnderTest;

    @Before
    public void initialiseSystemUnderTest() {
        systemUnderTest = new NearestMatch();
    }

    @Test
    public void shouldFindNearestMatch() {
        List<Integer> numbers = Arrays.asList(5, 10, 15, 20);
        Optional<Integer> result = systemUnderTest.findNearest(
                numbers,
                (a, b) -> b > 10,
                (a, b) -> b
        );

        assertThat(result.get(), is(15));
    }

}