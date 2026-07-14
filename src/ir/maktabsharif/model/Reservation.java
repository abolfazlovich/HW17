package ir.maktabsharif.model;

import ir.maktabsharif.enums.ReservationStatus;

import java.time.LocalDate;

public class Reservation {
    private Long id;
    private String customerName;
    private String customerPhone;
    private Long eventId;
    private Integer ticketCount;
    private LocalDate reservationDate;
    private ReservationStatus status;

    public Reservation(String customerName, String customerPhone, Long eventId, Integer ticketCount, LocalDate reservationDate, ReservationStatus status) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.eventId = eventId;
        this.ticketCount = ticketCount;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Integer getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", eventId=" + eventId +
                ", ticketCount=" + ticketCount +
                ", reservationDate=" + reservationDate +
                ", status=" + status +
                '}'+'\'';
    }
}
