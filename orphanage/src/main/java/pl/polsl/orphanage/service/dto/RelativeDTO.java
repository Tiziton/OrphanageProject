package pl.polsl.orphanage.service.dto;

import java.util.Objects;

/**
 * A DTO for the Relative entity.
 */
public class RelativeDTO {

    private Long id;
    private String name;
    private String lastname;
    private Character gender;
    private String typeRelation;

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

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getTypeRelation() {
        return typeRelation;
    }

    public void setTypeRelation(String typeRelation) {
        this.typeRelation = typeRelation;
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
        RelativeDTO jobHistory = (RelativeDTO) o;
        if (jobHistory.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jobHistory.getId());
    }

    @Override
    public String toString() {
        return "Relative{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", lastname='" + getLastname() + "'" +
                ", gender='" + getGender() + "'" +
                ", typeRelation='" + getTypeRelation() + "'" +
                "}";
    }
}
