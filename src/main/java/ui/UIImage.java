package ui;

import core.Position;
import core.Size;
import state.State;

import java.awt.*;

public class UIImage extends UIComponent {

    private Image image;

    public UIImage(Image image) {
        this.image = image;
        size = new Size(image.getWidth(null), image.getHeight(null));
    }

    public UIImage(Image image, Position position) {
        this.image = image;
        size = new Size(image.getWidth(null), image.getHeight(null));
    }

    @Override
    public Image getSprite() {
        return image;
    }

    @Override
    public void update(State state) {}
}
