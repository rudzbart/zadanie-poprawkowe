package pl.rudz.linkshortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.URISyntaxException;

@Service
public class LinkShortenerServiceImplementation implements LinkShortenerService {

    private LinkShortenerApiClient linkShortenerApiClient;

    @Autowired
    public LinkShortenerServiceImplementation(LinkShortenerApiClient linkShortenerApiClient) {
        this.linkShortenerApiClient = linkShortenerApiClient;
    }

    @Override
    public String getShortenedUrl(String urlToShorten) throws URISyntaxException {
        return linkShortenerApiClient.getShortenedUrl(urlToShorten);
    }
}