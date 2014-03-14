package main;

public abstract class Environment
{	
	private static int			_precision = 10;
	private static int			_nThreads = 4;
	
	public static int getPrecision()
	{
		return (Environment._precision);
	}
	
	public static int getNThreads()
	{
		return (Environment._nThreads);
	}
	
	public static void setPrecision(int p)
	{
		Environment._precision = p;
	}
	
	public static void setNThreads(int t)
	{
		Environment._nThreads = t;
	}
}
