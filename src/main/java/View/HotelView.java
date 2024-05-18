package View;

import Domain.Hotel;
import org.example.Main;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HotelView implements View{
    private Map<String, Command> commands = new HashMap<>();
    private List<Hotel> hotels;
    public HotelView(List<Hotel> hotels) throws SQLException {
        this.hotels = hotels;
        if (hotels.isEmpty()) {
            System.out.println("No hotels found");
            return;
        }
        this.commands.put("0", new ExitCommand());
        int i = 1;
        for(Hotel hotel: hotels){
            commands.put(Integer.toString(i++), new HotelCommand(hotel));
        }
        commands.put(Integer.toString(i++), new DisplayReservationsCommand(hotels));
        for(Hotel hotel: hotels){
            commands.put(Integer.toString(i++), new FeedbackCommand(hotel));
        }
        show();
    }

    public void show() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while(true){
            for(Map.Entry<String, Command> command: this.commands.entrySet()) {
                System.out.println(command.getKey() + " : " + command.getValue().Display());
            }
            System.out.println(">> ");
            String key = scanner.nextLine();
            Command command = commands.get(key);
            if(command == null){
                System.out.println("Invalid Option");
                continue;
            }
            command.execute();
        }
    }


}
