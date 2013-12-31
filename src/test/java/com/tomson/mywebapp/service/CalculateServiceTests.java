package com.tomson.mywebapp.service;

import static org.junit.Assert.*;
import org.junit.Test;

public class CalculateServiceTests {
	@Test
	public void testAdd() {
		CalculateService calService=new CalculateService();
		assertEquals(2, calService.add(1, 1));
	}

}
