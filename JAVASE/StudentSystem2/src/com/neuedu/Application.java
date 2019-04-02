package com.neuedu;
import com.neuedu.view.SystemView;
import com.neuedu.view.impl.SystemViewImpl;

public class Application {
	public static void main(String[] args){
	SystemView student = new SystemViewImpl();
	student.welcome();//»¶Ó­µÇÂ½½çÃæ
	student.operateLogin();//µÇÂ½²Ù×÷
	student.operateMenu();//²éÑ¯²Ù×÷
	//student.appendStu();//Ìí¼Ó
	//student.deleteStu();//É¾³ý
	//student.modifyStu();//ÐÞ¸Ä
	}
}
