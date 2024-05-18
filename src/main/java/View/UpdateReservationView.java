package View;

import Domain.Reservation;
import Domain.Room;
import Service.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class UpdateReservationView implements View{
    private Reservation reservation;

    private static final HashMap<Integer, String> roomTypes = new HashMap<>() {{
        put(1, "Single");
        put(2, "Double");
        put(3, "Triple");
    }};
    private List<Room> possibleRooms;
    public UpdateReservationView(Reservation reservation) throws SQLException {
        this.reservation = reservation;
        this.possibleRooms = Service.getInstance().getAvailableRooms(reservation.getRoom().getHotel(), reservation.getStartDate(), reservation.getStartDate());
        show();
    }

    private void show(){
        System.out.println("0 : Exit section");
        for(int i = 0; i < possibleRooms.size(); i++){
            System.out.println(i + 1 + " : " + possibleRooms.get(i).getNumber() + " " + roomTypes.get(possibleRooms.get(i).getType()) + " " + possibleRooms.get(i).getPrice());
        }
        System.out.println(">> ");
        Scanner scanner = new Scanner(System.in);
        String key = scanner.nextLine();
        if(key.equals("0")) {
            return;
        }
        try {
            int index = Integer.parseInt(key) - 1;
            Room room = possibleRooms.get(index);
            Service.getInstance().updateReservation(reservation, room);
        } catch (Exception e) {
            System.out.println("Invalid Option");
        }
    }
}
