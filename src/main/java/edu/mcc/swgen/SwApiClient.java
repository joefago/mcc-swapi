package edu.mcc.swgen;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SwApiClient {

    private final RestTemplate restTemplate;

    SwApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SwApiCharacterList retrieveCharacters(Integer page) {
        String url = "/people/?page={page}";

        Map<String, Object> params = new HashMap<>();
        params.put("page", page == null ? 1 : page);

        String userAgent = "java/" + System.getProperty("java.version");
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", userAgent);
        HttpEntity entity = new HttpEntity(headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, SwApiCharacterList.class, params).getBody();
    }
}
