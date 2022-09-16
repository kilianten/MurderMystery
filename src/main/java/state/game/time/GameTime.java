package state.game.time;

public class GameTime {

    private int hour;
    private int minute;
    private int day;

    public GameTime(int day, int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public GameTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public GameTime(int hour) {
        this(hour, 0);
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
