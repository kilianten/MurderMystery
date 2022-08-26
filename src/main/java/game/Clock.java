package game;

public class Clock {

    private int updatesSinceStart;

    public Clock() {
        updatesSinceStart = 0;
    }

    public int getUpdatesFromSeconds(double seconds){
        return (int) (Math.round(seconds * GameLoop.UPDATES_PER_SECOND));
    }

    public void update(){
        updatesSinceStart++;
    }

    public boolean secondsDividableBy(double seconds) {
        return updatesSinceStart % getUpdatesFromSeconds(seconds) == 0;
    }
}
