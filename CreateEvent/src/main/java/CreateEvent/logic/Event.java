package CreateEvent.logic;


import javafx.scene.image.Image;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class Event {

    private Image logo;
    private String name;
    private String localization;
    private String description;
    private LocalDate date;
    private LocalTime timeStart = LocalTime.of(8,00);
    private LocalTime timeEnd = LocalTime.of(16,00);
    private ArrayList<Prelegent> prelegents;
    private ArrayList<Room> rooms;
    private ArrayList<Lecture> lectures;

    public Event(){
        this.logo = null;
        this.name = "";
        this.localization = "";
        this.description = "";
        this.date = LocalDate.now();
        this.prelegents = new ArrayList<Prelegent>();
        this.rooms = new ArrayList<Room>();
        this.lectures = new ArrayList<Lecture>();
    }

    public Image getLogo() {
        return logo;
    }

    public String getName() {
        return name;
    }

    public String getLocalization() {
        return localization;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTimeStart(){
        return timeStart;
    }

    public LocalTime getTimeEnd(){
        return timeEnd;
    }

    public void setPrelegent(int i, Prelegent prelegent){
        prelegents.set(i,prelegent);
    }

    public ArrayList<Prelegent> getPrelegents(){
        return prelegents;
    }

    public Prelegent getPrelegent(int i){
        return prelegents.get(i);
    }

    public void setRoom(int i, Room room){
        rooms.set(i,room);
    }

    public ArrayList<Room> getRooms(){
        return rooms;
    }

    public Room getRoom(int i){
        return rooms.get(i);
    }

    public void setLecture(int i, Lecture lecture){
        lectures.set(i,lecture);
    }

    public ArrayList<Lecture> getLectures(){
        return  lectures;
    }

    public Lecture getLecture(int i){
        return  lectures.get(i);
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTimeStart(LocalTime timeStart){
        this.timeStart=timeStart;
    }

    public void setTimeEnd(LocalTime timeEnd){
        this.timeEnd=timeEnd;
    }

    public void addPrelegent(Prelegent prelegent){
        prelegents.add(prelegent);
    }

    public void removePrelegent(int i){
        prelegents.remove(i);
    }

    public void addRoom(Room room){
        rooms.add(room);
    }

    public void removeRoom(int i){
        rooms.remove(i);
    }

    public void addLecture(Lecture lecture){
        lectures.add(lecture);
    }

    public  void removeLecture(int i){
        lectures.remove(i);
    }




}
