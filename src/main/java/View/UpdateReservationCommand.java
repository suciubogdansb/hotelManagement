package View;

import Domain.Reservation;

import java.sql.SQLException;

public class UpdateReservationCommand implements Command {
    private Reservation reservation;
    public UpdateReservationCommand(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public void execute() throws SQLException {
        UpdateReservationView updateReservationView = new UpdateReservationView(reservation);
    }

    @Override
    public String Display() {
        return "Update reservation";
    }
}
