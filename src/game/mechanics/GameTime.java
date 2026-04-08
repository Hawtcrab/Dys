package game.mechanics;

public class GameTime {
    public static int turns = 0;
    public static int days = 0;
    public static int hours = 0;
    public static int minutes = 0;

    private static final boolean debug = true;

    public static void tickTurn() {
        minutes++;
        turns++;
        if (minutes == 60) {hours++; minutes = 0;}
        if (hours == 24) {days++; hours = 0;}
    }

    public static String timeString() {
        if (debug) return hours + ":" + minutes;
        if (hours >= 18 || hours <= 5) return "Night";
        if (hours <= 10 && hours > 5) return "Day";
        return "Morning";
    }
}
