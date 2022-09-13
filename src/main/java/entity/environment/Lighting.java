package entity.environment;

import core.CollisionBox;
import core.Position;
import core.Size;
import entity.GameObject;
import entity.scenery.Bench;
import entity.scenery.LampPost;
import game.Game;
import graphics.ImageUtils;
import state.State;
import state.game.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Lighting extends GameObject {

    private BufferedImage sprite;
    private int lightWidth = 175;
    private float lightBrightness = 0.0f;
    private boolean evening;
    private int SAFE_SPACE = 2 * Game.SPRITE_SIZE;
    private BufferedImage lightMap;
    private ArrayList<Light> lights;

    public Lighting(State state){
        size = new Size(state.getGameMap().getWidth() + SAFE_SPACE * 2, state.getGameMap().getHeight() + SAFE_SPACE * 2);
        renderOrder = 9;

        lightMap = new BufferedImage(state.getGameMap().getWidth() + SAFE_SPACE * 2, state.getGameMap().getHeight() + SAFE_SPACE * 2, BufferedImage.TYPE_INT_ARGB);
        position = new Position(-SAFE_SPACE, -SAFE_SPACE);

        lights = new ArrayList<>();
        for(LampPost lamp: state.getGameObjectsOfClass(LampPost.class)){

            lights.add(new Light(SAFE_SPACE + lamp.getPosition().getIntX() - 4, SAFE_SPACE + lamp.getPosition().getIntY(), lightWidth, 0.6f));
        }
        drawSprite(state);
    }

    private void drawSprite(State state) {
        sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BLEND);
        paintLightMap(state);
    }

    @Override
    public CollisionBox getCollisionBox() {
        return null;
    }

    @Override
    public void update(State state) {
        if(!((GameState) state).isPaused()){
            if(evening){
                lightBrightness += 0.0006;
                if(lightBrightness > .97f){
                    lightBrightness = .97f;
                }
            }
        }
    }

    @Override
    public Image getSprite() {
        return sprite;
    }

    public float getLightBrightness() {
        return lightBrightness;
    }

    public float resetLightBrightness() {
        return lightBrightness = 0.0f;
    }

    public void setEvening(boolean isEvening){
        evening = isEvening;
    }

    private void paintLightMap(State state) {
        Graphics2D graphics = sprite.createGraphics();
        graphics.setColor(new Color(15, 15, 19, 255));
        graphics.fillRect(0, 0, state.getGameMap().getWidth() + SAFE_SPACE * 2, state.getGameMap().getHeight() + SAFE_SPACE * 2);
        Composite oldComp = graphics.getComposite();
        graphics.setComposite(AlphaComposite.DstOut);

        for(Light light : lights) light.render(graphics);

        graphics.setComposite(oldComp);
        graphics.dispose();
    }



}
