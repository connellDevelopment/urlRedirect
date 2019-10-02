/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.url.shorten.common;

import com.url.shorten.controller.UrlShortenerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by paraicconnell on 1/10/19.
 */
public class UrlValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlShortenerController.class);

    public static boolean validUrl (String url) {
        try {
            new URL(url).toURI();
            return Boolean.TRUE;
        }
        catch (URISyntaxException exception) {
            LOGGER.debug("Invalid URL : URISyntaxException.");
            return Boolean.FALSE;
        }
        catch (MalformedURLException exception) {
            LOGGER.debug("Invalid URL : MalformedURLException.");
            return Boolean.FALSE;
        }
    }
}
