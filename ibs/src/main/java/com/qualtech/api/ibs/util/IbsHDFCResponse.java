package com.qualtech.api.ibs.util;




import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({
    "1",
    "2",
    "3",
    "4",
    "5",
    "6",
    "7",
    "8",
    "9"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class IbsHDFCResponse {

    @JsonProperty("1")
    private String one;
    @JsonProperty("2")
    private String two;
    @JsonProperty("3")
    private String three;
    @JsonProperty("4")
    private String four;
    @JsonProperty("5")
    private String five;
    @JsonProperty("6")
    private String six;
    @JsonProperty("7")
    private String seven;
    @JsonProperty("8")
    private String eight;
    @JsonProperty("9")
    private String nine;
   
  
    @JsonProperty("1")
	public String getOne() {
		return one;
	}

    @JsonProperty("1")
	public void setOne(String one) {
		this.one = one;
	}

    @JsonProperty("2")
	public String getTwo() {
		return two;
	}

    @JsonProperty("2")
	public void setTwo(String two) {
		this.two = two;
	}
    @JsonProperty("3")
	public String getThree() {
		return three;
	}
    
	 @JsonProperty("3")
	public void setThree(String three) {
		this.three = three;
	}
	 @JsonProperty("4")
	public String getFour() {
		return four;
	}

	 @JsonProperty("4")
	public void setFour(String four) {
		this.four = four;
	}
	 @JsonProperty("5")
	public String getFive() {
		return five;
	}

	 @JsonProperty("5")
	public void setFive(String five) {
		this.five = five;
	}
	 @JsonProperty("6")
	public String getSix() {
		return six;
	}

	 @JsonProperty("6")
	public void setSix(String six) {
		this.six = six;
	}
	 @JsonProperty("7")
	public String getSeven() {
		return seven;
	}

	 @JsonProperty("7")
	public void setSeven(String seven) {
		this.seven = seven;
	}
	 @JsonProperty("8")
	public String getEight() {
		return eight;
	}

	 @JsonProperty("8")
	public void setEight(String eight) {
		this.eight = eight;
	}
	 @JsonProperty("9")
	public String getNine() {
		return nine;
	}

	 @JsonProperty("9")
	public void setNine(String nine) {
		this.nine = nine;
	}



}

