import java.util.Random;

class NumGuess
{
	private int count;
	private int ans;

	NumGuess()
	{
		Random rand = new Random();
		this.count = 3;
		this.ans = rand.nextInt(10)+1;
	}


	public int Choice(int player)
	{
		if (this.ans == player)
		{
			return 0;
		}
		else if (this.ans < player)
		{
			this.count--;
			return 1;
		}
		else if (this.ans > player)
		{
			this.count--;
			return 2;
		}
		return 3;
	}


	public int GetAns()
	{
		return this.ans;
	}


	public int GetCount()
	{
		return this.count;
	}
}
