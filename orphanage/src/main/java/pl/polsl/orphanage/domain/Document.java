package pl.polsl.orphanage.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Documents")
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Basic(optional = false)
    @Column(name = "Type")
    private String type;

    @Lob
    @Column(name = "File_Contetnt")
    private byte[] fileContetnt;

    @Column(name = "File_Extension")
    private String fileExtension;

    @JoinColumn(name = "Fosterling_Id", referencedColumnName = "Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Fosterling fosterling;

    public Document() { }

    public Document(Long id) {
        this.id = id;
    }

    public Document(Long id, String type) {
        this.id = id;
        this.type = type;
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

    public byte[] getFileContetnt() {
        return fileContetnt;
    }

    public void setFileContetnt(byte[] fileContetnt) {
        this.fileContetnt = fileContetnt;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
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
        Document country = (Document) o;
        if (country.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), country.getId());
    }

    @Override
    public String toString() {

        return "Document{" +
                "id=" + getId() +
                ", date='" + getDate() + "'" +
                ", type='" + getType() + "'" +
                ", file='" + getFileContetnt() != null ? "document exists": "document doesn`t exist"+ "'" +
                ", type='" + getType() + "'" +
                "}";
    }

}

