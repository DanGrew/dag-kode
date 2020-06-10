package uk.dangrew.kode.javafx.glyph;

import de.jensd.fx.glyphs.GlyphIcon;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Optional;
import java.util.OptionalDouble;

/**
 * {@link GlyphCircleBuilder} provides a builder for a {@link GlyphCircle} giving options for all configuration possible
 * for the widget.
 */
public class GlyphCircleBuilder {

    private static final Color GLYPH_COLOUR = Color.BLACK;
    private static final Color BACKGROUND_COLOUR = Color.WHITE;
    private static final Color HOVER_COLOUR = Color.LIGHTGRAY;
    private static final Color BORDER_COLOUR = Color.BLACK;

    /** Configured by javafx styling and is not programmatically changeable. **/
    static final Color DISABLED_COLOUR = Color.GRAY;

    private final GlyphIcon<?> glyph;
    private Color glyphColour;

    private Color backgroundColour;
    private Color hoverColour;
    private Color borderColour;

    private OptionalDouble radius;

    private Optional<EventHandler<MouseEvent>> actionHandler;
    private Optional<EventHandler<MouseEvent>> dragHandler;

    private Optional<ObservableValue<? extends Number>> horizontalBinding;
    private Optional<ObservableValue<? extends Number>> verticalBinding;
    private OptionalDouble fixedHorizontalOffset;
    private OptionalDouble fixedVerticalOffset;

    public GlyphCircleBuilder(GlyphIcon<?> glyph) {
        this.glyph = glyph;
        this.glyphColour = GLYPH_COLOUR;

        this.backgroundColour = BACKGROUND_COLOUR;
        this.borderColour = BORDER_COLOUR;
        this.hoverColour = HOVER_COLOUR;
        this.radius = OptionalDouble.empty();

        this.actionHandler = Optional.empty();
        this.dragHandler = Optional.empty();

        this.horizontalBinding = Optional.empty();
        this.verticalBinding = Optional.empty();
        this.fixedHorizontalOffset = OptionalDouble.empty();
        this.fixedVerticalOffset = OptionalDouble.empty();
    }

    public GlyphIcon<?> getGlyph() {
        return glyph;
    }

    public GlyphCircleBuilder withBackgroundColour(Color colour) {
        this.backgroundColour = colour;
        return this;
    }

    public Color getBackgroundColour() {
        return backgroundColour;
    }

    public GlyphCircleBuilder withHoverColour(Color colour) {
        this.hoverColour = colour;
        return this;
    }

    public Color getHoverColour() {
        return hoverColour;
    }

    public GlyphCircleBuilder withBorderColour(Color colour) {
        this.borderColour = colour;
        return this;
    }

    public Color getBorderColour() {
        return borderColour;
    }

    public GlyphCircleBuilder withGlyphColour(Color colour) {
        this.glyphColour = colour;
        return this;
    }

    public Color getGlyphColour() {
        return glyphColour;
    }

    public GlyphCircleBuilder withRadius(double radiusValue) {
        this.radius = OptionalDouble.of(radiusValue);
        return this;
    }

    public OptionalDouble getRadius() {
        return radius;
    }

    public GlyphCircleBuilder withAction(EventHandler<MouseEvent> handler) {
        this.actionHandler = Optional.of(handler);
        return this;
    }

    public Optional<EventHandler<MouseEvent>> getActionHandler() {
        return actionHandler;
    }

    public GlyphCircleBuilder withDrag(EventHandler<MouseEvent> handler) {
        this.dragHandler = Optional.of(handler);
        return this;
    }

    public Optional<EventHandler<MouseEvent>> getDragHandler() {
        return dragHandler;
    }

    public GlyphCircleBuilder boundHorizontally(ObservableValue<? extends Number> value) {
        this.horizontalBinding = Optional.of(value);
        return this;
    }

    public Optional<ObservableValue<? extends Number>> getHorizontalBinding() {
        return horizontalBinding;
    }

    public GlyphCircleBuilder boundVertically(ObservableValue<? extends Number> value) {
        this.verticalBinding = Optional.of(value);
        return this;
    }

    public Optional<ObservableValue<? extends Number>> getVerticalBinding() {
        return verticalBinding;
    }

    public GlyphCircleBuilder fixedHorizontally(double value) {
        this.fixedHorizontalOffset = OptionalDouble.of(value);
        return this;
    }

    public OptionalDouble getFixedHorizontalOffset() {
        return fixedHorizontalOffset;
    }

    public GlyphCircleBuilder fixedVertically(double value) {
        this.fixedVerticalOffset = OptionalDouble.of(value);
        return this;
    }

    public OptionalDouble getFixedVerticalOffset() {
        return fixedVerticalOffset;
    }

    public GlyphCircle build() {
        return new GlyphCircle(this);
    }

}
