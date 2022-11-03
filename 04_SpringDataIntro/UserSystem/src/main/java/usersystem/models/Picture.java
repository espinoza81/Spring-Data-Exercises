package usersystem.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.File;

@Entity(name = "picture")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 300)
    private String caption;

    @NotNull
    private File imageFilePath;

    protected Picture() {

    }

    public Picture(String title, String caption, File imageFilePath) {
        this.title = title;
        this.caption = caption;
        this.imageFilePath = imageFilePath;
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public File getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(File imageFilePath) {
        this.imageFilePath = imageFilePath;
    }
}