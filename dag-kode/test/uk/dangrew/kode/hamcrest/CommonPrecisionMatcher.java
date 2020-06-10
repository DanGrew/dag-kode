package uk.dangrew.kode.hamcrest;

import org.hamcrest.Matcher;
import uk.dangrew.kode.TestCommon;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;

/**
 * {@link Matcher} using common precision.
 */
public class CommonPrecisionMatcher {

    public static Matcher<Double> isCloseTo(double value){
        return is(closeTo(value, TestCommon.precision()));
    }
}
