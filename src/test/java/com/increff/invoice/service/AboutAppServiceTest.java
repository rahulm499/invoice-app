package com.increff.invoice.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AboutAppServiceTest extends AbstractUnitTest {

	@Autowired
	private AboutAppService service;

	@Test
	public void testServiceApis() {
		assertEquals("Invoice Application", service.getName());
		assertEquals("1.0", service.getVersion());
	}

}
