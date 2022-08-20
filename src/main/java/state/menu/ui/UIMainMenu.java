package state.menu.ui;

import core.Size;
import game.Game;
import state.State;
import state.editor.EditorState;
import state.game.GameState;
import state.menu.MenuState;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class UIMainMenu extends VerticalContainer {

    private JFileChooser fileChooser;

    public UIMainMenu(Size windowSize) {
        super(windowSize);
        createFileChoose();
        alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER);

        addUIComponent(new UIText(Game.GAME_TITLE, 35, 30, true));
        addUIComponent(new UIButton("Play", this::loadMap));
        addUIComponent(new UIButton("Level Edit",  (state) -> state.setNextState(new EditorState(windowSize, state.getInput(), state.getSettings(), new Size(20, 20)))));
        addUIComponent(new UIButton("Options", (state) -> ((MenuState) state).enterMenu(new UIOptionMenu(windowSize, state.getSettings()))));
        addUIComponent(new UIButton("Exit", (state) -> System.exit(0)));
    }

    private void createFileChoose() {
        fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter(Game.GAME_TITLE + " Map", "rim"));
        fileChooser.setCurrentDirectory(new File(getClass().getResource("/").getFile()));
    }

    private void loadMap(State state) {
        final int fileChosen = fileChooser.showOpenDialog(new JFrame());

        if(fileChosen == JFileChooser.APPROVE_OPTION){
            state.setNextState(new GameState(windowSize, state.getInput(), state.getSettings(), fileChooser.getSelectedFile().getPath()));
        }
    }

}
