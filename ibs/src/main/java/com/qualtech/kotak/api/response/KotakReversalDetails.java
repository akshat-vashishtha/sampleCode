package com.qualtech.kotak.api.response;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class KotakReversalDetails implements Serializable {

	private static final long serialVersionUID = -4475043506344265084L;
	
    @JsonIgnore
    @Transient
	private KotakReversalResponsePayload kotakReversalResponsePayload;
	@OneToMany (fetch=FetchType.LAZY,mappedBy="kotakReversalResponsePayload", cascade=CascadeType.ALL)
	private List<KotakReversalDetail> reversalDetail;
	
	
	public KotakReversalResponsePayload getKotakReversalResponsePayload() {
		return kotakReversalResponsePayload;
	}
	public void setKotakReversalResponsePayload(KotakReversalResponsePayload kotakReversalResponsePayload) {
		this.kotakReversalResponsePayload = kotakReversalResponsePayload;
	}
	public List<KotakReversalDetail> getReversalDetail() {
	  if(reversalDetail!=null) {
		  for(KotakReversalDetail kotakReversalDetail :reversalDetail) {
			  kotakReversalDetail.setKotakReversalResponsePayload(kotakReversalResponsePayload);
		  }
	  }
		return reversalDetail;
	}
	public void setReversalDetail(List<KotakReversalDetail> reversalDetail) {
		this.reversalDetail = reversalDetail;
	}
	@Override
	public String toString() {
		return "KotakReversalDetails [reversalDetail=" + reversalDetail + "]";
	}
	
	

}
