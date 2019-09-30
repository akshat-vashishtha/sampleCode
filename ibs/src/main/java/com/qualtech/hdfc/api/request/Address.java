
package com.qualtech.hdfc.api.request;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Embeddable
@JsonIgnoreProperties(ignoreUnknown=true)
public class Address implements Serializable{

   // @JsonProperty("area")
    private String area;
   // @JsonProperty("city")
    private String city;
   // @JsonProperty("houseOrFlat")
    private String houseOrFlat;
   // @JsonProperty("pinCode")
    private String pinCode;
   // @JsonProperty("state")
    private String state;
   // @JsonProperty("street")
    private String street;

   // @JsonProperty("area")
    public String getArea() {
        return area;
    }

   // @JsonProperty("area")
    public void setArea(String area) {
        this.area = area;
    }

   // @JsonProperty("city")
    public String getCity() {
        return city;
    }

   // @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

   // @JsonProperty("houseOrFlat")
    public String getHouseOrFlat() {
        return houseOrFlat;
    }

   // @JsonProperty("houseOrFlat")
    public void setHouseOrFlat(String houseOrFlat) {
        this.houseOrFlat = houseOrFlat;
    }

   // @JsonProperty("pinCode")
    public String getPinCode() {
        return pinCode;
    }

   // @JsonProperty("pinCode")
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

   // @JsonProperty("state")
    public String getState() {
        return state;
    }

   // @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

   // @JsonProperty("street")
    public String getStreet() {
        return street;
    }

   // @JsonProperty("street")
    public void setStreet(String street) {
        this.street = street;
    }

 

}
