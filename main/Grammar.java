package main;

import java.util.HashMap;

/**
 * A set of rewriting rules, with a number of occurrences for each.
 * @author Nate
 *
 */
public class Grammar
{
	private HashMap<String, Integer>		_map;
	
	/**
	 * Builds a new empty grammar.
	 */
	public Grammar()
	{
		this._map = new HashMap<String, Integer>();
	}
	
	/**
	 * Adds a rule to the grammar and either creates a new entry or increments
	 * its occurrence count.
	 * @param rule The rule to be added.
	 */
	public void addRule(String rule)
	{
		if (!this._map.containsKey(rule))
			this._map.put(rule, 1);
		else
			this._map.put(rule, this._map.get(rule).intValue() + 1);
	}
	
	/**
	 * Returns a String representation of the grammar.
	 * @return For each rule, its occurrences count and the rule itself
	 * separated by a space.
	 */
	@Override
	public String toString()
	{
		StringBuffer	s;
		
		s = new StringBuffer();
		for (String k : this._map.keySet())
		{
			s.append(this._map.get(k));
			s.append(' ');
			s.append(k);
			s.append('\n');
		}
		return (s.toString());
	}
}
