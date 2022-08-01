package ui.clickable;

import core.Size;
import graphics.ImageUtils;
import state.State;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UISlider extends UIClickable {

    private double value;
    private double min;
    private double max;

    public UISlider(double min, double max, double value) {
        this.min = min;
        this.max = max;
        this.value = value;
        this.size = new Size(360, 16);
    }

    @Override
    protected void onFocus(State state) {

    }

    @Override
    public void onDrag(State state) {
        this.value = getValueAt(state.getInput().getMousePosition().getX());
        this.value = Math.min(max, this.value);
        this.value = Math.max(min, this.value);
    }

    @Override
    public void onClick(State state) {}

    private double getValueAt(double xPosition) {
        double positionOnSlider = xPosition - absolutePosition.getX();
        double percentage = positionOnSlider / size.getWidth();
        double range = max - min;

        return min + range * percentage;
    }

    private int getPixelsOfCurrentValue() {
        double range = max - min;
        double percentage = value - min;

        return (int) ((percentage / range) * size.getWidth());
    }

    @Override
    public Image getSprite() {
        BufferedImage sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_OPAQUE);
        Graphics2D graphics = sprite.createGraphics();

        graphics.setColor(Color.GRAY);
        graphics.fillRect(0, 0, size.getWidth(), size.getHeight());

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, getPixelsOfCurrentValue(), size.getHeight());

        graphics.dispose();
        return sprite;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getMax() {
        return max;
    }
}
