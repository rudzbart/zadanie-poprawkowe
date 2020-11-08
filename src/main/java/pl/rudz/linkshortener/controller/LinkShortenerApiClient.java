package pl.rudz.linkshortener.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.rudz.linkshortener.model.LinkShortener;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class LinkShortenerApiClient {

    private RestTemplate restTemplate = new RestTemplate();

    public LinkShortenerApiClient() {
    }

    public String getShortenedUrl(String urlToShorten) throws URISyntaxException {
        LinkShortener linkShortener = new LinkShortener();

        linkShortener.setUrl(urlToShorten);

        final String url = "https://cleanuri.com/api/v1/shorten";
        URI uri = new URI(url);
        ResponseEntity<String> result = restTemplate.postForEntity(uri, linkShortener, String.class);

        return result.getBody().substring(15, 45).replace("\\", "");
    }
}
