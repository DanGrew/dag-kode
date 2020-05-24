package uk.dangrew.kode.javafx.color;

import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class JavaFxNamedColoursTest {

    private JavaFxNamedColours systemUnderTest;

    @Before public void initialiseSystemUnderTest(){
        systemUnderTest = new JavaFxNamedColours();
    }

    @Test
    public void shouldProvideNamedColours(){
        Collection<Color> colours = systemUnderTest.getColours();
        assertThat(colours.contains(Color.RED), is(true));
        assertThat(colours.contains(Color.GREEN), is(true));
        assertThat(colours.contains(Color.BLUE), is(true));
        assertThat(colours.contains(Color.BLACK), is(true));
        assertThat(colours.contains(Color.ORANGE), is(true));
    }
}