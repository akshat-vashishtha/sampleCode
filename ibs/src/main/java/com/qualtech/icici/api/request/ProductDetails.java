package com.qualtech.icici.api.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3118953189683147517L;

	
	
	private Product product;



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}



	@Override
	public String toString() {
		return "ProductDetails [product=" + product + "]";
	}


	
	
}
