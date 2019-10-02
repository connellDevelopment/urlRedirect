/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.url.shorten.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *  Data object to represent a URL object, pertinent to this task.
 */
@Data
@Entity
@Getter @Setter
public class UrlModel {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;

	private String longUrl;
	private String shortUrl;

	public UrlModel(){

	}

	public UrlModel(String longUrl, String shortUrl) {
		super();
		this.longUrl = longUrl;
		this.shortUrl = shortUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	/**
	 *
	 * @param o
	 * @return
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UrlModel urlModel = (UrlModel) o;

		if (id != null ? !id.equals(urlModel.id) : urlModel.id != null) return false;
		if (longUrl != null ? !longUrl.equals(urlModel.longUrl) : urlModel.longUrl != null) return false;
		return shortUrl != null ? shortUrl.equals(urlModel.shortUrl) : urlModel.shortUrl == null;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (longUrl != null ? longUrl.hashCode() : 0);
		result = 31 * result + (shortUrl != null ? shortUrl.hashCode() : 0);
		return result;
	}
}
