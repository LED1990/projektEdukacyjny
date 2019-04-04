package mainapp.rest.spring.wyjatkiHandler;

import org.springframework.http.HttpStatus;

public class KomunikatBledu {
    private String komunikat;
    private HttpStatus status;

    public KomunikatBledu(String komunikat, HttpStatus status) {
        this.komunikat = komunikat;
        this.status = status;
    }

    public String getKomunikat() {
        return komunikat;
    }

    public void setKomunikat(String komunikat) {
        this.komunikat = komunikat;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
