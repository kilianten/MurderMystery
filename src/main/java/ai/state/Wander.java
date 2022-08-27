package ai.state;

import ai.AITransition;
import controller.NPCController;
import core.Position;
import entity.human.NPC.NPC;
import map.Pathfinder;
import state.State;

import java.util.ArrayList;
import java.util.List;

public class Wander extends AIState {
    private List<Position> path;
    private Position target;

    public Wander() {
        super();
        path = new ArrayList<>();
    }

    @Override
    protected AITransition initializeTransition() {
        return new AITransition("stand", ((state, currentCharacter) -> arrived(currentCharacter)));
    }

    @Override
    public void update(State state, NPC currentCharacter) {
        if(target == null) {
            List<Position> path = Pathfinder.findPath(currentCharacter.getPosition(), state.getRandomPosition(), state.getGameMap());
            if(!path.isEmpty()) {
                target = path.get(path.size() - 1);
                this.path.addAll(path);
            }
        }

        NPCController controller = (NPCController) currentCharacter.getController();
        if(arrived(currentCharacter)) {
            controller.stop();
        }

        if(!path.isEmpty() && currentCharacter.getPosition().isInRangeOf(path.get(0))) {
            path.remove(0);
        }

        if(!path.isEmpty()) {
            controller.moveToTarget(path.get(0), currentCharacter.getPosition());
        }

    }

    private boolean arrived(NPC currentCharacter) {
        return target != null && currentCharacter.getPosition().isInRangeOf(target);
    }
}