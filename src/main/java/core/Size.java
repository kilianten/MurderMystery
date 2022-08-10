package core;

public class Size {

    private int width;
    private int height;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static Size copyOf(Size size) {
        return new Size(size.getWidth(), size.getHeight());
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
