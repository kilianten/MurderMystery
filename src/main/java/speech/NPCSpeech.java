package speech;


import entity.human.NPC.NPC;

public abstract class NPCSpeech {

    public abstract String doYouSmoke(NPC npc);
    public abstract String areYouReligious(NPC npc);
    public abstract String doYouHaveTattoos(NPC npc);
    public abstract String doYouDrink(NPC npc);
    public abstract String whatBringsYouHere();
    public abstract String howLongHaveYouLivedHere();
    public String whatAreYouInFor(NPC npc){
        return npc.getJailedReason();
    }
    public abstract String youJailedMe();

}
