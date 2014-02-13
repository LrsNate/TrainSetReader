package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

public class ArgumentParser
{
	private Integer						_precision;
	private LinkedList<BufferedReader>	_fds;
	
	public ArgumentParser(String argv[])
	{
		this._fds = new LinkedList<BufferedReader>();
		this._precision = null;
		for (int i = 0; i < argv.length; i++)
		{
			if (argv[i].equals("-p"))
			{
				Messages.info("precision option specified.");
				try
				{
					this._precision = this.parsePrecision(argv, i);
					i++;
				}
				catch (NumberFormatException e)
				{
					Messages.warning(String.format(
							"invalid precision argument: %s",
							argv[i + 1]));
				}
			}
			else
			{
				try
				{
					this._fds.addLast(this.openFile(argv[i]));
					Messages.info(String.format("%s: opened succesfully.",
							argv[i]));
				}
				catch (FileNotFoundException e)
				{
					Messages.warning(e.getMessage());
					continue ;
				}
			}
		}
		if (this._precision == null)
		{
			Messages.info("setting precision to 7 (default value)");
			this._precision = 7;
		}
		if (this._fds.isEmpty())
		{
			Messages.info("no valid input files provided.");
			Messages.info("reading from standard input.");
			this._fds.addLast(this.openStandardInput());
		}
	}
	
	public BufferedReader getNextFile()
	{
		return (this._fds.pollFirst());
	}
	
	public int getPrecision()
	{
		return (this._precision);
	}

	/**
	 * Tries to open a file and returns a BufferedReader pointing to it.
	 * @param filename The path to the input file.
	 * @return A BufferedReader instance.
	 * @throws Exception If the file could not be opened.
	 */
	private BufferedReader openFile(String filename)
			throws FileNotFoundException
	{
		FileInputStream		f;
		
		f = new FileInputStream(filename);
		try
		{
			Messages.info(String.format("opening file: %s", filename));
			return (new BufferedReader(new InputStreamReader(f, "UTF-8")));
		}
		catch (UnsupportedEncodingException e)
		{
			Messages.warning(e.getMessage());
			return (new BufferedReader(new InputStreamReader(f)));
		}
	}
	
	/**
	 * Opens standard input (stdin)
	 * @return A BufferedReader instance pointing to standard input.
	 */
	private BufferedReader openStandardInput()
	{
		try
		{
			return (new BufferedReader(
					new InputStreamReader(System.in, "UTF-8")));
		}
		catch (UnsupportedEncodingException e)
		{
			Messages.warning(e.getMessage());
			return (new BufferedReader(new InputStreamReader(System.in)));
		}
	}
	
	private int parsePrecision(String argv[], int idx)
		throws NumberFormatException
	{
		int		res;

		if ((idx + 1) >= argv.length)
			Messages.error("missing precision argument.");
		res = Integer.parseInt(argv[idx + 1]);
		Messages.info(String.format("precision set to: %d digits.",
				res));
		return (res);
	}
}
