package test;


import org.junit.Test;

import test.MathUtil;

import org.junit.Assert;

public class MathTest {
	
	@Test(expected=NumberFormatException.class)
	public void testPow() {
		MathUtil math = new MathUtil();
		double result = math.pow(2.1, 3.0);
		
		Assert.assertEquals(8.0, result, 2.0);
		math.pow(10,25);
	}
}
