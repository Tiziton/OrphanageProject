package pl.polsl.orphanage.web.rest.requestbody;

public class SibilingRequestBody {

    private Long id;
    private Long sibilingId;

    public SibilingRequestBody(Long id, Long sibilingId) {
        this.id = id;
        this.sibilingId = sibilingId;
    }

    public SibilingRequestBody() {

    }

    public Long getId() {
        return id;
    }

    public Long getSibilingId() {
        return sibilingId;
    }
}
