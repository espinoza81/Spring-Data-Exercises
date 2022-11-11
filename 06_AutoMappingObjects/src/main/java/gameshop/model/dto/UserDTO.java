package gameshop.model.dto;

import gameshop.model.entity.User;

public class UserDTO {
    private String fullName;
    private boolean admin;

    public UserDTO(User user) {
        this.fullName = user.getFullName();
        this.admin = user.isAdmin();
    }

    public UserDTO() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}