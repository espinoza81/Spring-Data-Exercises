package gameshop.domain.game;

import gameshop.exceptions.ValidationException;
import gameshop.messages.OutputMessages;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RegisterGameDTO {
    private static final Pattern TITLE_REGEX = Pattern.compile("^[A-Z].*$");
    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final Integer minTitleLength = 3;
    private static final Integer maxTitleLength = 100;
    private static final Integer minDescriptionLength = 20;

    private String title;
    private BigDecimal price;
    private double sizeInMB;
    private String youtubeId;
    private String imageThumbnailURL;
    private String description;
    private LocalDate releaseDate;
    private final List<String> invalidParameters;

    public RegisterGameDTO() {
        this.invalidParameters = new ArrayList<>();
    }

    public RegisterGameDTO(String[] input) {
        this();
        if (input[0].equals("EditGame")) {
            this.update(input);
        } else {
            setTitle(input[1]);
            setPrice(BigDecimal.valueOf(Double.parseDouble(input[2])));
            setSizeInMB(Double.parseDouble(input[3]));
            setYoutubeId(input[4]);
            setImageThumbnailURL(input[5]);
            setDescription(input[6]);
            this.releaseDate = LocalDate.parse(input[7], DateTimeFormatter.ofPattern(DATE_FORMAT));
        }
        this.validate();
    }

    private void update(String[] input) {
        for (int i = 2; i < input.length; i++) {
            String field = input[i].split("=")[0];
            String value = input[i].split("=")[1];

            switch (field) {
                case "price" -> this.setPrice(BigDecimal.valueOf(Double.parseDouble(value)));
                case "size" -> this.setSizeInMB(Double.parseDouble(value));
                case "trailer" -> this.setYoutubeId(value);
                case "thumbnailURL" -> this.setImageThumbnailURL(value);
                case "description" -> this.setDescription(value);
                case "title" -> this.setTitle(value);
                default -> this.invalidParameters.add(OutputMessages.NO_SUCH_FIELD);
            }
        }
    }

    public void validate() {
        if (!this.invalidParameters.isEmpty()) {
            String message = String.join(System.lineSeparator(), this.invalidParameters);

            throw new ValidationException(message);
        }
    }

    public void setTitle(String title) {
        if (title.length() < minTitleLength) {
            this.invalidParameters.add(OutputMessages.GAME_TITLE_TOO_SHORT);
        }

        if (title.length() > maxTitleLength) {
            this.invalidParameters.add(OutputMessages.GAME_TITLE_TOO_LONG);
        }

        if (!TITLE_REGEX.matcher(title).find()) {
            this.invalidParameters.add(OutputMessages.TITLE_MUST_START_WITH_UPPERCASE);
        } else {
            this.title = title;
        }
    }

    public void setPrice(BigDecimal price) {
        if (price.signum() <= 0) {
            this.invalidParameters.add(OutputMessages.PRICE_NOT_VALID);
        } else {
            this.price = price;
        }
    }

    public void setSizeInMB(double sizeInMB) {
        if (sizeInMB <= 0) {
            this.invalidParameters.add(OutputMessages.SIZE_NOT_VALID);
        } else {
            this.sizeInMB = sizeInMB;
        }
    }

    public void setYoutubeId(String youtubeId) {
        if (!OutputMessages.YOUTUBE_ID_REGEX.matcher(youtubeId).find()) {
            this.invalidParameters.add(OutputMessages.INVALID_YOUTUBE_ID);
        } else {
            this.youtubeId = youtubeId;
        }
    }

    public void setImageThumbnailURL(String imageThumbnailURL) {
        boolean rightURL = imageThumbnailURL.startsWith("https://") || imageThumbnailURL.startsWith("http://");

        if (!rightURL) {
            this.invalidParameters.add(OutputMessages.WRONG_THUMBNAIL_URL);
        } else {
            this.imageThumbnailURL = imageThumbnailURL;
        }
    }

    public void setDescription(String description) {
        if (description.length() < minDescriptionLength) {
            this.invalidParameters.add(OutputMessages.DESCRIPTION_TOO_SHORT);
        } else {
            this.description = description;
        }
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public double getSizeInMB() {
        return sizeInMB;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public String getImageThumbnailURL() {
        return imageThumbnailURL;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}