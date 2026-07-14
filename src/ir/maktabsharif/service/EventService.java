package ir.maktabsharif.service;

import ir.maktabsharif.exception.BusinessException;
import ir.maktabsharif.model.Event;
import ir.maktabsharif.repository.impl.EventRepositoryImpl;

import java.util.List;

public class EventService {
    EventRepositoryImpl eventRepository = new EventRepositoryImpl();
    public void create(Event event) {
        if (event.getTitle() == null) {
            throw new BusinessException("Title Is Empty");
        }
        if (event.getCapacity() < 0) {
            throw new BusinessException("Capacity Is Negative");
        }
        if (event.getTicketPrice() < 0) {
            throw new BusinessException("Ticket Is Negative");
        }
        eventRepository.save(event);
    }

    public void showAll() {
List<Event> eventList =eventRepository.findAll();
        System.out.println(eventList);
    }

    public void update(Event event, Long id) {
        if (event.getTitle() == null) {
            throw new BusinessException("Title Is Empty");
        }
        if (event.getCapacity() < 0) {
            throw new BusinessException("Capacity Is Negative");
        }
        if (event.getTicketPrice() < 0) {
            throw new BusinessException("Ticket Is Negative");
        }
        eventRepository.update(event,id);
    }

    public void cancel(Long id) {
eventRepository.delete(id);
    }
}
