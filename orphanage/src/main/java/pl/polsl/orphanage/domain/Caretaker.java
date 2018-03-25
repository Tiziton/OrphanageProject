package pl.polsl.orphanage.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Caretaker")
public class Caretaker implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Basic(optional = false)
    @Column(name = "Name")
    private String name;

    @Basic(optional = false)
    @Column(name = "Lastname")
    private String lastname;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "User_Id", nullable = false, unique = true)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caretaker", fetch = FetchType.LAZY)
    private Set<Fosterling> fosterlings = new HashSet<>();

    public Caretaker() {
    }

    public Caretaker(Long id) {
        this.id = id;
    }

    public Caretaker(Long id, String name, String lastnamer) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        Caretaker employee = (Caretaker) o;
        if (employee.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), employee.getId());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", lastName='" + getLastname() + "'" +
                "}";
    }

}
