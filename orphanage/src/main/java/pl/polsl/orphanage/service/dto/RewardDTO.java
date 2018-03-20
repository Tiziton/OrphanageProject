package pl.polsl.orphanage.service.dto;

import java.util.Date;

/**
 * A DTO for the Reward entity.
 */
public class RewardDTO {

    private Long id;
    private Date date;
    private String type;
    private String notes;
    private Long fosterlingId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getFosterlingId() {
        return fosterlingId;
    }

    public void setFosterlingId(Long fosterlingId) {
        this.fosterlingId = fosterlingId;
    }
}
