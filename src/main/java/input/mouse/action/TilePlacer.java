package input.mouse.action;

import core.Position;
import game.Game;
import map.Tile;
import state.State;
import ui.UIImage;

public class TilePlacer extends MouseAction {

    private Tile tile;
    private UIImage preview;
    private int gridX;
    private int gridY;

    public TilePlacer(Tile tile) {
        this.tile = tile;
        preview = new UIImage(tile.getSprite());
    }

    @Override
    public void update(State state) {
        Position position = Position.copyOf(state.getInput().getMousePosition());
        position.add(state.getCamera().getPosition());

        gridX = position.getIntX() / Game.SPRITE_SIZE;
        gridY = position.getIntY() / Game.SPRITE_SIZE;

        position.subtract(new Position(position.getIntX() % Game.SPRITE_SIZE, position.getIntY() % Game.SPRITE_SIZE));
        position.subtract(state.getCamera().getPosition());

        preview.setAbsolutePosition(position);
    }

    @Override
    public void onClick(State state) {

    }

    @Override
    public void onDrag(State state) {
        if(state.getGameMap().gridWithinBounds(gridX, gridY)){
            state.getGameMap().setTile(gridX, gridY, Tile.copyOf(tile));
        }
    }

    @Override
    public UIImage getSprite() {
        return preview;
    }

    @Override
    public void cleanup() {}
}
