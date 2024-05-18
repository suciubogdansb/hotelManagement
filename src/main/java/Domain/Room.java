package Domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "rooms")
public class Room {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField()
    private int number;

    @DatabaseField
    private int type;

    @DatabaseField
    private int price;

    @DatabaseField(canBeNull = false, foreign = true)
    private Hotel hotel;

    public Room() {
    }

    public Room(int number, int type, int price, Hotel hotel) {
        this.number = number;
        this.type = type;
        this.price = price;
        this.hotel = hotel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
