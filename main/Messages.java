package main;

public class Messages
{
	private static String	_name = "TrainSetReader";

	public static void error(String s)
	{
		System.err.printf("[Error] %s: %s", Messages.message(s));
		System.exit(-1);
	}
	
	public static void info(String s)
	{
		System.err.printf("[Info] %s", Messages.message(s));
	}
	
	public static void warning(String s)
	{
		System.err.printf("[Warning] %s", Messages.message(s));
	}
	
	private static String message(String s)
	{
		StringBuffer	res;
		
		res = new StringBuffer(Messages._name);
		res.append(": ");
		res.append(s);
		if (!s.endsWith("\n"))
			res.append("\n");
		return (res.toString());
	}
}
