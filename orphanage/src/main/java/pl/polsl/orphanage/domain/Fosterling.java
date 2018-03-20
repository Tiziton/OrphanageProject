package pl.polsl.orphanage.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Fosterling")
public class Fosterling implements Serializable {

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
    @Column(name = "Date_Birth")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBirth;

    @Column(name = "Legal_status")
    private String legalStatus;

    @JoinTable(name = "Relationship", joinColumns = {
            @JoinColumn(name = "Fosterling_Id", referencedColumnName = "Id")}, inverseJoinColumns = {
            @JoinColumn(name = "Relative_id", referencedColumnName = "Id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Relative> relatives;

    @JoinTable(name = "Sibilings", joinColumns = {
            @JoinColumn(name = "Fosterling_Id", referencedColumnName = "Id")}, inverseJoinColumns = {
            @JoinColumn(name = "Sibiling_id", referencedColumnName = "Id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Fosterling> sibilings;

    /*@ManyToMany(mappedBy = "fosterlingCollection", fetch = FetchType.LAZY)
    private Set<FosterlingRepository> fosterlingCollection1;*/

    @JoinColumn(name = "Caretaker_Id", referencedColumnName = "Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Caretaker caretaker;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fosterling", fetch = FetchType.LAZY)
    private Set<Holiday> holidays;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fosterling", fetch = FetchType.LAZY)
    private Set<Document> documents;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fosterling", fetch = FetchType.LAZY)
    private Set<Reward> rewards;

    public Fosterling() {  }

    public Fosterling(Long id) {
        this.id = id;
    }

    public Fosterling(Long id, String name, String lastname, Date dateBirth) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.dateBirth = dateBirth;
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

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getLegalstatus() {
        return legalStatus;
    }

    public void setLegalstatus(String legalStatus) {
        this.legalStatus = legalStatus;
    }

    public Set<Relative> getRelatives() {
        return relatives;
    }

    public void setRelatives(Set<Relative> relatives) {
        this.relatives = relatives;
    }

    public Set<Fosterling> getSibilings() {
        return sibilings;
    }

    public void setSibilings(Set<Fosterling> sibilings) {
        this.sibilings = sibilings;
    }

    /*public Set<FosterlingRepository> getFosterlingCollection1() {
        return fosterlingCollection1;
    }

    public void setFosterlingCollection1(Collection<FosterlingRepository> fosterlingCollection1) {
        this.fosterlingCollection1 = fosterlingCollection1;
    }*/

    public Caretaker getCaretaker() {
        return caretaker;
    }

    public void setCaretaker(Caretaker caretaker) {
        this.caretaker = caretaker;
    }

    public Set<Holiday> getHolidays() {
        return holidays;
    }

    public void setHolidays(Set<Holiday> holidays) {
        this.holidays = holidays;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Set<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(Set<Reward> rewards) {
        this.rewards = rewards;
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
        Fosterling jobHistory = (Fosterling) o;
        if (jobHistory.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jobHistory.getId());
    }

    @Override
    public String toString() {

        return "FosterlingRepository{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", lastname='" + getLastname() + "'" +
                ", dateBirth='" + getDateBirth() + "'" +
                ", legalStatus='" + getLegalstatus() + "'" +
                "}";
    }

}

