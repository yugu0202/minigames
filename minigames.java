import java.util.Scanner;

class minigames
{
	public static void main(String args[])
	{
		Scanner scan = new Scanner(System.in);
		Boolean game = true;

		System.out.println("こんにちは\nミニゲーム広場へようこそ");
		while (true)
		{
			System.out.println("名前を入力してね");
			String name = scan.nextLine();
			if (name.indexOf(":") == -1)
			{
				System.out.println("名前に使用できない文字が含まれています(:)");
			}
			else
			{
				break;
			}
		}
		Coin cup = new Coin();

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
		System.out.println("どのゲームをプレイする？\n1:じゃんけん 2:ハイ&ロー 3:数字当て 4:終了");
		switch (scan.nextInt())
		{
			case 1:
				RPS rps = new RPS();

				System.out.println("じゃんけんをするよ");

				while (!BetCoin(cup,scan));

				while (RPSBattle(cup,rps,scan,1));

				return true;
			case 2:
				HL hl = new HL();

				System.out.println("ハイ&ローゲームをするよ");
				System.out.println("1～13までの数字を最初に見せるよ\nその数字が隠れている数字より大きいか小さいかを当ててね");

				while (!BetCoin(cup,scan));

				while (HLBattle(cup,hl,scan,0.75));

				return true;
			case 3:
				NumGuess ng = new NumGuess();

				System.out.println("数字当てゲームをするよ");
				System.out.println("1～10までの数字が隠されているよ\nそれを三回までの間に当てるゲームだよ");

				while (!BetCoin(cup,scan));

				while (NGBattle(cup,ng,scan,0.5));

				return true;
			case 4:
				return false;
			default:
				return true;
		}
	}


	static boolean BetCoin(Coin cup,Scanner scan)
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


	static boolean RPSBattle(Coin cup,RPS rps,Scanner scan,double odds)
	{
		System.out.println("ジャーンケーン...\n1:グー 2:パー 3:チョキ");
		int hand = scan.nextInt();
		if (rps.HandName(hand) == null)
		{
			System.out.println("違う数字だよ やり直し");
			return true;
		}
		int result = rps.Battle(hand);
		System.out.println(String.format("ポン!\nあなたは%s,敵は%sで%sです",rps.HandName(hand),rps.EnemyHandName(),rps.ConvertResult(result)));
		if (result == 2)
		{
			System.out.println("負けたのでゲーム選択に戻ります");
			cup.ResetBet();
			return false;
		}
		int count = rps.GetCount();
		System.out.println(String.format("現在%d連勝中です\n今降ろすと%dコインになります",count,cup.SchReward(count,odds)));
		System.out.println("続けますか？ 1:続ける 2:やめる");
		if (scan.nextInt() == 2)
		{
			cup.GetReward(count,odds);
			return false;
		}

		return true;
	}


	static boolean HLBattle(Coin cup,HL hl,Scanner scan,double odds)
	{
		int card = hl.GameStart();
		System.out.println(String.format("ゲームスタート!\n見えている数字は %d だよ! 1:ハイ 2:ロー",card));
		int player = scan.nextInt();
		if (player <= 0 || player >= 3)
		{
			System.out.println("違う数字だよ やり直し");
			return true;
		}
		Boolean result = hl.Choice(player);
		String ans = result ? "アタリ" : "ハズレ";

		System.out.println(String.format("見えている数字は %d で隠れている数字は %d で%sです",card,hl.GetSecretCard(),ans));
		if (!result)
		{
			System.out.println("ハズレだったのでゲーム選択に戻ります");
			cup.ResetBet();
			return false;
		}
		int count = hl.GetCount();
		System.out.println(String.format("現在%d連続正解中です\n今降ろすと%dコインになります",count,cup.SchReward(count,odds)));
		System.out.println("続けますか？ 1:続ける 2:やめる");
		if (scan.nextInt() == 2)
		{
			cup.GetReward(count,odds);
			return false;
		}

		return true;
	}


	static boolean NGBattle(Coin cup,NumGuess ng,Scanner scan,double odds)
	{
		int count = ng.GetCount();
		if (count == 0)
		{
			System.out.println("残念…また挑戦してね");
			cup.ResetBet();
			return false;
		}
		System.out.println(String.format("後%d回!",count));
		System.out.println("数字を入力してね");
		System.out.println(String.format("ここで正解すると%dコイン獲得",cup.SchReward(count,odds)));
		int ret = ng.Choice(scan.nextInt());

		switch (ret)
		{
			case 0:
				System.out.println("正解!");
				cup.GetReward(count,odds);
				return false;
			case 1:
				System.out.println("隠れている数字より大きいよ");
				return true;
			case 2:
				System.out.println("隠れている数字より小さいよ");
				return true;
			default:
				return false;
		}
	}
}
