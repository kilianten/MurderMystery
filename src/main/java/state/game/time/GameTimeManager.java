package state.game.time;

import game.GameLoop;
import state.State;

import java.util.HashMap;
import java.util.Map;

public class GameTimeManager {

    private int day = 1;
    private int hour = 8;
    private int minute;
    private final int TIME_SPEED = 10;
    private int counter = 0;

    public final GameTime START_DAY = new GameTime(8);
    public final GameTime END_DAY = new GameTime(22);
    public final GameTime EVENING_TIME = new GameTime(16);

    public void update(State state){
        counter += TIME_SPEED;
        if(counter >= GameLoop.UPDATES_PER_SECOND){

            counter = 0;
            minute++;
            if(minute >= 60){
                minute = 0;
                hour++;
                if(EVENING_TIME.getHour() == hour){
                    state.getLighting().setEvening(true);
                }
                if(hour == END_DAY.getHour()){
                    resetDay(state);
                    state.getLighting().setEvening(false);
                }
            }
        }
    }

    private void resetDay(State state) {
        day++;
        hour = START_DAY.getHour();
        minute = START_DAY.getMinute();
        state.getLighting().resetLightBrightness();
    }

    public String getDay(){
        return "Day: " + day + "; " + getDayOfWeek(day) + "";
    }

    public String getDayOfWeek(int dayOfWeek){
        dayOfWeek %= 7;
        return switch (dayOfWeek){
            case 0 -> "Mon";
            case 1 -> "Tues";
            case 2 -> "Wed";
            case 3 -> "Thurs";
            case 4 -> "Fri";
            case 5 -> "Sat";
            case 6 -> "Sun";
            default -> "null";
        };
    }

    public String getFormattedTime(){
        StringBuilder stringBuilder = new StringBuilder();

        if(hour < 10){
            stringBuilder.append(0);
        }
        stringBuilder.append(hour);
        stringBuilder.append(":");
        if(minute < 10){
            stringBuilder.append(0);
        }
        stringBuilder.append(minute);
        return stringBuilder.toString();
    }

    public GameTime getCurrentTime() {
        return new GameTime(day, hour, minute);
    }
}
