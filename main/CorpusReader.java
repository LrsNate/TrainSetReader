package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * A class whose role is to read a file and return rewriting rules, one at a 
 * time.
 * @author Antoine LAFOUASSE
 *
 */
public class CorpusReader
{
	private LinkedList<String>	_buffer;
	private BufferedReader		_fd;
	
	public CorpusReader(BufferedReader in)
	{
		this._buffer = new LinkedList<String>();
		this._fd = in;
	}
	
	public String getNextRule() throws IOException
	{
		TreeNode	n;
		
		if (this._buffer.isEmpty())
		{
			if ((n = this.getNextTree()) == null)
				return (null);
			for (String s : n.dump().split("\n"))
				this._buffer.addLast(s);
		}
		return (this._buffer.pollFirst());
	}
	
	private TreeNode getNextTree() throws IOException
	{
		String		line;
		
		if ((line = this._fd.readLine()) == null)
			return (null);
		if (line.length() < 3)
			throw new IOException ("Wrong file format");
		line = line.substring(2, line.length() - 1);
		return new TreeNode(line);
	}
}
