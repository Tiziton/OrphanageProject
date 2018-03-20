package pl.polsl.orphanage.web.rest.util;

import org.springframework.http.HttpHeaders;

public class HeaderUtil {

    private static final String APPLICATION_NAME = "Orphanage Project";

    private HeaderUtil() {
    }

    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-alert", message);
        headers.add("X-params", param);
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".created", param);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".updated", param);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".deleted", param);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-error", "error." + errorKey);
        headers.add("X-params", entityName);
        return headers;
    }
}
