package state.editor.ui;

import core.Size;
import game.settings.RenderSettings;
import map.GameMap;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UICheckbox;
import ui.clickable.UIMiniMap;

public class UIRenderingSettings extends VerticalContainer {

    private UIMiniMap miniMap;

    public UIRenderingSettings(Size windowSize, RenderSettings renderSettings, GameMap gameMap) {
        super(windowSize);

        setAlignment(new Alignment(Alignment.Position.END, Alignment.Position.START));
        miniMap = new UIMiniMap(gameMap);
        addUIComponent(miniMap);
        addUIComponent(new UICheckbox("Grid", renderSettings.getShouldRenderGrid()));
        addUIComponent(new UICheckbox("Collision", renderSettings.getCollisionBox()));
    }

    public void resetMiniMap(GameMap gameMap){
        children.remove(miniMap);
        miniMap = new UIMiniMap(gameMap);
        addUIComponentToFront(miniMap);
    }

}
