package View;

import Domain.Room;
import Service.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

public class BookRoomCommand implements Command{
    private Room room;
    private Date checkIn;
    private Date checkOut;
    private static final HashMap<Integer, String> roomTypes = new HashMap<>() {{
        put(1, "Single");
        put(2, "Double");
        put(3, "Triple");
    }};

    public BookRoomCommand(Room room, Date checkIn, Date checkOut) {
        this.room = room;
    }
    @Override
    public void execute() throws SQLException {

        Service.getInstance().bookRoom(room, checkIn, checkOut, "mockUser");
    }

    @Override
    public String Display() {
        return "Book " + roomTypes.get(room.getType()) + " Room " + room.getNumber() + " for " + room.getPrice() + "RON";
    }
}
