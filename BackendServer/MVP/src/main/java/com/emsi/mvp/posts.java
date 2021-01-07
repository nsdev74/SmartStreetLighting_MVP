package com.emsi.mvp;

public class posts {
	private long id;
	private double longitude;
	private double latitude;
	private String message;
	private int icon;
	

	public long getId() {
		return id;
	}


	public void setId(long l) {
		this.id = l;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public int getIcon() {
		return icon;
	}


	public void setIcon(int icon) {
		this.icon = icon;
	}

	public posts() {};
	
	public posts(long id, double longitude, double latitude, String message, int icon) {
		super();
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.message = message;
		this.icon = icon;
	}
	
	public posts(long id, String longitude, String latitude, String message, String icon) {
		super();
		this.id = id;
		this.longitude = Double.parseDouble(longitude);
		this.latitude = Double.parseDouble(latitude);
		this.message = message;
		this.icon = Integer.parseInt(icon);
	}
	
	public posts(String longitude, String latitude, String message, String icon) {
		super();
		this.longitude = Double.parseDouble(longitude);
		this.latitude = Double.parseDouble(latitude);
		this.message = message;
		this.icon = Integer.parseInt(icon);
	}


	@Override
	public String toString() {
		return "\t{\n\t\t\"id\": "+id+","
				+"\n\t\t\"longitude\": "+longitude + ","
				+"\n\t\t\"latitude\": "+latitude + ","
				+"\n\t\t\"message\": \""+ message + "\","
				+"\n\t\t\"icon\": "+ icon
				+"\n\t}";
	}

	
}
