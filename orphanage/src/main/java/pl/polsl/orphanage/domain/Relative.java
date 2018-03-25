package pl.polsl.orphanage.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Relatives")
public class Relative implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Basic(optional = false)
    @Column(name = "Name")
    private String name;

    @Basic(optional = false)
    @Column(name = "Lastname")
    private String lastname;

    @Basic(optional = false)
    @Column(name = "Gender")
    private Character gender;

    @Basic(optional = false)
    @Column(name = "TypeRelation")
    private String typeRelation;

    @ManyToMany(mappedBy = "relatives", fetch = FetchType.EAGER)
    private Set<Fosterling> fosterlings;

    public Relative() { }

    public Relative(Long id) {
        this.id = id;
    }

    public Relative(Long id, String name, String lastname, Character gender, String typeRelation) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.gender = gender;
        this.typeRelation = typeRelation;
    }

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

    public Set<Fosterling> getFosterlings() {
        return fosterlings;
    }

    public void setFosterlings(Set<Fosterling> fosterlings) {
        this.fosterlings = fosterlings;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Relative jobHistory = (Relative) o;
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
