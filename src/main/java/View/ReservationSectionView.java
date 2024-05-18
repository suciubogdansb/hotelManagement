package View;

import Domain.Reservation;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReservationSectionView implements View{
    private final Reservation reservation;
    private HashMap<String, Command> commands;

    public ReservationSectionView(Reservation reservation) throws SQLException {
        this.reservation = reservation;
        this.commands = new HashMap<String, Command>();
        this.commands.put("1", new UpdateReservationCommand(reservation));
        this.commands.put("2", new CancelReservationCommand(reservation));
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
