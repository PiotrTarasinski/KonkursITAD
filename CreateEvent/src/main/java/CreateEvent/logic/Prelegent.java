package CreateEvent.logic;


import javafx.scene.image.Image;

public class Prelegent {

    private Image photo;
    private String name;
    private String description;

    public Prelegent(){
        this.photo = null;
        this.name = "";
        this.description = "";
    }

    public Prelegent(Image photo, String name, String description){
        this.photo = photo;
        this.name = name;
        this.description = description;
    }

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
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

}
