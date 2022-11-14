package gameshop.domain.game;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "games")
public class Game{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "youtube_id")
    private String youtubeId;

    @Column(name = "image_thumbnail_url", nullable = false)
    private String imageThumbnailURL;

    @Column(name = "size_mb")
    private double sizeInMB;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(nullable = false)
    private boolean deleted;

    public Game() {
        this.deleted = false;
    }

    public Game(String title, String youtubeId, String imageThumbnailURL, double sizeInMB,
                BigDecimal price, String description, LocalDate releaseDate) {
        this();
        this.title = title;
        this.youtubeId = youtubeId;
        this.imageThumbnailURL = imageThumbnailURL;
        this.sizeInMB = sizeInMB;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public String getImageThumbnailURL() {
        return imageThumbnailURL;
    }

    public void setImageThumbnailURL(String imageThumbnailURL) {
        this.imageThumbnailURL = imageThumbnailURL;
    }

    public double getSizeInMB() {
        return sizeInMB;
    }

    public void setSizeInMB(double sizeInMB) {
        this.sizeInMB = sizeInMB;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void update(RegisterGameDTO updateGameDTO) {
        if(updateGameDTO.getTitle() != null) this.title = updateGameDTO.getTitle();
        if(updateGameDTO.getPrice() != null) this.price = updateGameDTO.getPrice();
        if(updateGameDTO.getSizeInMB() > 0) this.sizeInMB = updateGameDTO.getSizeInMB();
        if(updateGameDTO.getYoutubeId() != null) this.youtubeId = updateGameDTO.getYoutubeId();
        if(updateGameDTO.getImageThumbnailURL() != null) this.imageThumbnailURL = updateGameDTO.getImageThumbnailURL();
        if(updateGameDTO.getDescription() != null) this.description = updateGameDTO.getDescription();
        if(updateGameDTO.getReleaseDate() != null) this.releaseDate = updateGameDTO.getReleaseDate();
    }
}