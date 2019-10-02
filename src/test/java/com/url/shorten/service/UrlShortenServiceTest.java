/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.url.shorten.service;

import com.url.shorten.controller.UrlShortenerController;
import com.url.shorten.exceptions.InvalidShortUrlException;
import com.url.shorten.exceptions.UrlAlreadyAddedException;
import com.url.shorten.model.UrlModel;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Integration test for URL shorting service.
 */
public class UrlShortenServiceTest {
    final String shortUrl = "http://neu.co/45028b43";
    final String validLongUrl = "http://www.java2s.com/Open-Source/Maven_Repository/Java_Library/guava/guava_20_0.html";
    final String inValidLongUrl = "httpX://www.java2s.com/Open-Source/Maven_Repository/Java_Library/guava/guava_20_0.html";

    @Mock
    private HttpServletResponse response;

    /**
     *  Test Service endpoint with valid URL
     */
    @Test
    public void testShortenServiceValidUrl() {
        UrlShortenService serviceMock = mock(UrlShortenService.class);
        try {
            when(serviceMock.generateShortUrl(validLongUrl)).thenReturn(shortUrl);
            UrlShortenerController controller = new UrlShortenerController(serviceMock);
            try {
                ResponseEntity responseEntity = controller.registerUrl(validLongUrl);
                assertTrue(shortUrl.equalsIgnoreCase(responseEntity.getBody().toString()));
                assertTrue(HttpStatus.OK.toString().equalsIgnoreCase(responseEntity.getStatusCode().toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (UrlAlreadyAddedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test Service endpoint with invalid URL
     */
    @Test
    public void testShortenServiceInvalidUrl() {
        UrlShortenService serviceMock = mock(UrlShortenService.class);
        try {
            when(serviceMock.generateShortUrl(inValidLongUrl)).thenThrow(UrlAlreadyAddedException.class);
            UrlShortenerController controller = new UrlShortenerController(serviceMock);
            try {
                ResponseEntity responseEntity = controller.registerUrl(inValidLongUrl);
                assertTrue(HttpStatus.BAD_REQUEST.toString().equalsIgnoreCase(responseEntity.getStatusCode().toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (UrlAlreadyAddedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test Service endpoint for listing all previously entered URL's
     */
    @Test
    public void testListAllUrls() {
        UrlShortenService serviceMock = mock(UrlShortenService.class);
        try {

            List<UrlModel> urlList = new ArrayList<>();
            urlList.add(new UrlModel(validLongUrl,shortUrl));
            urlList.add(new UrlModel(validLongUrl,shortUrl));
            urlList.add(new UrlModel(validLongUrl,shortUrl));
            when(serviceMock.listAll()).thenReturn(urlList);

            UrlShortenerController controller = new UrlShortenerController(serviceMock);
            try {
                ResponseEntity responseEntity = controller.listUrls();
                //TODO: refactor to be more specific
                assertTrue(responseEntity.getBody().toString().indexOf("http://neu.co/45028b43") > -1);
                assertTrue(HttpStatus.OK.toString().equalsIgnoreCase(responseEntity.getStatusCode().toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Ignore
    public void testRedirect() throws IOException {
        //TODO: Revisit testing for testing HttpServletResponse redirect
        UrlShortenService serviceMock = mock(UrlShortenService.class);
        try {
            when(serviceMock.redirect(shortUrl)).thenReturn(validLongUrl);
            UrlShortenerController controller = new UrlShortenerController(serviceMock);
            controller.redirect(shortUrl,response);
            verify(response).sendRedirect(validLongUrl);
        } catch (InvalidShortUrlException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
