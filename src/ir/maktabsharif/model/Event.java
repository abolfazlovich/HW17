package ir.maktabsharif.model;

import ir.maktabsharif.enums.EventStatus;

public class Event {
    private Long id;
    private String title;
    private String location;
    private Integer capacity;
    private Integer reservedCount;
    private Double ticketPrice;
    private EventStatus status;

    public Event(String title, String location, Integer capacity, Integer reservedCount, Double ticketPrice, EventStatus status) {
        this.title = title;
        this.location = location;
        this.capacity = capacity;
        this.reservedCount = reservedCount;
        this.ticketPrice = ticketPrice;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getReservedCount() {
        return reservedCount;
    }

    public void setReservedCount(Integer reservedCount) {
        this.reservedCount = reservedCount;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", reservedCount=" + reservedCount +
                ", ticketPrice=" + ticketPrice +
                ", status=" + status +
                '}'+'\'';
    }
}
