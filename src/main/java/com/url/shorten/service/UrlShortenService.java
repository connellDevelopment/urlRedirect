/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.url.shorten.service;

import com.url.shorten.common.ShortUrlGenerator;
import com.url.shorten.exceptions.InvalidShortUrlException;
import com.url.shorten.exceptions.UrlAlreadyAddedException;
import com.url.shorten.model.UrlModel;
import com.url.shorten.repository.UrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by paraicconnell on 1/10/19.
 */
@Service
public class UrlShortenService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlShortenService.class);
    private final UrlRepository urlRepository;

    @Value("${short.url.prefix}")
    private String ORG_URL_PREFIX;

    @Autowired
    public UrlShortenService(final UrlRepository repository) {
        this.urlRepository = repository;
    }

    /**
     *
     * @param longUrl
     * @return
     * @throws UrlAlreadyAddedException
     */
    public String generateShortUrl(String longUrl) throws UrlAlreadyAddedException{

        LOGGER.debug("UrlShortenService generateShortUrl");
        Optional<UrlModel> url =  urlRepository.findByLongUrl(longUrl);
        if(url.isPresent()) {
            LOGGER.error("URL already added to database : " + longUrl);
            throw new UrlAlreadyAddedException(longUrl);
        }

        StringBuilder sb = new StringBuilder (ORG_URL_PREFIX);
        sb.append(ShortUrlGenerator.shortenUrl(longUrl));

        urlRepository.save(new UrlModel(longUrl, sb.toString()));

        return sb.toString();
    }


    /**
     *
     * @param shortUrl
     * @return
     */
    public String redirect(String shortUrl) throws InvalidShortUrlException{
        UrlModel url = urlRepository.findByShortUrl(shortUrl).orElseThrow(() -> new InvalidShortUrlException(shortUrl)) ;
        return url.getLongUrl();
    }

    /**
     *
     * @return
     * @throws InvalidShortUrlException
     */
    public List<UrlModel> listAll() {
        return urlRepository.findAll();
    }

}
