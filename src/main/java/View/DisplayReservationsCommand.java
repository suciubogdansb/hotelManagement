package View;

import Domain.Hotel;
import Domain.Reservation;
import Service.Service;

import java.sql.SQLException;
import java.util.List;

public class DisplayReservationsCommand implements Command{
    List<Hotel> hotels;

    public DisplayReservationsCommand(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    @Override
    public void execute() throws SQLException {
        List<Reservation> reservations = Service.getInstance().getAllReseravtionFromListOfHotels(hotels, "mockUser");
        ReservationListView reservationListView = new ReservationListView(reservations);
    }

    @Override
    public String Display() {
        return "Display reservations for hotels.";
    }
}
