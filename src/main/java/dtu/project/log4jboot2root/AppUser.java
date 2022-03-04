package dtu.project.log4jboot2root;

public class AppUser { //Represents a record in the APP_USER table in DB
    private Long userID;
    private String username;
    private String encryptedPassword;

    public AppUser(Long userID, String username, String encryptedPassword) {
        this.userID = userID;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
    }

    public Long getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

}
