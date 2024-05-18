package Service;

import Domain.Feedback;
import Domain.Hotel;
import Domain.Reservation;
import Domain.Room;
import Repo.Repository;
import io.ipgeolocation.api.Geolocation;
import io.ipgeolocation.api.GeolocationParams;
import io.ipgeolocation.api.IPGeolocationAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Service {
    private final Repository repository;
    private static Service instance = null;
    private Service(Repository repository) {
        this.repository = repository;
    }

    public static Service getInstance(Repository repository) {
        if(instance == null) {
            instance = new Service(repository);
        }
        return instance;
    }

    public static Service getInstance() {
        if(instance == null) {
            throw new RuntimeException("Service not initialized");
        }
        return instance;
    }

    public List<Hotel> getNearbyHotels(int radiusInKilometers) throws SQLException, UnknownHostException {
        double[] coordinates = getCoordinates();
        if (coordinates == null) {
            throw new RuntimeException("Could not get coordinates");
        }
        List<Hotel> allHotels = repository.getAllHotels();
        List<Hotel> nearbyHotels = new ArrayList<>();
        for(Hotel hotel : allHotels) {
            double distance = calculateDistance(coordinates, hotel.getLatitude(), hotel.getLongitude());
            if(distance < radiusInKilometers) {
                nearbyHotels.add(hotel);
            }
        }
        return nearbyHotels;
    }

    public List<Room> getAvailableRooms(Hotel hotel, Date startTime, Date endTime) throws SQLException {
        List<Room> rooms = repository.getRoomsForHotel(hotel);
        List<Room> availableRooms = new ArrayList<>();
        for(Room room : rooms) {
            List<Reservation> reservations = repository.getReservationsForRoom(room);
            boolean available = true;
            for(Reservation reservation : reservations) {
                if(startTime.before(reservation.getEndDate()) && endTime.after(reservation.getStartDate())) {
                    available = false;
                    break;
                }
            }
            if(available) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    private double[] getCoordinates() throws UnknownHostException {
        IPGeolocationAPI api = new IPGeolocationAPI("f843370ec9234a5bb467d2b328b200bd\n");
        GeolocationParams geoParams = new GeolocationParams();
        geoParams.setIPAddress(getIpAddress());
        geoParams.setFields("geo,lat,lng");

        Geolocation geolocation = api.getGeolocation(geoParams);
        try {
            return new double[]{Double.parseDouble(geolocation.getLatitude()), Double.parseDouble(geolocation.getLongitude())};
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private double calculateDistance(double[] coordinates1, double latitude2, double longitude2) {
        double earthRadius = 6371;
        double lat1 = Math.toRadians(coordinates1[0]);
        double lon1 = Math.toRadians(coordinates1[1]);
        double lat2 = Math.toRadians(latitude2);
        double lon2 = Math.toRadians(longitude2);
        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;
    }

    private String getIpAddress() throws UnknownHostException {
        InetAddress localhost = InetAddress.getLocalHost();
        String systemipaddress = "";
        try {
            URL url_name = new URL("http://checkip.amazonaws.com/");

            BufferedReader sc =
                    new BufferedReader(new InputStreamReader(url_name.openStream()));

            // reads system IPAddress
            systemipaddress = sc.readLine().trim();
        } catch (Exception e) {
            throw new RuntimeException("Could not get IP address");
        }
        return systemipaddress;
    }

    public void bookRoom(Room room, Date startDate, Date endDate, String username) throws SQLException {
        Reservation reservation = new Reservation(room, startDate, endDate, username);
        repository.addReservation(reservation);
    }

    public List<Reservation> getAllReseravtionFromListOfHotels(List<Hotel> hotels, String username) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        for(Hotel hotel : hotels) {
            List<Room> rooms = repository.getRoomsForHotel(hotel);
            for(Room room : rooms) {
                reservations.addAll(repository.getReservationsForRoomOfUser(room, username));
            }
        }
        return reservations;
    }

    public void cancelReservation(Reservation reservation) {
        repository.deleteReservation(reservation);
    }

    public void updateReservation(Reservation reservation, Room room) {
        reservation.setRoom(room);
        repository.updateReservation(reservation);
    }

    public void addFeedback(Hotel hotel, String username, String servicesFeedback, String cleaningFeedback) throws SQLException {
        Feedback feedback = new Feedback(hotel, username, servicesFeedback, cleaningFeedback);
        repository.addFeedback(feedback);
    }
}
