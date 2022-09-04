package state.game.ui;

import core.CollisionBox;
import core.Position;
import core.Size;
import entity.human.NPC.NPC;
import state.State;
import ui.Alignment;
import ui.HorizontalContainer;
import ui.UIImage;
import ui.UIText;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UIClue extends HorizontalContainer {

    public UIClue(State state, String name) {
        super(state.getWindowSize());
        setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));
        addUIComponent(new UIImage(state.getSpriteLibrary().getClueImage(name), new Position(0, 0)));
        addUIComponent(new UIText(getClueHint(name)));
    }

    private String getClueHint(String clue) {
        if(clue.startsWith("blood")){
            return  "The victim tried to write his killer’s name. ’ " + clue.charAt(5) + " ’";
        }
        else {
            switch (clue){
                case "cigarette":
                    return "Looks like our killer is a smoker";
            }
        }
        return "";
    }

    public static String getClue(NPC murderer){
        List<String> clues = new ArrayList<>();
        Random random = new Random();
        if(murderer.isSmoker()){
            clues.add("cigarette");
        }
        clues.add("blood" + murderer.getFirstName().charAt(0));
        clues.add("blood" + murderer.getSecondName().charAt(0));

        return(clues.get(random.nextInt(clues.size())));
    }

}
