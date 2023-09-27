package com.springboot.stock.model;


//not exact model class, just for storing data
public class Quote {
	private String quote;

	private String price;

	public Quote(String quote, String price) {
		super();
		this.quote = quote;
		this.price = price;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	
}
