/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.url.shorten.common;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test to test generating short URL of a based on long url.
 */
public class ShortUrlGeneratorTest {
    private final String longUrl = "http://www.java2s.com/Open-Source/Maven_Repository/Java_Library/guava/guava_20_0.html";
    private final String shortUrl = "45028b43";

    @Test
    public void shortenUrlTest() throws Exception {
        assertTrue(ShortUrlGenerator.shortenUrl(longUrl).toString().equalsIgnoreCase(shortUrl));
    }
}