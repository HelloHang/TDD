package dan.gao;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by dangao on 5/27/2019.
 */
public class FizzBuzzTest
{
	private FizzBuzz fizzBuzz;

	@Before
	public void init()
	{
		fizzBuzz = new FizzBuzz();
	}

	@Test
	public void testFizzBuzz1()
	{
		assertEquals(fizzBuzz.fizzBuzz(1), "1");
	}

	@Test
	public void testFizzBuzz3()
	{
		assertEquals(fizzBuzz.fizzBuzz(3), "Fizz");
	}

	@Test
	public void testFizzBuzz5()
	{
		assertEquals(fizzBuzz.fizzBuzz(5), "Buzz");
	}

	@Test
	public void testFizzBuzz15()
	{
		assertEquals(fizzBuzz.fizzBuzz(15), "FizzBuzz");
	}

	@Test
	public void testFizzBuzzContains3()
	{
		assertEquals(fizzBuzz.fizzBuzz(13),"Fizz");
	}

	@Test
	public void testFizzBuzzContains5()
	{
		assertEquals(fizzBuzz.fizzBuzz(52), "Buzz");
	}

	@Test
	public void testFizzBuzzContains3And5()
	{
		assertEquals(fizzBuzz.fizzBuzz(53),"FizzBuzz");
	}
}