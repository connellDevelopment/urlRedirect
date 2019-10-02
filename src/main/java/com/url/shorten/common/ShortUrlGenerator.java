/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.url.shorten.common;

import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * Created by paraicconnell on 1/10/19.
 */
public class ShortUrlGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShortUrlGenerator.class);

    static public String shortenUrl(String longUrl){
        LOGGER.debug("Generating short version of url:  " + longUrl);
        return Hashing.murmur3_32().hashString(longUrl, StandardCharsets.UTF_8).toString();
    }
}
