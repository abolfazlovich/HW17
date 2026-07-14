package ir.maktabsharif.repository.impl;

import ir.maktabsharif.enums.EventStatus;
import ir.maktabsharif.exception.EventNotFoundException;
import ir.maktabsharif.exception.StatmentConnectionException;
import ir.maktabsharif.model.Event;
import ir.maktabsharif.repository.GenericRepository;
import ir.maktabsharif.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventRepositoryImpl implements GenericRepository<Event, Long> {
    @Override
    public Event save(Event event) {
        Connection connection = DatabaseConnection.getConnection();
        String Query = "insert into event(title, location, capacity, reserved_count, ticket_price, status) VALUES (?,?,?,?,?,?) RETURNING id;";
        try {
            PreparedStatement ps = connection.prepareStatement(Query);
            ps.setString(1, event.getTitle());
            ps.setString(2, event.getLocation());
            ps.setInt(3, event.getCapacity());
            ps.setInt(4, event.getReservedCount());
            ps.setDouble(5, event.getTicketPrice());
            ps.setString(6, event.getStatus().toString());
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
    public void update(Event event,Long aLong) {
Connection connection = DatabaseConnection.getConnection();
String query = "update event set title =?,location = ?,capacity = ?, reserved_count = ?,ticket_price = ?,status = ? where id =?; ";
try{
    PreparedStatement ps = connection.prepareStatement(query);
    ps.setString(1,event.getTitle());
    ps.setString(2,event.getLocation());
    ps.setInt(3,event.getCapacity());
    ps.setInt(4,event.getReservedCount());
    ps.setDouble(5,event.getTicketPrice());
    ps.setString(6,event.getStatus().toString());
    ps.setLong(7,aLong);
    int rowsAffected = ps.executeUpdate();
    if(rowsAffected == 1){
        System.out.println("Event Updated");
    }
    else
        throw new EventNotFoundException("Event Not Found Exception");
}
catch (SQLException e) {
    throw new StatmentConnectionException("Statement Connection Exception" + e.getMessage());
}
    }

    @Override
    public void delete(Long aLong) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "update event set status =CANCELED where id= ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1,aLong);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Event Canceled");
            } else
                throw new EventNotFoundException("Event Not Found Exception");
        } catch (SQLException e) {
            throw new StatmentConnectionException("Statement Connection Exception" + e.getMessage());
        }
    }

    @Override
    public Event findById(Long aLong) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "select * from event where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, aLong);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Event event = new Event(rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getDouble(6),
                        EventStatus.valueOf(rs.getString(7)));
                event.setId(rs.getLong(1));
                return event;
            } else
                throw new EventNotFoundException("Event Not Found Exception");
        } catch (SQLException e) {
            throw new StatmentConnectionException("Statement Connection Exception" + e.getMessage());
        }
    }

    @Override
    public List<Event> findAll() {
        Connection connection = DatabaseConnection.getConnection();
        List<Event> events = new ArrayList<>();
        String query = "select * from event";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()){
                throw new EventNotFoundException("Event Not Found Exception");
            }
            while (rs.next()) {
                events.add(findById(rs.getLong(1)));
            }
            return events;
        } catch (SQLException e) {
            throw new StatmentConnectionException("Statement Connection Exception" + e.getMessage());
        }

    }
}
