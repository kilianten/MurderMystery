package state.editor.ui;

import core.Size;
import game.Game;
import state.State;
import state.menu.MenuState;
import ui.HorizontalContainer;
import ui.clickable.UIButton;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class UIEditorMenu extends HorizontalContainer {

    private JFileChooser fileChooser;

    public UIEditorMenu(Size windowSize) {
        super(windowSize);
        fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter(Game.GAME_TITLE + " Map", "rim"));
        fileChooser.setCurrentDirectory(new File(getClass().getResource("/").getFile()));
        addUIComponent(new UIButton("Main Menu", state -> state.setNextState(new MenuState(state.getCamera().getSize(), state.getInput(), state.getSettings()))));
        addUIComponent(new UIButton("Save", this::saveMap));
        addUIComponent(new UIButton("Load", this::loadMap));
    }

    private void loadMap(State state) {
        final int fileChosen = fileChooser.showOpenDialog(new JFrame());

        if(fileChosen == JFileChooser.APPROVE_OPTION){
            state.loadGameMap(fileChooser.getSelectedFile().getPath());
        }
    }

    private void saveMap(State state) {
        final int fileChosen = fileChooser.showSaveDialog(new JFrame());

        if(fileChosen == JFileChooser.APPROVE_OPTION){
            state.saveGameMap(fileChooser.getSelectedFile().toString() + ".rim");
        }
    }

}
