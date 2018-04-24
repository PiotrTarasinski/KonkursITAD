package CreateEvent.logic;

/** Klasa sluzaca do przekazywania tego samego obiektu do roznych kontrolerow */

public class Context {
    private final static Context instance = new Context();

    public static Context getInstance() {
        return instance;
    }

    private Event event = new Event();

    public Event currentEvent() {
        return event;
    }
}
