package finalproject;

public class Help {
    // 1. Private fields to achieve Encapsulation
    private String title;
    private String message;

    // 2. Default Constructor
    public Help() {}

    // 3. Parameterized Constructor
    public Help(String title, String message) {
        this.title = title;
        this.message = message;
    }

    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}