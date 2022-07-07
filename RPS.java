import java.util.Random;
import java.util.HashMap;

class RPS
{
	// 0 rock, 1 papaer, 2 scissors
	private int coin;
	private int count;
	private int enemyHand;
	private HashMap<Integer,String> handMap;
	private HashMap<Integer,String> resultMap;

	RPS()
	{
		this.count = 0;
		this.handMap = new HashMap<Integer,String>()
		{
			{
				put(1,"グー");
				put(2,"パー");
				put(3,"チョキ");
			}
		};
		this.resultMap = new HashMap<Integer,String>()
		{
			{
				put(0,"引き分け");
				put(1,"勝ち");
				put(2,"負け");
			}
		};
	}

	public int Battle(int hand) //0 draw,1 win,2 lose
	{
		Random rand = new Random();
		this.enemyHand = rand.nextInt(3)+1;
		if (hand == this.enemyHand)
		{
			return 0;
		}
		else if (hand-this.enemyHand == 1 || hand-this.enemyHand == 2)
		{
			this.count++;
			return 1;
		}
		else if (this.enemyHand-hand == 1 || this.enemyHand-hand == 2)
		{
			this.count = 0;
			this.coin = 0;
			return 2;
		}
		else
		{
			return 3;
		}
	}

	public boolean BetCoin(int bet,Coin cup)
	{
		if (! cup.PullCoin(bet))
		{
			return false;
		}
		this.coin += bet;
		return true;
	}

	public int GetReward(Coin cup)
	{
		int reward = (this.count+1)*this.coin;
		this.count = 0;
		this.coin = 0;
		cup.AddCoin(reward);
		return reward;
	}

	public String ConvertResult(int result)
	{
		return this.resultMap.get(result);
	}

	public String EnemyHandName()
	{
		return this.handMap.get(this.enemyHand);
	}

	public String HandName(int hand)
	{
		return this.handMap.get(hand);
	}

	public int GetCount()
	{
		return this.count;
	}

	public int SchReward()
	{
		return this.coin*(this.count+1);
	}
}
