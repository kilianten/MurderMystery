package entity;

public class ColourHandler {

    public int hairColour;
    public int shirtColour;
    public int shirtColourDark;
    public int shirtColourLight;
    public int legColor;
    public int legColorDark;
    public int shoeColour;
    public int shoeShadow;
    public int primaryColour;

    public ColourHandler(int hairColour, int shirtColour, int shirtColourDark, int shirtColourLight, int legColor, int legColorDark,int shoeColour, int shoeShadow) {
        this.hairColour = hairColour;
        this.shirtColour = shirtColour;
        this.shirtColourDark = shirtColourDark;
        this.shirtColourLight = shirtColourLight;
        this.legColor = legColor;
        this.legColorDark = legColorDark;
        this.shoeColour = shoeColour;
        this.shoeShadow = shoeShadow;
    }

    public ColourHandler(int hairColour, int shirtColour, int shirtColourDark, int shirtColourLight, int legColor, int legColorDark,int shoeColour, int shoeShadow, int primaryColour) {
        this.hairColour = hairColour;
        this.shirtColour = shirtColour;
        this.shirtColourDark = shirtColourDark;
        this.shirtColourLight = shirtColourLight;
        this.legColor = legColor;
        this.legColorDark = legColorDark;
        this.shoeColour = shoeColour;
        this.shoeShadow = shoeShadow;
        this.primaryColour = primaryColour;
    }

    public int getHairColour() {
        return hairColour;
    }

    public int getShirtColour() {
        return shirtColour;
    }

    public int getShirtColourDark() {
        return shirtColourDark;
    }

    public int getShirtColourLight() {
        return shirtColourLight;
    }

    public int getLegColor() {
        return legColor;
    }

    public int getLegColorDark() {
        return legColorDark;
    }

    public int getShoeColour() {
        return shoeColour;
    }
}
