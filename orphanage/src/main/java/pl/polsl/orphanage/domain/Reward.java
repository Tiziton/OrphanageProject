package pl.polsl.orphanage.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Rewards")
public class Reward implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Basic(optional = false)
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "Type")
    private String type;

    @Column(name = "Notes")
    private String notes;

    @JoinColumn(name = "Fosterling_id", referencedColumnName = "Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Fosterling fosterling;

    public Reward() { }

    public Reward(Long id) {
        this.id = id;
    }

    public Reward(Long id, Date date) {
        this.id = id;
        this.date = date;
    }

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

    public Fosterling getFosterling() {
        return fosterling;
    }

    public void setFosterling(Fosterling fosterling) {
        this.fosterling = fosterling;
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
        Reward jobHistory = (Reward) o;
        if (jobHistory.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jobHistory.getId());
    }

    @Override
    public String toString() {

        return "Reward{" +
                "id=" + getId() +
                ", date='" + getDate() + "'" +
                ", type='" + getType() + "'" +
                ", notes='" + getNotes() + "'" +
                "}";
    }

}

