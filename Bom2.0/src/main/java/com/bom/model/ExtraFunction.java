package com.bom.model;

public class ExtraFunction
{
	public static String popUp(String message)
	{
		String data;
		data = "<html><head></head><body><script>"
				+"alert(\""+"message"+")</script></body></html>";
		return data;
	}

}
