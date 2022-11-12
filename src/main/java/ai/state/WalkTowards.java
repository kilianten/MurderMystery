package ai.state;

import ai.AITransition;
import controller.NPCController;
import core.Position;
import entity.human.NPC.NPC;
import map.Pathfinder;
import state.State;
import state.game.GameState;

import java.util.ArrayList;
import java.util.List;

public class WalkTowards extends AIState {
    private List<Position> path;
    private Position targetLocation;
    private NPC target;

    public WalkTowards(NPC npc) {
        super();
        path = new ArrayList<>();
        target = npc;
    }

    @Override
    protected AITransition initializeTransition() {
        return new AITransition("stand", ((state, currentCharacter) -> arrived(currentCharacter)));
    }

    @Override
    public void update(State state, NPC currentCharacter) {
        if(targetLocation == null) {
            List<Position> path = Pathfinder.findPath(currentCharacter.getPosition(), target.getPosition(), ((GameState) state).getGameMapOfObject(currentCharacter));
            System.out.println(target.getPosition().getX() + "targetPos Y:" + target.getPosition().getY());
            if(!path.isEmpty()) {
                targetLocation = path.get(path.size() - 1);
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
        return targetLocation != null && currentCharacter.getPosition().isInRangeOf(targetLocation);
    }
}
