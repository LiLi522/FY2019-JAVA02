import java.util.Scanner;

/**
 * 编写一个方法，输出在一个字符串中，指定字符串出现的次数。
 *
 */
public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test2 test2 = new Test2();
		test2.numOfString();
	}
	
	public void numOfString(){
		
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入字符串：");
		String str = scan.nextLine();
		System.out.print("请输入要判断的字符串：");
		String strFind = scan.nextLine();
		int count = 0;
		int beginindex = -1;
		while ((beginindex = str.indexOf(strFind)) != -1) {
			str = str.substring(beginindex + strFind.length());
			++count;
		}
		System.out.println(count);
	}

}
