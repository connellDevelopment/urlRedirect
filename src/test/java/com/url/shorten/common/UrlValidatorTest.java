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
 * Created by paraicconnell on 1/10/19.
 */
public class UrlValidatorTest {

    private final String validUrl = "http://www.longwindedaddress/contactus/details.html";
    private final String invalidUrl = "httpx://www.longwindedaddress/contactus/details.html";

    @Test
    public void validUrlTest() throws Exception {
        assertTrue(UrlValidator.validUrl(validUrl));
    }

    @Test
    public void invalidUrlTest() throws Exception {
        assertFalse(UrlValidator.validUrl(invalidUrl));
    }
}