package com.increff.invoice.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.increff.invoice.spring.SpringConfig;

@Configuration
@ComponentScan(//
		basePackages = { "com.increff.invoice" }, //
		excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, value = { SpringConfig.class })//
)
@PropertySources({ //
		@PropertySource(value = "classpath:./com/increff/invoice/test.properties", ignoreResourceNotFound = true) //
})
public class QaConfig {


}
