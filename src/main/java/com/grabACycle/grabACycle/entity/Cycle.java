package com.grabACycle.grabACycle.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor

@Builder
@Entity
@Table(name = "Cycle")
public class Cycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    @NotEmpty(message = "Cycle name should not be blank.")
    @Size(min = 2, message = "Cycle name should atleast contain 2 characters.")
    private String name;

    @Column(name = "model", nullable = false)
    @NotEmpty(message = "Cycle model should not be blank.")
    @Size(min = 2, message = "Cycle model should atleast contain 2 characters.")
    private String model;

    @Column(name = "type", length = 100)
    @NotEmpty(message = "Cycle type should not be blank.")
    @Size(min = 2, message = "Cycle type should atleast contain 2 characters.")
    private String type;

    @Column(name="booking_status", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean bookingStatus;

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

    @Override
    public String toString() {
        return "Cycle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", bookingStatus=" + bookingStatus +
                '}';
    }

    public void setBookingStatus(boolean bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
