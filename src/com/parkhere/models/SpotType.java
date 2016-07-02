package com.parkhere.models;

public enum SpotType {
	FOURWHEELER("four"), TWOWHEELER("two");
	
	private String type;
	SpotType(String type){
		this.type =type;
	}
	
	public String getType(){
		return type;
	}
}
