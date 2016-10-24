package com.cineplexnotifier.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name, cineplexKey, descriptionHtml, thumbnailImageUrl;
	private byte[] thumbnailImage, posterImage;

	public String getCineplexKey() {
		return cineplexKey;
	}

	public String getDescriptionHtml() {
		return descriptionHtml;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public byte[] getPosterImage() {
		return posterImage;
	}

	public byte[] getThumbnailImage() {
		return thumbnailImage;
	}

	public String getThumbnailImageUrl() {
		return thumbnailImageUrl;
	}

	public void setCineplexKey(String cineplexKey) {
		this.cineplexKey = cineplexKey;
	}

	public void setDescriptionHtml(String descriptionHtml) {
		this.descriptionHtml = descriptionHtml;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPosterImage(byte[] posterImage) {
		this.posterImage = posterImage;
	}

	public void setThumbnailImage(byte[] thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}

	public void setThumbnailImageUrl(String thumbnailImageUrl) {
		this.thumbnailImageUrl = thumbnailImageUrl;
	}

}
