package http.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by alber on 5/24/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WrapperResponse {
    @JsonProperty
    private final String url;
    @JsonProperty
    private final String payload;

    public WrapperResponse(String url, String payload) {
        this.url = url;
        this.payload = payload;
    }

    public String getUrl() {
        return url;
    }

    public String getPayload() {
        return payload;
    }
}
