package com.springboot.stock.resource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springboot.stock.model.Quote;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController
@RequestMapping("/rest/stock")
public class StockResource {
	
	@Autowired
    RestTemplate restTemplate;

    YahooFinance yahooFinance;
    
    public StockResource() {
    	this.yahooFinance=new YahooFinance();
    }
    
    @GetMapping("/{userName}")
    public List<Quote> getStock(@PathVariable("userName")
    final String userName) {
    	
    	ResponseEntity<List<String>> quoteResponse=restTemplate.
				 exchange("http://db-service/rest/db/"+userName,
				 HttpMethod.GET,
				 null,
				 new ParameterizedTypeReference<List<String>>() {
        });
    	
    	List<String> quotes=quoteResponse.getBody();
		return quotes.stream().map(var->{
			Stock stock = getStockPrice_internal(var); //stock1
			return new Quote(var,stock.getCurrency());
            //(Airtel, 300$) //(SBI,400$) //(ICICI,500$),//(JIO,600$)//(HDFC,800$)
            })
            .collect(Collectors.toList());

    }
    
    private Stock getStockPrice_yahoo(String quote) {
        try {
            return YahooFinance.get(quote);
        } catch (IOException e) {
            e.printStackTrace();
            return new Stock(quote);
        }
    }


 private Stock getStockPrice_internal(String quote)
 {
	 //return	YahooFinance.get(quote);
	 if(quote.equalsIgnoreCase("GOOG"))
	 {
		 Stock stock=new Stock(quote);
		 stock.setCurrency("100$");
		 return stock;
	 } else if(quote.equalsIgnoreCase("RIL"))
	 {
		 Stock stock=new Stock(quote);

		 stock.setCurrency("200$");
		 return stock;
	 }else if(quote.equalsIgnoreCase("AIRTEL"))
	 {
		 Stock stock=new Stock(quote);

		 stock.setCurrency("300$");
		 return stock;
	 }else if(quote.equalsIgnoreCase("JIO"))
	 {
		 Stock stock=new Stock(quote);

		 stock.setCurrency("400$");
		 return stock;
	 }if(quote.equalsIgnoreCase("SUZ"))
	 {
		 Stock stock=new Stock(quote);

		 stock.setCurrency("10$");
		 return stock;
	 }
	 if(quote.equalsIgnoreCase("SBI"))
	 {
		 Stock stock=new Stock(quote);

		 stock.setCurrency("20$");
		 return stock;
	 }

	 if(quote.equalsIgnoreCase("YESBANK"))
	 {
		 Stock stock=new Stock(quote);

		 stock.setCurrency("10$");
		 return stock;
	 }

	 if(quote.equalsIgnoreCase("RLAB"))
	 {
		 Stock stock=new Stock(quote);

		 stock.setCurrency("1000$");
		 return stock;
	 }


	 else
		 {
		 Stock stock=new Stock("NA");

		 stock.setCurrency("0$");
		 return stock;
		 }
 }
    
    
    
    
}
