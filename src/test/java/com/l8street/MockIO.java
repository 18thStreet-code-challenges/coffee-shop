package com.l8street;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
/*
 * Allows us to simulate the IO conditions of the live app for testing.
 * System.in becomes an InputStream, PrintStream becomes an OutputStream.
 */
public class MockIO {
	
	private PrintStream printStream;
	private ByteArrayInputStream inputStream;
	private ByteArrayOutputStream outputStream;

	public MockIO(String userInput) {
		byte userBytes[] = userInput.getBytes();
		inputStream = new ByteArrayInputStream(userBytes); 
		outputStream = new ByteArrayOutputStream();
		printStream = new PrintStream(outputStream);
	}

	public PrintStream getPrintStream() {
		return printStream;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

	public ByteArrayOutputStream getOutputStream() {
		return outputStream;
	}
	public String getOutput() {
		String output = "";
		try {
			 output = getOutputStream().toString("UTF-8").trim();
		} 
		catch (UnsupportedEncodingException e) {
			throw new RuntimeException("This platform does not support UTF-8 character encoding");
		} 
		return output;
	}
	
}
