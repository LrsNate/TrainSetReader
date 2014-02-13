package main;

import java.util.HashMap;

/**
 * A set of rewriting rules, with a number of occurrences for each.
 * @author Nate
 *
 */
public class Grammar
{
	private HashMap<String, RewritingRule>		_map;
	
	/**
	 * Builds a new empty grammar.
	 */
	public Grammar()
	{
		this._map = new HashMap<String, RewritingRule>();
	}
	
	/**
	 * Adds a rule to the grammar and either creates a new entry or increments
	 * its occurrence count.
	 * @param rule The rule to be added.
	 */
	public void addRule(String rule)
	{
		String		tab[];
		
		tab = rule.split("->");
		if (tab.length != 2)
			throw new IllegalArgumentException("Grammar: illegal rule");
		for (int i = 0; i < tab.length; i++)
			tab[i] = tab[i].trim();
		if (this._map.containsKey(tab[0]))
			this._map.get(tab[0]).addRule(tab[1]);
		else
			this._map.put(tab[0], new RewritingRule(tab[0], tab[1]));
	}

	@Deprecated
	@Override
	public String toString()
	{
		StringBuffer	s;
		
		s = new StringBuffer();
		for (RewritingRule r : this._map.values())
			s.append(r.toString());
		return (s.toString());
	}
	
	public String toString(int precision)
	{
		StringBuffer	s;
		
		s = new StringBuffer();
		for (RewritingRule r : this._map.values())
			s.append(r.toString(precision));
		return (s.toString());
	}
}
