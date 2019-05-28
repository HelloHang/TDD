package dan.gao;

import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Created by dangao on 5/27/2019.
 */
public class FizzBuzz
{

	public static final String FIZZ_BUZZ = "FizzBuzz";
	public static final String FIZZ = "Fizz";
	public static final String BUZZ = "Buzz";

	public String print()
	{
		return IntStream.range(1, 10).mapToObj(this::fizzBuzz).collect(Collectors.joining("\n"));
	}

	public String fizzBuzz(int i)
	{
		if (isFizz(i) && isBuzz(i))
		{
			return FIZZ_BUZZ;
		}
		if (isFizz(i))
		{
			return FIZZ;
		}
		if (isBuzz(i))
		{
			return BUZZ;
		}
		return String.valueOf(i);
	}

	private boolean isBuzz(int i)
	{
		return canExactDivide(i, 5) || isContains(i, "5");
	}

	private boolean isFizz(int i)
	{
		return canExactDivide(i, 3) || isContains(i, "3");
	}

	private boolean isContains(int i, String input)
	{
		return String.valueOf(i).contains(input);
	}

	private boolean canExactDivide(int i, int i1)
	{
		return i % i1 == 0;
	}

}
