package com.test.classes;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Base {
	
	private int indent = 0;
	private final static int INDENT_SIZE = 2;

	public Base() {
		/* This is the very first thing run */
		System.out.println("Base() class constructor");
	}

	@BeforeMethod
	public void beforeMethod(Method method, ITestContext testContext) {
		myPrintln("@BeforeMethod in Base.java");
	}

	/*
	 * looks like you can have as many @BeforeMethods as you want.
	 * These two ran in order but not sure if they will always run in order
	 */
	@BeforeMethod
	@Parameters({"os", "browserType", "browserVersion", "platform"})
	public void beforeMethod(ITestContext testContext, String os,
			String browserType, @Optional("")String browserVersion, @Optional("") String platform) {
		myPrintln("A second @BeforeMethod in the Base.java");
		myPrintln("@PassingParameters(): "
				+ "os: " + os
				+ ", browserType: " + browserType
				+ ", browserVersion: " + browserVersion
				+ ", platform: " + platform);
	}
	
	protected void myPrintln(String str) {
		if (str.contains("@After") && indent >= 0) {
			indent -= INDENT_SIZE;
		}
		printIndent(indent);
		System.out.println(str);
		
		if (str.contains("@Before")) {
			indent += INDENT_SIZE;
		}
	}
	
	private void printIndent(int i) {
		while (i > 0) {
			System.out.print(" ");
			i--;
		}
	}

}
