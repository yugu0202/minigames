import java.util.Random;

class HL
{
	private int count;
	private int[] card;
	private int ans;

	HL()
	{
		this.count = 0;
		this.card = new int[2];
		this.ans = 0;
	}

	public int GameStart()
	{
		this.card = new int[2];
		Random rand = new Random();
		while (this.card[0] == this.card[1])
		{
			this.card[0] = rand.nextInt(13)+1;
			this.card[1] = rand.nextInt(13)+1;
		}

		this.ans = this.card[0] < this.card[1] ? 1 : 2;

		return this.card[0];
	}

	public boolean Choice(int player)
	{
		if (this.ans == player) 
		{
			this.count++;
			return true;
		}
		return false;
	}

	public int GetSecretCard()
	{
		return this.card[1];
	}

	public int GetCount()
	{
		return this.count;
	}
}
