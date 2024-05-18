package Repo;

import Domain.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class Repository {
    ConnectionSource connectionSource;
    Dao<Hotel, Integer> hotelDao;
    Dao<Room, Integer> roomDao;
    Dao<Reservation, Integer> reservationDao;
    Dao<Feedback, Integer> feedbackDao;

    public Repository() throws SQLException {
        this.connectionSource = new JdbcConnectionSource("jdbc:sqlite:database.db");
        this.hotelDao = DaoManager.createDao(connectionSource, Hotel.class);
        this.roomDao = DaoManager.createDao(connectionSource, Room.class);
        this.reservationDao = DaoManager.createDao(connectionSource, Reservation.class);
        this.feedbackDao = DaoManager.createDao(connectionSource, Feedback.class);
        TableUtils.createTableIfNotExists(connectionSource, Hotel.class);
        TableUtils.createTableIfNotExists(connectionSource, Room.class);
//        TableUtils.dropTable(connectionSource, Reservation.class, true);
        TableUtils.createTableIfNotExists(connectionSource, Reservation.class);
        TableUtils.createTableIfNotExists(connectionSource, Feedback.class);
        if (hotelDao.queryForAll().isEmpty()) {
            loadData("hotels.json");
        }
    }

    private void loadData(String path) {
        File file = new File(path);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            HotelDto[] hotels = objectMapper.readValue(file, HotelDto[].class);
            for (HotelDto hotel : hotels) {
                Hotel hotelEntity = new Hotel(hotel.getId(), hotel.getName(), hotel.getLatitude(), hotel.getLongitude());
                hotelDao.create(hotelEntity);
                for (int i = 0; i < hotel.getRooms().length; i++) {
                    Room room = new Room(hotel.getRooms()[i].getRoomNumber(), hotel.getRooms()[i].getType(), hotel.getRooms()[i].getPrice(), hotelEntity);
                    roomDao.create(room);
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Hotel> getAllHotels() throws SQLException {
        return hotelDao.queryForAll();
    }

    public List<Room> getRoomsForHotel(Hotel hotel) throws SQLException {
        return roomDao.queryForEq("hotel_id", hotel.getId());
    }

    public List<Reservation> getReservationsForRoom(Room room) throws SQLException {
        return reservationDao.queryForEq("room_id", room.getId());
    }

    public void addReservation(Reservation reservation) throws SQLException {
        reservationDao.create(reservation);
    }

    public Collection<Reservation> getReservationsForRoomOfUser(Room room, String username) throws SQLException {
        return reservationDao.queryBuilder()
                .where()
                .eq("room_id", room.getId())
                .and()
                .eq("username", username)
                .query();
    }

    public void deleteReservation(Reservation reservation) {
        try {
            reservationDao.delete(reservation);
        } catch (SQLException ignored) {
        }
    }

    public void updateReservation(Reservation reservation) {
        try {
            reservationDao.update(reservation);
        } catch (SQLException ignored) {
        }
    }

    public void addFeedback(Feedback feedback) throws SQLException {
        feedbackDao.create(feedback);
    }
}
