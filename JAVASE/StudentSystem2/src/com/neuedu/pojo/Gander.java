package com.neuedu.pojo;

public enum Gander {
	MAN("��"),
	WOMAN("Ů")
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
