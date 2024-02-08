package com.grabACycle.grabACycle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Cycle")
public class Cycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "type", length = 100)
    private String type;

    @Column(name="booking_status", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean bookingStatus;


//    @Column(name = "booked_by")
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "booked_by", referencedColumnName = "id")
//    private int bookedBy;


    public Cycle(String name, String model, String type, boolean bookingStatus) {
        this.name = name;
        this.model = model;
        this.type = type;
        this.bookingStatus = bookingStatus;
    }

    public Cycle() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(boolean bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
