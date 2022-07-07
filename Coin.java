class Coin
{
	private int coin;
	private String name;

	Coin(String name)
	{
		this.coin = 20;
		this.name = name;
	}

	public boolean PullCoin(int coin)
	{
		if (this.coin < coin)
		{
			return false;
		}
		this.coin -= coin;
		return true;
	}

	public void AddCoin(int coin)
	{
		this.coin += coin;
	}

	public int GetNowCoin()
	{
		return this.coin;
	}
}
