package View;

import Domain.Reservation;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReservationListView implements View{
    private final List<Reservation> reservations;
    private HashMap<String, Command> commands;
    public ReservationListView(List<Reservation> reservations) throws SQLException {
        this.reservations = reservations;
        if(reservations.isEmpty()){
            System.out.println("No reservations found");
            return;
        }
        this.commands = new HashMap<String, Command>();
        int i = 1;
        for(Reservation reservation: reservations){
            this.commands.put(Integer.toString(i), new ReservationCommand(reservation));
            i++;
        }
        show();
    }

    private void show() throws SQLException {
        Scanner scanner = new Scanner(System.in);
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
