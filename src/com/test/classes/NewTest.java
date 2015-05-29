package com.test.classes;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class NewTest extends Base {

	public NewTest() {
		/* Second thing run */
		myPrintln("NewTest() class constructor");
	}

	@BeforeMethod
	public void setup(Method method, ITestContext testContext) {
		myPrintln("@BeforeMethod in NewTest.java");

		/* returns info about the @Test method which is about to be called */
		myPrintln("method: " + method);

		/* returns the name of the @Test method which is about to be called */
		myPrintln("method name: " + method.getName());

		/* returns name of test from XML file */
		myPrintln("testContext.getName(): " + testContext.getName());

		testContext.setAttribute("foo", "bar");
		testContext.setAttribute("star", "wars");

		Set<String> set = testContext.getAttributeNames();


		Iterator<String> i = set.iterator();
		while (i.hasNext()) {
			String attribute = i.next();
			myPrintln("testContextAttribute: " + attribute +" = "+ testContext.getAttribute(attribute));
		}
	}

	@Test
	public void aTest() {
		myPrintln("@Test - method: aTest");
		Assert.assertEquals(1, 1);
	}

	@Test
	@Parameters({"account-type", "username", "password", "countryCode"})
	public void testWithParameters(String accountType, String username,
			String password, @Optional("") String countryCode) {
		myPrintln("@Test testWithParameters() received parameters: "
				+ "account-type: " + accountType
				+ ", username: " + username
				+ ", password: " + password
				+ ", countryCode: " + countryCode);
	}

	@AfterMethod
	public void afterMethod() {
		myPrintln("@AfterMethod");
	}


	@BeforeClass
	public void beforeClass() {
		myPrintln("@BeforeClass");
	}

	@AfterClass
	public void afterClass() {
		myPrintln("@AfterClass");
	}


	@BeforeTest
	public void beforeTest() {
		myPrintln("@BeforeTest");
	}

	@AfterTest
	public void afterTest() {
		myPrintln("@AfterTest");
	}


	@BeforeSuite
	public void beforeSuite() {
		myPrintln("@BeforeSuite");
	}

	@AfterSuite
	public void afterSuite() {
		myPrintln("@AfterSuite");
	}

}
