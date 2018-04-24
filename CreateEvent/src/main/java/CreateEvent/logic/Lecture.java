package CreateEvent.logic;

import java.time.LocalTime;

public class Lecture {

    private String name;
    private  String description;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private Room room;
    private Prelegent prelegent;

    public Lecture(){
        this.name = "";
        this.description = "";
        this.room = new Room();
        this.prelegent = new Prelegent();
    }

    public Lecture(String name, String description, LocalTime timeStart, LocalTime timeEnd, Room room, Prelegent prelegent){
        this.name = name;
        this.description = description;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.room = room;
        this.prelegent = prelegent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Prelegent getPrelegent() {
        return prelegent;
    }

    public void setPrelegent(Prelegent prelegent) {
        this.prelegent = prelegent;
    }
}
