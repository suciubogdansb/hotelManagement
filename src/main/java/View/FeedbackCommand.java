package View;

import Domain.Hotel;
import Service.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class FeedbackCommand implements Command{
    private Hotel hotel;

    public FeedbackCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void execute() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Services feedback >>");
        String serviceFeedback = scanner.nextLine();
        System.out.println("Cleanliness feedback >>");
        String cleanlinessFeedback = scanner.nextLine();
        Service.getInstance().addFeedback(hotel, "mockUser", serviceFeedback, cleanlinessFeedback);
        System.out.println("Feedback added successfully! Thank you!");
    }

    @Override
    public String Display() {
        return "Feedback for the hotel " + hotel.getName();
    }
}
