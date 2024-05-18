package View;

import Domain.Hotel;

import java.sql.SQLException;

public class HotelCommand implements Command{
    private final Hotel hotel;

    public HotelCommand(Hotel hotel) {
        this.hotel = hotel;
    }
    @Override
    public void execute() throws SQLException {
        AvailableRoomView availableRoomView = new AvailableRoomView(hotel);
    }

    @Override
    public String Display() {
        return hotel.getName();
    }
}
