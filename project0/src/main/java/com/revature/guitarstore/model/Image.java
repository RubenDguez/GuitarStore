package com.revature.guitarstore.model;

public class Image {

	private int uniqueID;
	private String imageName;
	private int imageOID;

	public Image() {
		super();
	}

	public int getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public int getImageOID() {
		return imageOID;
	}

	public void setImageOID(int imageOID) {
		this.imageOID = imageOID;
	}

	@Override
	public String toString() {
		return "Images [uniqueID=" + uniqueID + ", imageName=" + imageName + ", imageOID=" + imageOID + "]";
	}

}
