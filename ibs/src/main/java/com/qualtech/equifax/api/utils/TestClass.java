package com.qualtech.equifax.api.utils;

import java.util.ResourceBundle;

public class TestClass {

	static ResourceBundle res=ResourceBundle.getBundle("application");
	void show(){
		String	filepath = (new StringBuilder(res.getString("LOGO_PATH"))).append("equifax-logo.png").toString();
//		System.out.println(filepath);
		//new ImageIcon(path);
	}
	public static void main(String[] args) {
		new TestClass().show();

	}

}
