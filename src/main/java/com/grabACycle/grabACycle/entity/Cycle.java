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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cycleId;

    @Column(name = "cycleName", nullable = false, length = 50)
    private String cycleName;

    @Column(name = "cycleModel", nullable = false, length = 10)
    private String cycleModel;

    @Column(name = "cycleType", length = 100)
    private String cycleType;

    @Column(name = "bookingStatus")
    @ColumnDefault("false")
    private boolean bookingStatus;

    public int getCycleId() {
        return cycleId;
    }

    public String getCycleName() {
        return cycleName;
    }

    public String getCycleModel() {
        return cycleModel;
    }

    public String getCycleType() {
        return cycleType;
    }

    public boolean isBookingStatus() {
        return bookingStatus;
    }

    public void setCycleId(int cycleId) {
        this.cycleId = cycleId;
    }

    public void setCycleName(String cycleName) {
        this.cycleName = cycleName;
    }

    public void setCycleModel(String cycleModel) {
        this.cycleModel = cycleModel;
    }

    public void setCycleType(String cycleType) {
        this.cycleType = cycleType;
    }

    public void setBookingStatus(boolean bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
