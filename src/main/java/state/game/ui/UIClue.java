package state.game.ui;

import core.Position;
import entity.human.NPC.Kate;
import entity.human.NPC.NPC;
import state.State;
import state.game.GameState;
import ui.Alignment;
import ui.HorizontalContainer;
import ui.UIImage;
import ui.UIText;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UIClue extends HorizontalContainer {

    public UIClue(State state, String name) {
        super(state.getWindowSize());
        setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));
        addUIComponent(new UIImage(state.getSpriteLibrary().getClueImage(name), new Position(0, 0)));
        addUIComponent(new UIText(getClueHint(state, name)));
    }

    private String getClueHint(State state, String clue) {
        if(clue.startsWith("blood")){
            return  "The victim tried to write his killer’s name. ’ " + clue.charAt(5) + " ’";
        }
        else {
            switch (clue){
                case "cigarette":
                    return "Looks like our killer is a smoker";
                case "tattooCard":
                    return "Our killer must be into tattoos";
                case "bottleCap":
                    return "A bottle cap. Looks like our killer likes alcohol";
                case "boatReceipt":
                    return "A non-local. Someone who arrived the same day as me";
                case "pillsRed":
                    if(state.getGameObjectsOfClass(Kate.class).size() == 1){
                        return "Antihistamine tables. Maybe the doctor knows more";
                    } else {
                        return "Antihistamine tablets. Dr. Kate would have known more";
                    }
                case "pillsBlue":
                    if(state.getGameObjectsOfClass(Kate.class).size() == 1){
                        return "Statin tables. Maybe the doctor knows more";
                    } else {
                        return "Statin tablets. Dr. Kate would have known more";
                    }
            }
        }
        return "";
    }

    public static String getClue(NPC murderer){
        List<String> clues = new ArrayList<>();
        Random random = new Random();
//        if(murderer.isSmoker()){
//            clues.add("cigarette");
//        }
//        if(murderer.hasTattoo() || murderer.isInterestedInGettingTattoo()){
//            clues.add("tattooCard");
//        }
//        if(murderer.drinksAlcohol()){
//            clues.add("bottleCap");
//        }
//        if(!murderer.isLocal()){
//            clues.add("boatReceipt");
//        }
//        if(murderer.hasHayFever()){
//            clues.add("pillsRed");
//        }
//        clues.add("blood" + murderer.getFirstName().charAt(0));
//        clues.add("blood" + murderer.getSecondName().charAt(0));
//
//        return(clues.get(random.nextInt(clues.size())));
        return "pillsRed";
    }

    @Override
    public void update(State state){
        super.update(state);
        if(state.getInput().isPressed(KeyEvent.VK_ESCAPE)){
            state.removeUIComponent(this);
            ((GameState) state).setPaused(false);
        }
    }

}
