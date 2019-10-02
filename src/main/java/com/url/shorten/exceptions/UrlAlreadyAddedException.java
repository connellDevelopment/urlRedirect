/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.url.shorten.exceptions;

/**
 * Specific exception catering for when a long URL has already been submitted & persisted.
 */
public class UrlAlreadyAddedException extends Throwable {

    public UrlAlreadyAddedException(String longUrl) {
        super("Url has already been added :" + longUrl);
    }
}
