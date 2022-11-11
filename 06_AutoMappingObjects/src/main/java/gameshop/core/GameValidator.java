package gameshop.core;

import gameshop.messages.OutputMessages;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class GameValidator {
    private static final Pattern TITLE_REGEX = Pattern.compile("^[A-Z].*$");

    private static final Integer minTitleLength = 3;
    private static final Integer maxTitleLength = 100;
    private static final Integer minDescriptionLength = 20;

    private final List<String> invalidParameters;

    public GameValidator() {
        this.invalidParameters = new ArrayList<>();
    }

    public List<String> isValid(final String title, final BigDecimal price, final double sizeInMB, final String youtubeId,
                           final String imageThumbnailURL, final String description) {

        if (title.length() < minTitleLength) {
            invalidParameters.add(OutputMessages.GAME_TITLE_TOO_SHORT);
        }

        if (title.length() > maxTitleLength) {
            invalidParameters.add(OutputMessages.GAME_TITLE_TOO_LONG);
        }

        if (!TITLE_REGEX.matcher(title).find()) {
            invalidParameters.add(OutputMessages.TITLE_MUST_START_WITH_UPPERCASE);
        }

        if(price.signum() <= 0) {
            invalidParameters.add(OutputMessages.PRICE_NOT_VALID);
        }

        if(sizeInMB <= 0) {
            invalidParameters.add(OutputMessages.SIZE_NOT_VALID);
        }

        if (!OutputMessages.YOUTUBE_ID_REGEX.matcher(youtubeId).find()) {
            invalidParameters.add(OutputMessages.INVALID_YOUTUBE_ID);
        }

        boolean rightURL = imageThumbnailURL.startsWith("https://") || imageThumbnailURL.startsWith("http://");

        if(!rightURL) {
            invalidParameters.add(OutputMessages.WRONG_THUMBNAIL_URL);
        }

        if (description.length() < minDescriptionLength) {
            invalidParameters.add(OutputMessages.DESCRIPTION_TOO_SHORT);
        }

        return Collections.unmodifiableList(invalidParameters);
    }
}