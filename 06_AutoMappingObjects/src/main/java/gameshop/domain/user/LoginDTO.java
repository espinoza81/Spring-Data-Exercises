package gameshop.domain.user;

public class LoginDTO {

    private final String email;
    private final String password;

    public LoginDTO(String[] input){
        this.email = input[1];
        this.password = input[2];
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
