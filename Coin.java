import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

class Coin
{
	private int coin;
	private int betCoin;
	private String name;

	Coin() {
		this.coin = 20;
		this.betCoin = 0;
		this.name = "guest";
	}


	private boolean PullCoin(int coin)
	{
		if (this.coin < coin)
		{
			return false;
		}
		this.coin -= coin;
		return true;
	}


	public boolean BetCoin(int bet)
	{
		if (bet <= 0) return false;

		AddCoin(this.betCoin);

		if (! PullCoin(bet)) return false;

		this.betCoin = bet;
		return true;
	}


	public int GetReward(int count,double odds)
	{
		int reward = (int)((1+(count*odds))*this.betCoin);
		this.betCoin = 0;
		AddCoin(reward);
		return reward;
	}


	public void ResetBet()
	{
		this.betCoin = 0;
	}


	public void AddCoin(int coin)
	{
		this.coin += coin;
	}


	public int GetNowCoin()
	{
		return this.coin;
	}


	public String GetName()
	{
		return this.name;
	}


	public int GetNowBet()
	{
		return this.betCoin;
	}


	public int SchReward(int count,double odds)
	{
		return (int)((1+(count*odds))*this.betCoin);
	}
}
