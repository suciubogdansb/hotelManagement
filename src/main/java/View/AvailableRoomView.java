package View;

import Domain.Hotel;
import Domain.Room;
import Service.Service;
import com.j256.ormlite.stmt.query.In;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AvailableRoomView implements View{
    private final Hotel hotel;
    private Map<String, Command> commands = new HashMap<>();
    public AvailableRoomView(Hotel hotel) throws SQLException {
        this.hotel = hotel;
        show();
    }

    private void show() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy:HH:mm");
        Date checkInDate;
        Date checkOutDate;
        System.out.println("Enter the check-in date (dd-MM-yyyy:HH:mm): ");
        String checkIn = scanner.nextLine();
        try {
            checkInDate = sdf.parse(checkIn);
        } catch (Exception e) {
            System.out.println("Invalid date format");
            return;
        }
        System.out.println("Enter the check-out date (dd-MM-yyyy:HH:mm): ");
        String checkOut = scanner.nextLine();
        try {
            checkOutDate = sdf.parse(checkOut);
        } catch (Exception e) {
            System.out.println("Invalid date format");
            return;
        }
        if (checkInDate.after(checkOutDate)) {
            System.out.println("Check-out date must be after check-in date");
            return;
        }
        List<Room> rooms = Service.getInstance().getAvailableRooms(hotel, checkInDate, checkOutDate);
        if (rooms.isEmpty()) {
            System.out.println("No rooms available at this time");
            return;
        }
        for (Room room : rooms) {
            commands.put(Integer.toString(room.getNumber()), new BookRoomCommand(room, checkInDate, checkOutDate));
        }
        while(true){
            System.out.println("0 : Exit section");
            for(Map.Entry<String, Command> command: this.commands.entrySet()) {
                System.out.println(command.getKey() + " : " + command.getValue().Display());
            }
            System.out.println(">> ");
            String key = scanner.nextLine();
            if(key.equals("0")) {
                return;
            }
            Command command = commands.get(key);
            if(command == null){
                System.out.println("Invalid Option");
                continue;
            }
            command.execute();
            return;
        }
    }
}
