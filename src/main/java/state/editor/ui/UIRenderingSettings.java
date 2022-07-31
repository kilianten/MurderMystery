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

    public UIRenderingSettings(Size windowSize, RenderSettings renderSettings, GameMap gameMap) {
        super(windowSize);

        setAlignment(new Alignment(Alignment.Position.END, Alignment.Position.START));

        addUIComponent(new UIMiniMap(gameMap));
        addUIComponent(new UIText("Render Settings"));
        addUIComponent(new UICheckbox("Grid", renderSettings.getShouldRenderGrid()));

    }

}
