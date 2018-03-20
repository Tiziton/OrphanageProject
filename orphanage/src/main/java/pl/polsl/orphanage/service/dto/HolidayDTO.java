package pl.polsl.orphanage.service.dto;

import java.util.Date;
import java.util.Objects;

/**
 * A DTO for the Holiday entity.
 */
public class HolidayDTO {

    private Long id;
    private Date startDate;
    private Date endDate;
    private Boolean isReturned;
    private String notes;
    private Long fosterlingId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getReturned() {
        return isReturned;
    }

    public void setReturned(Boolean returned) {
        isReturned = returned;
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

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HolidayDTO jobHistory = (HolidayDTO) o;
        if (jobHistory.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jobHistory.getId());
    }

    @Override
    public String toString() {
        return "Holiday{" +
                "id=" + getId() +
                ", startDate='" + getStartDate() + "'" +
                ", endDate='" + getEndDate() + "'" +
                ", isReturned='" + getReturned() + "'" +
                ", notes='" + getNotes() + "'" +
                ", fosterlingId='" + getFosterlingId() + "'" +
                "}";
    }
}
