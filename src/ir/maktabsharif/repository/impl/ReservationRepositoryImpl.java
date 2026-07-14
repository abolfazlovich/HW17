package ir.maktabsharif.repository.impl;

import ir.maktabsharif.enums.ReservationStatus;
import ir.maktabsharif.exception.EventNotFoundException;
import ir.maktabsharif.exception.ReservationNotFoundException;
import ir.maktabsharif.exception.StatmentConnectionException;
import ir.maktabsharif.model.Event;
import ir.maktabsharif.model.Reservation;
import ir.maktabsharif.repository.GenericRepository;
import ir.maktabsharif.util.DatabaseConnection;

import java.sql.*;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationRepositoryImpl implements GenericRepository<Reservation, Long> {
    @Override
    public Reservation save(Reservation reservation) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "insert into reservation (customer_name, customer_phone, event_id, ticket_count," +
                " reservation_date, status) VALUES (?,?,?,?,?,?) ruturning id";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, reservation.getCustomerName());
            ps.setString(2, reservation.getCustomerPhone());
            ps.setLong(3, reservation.getEventId());
            ps.setInt(4, reservation.getTicketCount());
            ps.setDate(5, Date.valueOf(reservation.getReservationDate()));
            ps.setString(6, reservation.getStatus().toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return findById(rs.getLong(1));
            }
            return null;
        } catch (SQLException e) {
            throw new StatmentConnectionException("Statement Connection Exception" + e.getMessage());
        }
    }

    @Override
    public void update(Reservation reservation,Long aLong) {
Connection connection = DatabaseConnection.getConnection();
String query ="update reservation set customer_name = ?,customer_phone =?,event_id =?,ticket_count =?,reservation_date =?,status =? where id =?;";
try {
    PreparedStatement ps = connection.prepareStatement(query);
    ps.setString(1,reservation.getCustomerName());
    ps.setString(2,reservation.getCustomerPhone());
    ps.setLong(3,reservation.getEventId());
    ps.setInt(4,reservation.getTicketCount());
    ps.setDate(5,Date.valueOf(reservation.getReservationDate()));
    ps.setString(6,reservation.getStatus().toString());
    ps.setLong(7,aLong);
    int rowsAffected = ps.executeUpdate();
    if(rowsAffected == 1){
        System.out.println("Reservation Updated");
    }
    else
        throw  new ReservationNotFoundException("Reservation Not Found Exception");

} catch (SQLException e) {
    throw new StatmentConnectionException("Statement Connection Exception" + e.getMessage());}
    }

    @Override
    public void delete(Long aLong) {
Connection connection = DatabaseConnection.getConnection();
String query = "delete from reservation where id = ?";
try {
    PreparedStatement ps = connection.prepareStatement(query);
    ps.setLong(1,aLong);
    int rowsAffected = ps.executeUpdate();
    if(rowsAffected == 1){
        System.out.println("Reservation Deleted");
    }
    else
        throw  new ReservationNotFoundException("Reservation Not Found Exception");
}
catch (SQLException e) {
    throw new StatmentConnectionException("Statement Connection Exception" + e.getMessage());
}
    }

    @Override
    public Reservation findById(Long aLong) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "select * from reservation where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, aLong);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Reservation reservation = new Reservation(rs.getString(2),
                        rs.getString(3),
                        rs.getLong(4),
                        rs.getInt(5),
                        rs.getDate(6).toLocalDate(),
                        ReservationStatus.valueOf(rs.getString(7)));
                reservation.setId(rs.getLong(1));
                return reservation;
            } else
                throw new ReservationNotFoundException("Reservation Not Found Exception");
        } catch (SQLException e) {
            throw new StatmentConnectionException("Statement Connection Exception" + e.getMessage());
        }
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String query = "select * from reservation";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new ReservationNotFoundException("Reservation Not Found Exception");
            }
            while (rs.next()) {
                reservations.add(findById(rs.getLong(1)));
            }
            return reservations;

        } catch (SQLException e) {
            throw new StatmentConnectionException("Statement Connection Exception" + e.getMessage());
        }
    }
}
