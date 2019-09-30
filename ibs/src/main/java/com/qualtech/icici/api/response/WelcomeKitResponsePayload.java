package com.qualtech.icici.api.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_IC_WLCM_KT_RES")
public class WelcomeKitResponsePayload implements Serializable{

	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@Id
	private int eid;
	private String url;

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }
    

    public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	@Override
    public String toString()
    {
        return "ResponsePayload [url = "+url+"]";
    }
}
