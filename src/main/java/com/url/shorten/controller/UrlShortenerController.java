package com.url.shorten.controller;

import com.url.shorten.common.UrlValidator;
import com.url.shorten.exceptions.InvalidShortUrlException;
import com.url.shorten.exceptions.UrlAlreadyAddedException;
import com.url.shorten.model.UrlModel;
import com.url.shorten.service.UrlShortenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Main API controller for URL shortening related operations.
 */

@RestController
public class UrlShortenerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlShortenerController.class);

    private final UrlShortenService urlShortenService;

    public UrlShortenerController(UrlShortenService service) {
        this.urlShortenService = service;
    }

    /**
     * Add new URL
     * @param longUrl
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,
            value = "/addUrl",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerUrl(@RequestBody String longUrl){//} throws Exception{
        if (UrlValidator.validUrl(longUrl)) {
            String shortUrl = "";
            try {
                shortUrl = urlShortenService.generateShortUrl(longUrl);
            }catch(UrlAlreadyAddedException e){
                LOGGER.info("URL : " + longUrl + " has already been added");
                return new ResponseEntity<>("URL already added.", HttpStatus.OK);
            }
            return new ResponseEntity<>(shortUrl, HttpStatus.OK);

        } else {
            LOGGER.error("Error - invalid URL :" + longUrl);
            return new ResponseEntity<>("Invalid URL : " + longUrl, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Redirect to long URL, given short URL
     * @param shortUrl
     * @param resp
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST,
            value = "/redirect",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void redirect(@RequestBody String shortUrl, HttpServletResponse resp) throws Exception{
        LOGGER.info("Redirecting from short URL : " + shortUrl);
        String longUrl = "";
        if (UrlValidator.validUrl(shortUrl)) {
            try {
                longUrl = urlShortenService.redirect(shortUrl);
            }catch(InvalidShortUrlException e){
                LOGGER.info("Shortened URL : " + shortUrl + " is invalid");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
            resp.sendRedirect(longUrl);
        } else {
            LOGGER.error("Invalid url : " + shortUrl);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    /**
     * API Endpoint to list all currently persisted URLs
     * @return List<UrlModel>
     */
    @RequestMapping(method = RequestMethod.GET,
            value = "/list",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UrlModel>> listUrls() {
            List<UrlModel> urls =  urlShortenService.listAll();
            LOGGER.info("Number of persisted URLs : " + urls.size());
            return new ResponseEntity<>(urls, HttpStatus.OK);
    }
}
