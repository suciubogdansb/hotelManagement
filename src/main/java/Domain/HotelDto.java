package Domain;

public class HotelDto {
    private int id;
    private String name;
    private double latitude;
    private double longitude;

    private RoomDto[] rooms;

    public HotelDto() {
    }

    public HotelDto(int id, String name, double latitude, double longitude, RoomDto[] rooms) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rooms = rooms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public RoomDto[] getRooms() {
        return rooms;
    }

    public void setRooms(RoomDto[] rooms) {
        this.rooms = rooms;
    }
}
