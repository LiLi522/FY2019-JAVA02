import java.util.Scanner;

/**
 * ��дһ�������������һ���ַ����У�ָ���ַ������ֵĴ�����
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
		System.out.print("�������ַ�����");
		String str = scan.nextLine();
		System.out.print("������Ҫ�жϵ��ַ�����");
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
