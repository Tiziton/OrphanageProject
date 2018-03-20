package pl.polsl.orphanage.service.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the Fosterling entity.
 */
public class FosterlingDTO {

    private Long id;
    private String name;
    private String lastname;
    private Date dateBirth;
    private String legalStatus;
    private Long caretakerId;
    private Set<RelativeDTO> relatives = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getLegalStatus() {
        return legalStatus;
    }

    public void setLegalStatus(String legalStatus) {
        this.legalStatus = legalStatus;
    }

    public Long getCaretakerId() {
        return caretakerId;
    }

    public void setCaretakerId(Long caretakerId) {
        this.caretakerId = caretakerId;
    }

    public Set<RelativeDTO> getRelatives() {
        return relatives;
    }

    public void setRelatives(Set<RelativeDTO> relatives) {
        this.relatives = relatives;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FosterlingDTO country = (FosterlingDTO) o;
        if (country.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), country.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {

        return "FosterlingRepository{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", lastname='" + getLastname() + "'" +
                ", dateBirth='" + getDateBirth() + "'" +
                ", legalStatus='" + getLegalStatus() + "'" +
                ", caretakerId='" + getCaretakerId() + "'" +
                "}";
    }
}
