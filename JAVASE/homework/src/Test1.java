import java.util.Scanner;

/**
 *编写一个程序，输出一个字符串中的大写英文字母数，
 *小写英文字母数以及非英文字母数。
 * A-Z(65-90) a-z(97-122)
 *
 */
public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test1 test1 = new Test1();
		test1.isEnglish();
	}
	
	public void isEnglish(){
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入要判断的字符串：");
		String str = scan.nextLine();
		int isenglish = 0;//小写英文字母个数
		int isEnglish = 0;//大写英文字母个数
		int noEnglish = 0;//非英文字母个数
		for (int i = 0; i < str.length(); ++i) {
			if (str.charAt(i) > 64 && str.charAt(i) < 91) {
				isenglish++;
			}else if (str.charAt(i) > 96 && str.charAt(i) < 123) {
				isEnglish++;
			}else {
				noEnglish++;
			}
		}
		System.out.println("大写英文字母个数" + isenglish);
		System.out.println("小写英文字母个数" + isEnglish);
		System.out.println("非英文字母个数" + noEnglish);
	}

}
