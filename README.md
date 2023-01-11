# minigames
## 三種類のゲームを遊べます
- じゃんけん
- ハイ＆ロー
- 数字当て

## 始め方
```javac minigames.java Coin.java RPS.java HL.java NumGuess.java```をして```java minigames```を実行

## 遊び方
起動後最初にハンドルネームを決めます(後のランキング実装で使用予定)  
そうしたらコインが20枚用意されるのでゲームを選択し、ベットしてコインを増やしていきます  
※ゲームごとに倍率は変わります

### じゃんけん
よくある普通のじゃんけんです

### ハイ＆ロー
トランプで行われるゲームです  
二枚のカードを一枚は表、もう一枚は裏向きで出します  
そしてプレイヤーは表になっているカードの数字より裏になっているカードの数字が大きいか、小さいかを当てます

### 数字当て
隠された数字を当てるゲームです  
1~10までの間で数字が用意されプレイヤーはその数字を3回までの間に当てます  
失敗するたびにヒントがもらえます
