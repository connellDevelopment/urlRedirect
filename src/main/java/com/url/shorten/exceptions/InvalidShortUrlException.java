/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.url.shorten.exceptions;

/**
 * Created by paraicconnell on 1/10/19.
 */
public class InvalidShortUrlException extends Throwable {

    public InvalidShortUrlException(String shortUrl) {
        super("Could not find Short url :" + shortUrl);
    }
}
