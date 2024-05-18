package Domain;

public class RoomDto {
    private int roomNumber;
    private int type;
    private int price;
    private boolean isAvailable;

    public RoomDto() {
    }

    public RoomDto(int number, int type, int price, boolean isAvailable) {
        this.roomNumber = number;
        this.type = type;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
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

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean available) {
        this.isAvailable = available;
    }
}
