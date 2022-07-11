import java.io.Path;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

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


	public SetName(String name)
	{
		List<String> lines = GetData();
	}


	private List<String> GetData()
	{
		Path path = Paths.get("./usrdata.txt");
		File file = path.toFile();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
		String str;

		List<String> lines = new ArrayList<>();
		while ((str = reader.readLine()) != null)
		{
			lines.add(str);
		}
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
