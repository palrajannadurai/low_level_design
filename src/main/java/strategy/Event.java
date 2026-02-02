package strategy;

public class Event {
    private final int matchesPlayed;
    private final int steps;
    private final int badgeCount;

    public Event(int matchesPlayed, int steps, int badgeCount) {
        this.matchesPlayed = matchesPlayed;
        this.steps = steps;
        this.badgeCount = badgeCount;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public int getSteps() {
        return steps;
    }

    public int getBadgeCount() {
        return badgeCount;
    }
}
