package com.neuedu.pojo;

public enum Gander {
	MAN("ÄĞ"),
	WOMAN("Å®")
	;
	private String gander;
	Gander(String gander) {
		this.gander = gander;
	}
	public String getGander() {
		return gander;
	}
	public void setGander(String gander) {
		this.gander = gander;
	}
	
}
