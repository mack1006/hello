import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class kazuate {

	private static int NUM = 5;
	public static int set;
	public static void main(String[] args) {

		System.out.println("数当てゲームです．2桁の数字を入力してください");
		set = NumSet();//値の設定

		//System.out.println("set:" + set);
		int num = 0;
		int i=0;
		while(i<NUM){
			System.out.println((i+1)+"回目:");
			num = value();
			if(judge(num))
				break;
			i++;
		}
		if(i==NUM)
			System.out.println("残念!5回以内に当てられませんでしたね");
	}
	/*入力された数の判定*/
	public static boolean judge(int num){

		if(num == set){
			System.out.println("当たり!");
			return true;
		}else if(num > set){
			System.out.println("当たりの数より大きいです");
			if(num >= set + 20){
				System.out.println("当たりの数と20以上差があります");
			}
			return false;
		}else{
			System.out.println("当たりの数より小さいです");
			if(num <= set - 20){
				System.out.println("当たりの数と20以上差があります");
			}
			return false;
		}
	}
	/*値の設定*/
	public static int NumSet(){
		return (int)(Math.random()*90 + 10);
	}
	/*数の入力*/
	public static int value() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String line;
		int val = 0;
		try {
			line = br.readLine();
			val = Integer.parseInt(line);
			/*10~99の範囲でない場合はやり直し*/
            if(val < 10 || val >= 100){
                System.out.print("2桁の数字を入力してください:");
                val = value();
            }
		} catch (NumberFormatException e) {
			System.out.print("数字を入力してください:");
			val = value();
		} catch (IOException e) {
			System.out.print("数字を入力してください:");
			val = value();
		}
		return val;
	}
}
