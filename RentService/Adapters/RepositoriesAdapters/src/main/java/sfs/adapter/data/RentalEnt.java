package sfs.adapter.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "rentals")
public class RentalEnt {
    @Id
    private String id;
    private String clientId;
    private String facilityId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public RentalEnt(String clientId, String facilityId, LocalDateTime startTime, LocalDateTime endTime) {
        this.clientId = clientId;
        this.facilityId = facilityId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    //    public UUID getId() {
//        return id;
//    }
//
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    public UUID getClientId() {
//        return clientId;
//    }
//
//    public void setClientId(UUID clientId) {
//        this.clientId = clientId;
//    }
//
//    public UUID getFacilityId() {
//        return facilityId;
//    }
//
//    public void setFacilityId(UUID facilityId) {
//        this.facilityId = facilityId;
//    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean overlaps(LocalDateTime otherStart, LocalDateTime otherEnd) {
        return this.startTime.isBefore(otherEnd) && otherStart.isBefore(this.endTime);
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", facilityId=" + facilityId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
