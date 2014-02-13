package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * The program's entry point.
 * @author Antoine LAFOUASSE
 *
 */
public class Main
{
	public static void main(String[] args)
	{
		CorpusReader	r;
		String			s;
		Grammar			g;

		try
		{
			r = new CorpusReader(Main.openFile(args[0]));
			System.err.printf("Opened file: %s\n", args[0]);
		}
		catch (Exception e)
		{
			r = new CorpusReader(Main.openStandardInput());
			System.err.printf("Reading from standard input.\n");
		}
		g = new Grammar();
		try
		{
			while ((s = r.getNextRule()) != null)
				g.addRule(s);
			System.out.print(g.toString());
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
			System.exit(-1);
		}
	}
	
	/**
	 * Tries to open a file and returns a BufferedReader pointing to it.
	 * @param filename The path to the input file.
	 * @return A BufferedReader instance.
	 * @throws Exception If the file could not be opened.
	 */
	private static BufferedReader openFile(String filename) throws Exception
	{
		FileInputStream		f;
		
		try
		{
			f = new FileInputStream(filename);
			return (new BufferedReader(new InputStreamReader(f, "UTF-8")));
		}
		catch (FileNotFoundException e)
		{
			System.err.printf("%s\n", e.getMessage());
			throw new Exception();
		}
	}
	
	/**
	 * Opens standard input (stdin)
	 * @return A BufferedReader instance pointing to standard input.
	 */
	private static BufferedReader openStandardInput()
	{
		try
		{
			return (new BufferedReader(
					new InputStreamReader(System.in, "UTF-8")));
		}
		catch (UnsupportedEncodingException e1)
		{
			System.err.println("UTF-8 appears to be unsupported.");
			return (new BufferedReader(new InputStreamReader(System.in)));
		}
	}
}
