package com.lovecoding.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lovecoding.microservices.currencyexchangeservice.bean.ExchangeValue;
import com.lovecoding.microservices.currencyexchangeservice.repository.ExchangeValueRepository;

@RestController
public class CurrencyExchangeController {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository exchangeRepository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
		
		 
		 ExchangeValue exchangeValue = exchangeRepository.findByFromAndTo(from, to);
		 exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		 
		 logger.info("exchange value--> {}", exchangeValue);
		 return exchangeValue;
	}
}
