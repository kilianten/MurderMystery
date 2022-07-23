package ui;

public class Spacing {

    private int top;
    private int bottom;
    private int right;
    private int left;

    public Spacing(int spacing){
        this(spacing, spacing);
    }

    public Spacing(int horizontal, int vertical){
        this(horizontal, vertical, vertical, horizontal);
    }

    public Spacing(int top, int bottom, int right, int left) {
        this.top = top;
        this.bottom = bottom;
        this.right = right;
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    public int getRight() {
        return right;
    }

    public int getLeft() {
        return left;
    }

    public int getVertical(){
        return top + bottom;
    }

    public int getHorizontal(){
        return right + left;
    }
}
