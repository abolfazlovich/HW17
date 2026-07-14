package ir.maktabsharif.service;

import ir.maktabsharif.exception.CapacityExceededException;
import ir.maktabsharif.exception.EventCancelledException;
import ir.maktabsharif.model.Event;
import ir.maktabsharif.model.Reservation;
import ir.maktabsharif.repository.impl.EventRepositoryImpl;
import ir.maktabsharif.repository.impl.ReservationRepositoryImpl;

import java.util.List;

public class ReservationService {
    ReservationRepositoryImpl reservationRepository = new ReservationRepositoryImpl();
    EventRepositoryImpl eventRepository = new EventRepositoryImpl();

    public void create(Reservation reservation) {
        Event event = eventRepository.findById(reservation.getEventId());
        if (event.getStatus().toString().equalsIgnoreCase("canceled")) {
            throw new EventCancelledException("Event Cancelled Exception");
        }
        if (event.getCapacity() <= reservation.getTicketCount()) {
            throw new CapacityExceededException("Capacity Exceeded Exception");
        }
        reservation.setTicketCount(reservation.getTicketCount() + 1);
        event.setReservedCount(event.getReservedCount() + 1);
        reservationRepository.save(reservation);
        eventRepository.update(event, reservation.getEventId());
    }

    public List<Reservation> showAll() {
List<Reservation> reservationList = reservationRepository.findAll();
        System.out.println(reservationList);
        return reservationList;
    }

    public void update(Reservation reservation, Long id) {
reservationRepository.update(reservation,id);
    }

    public void cancel(Long id) {
Reservation reservation = reservationRepository.findById(id);
Event event =eventRepository.findById(reservation.getEventId());
reservationRepository.delete(id);
event.setReservedCount(event.getReservedCount()-1);
eventRepository.update(event, reservation.getEventId());
    }
}
