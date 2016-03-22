package com.imooc.menu;

public class NewButton extends NewBaseButton{

	private String key;
	
	private SubButton sub_button;
	public SubButton getSub_button() {
		return sub_button;
	}

	public void setSub_button(SubButton sub_button) {
		this.sub_button = sub_button;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
}
