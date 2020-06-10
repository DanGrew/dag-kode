package uk.dangrew.kode.javafx.glyph;

import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import uk.dangrew.kode.utility.event.TestMouseEvent;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class GlyphCircleTest {

    @Mock private EventHandler<MouseEvent> handler1;
    @Mock private EventHandler<MouseEvent> handler2;

    private GlyphIcon<?> glyph;
    private GlyphCircleBuilder glyphCircleBuilder;
    private GlyphCircle systemUnderTest;

    @Before
    public void initialiseSystemUnderTest() {
        initMocks(this);
        glyph = new MaterialIconView(MaterialIcon.CLOSE);
        glyphCircleBuilder = new GlyphCircleBuilder(glyph);
    }

    @Test public void shouldConfigureComponents() {
        glyphCircleBuilder.withBackgroundColour(Color.BLACK);
        glyphCircleBuilder.withBorderColour(Color.BLUE);
        glyphCircleBuilder.withGlyphColour(Color.GREEN);
        glyphCircleBuilder.withRadius(12);

        glyphCircleBuilder.withAction(handler1);
        glyphCircleBuilder.withDrag(handler2);

        systemUnderTest = new GlyphCircle(glyphCircleBuilder);

        assertThat(systemUnderTest.circle().getFill(), is(Color.BLACK));
        assertThat(systemUnderTest.circle().getStroke(), is(Color.BLUE));
        assertThat(systemUnderTest.circle().getRadius(), is(12.0));
        assertThat(glyph.getFill(), is(Color.GREEN));

        assertThat(systemUnderTest.circle().getOnMouseReleased(), is(handler1));
        assertThat(systemUnderTest.circle().getOnMouseDragged(), is(handler2));

        assertThat(glyph.isPickOnBounds(), is(false));
    }

    @Test public void shouldBindHorizontalPositioning() {
        DoubleProperty property = new SimpleDoubleProperty(10.0);
        glyphCircleBuilder.boundHorizontally(property);

        systemUnderTest = new GlyphCircle(glyphCircleBuilder);
        assertThat(systemUnderTest.getTranslateX(), is(10.0));
        property.set(20);
        assertThat(systemUnderTest.getTranslateX(), is(20.0));
    }

    @Test public void shouldBindVerticalPositioning() {
        DoubleProperty property = new SimpleDoubleProperty(10.0);
        glyphCircleBuilder.boundVertically(property);

        systemUnderTest = new GlyphCircle(glyphCircleBuilder);
        assertThat(systemUnderTest.getTranslateY(), is(10.0));
        property.set(20);
        assertThat(systemUnderTest.getTranslateY(), is(20.0));
    }

    @Test public void shouldFixPositioning() {
        glyphCircleBuilder.fixedHorizontally(30.0);
        glyphCircleBuilder.fixedVertically(20.0);

        systemUnderTest = new GlyphCircle(glyphCircleBuilder);
        assertThat(systemUnderTest.getTranslateX(), is(30.0));
        assertThat(systemUnderTest.getTranslateY(), is(20.0));
    }

    @Test public void shouldDisableComponents() {
        systemUnderTest = new GlyphCircle(glyphCircleBuilder);

        systemUnderTest.friendly_setDisable(true);
        assertThat(systemUnderTest.isDisable(), is(true));
        assertThat(glyph.getFill(), is(GlyphCircleBuilder.DISABLED_COLOUR));
        assertThat(systemUnderTest.circle().getStroke(), is(GlyphCircleBuilder.DISABLED_COLOUR));

        systemUnderTest.friendly_setDisable(false);
        assertThat(systemUnderTest.isDisable(), is(false));
        assertThat(glyph.getFill(), is(glyphCircleBuilder.getGlyphColour()));
        assertThat(systemUnderTest.circle().getStroke(), is(glyphCircleBuilder.getBorderColour()));
    }

    @Test public void shouldHighlightUsingMouse() {
        glyphCircleBuilder.withHoverColour(Color.RED);
        glyphCircleBuilder.withBackgroundColour(Color.BLUE);

        systemUnderTest = new GlyphCircle(glyphCircleBuilder);

        assertThat(systemUnderTest.circle().getFill(), is(Color.BLUE));
        systemUnderTest.circle().getOnMouseEntered().handle(new TestMouseEvent());
        assertThat(systemUnderTest.circle().getFill(), is(Color.RED));
        systemUnderTest.circle().getOnMouseExited().handle(new TestMouseEvent());
        assertThat(systemUnderTest.circle().getFill(), is(Color.BLUE));
    }

}