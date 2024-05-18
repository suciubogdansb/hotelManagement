package View;

import Domain.Reservation;
import Service.Service;

public class CancelReservationCommand implements Command {
    private Reservation reservation;
    public CancelReservationCommand(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public void execute() {
        Service.getInstance().cancelReservation(reservation);
    }

    @Override
    public String Display() {
        return "Cancel reservation";
    }
}
