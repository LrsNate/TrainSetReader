package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.Grammar;

public class GrammarTest
{
	private Grammar		_instance;
	
	@Before
	public void setUp() throws Exception
	{
		this._instance = new Grammar();
	}

	@Test
	public void testGrammar()
	{
		Assert.assertEquals("", this._instance.toString());
	}

	@Test
	public void testAddRule()
	{
		this._instance.addRule("a");
		this._instance.addRule("a");
		this._instance.addRule("b");
		Assert.assertEquals("1 b\n2 a\n", this._instance.toString());
	}

	@Test
	public void testToString()
	{
		this._instance.addRule("a");
		Assert.assertEquals("1 a\n", this._instance.toString());
	}

}
