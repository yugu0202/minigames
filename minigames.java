import java.util.Scanner;

class minigames
{
	public static void main(String args[])
	{
		Scanner scan = new Scanner(System.in);
		Boolean game = true;

		System.out.println("こんにちは\nミニゲーム広場へようこそ\n名前を入力してね");
		String name = scan.nextLine();
		Coin cup = new Coin(name);

		while (PlayGame(cup,scan));

	System.out.println("プレイしてくれてありがとう");
	System.exit(0);

	}

	static Boolean PlayGame(Coin cup,Scanner scan)
	{
		int nowCoin = cup.GetNowCoin();
		System.out.println(String.format("%sさんの現在の所持コインは%d枚だよ",cup.GetName(),nowCoin));
		if (nowCoin == 0)
		{
			System.out.println("コインが無くなっちゃった!\n残念…また遊んでね!");
			return false;
		}
		System.out.println("どのゲームをプレイする？\n1:じゃんけん 2:ハイ&ロー 3:終了");
		switch (scan.nextInt())
		{
			case 1:
				RPS rps = new RPS();

				System.out.println("じゃんけんをするよ");

				while (!BetCoin(cup,scan));

				while (RPSBattle(cup,rps,scan));

				return true;
			case 2:
				HL hl = new HL();

				System.out.println("ハイ&ローゲームをするよ");

				while (!BetCoin(cup,scan));

				while (HLBattle(cup,hl,scan));

				return true;
			case 3:
				return false;
			default:
				return true;
		}
	}

	static Boolean BetCoin(Coin cup,Scanner scan)
	{
		System.out.println("何コインベットする？");
		int bet = scan.nextInt();
		if (cup.BetCoin(bet))
		{
			System.out.println(String.format("ベット完了 現在%dコインベット中だよ\n1:スタート 2:ベット変更",cup.GetNowBet()));
			if (scan.nextInt() == 1) return true;
		}
		else
		{
			System.out.println("ベット失敗…もう一度お願いします");
		}

		return false;
	}

	static Boolean RPSBattle(Coin cup,RPS rps,Scanner scan)
	{
		System.out.println("ジャーンケーン...\n1:グー 2:パー 3:チョキ");
		int hand = scan.nextInt();
		int result = rps.Battle(hand);
		System.out.println(String.format("ポン!\nあなたは%s,敵は%sで%sです",rps.HandName(hand),rps.EnemyHandName(),rps.ConvertResult(result)));
		if (result == 2)
		{
			System.out.println("負けたのでゲーム選択に戻ります");
			cup.ResetBet();
			return false;
		}
		int count = rps.GetCount();
		System.out.println(String.format("現在%d連勝中です\n今降ろすと%dコインになります",count,cup.SchReward(count)));
		System.out.println("続けますか？ 1:続ける 2:やめる");
		if (scan.nextInt() == 2)
		{
			cup.GetReward(count);
			return false;
		}

		return true;
	}

	static Boolean HLBattle(Coin cup,HL hl,Scanner scan)
	{
		int card = hl.GameStart();
		System.out.println(String.format("ゲームスタート!\n見えている数字は %d だよ! 1:ハイ 2:ロー",card));
		Boolean result = hl.Choice(scan.nextInt());
		String ans = result ? "アタリ" : "ハズレ";

		System.out.println(String.format("見えている数字は %d で隠れている数字は %d で%sです",card,hl.GetSecretCard(),ans));
		if (!result)
		{
			System.out.println("ハズレだったのでゲーム選択に戻ります");
			cup.ResetBet();
			return false;
		}
		int count = hl.GetCount();
		System.out.println(String.format("現在%d連続正解中です\n今降ろすと%dコインになります",count,cup.SchReward(count)));
		System.out.println("続けますか？ 1:続ける 2:やめる");
		if (scan.nextInt() == 2)
		{
			cup.GetReward(count);
			return false;
		}

		return true;
	}
}
