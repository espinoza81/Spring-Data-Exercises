package gameshop.domain.user;

public class UserNameIsAdminDTO {
    private String fullName;
    private boolean admin;

    public UserNameIsAdminDTO(User user) {
        this.fullName = user.getFullName();
        this.admin = user.isAdmin();
    }

    public UserNameIsAdminDTO() {
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