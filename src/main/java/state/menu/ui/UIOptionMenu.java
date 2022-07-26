package state.menu.ui;

import core.Size;
import game.settings.GameSettings;
import state.State;
import state.menu.MenuState;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;
import ui.clickable.UISlider;

public class UIOptionMenu extends VerticalContainer {

    private UISlider musicVolumeSlider;
    private UIText musicVolumeLabel;

    public UIOptionMenu(Size windowSize, GameSettings settings) {
        super(windowSize);
        alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER);

        musicVolumeSlider = new UISlider(0, 1, settings.getAudioSettings().getMusicVolume());
        musicVolumeLabel = new UIText("", 30, 30);

        addUIComponent(new UIText("OPTIONS"));

        addUIComponent(musicVolumeLabel);
        addUIComponent(musicVolumeSlider);

        addUIComponent(new UIButton("BACK", (state) -> ((MenuState) state).enterMenu(new UIMainMenu(windowSize))));
    }

    @Override
    public void update(State state){
        super.update(state);
        handleVolume(state);
    }

    private void handleVolume(State state) {
        state.getSettings().getAudioSettings().setMusicVolume((float) musicVolumeSlider.getValue());
        musicVolumeLabel.setText(String.format("Music Volume %d", Math.round(musicVolumeSlider.getValue() * 100)));
    }
}
