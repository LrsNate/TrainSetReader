package main;

import java.util.LinkedList;

/**
 * The representation of a syntactic tree.
 * @author Antoine LAFOUASSE
 *
 */
public class TreeNode
{
	private String					_value;
	private LinkedList<TreeNode>	_children;
	
	/**
	 * Builds a new syntactic tree.
	 * @param v The representation of the tree (under bracketed form).
	 */
	public TreeNode(String v)
	{
		int		end;

		if (v.isEmpty())
			throw new IllegalArgumentException("Empty value");
		if (v.charAt(0) == '(' && v.charAt(v.length() - 1) == ')')
			v = v.substring(1, v.length() - 1);
		end = 0;
		while (v.charAt(end) != ' ' && v.charAt(end) != '-')
			end++;
		this._value = v.substring(0, end);
		this._children = new LinkedList<TreeNode>();
		while ((v = TreeNode.skipToNextChild(v)) != null)
			this._children.addLast(new TreeNode(TreeNode.getNextChild(v)));
	}
	
	/**
	 * Dumps the entire tree into a String by recursively calling its toString
	 * method and stops on encountering a leaf.
	 * @return A partial collection of rewriting rules.
	 */
	public String dump()
	{
		StringBuffer	s;

		s = new StringBuffer();
		if (!this._children.isEmpty())
		{
			s.append(this.toString() + "\n");
			for (TreeNode n : this._children)
				s.append(n.dump());
		}
		return (s.toString());
	}
	
	/**
	 * Returns the tree's syntactic category.
	 * @return The tree root's syntactic category.
	 */
	public String getValue()
	{
		return (this._value);
	}
	
	@Override
	public String toString()
	{
		StringBuffer	s;

		s = new StringBuffer(this._value);
		if (!this._children.isEmpty())
			s.append(" ->");
		for (TreeNode n : this._children)
		{
			s.append(' ');
			s.append(n.getValue());
		}
		return (s.toString());
	}
	
	private static String getNextChild(String s)
	{
		int		end;
		int		nest;
		
		end = 0;
		nest = 0;
		while (s.charAt(end) != ')' || nest > 1)
		{
			if (s.charAt(end) == '(')
				nest++;
			else if (s.charAt(end) == ')')
				nest--;
			end++;
		}
		return (s.substring(0, end + 1));
	}
	
	private static String skipToNextChild(String s)
	{
		int		idx;
		int		nest;

		idx = 0;
		if (!s.contains(")"))
			return (null);
		else if (!s.startsWith("("))
		{
			while (s.charAt(idx) != '(')
				idx++;
			return (s.substring(idx));
		}
		else
		{
			nest = 0;
			while (s.charAt(idx) != ')' || nest > 1)
			{
				if (s.charAt(idx) == '(')
					nest++;
				else if (s.charAt(idx) == ')')
					nest--;
				idx++;
			}
			try
			{
				while (s.charAt(idx) != '(')
					idx++;
				return (s.substring(idx));
			}
			catch (StringIndexOutOfBoundsException e)
			{
				return (null);
			}
		}
	}
}
