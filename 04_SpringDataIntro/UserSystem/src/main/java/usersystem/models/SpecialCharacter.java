package usersystem.models;

import org.passay.CharacterData;

public enum SpecialCharacter implements CharacterData {

    Special("INSUFFICIENT_SPECIAL", "!@#$%^&*()_+<>?");

    private final String errorCode;
    private final String characters;

    private SpecialCharacter(String code, String charString) {
        this.errorCode = code;
        this.characters = charString;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getCharacters() {
        return this.characters;
    }
}
