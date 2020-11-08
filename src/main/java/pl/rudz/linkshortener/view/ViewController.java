package pl.rudz.linkshortener.view;

import pl.rudz.linkshortener.controller.LinkShortenerServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.net.URISyntaxException;

@Controller
public class ViewController {
    private LinkShortenerServiceImplementation linkShortenerServiceImplementation;

    private String shortenedUrl = "";
    private final String qrcBase = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=";

    @Autowired
    public ViewController(LinkShortenerServiceImplementation linkShortenerServiceImplementation) {
        this.linkShortenerServiceImplementation = linkShortenerServiceImplementation;
    }

    @GetMapping("/home")
    public String get(Model model) {
        model.addAttribute("shortenedLink", shortenedUrl);
        model.addAttribute("qrcBase", qrcBase);
        return "home";
    }

    @PostMapping("/shortened-link")
    public String getUrlToShorten(@RequestParam String urlToShorten) throws URISyntaxException {
        shortenedUrl = linkShortenerServiceImplementation.getShortenedUrl(urlToShorten);
        return "redirect:/home";
    }
}
