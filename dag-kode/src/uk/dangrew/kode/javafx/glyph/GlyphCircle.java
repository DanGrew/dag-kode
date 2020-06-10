package uk.dangrew.kode.javafx.glyph;

import de.jensd.fx.glyphs.GlyphIcon;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

/**
 * {@link GlyphCircle} provides a {@link Circle} with a {@link GlyphIcon} layered with basic click operations listened
 * for.
 */
public class GlyphCircle extends StackPane {

    private final GlyphCircleBuilder glyphCircleBuilder;
    private final Circle circle;
    private final GlyphIcon<?> glyph;

    public GlyphCircle(GlyphCircleBuilder builder) {
        this.glyphCircleBuilder = builder;

        this.glyph = builder.getGlyph();
        this.glyph.setPickOnBounds(false);
        this.glyph.setFill(builder.getGlyphColour());

        this.circle = new Circle();
        this.circle.setFill(builder.getBackgroundColour());
        this.circle.setStroke(builder.getBorderColour());
        this.circle.setOnMouseEntered(e -> circle.setFill(builder.getHoverColour()));
        this.circle.setOnMouseExited(e -> circle.setFill(builder.getBackgroundColour()));

        this.getChildren().add(circle);
        this.getChildren().add(glyph);

        builder.getRadius().ifPresent(circle::setRadius);
        builder.getActionHandler().ifPresent(circle::setOnMouseReleased);
        builder.getDragHandler().ifPresent(circle::setOnMouseDragged);
        builder.getHorizontalBinding().ifPresent(this.translateXProperty()::bind);
        builder.getVerticalBinding().ifPresent(this.translateYProperty()::bind);
        builder.getFixedHorizontalOffset().ifPresent(this.translateXProperty()::set);
        builder.getFixedVerticalOffset().ifPresent(this.translateYProperty()::set);
    }

    /**
     * Provides a testable interface to {@link StackPane#setDisable(boolean)} additionally disabling the components of
     * this widget.
     *
     * @param disabled when disable or not.
     */
    public void friendly_setDisable(boolean disabled) {
        setDisable(disabled);
        if (disabled) {
            glyph.setFill(GlyphCircleBuilder.DISABLED_COLOUR);
            circle.setStroke(GlyphCircleBuilder.DISABLED_COLOUR);
        } else {
            glyph.setFill(glyphCircleBuilder.getGlyphColour());
            circle.setStroke(glyphCircleBuilder.getBorderColour());
        }
    }

    /**
     * Convenience method to get the handler associated with a conceptual button click.
     * @return the {@link EventHandler}.
     */
    public EventHandler<? super MouseEvent> getActionHandler(){
        return circle.getOnMouseReleased();
    }

    Circle circle() {
        return circle;
    }

}
