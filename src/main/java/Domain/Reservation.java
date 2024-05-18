package Domain;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "reservations")
public class Reservation {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Room room;
    @DatabaseField(dataType = DataType.DATE_STRING)
    private Date startDate;
    @DatabaseField(dataType = DataType.DATE_STRING)
    private Date endDate;

    @DatabaseField
    private String username;

    public Reservation() {
    }

    public Reservation(Room room, Date startDate, Date endDate, String username) {
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.username = username;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
