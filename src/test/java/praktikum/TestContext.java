package praktikum;

public class TestContext {
    private String createdEmail;
    private String adNameForDelete;
    private String adName;

    public String getCreatedEmail() {
        return createdEmail;
    }

    public void setCreatedEmail(String email) {
        this.createdEmail = email;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getAdNameForDelete() {
        return adNameForDelete;
    }

    public void setAdNameForDelete(String adNameForDelete) {
        this.adNameForDelete = adNameForDelete;
    }
}