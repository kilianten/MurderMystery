package state.menu.ui;

import core.Size;
import game.settings.GameSettings;
import state.State;
import state.editor.EditorState;
import state.game.GameState;
import state.menu.MenuState;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;
import ui.clickable.UISlider;

public class UIPreEditorMenu extends VerticalContainer {

    public Size[] mapSizes = {
            new Size(20, 20),
            new Size(25, 25),
            new Size(30, 30),
            new Size(40, 40),
            new Size(50, 50),
            new Size(128, 128)
    };

    public UIPreEditorMenu(Size windowSize, GameSettings settings) {
        super(windowSize);
        alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER);
        addUIComponent(new UIText("Map Size"));
        for(Size size: mapSizes){
            addUIComponent(
                    new UIButton(String.format("%dx%d", size.getWidth(), size.getHeight()),
                    (state) -> state.setNextState(new EditorState(windowSize, state.getInput(), state.getSettings(), new Size(size.getWidth(), size.getHeight())))));
        }
        addUIComponent(new UIButton("Back", (state) -> ((MenuState) state).enterMenu(new UIMainMenu(windowSize))));
    }

    @Override
    public void update(State state){
        super.update(state);
    }


}
