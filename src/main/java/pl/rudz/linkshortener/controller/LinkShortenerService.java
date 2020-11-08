package pl.rudz.linkshortener.controller;

import java.net.URISyntaxException;

public interface LinkShortenerService {

    String getShortenedUrl(String urlToShorten) throws URISyntaxException;
}
