package game;

public class Clock {
    private int updatesSinceStart;
    private int minutesInDay;
    private int day;
    private int timeSpeed = 1;

    public Clock() {
        updatesSinceStart = 0;
        minutesInDay = 0;
        day = 1;
    }

    public int getUpdatesFromSeconds(double seconds){
        return (int) (Math.round(seconds * GameLoop.UPDATES_PER_SECOND));
    }

    public void update(){
        updatesSinceStart++;
        minutesInDay += timeSpeed;
        if(minutesInDay / GameLoop.UPDATES_PER_SECOND >= 1440){
            minutesInDay = 0;
            day++;
        }
    }

    public String getFormattedTime(){
        StringBuilder stringBuilder = new StringBuilder();
        int minutes = minutesInDay / GameLoop.UPDATES_PER_SECOND / 60;
        int seconds = minutesInDay / GameLoop.UPDATES_PER_SECOND % 60;

        if(minutes < 10){
            stringBuilder.append(0);
        }
        stringBuilder.append(minutes);
        stringBuilder.append(":");
        if(seconds < 10){
            stringBuilder.append(0);
        }
        stringBuilder.append(seconds);
        return stringBuilder.toString();
    }

    public String getDay(){
        return "Day: " + day;
    }

    public boolean secondsDividableBy(double seconds) {
        return updatesSinceStart % getUpdatesFromSeconds(seconds) == 0;
    }
}
