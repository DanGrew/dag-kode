package dump.words;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ThisOrThatFormatterTest {

    private Random random;
    private ThisOrThatFormatter sut;

    @Before
    public void initSut() {
        random = mock(Random.class);
        sut = new ThisOrThatFormatter(random);
    }

    @Test
    public void shouldFindMatch() {
        sut.matchWords(List.of("start"), List.of("start", "match"));
        assertEquals("match", sut.getMatch("start"));
    }

    @Test
    public void shouldFindMatch_sameLength() {
        sut.matchWords(List.of("start"), List.of("start", "4444", "55555", "666666"));
        assertEquals("55555", sut.getMatch("start"));
    }

    @Test
    public void shouldFindMatch_nextLongestLength() {
        sut.matchWords(List.of("start"), List.of("start", "4444", "666666"));
        assertEquals("666666", sut.getMatch("start"));
    }

    @Test
    public void shouldFindMatch_anythingAvailable() {
        sut.matchWords(List.of("start"), List.of("start", "88888888"));
        assertEquals("88888888", sut.getMatch("start"));
    }

    @Test
    public void shouldFormatOutput() {
        sut.matchWords(List.of("start"), List.of("start", "match"));

        when(random.nextBoolean()).thenReturn(true).thenReturn(false);
        assertEquals("start OR match", sut.buildWordList().get(0));
        assertEquals("match OR start", sut.buildWordList().get(0));
    }

    @Test
    public void shouldNotReuseWords() {

    }

    @Test
    public void generator() {
        sut = new ThisOrThatFormatter(new Random());

        sut.matchWords(new ArrayList<>(List.of(
                "Ball,Bike,Binoculars,Car,Card,Chalk,Clown,Crib,Dollhouse,Domino,Drum,Frisbee,Helmet,Hulahoop,Jigsaw,Jumprope,Kaleidoscope,Kite,Maraca,Marker,Mask,Nerfgun,Plane,Puzzle,Quoits,Sandbox,Scooter,Skateboard,Teddy,Train,Xylophone,Zipper".split(","))
        ), List.of(
                "Boat,Lego,Inflatable,Cube,Top,Globe,Tower,Doll,Slingshot,Kickball,Dice,Puppet,Unicycle,Chalkboard,Lollipop,Velcroball,Balancebeam,Sled,Rocket,Crayon,Yoyo,Balloon,Easel,Bubble,Swing,Playdough,Origami,Fingerpuppet,Robot,Truck,Nestingdoll,Marbles".split(",")

        ));

        when(random.nextBoolean()).thenReturn(true);

        sut.buildWordList().forEach(w -> System.out.println(w));
    }


}