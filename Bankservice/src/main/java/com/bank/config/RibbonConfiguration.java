package com.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;

public class RibbonConfiguration {

	@Autowired
	IClientConfig config;
	
	@Bean
	public IPing ribbonping(IClientConfig config) {
		return new PingUrl();
	}
	
	@Bean
	public IRule ribbonrule(IClientConfig config) {
		return new AvailabilityFilteringRule();
	}
	
	
	
}
