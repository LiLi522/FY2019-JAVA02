import java.util.Scanner;

/**
 *��дһ���������һ���ַ����еĴ�дӢ����ĸ����
 *СдӢ����ĸ���Լ���Ӣ����ĸ����
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
		System.out.print("������Ҫ�жϵ��ַ�����");
		String str = scan.nextLine();
		int isenglish = 0;//СдӢ����ĸ����
		int isEnglish = 0;//��дӢ����ĸ����
		int noEnglish = 0;//��Ӣ����ĸ����
		for (int i = 0; i < str.length(); ++i) {
			if (str.charAt(i) > 64 && str.charAt(i) < 91) {
				isenglish++;
			}else if (str.charAt(i) > 96 && str.charAt(i) < 123) {
				isEnglish++;
			}else {
				noEnglish++;
			}
		}
		System.out.println("��дӢ����ĸ����" + isenglish);
		System.out.println("СдӢ����ĸ����" + isEnglish);
		System.out.println("��Ӣ����ĸ����" + noEnglish);
	}

}
