package com.qualtech.karza.api.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TestSample {
  public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String formatted = format.format(cal.getTime());
//	System.out.println(gender("hibhuib"));
}
  public static String date(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String formatted = format.format(cal.getTime());
		return formatted;
  }
  
  public static String gender(String gender){
	  Map<String, String> fullGender = new HashMap<String, String>();
	  fullGender.put("M", "Male");
	  fullGender.put("m", "Male");
	  fullGender.put("0", "Male");
	  fullGender.put("male", "Male");
	  fullGender.put("Male", "Male");
	  fullGender.put("MALE", "Male");
	  fullGender.put("F", "Female");
	  fullGender.put("f", "Female");
	  fullGender.put("1", "Female");
	  fullGender.put("female", "Female");
	  fullGender.put("Female", "Female");
	  fullGender.put("FEMALE", "Female");
	  fullGender.put("T", "Transgender");
	  fullGender.put("t", "Transgender");
	  
	  if(fullGender.containsKey(gender)){
		  return gender= fullGender.get(gender);
	  }
	  return gender;
  }
 
}
