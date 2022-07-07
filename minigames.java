import java.util.Scanner;

class minigames
{
	public static void main(String args[])
	{
		Scanner scan = new Scanner(System.in);

		System.out.println("こんにちは\nミニゲーム広場へようこそ\n名前を入力してね");
		String name = scan.nextLine();
		Coin cup = new Coin(name);

		while (true)
		{
			System.out.println(String.format("あなたの現在の所持コインは%d枚だよ",cup.GetNowCoin()));
			System.out.println("どのゲームをプレイする？\n1:じゃんけん 2:終了");
			int playGame = scan.nextInt();
			if (playGame == 1)
			{
				RPS rps = new RPS();

				RPSBetCoin(cup,rps,scan);

				RPSStart(cup,rps,scan);

			}
			else if (playGame == 2)
			{
				break;
			}

		}

	System.out.println("プレイしてくれてありがとう");
	System.exit(0);

	}

	static void RPSBetCoin(Coin cup,RPS rps,Scanner scan)
	{
		while (true)
		{
			System.out.println("じゃんけんをするよ\n何コインベットする？");
			int bet = scan.nextInt();
			if (rps.BetCoin(bet,cup))
			{
				System.out.println("ベット完了\n1:スタート 2:追加ベット");
				int decision = scan.nextInt();
				if (decision == 1)
				{
					break;
				}
			}
			else
			{
				System.out.println("ベット失敗…もう一度お願いします");
			}
		}

		return;
	}

	static void RPSStart(Coin cup,RPS rps,Scanner scan)
	{
		while (true)
		{
			System.out.println("ジャーンケーン...\n1:グー 2:パー 3:チョキ");
			int hand = scan.nextInt();
			int result = rps.Battle(hand);
			System.out.println(String.format("あなたは%s,敵は%sで%sです",rps.HandName(hand),rps.EnemyHandName(),rps.ConvertResult(result)));
			if (result == 2)
			{
				System.out.println("負けたのでゲーム選択に戻ります");
				break;
			}
			System.out.println(String.format("現在%d連勝中です\n今降ろすと%dコインになります",rps.GetCount(),rps.SchReward()));
			System.out.println("続けますか？ 1:続ける 2:やめる");
			int decision = scan.nextInt();
			if (decision == 2)
			{
				rps.GetReward(cup);
				break;
			}
		}

		return;
	}

}
