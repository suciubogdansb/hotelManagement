package View;

import Domain.Reservation;

import java.sql.SQLException;

public class ReservationCommand implements Command{
    private Reservation reservation;

    public ReservationCommand(Reservation reservation) {
        this.reservation = reservation;
    }
    @Override
    public void execute() throws SQLException {
        ReservationSectionView reservationSectionView = new ReservationSectionView(reservation);
    }

    @Override
    public String Display() {
        return "Reservation for hotel " + reservation.getRoom().getHotel().getName() + " from " + reservation.getStartDate() +
        " to " + reservation.getEndDate() + " for room " + reservation.getRoom().getNumber();
    }
}
