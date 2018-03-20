package pl.polsl.orphanage.service.dto;

import java.util.Date;
import java.util.Objects;

/**
 * A DTO for the Document entity.
 */
public class DocumentDTO {

    private Long id;
    private Date date;
    private String type;
    private byte[] fileContetnt;
    private String fileExtension;
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

    public Long getFosterlingId() {
        return fosterlingId;
    }

    public void setFosterlingId(Long fostterlingId) {
        this.fosterlingId = fostterlingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DocumentDTO country = (DocumentDTO) o;
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

        return "Document{" +
                "id=" + getId() +
                ", date='" + getDate() + "'" +
                ", type='" + getType() + "'" +
                ", file='" + getFileContetnt() != null ? "document exists": "document doesn`t exist"+ "'" +
                ", type='" + getType() + "'" +
                ", fosterlingId='" + getFosterlingId() + "'" +
                "}";
    }
}
