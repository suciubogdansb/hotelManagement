package Domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "feedback")
public class Feedback {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Hotel hotel;
    @DatabaseField
    private String username;
    @DatabaseField
    private String servicesFeedback;

    @DatabaseField
    private String cleaningFeedback;

    public Feedback() {
    }

    public Feedback(Hotel hotel, String username, String servicesFeedback, String cleaningFeedback) {
        this.hotel = hotel;
        this.username = username;
        this.servicesFeedback = servicesFeedback;
        this.cleaningFeedback = cleaningFeedback;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getServicesFeedback() {
        return servicesFeedback;
    }

    public void setServicesFeedback(String servicesFeedback) {
        this.servicesFeedback = servicesFeedback;
    }

    public String getCleaningFeedback() {
        return cleaningFeedback;
    }

    public void setCleaningFeedback(String cleaningFeedback) {
        this.cleaningFeedback = cleaningFeedback;
    }
}
