package pl.polsl.orphanage.web.rest.requestbody;

public class RelativeRequestBody {
    private Long id;
    private Long sibilingId;

    public RelativeRequestBody(Long id, Long sibilingId) {
        this.id = id;
        this.sibilingId = sibilingId;
    }
    
    public RelativeRequestBody(){
    
    }

    public Long getId() {
        return id;
    }

    public Long getSibilingId() {
        return sibilingId;
    }
}
