package com.lili.common;

public enum ConstEnum {
	//��¼ģ��
	USERNAME_NOT_NULL(10,"�û�������Ϊ��"),
	PASSWORD_NOT_NULL(11,"���벻��Ϊ��"),
	USERNAME_NOT_EXIST(12,"�û���������"),
	PASSWORD_NOT_TRUE(13,"���벻��ȷ"),
	LOGIN_SUCCES(14,"��¼�ɹ�"),
	USER_PASSWD_NO_SAME(6,"�û��������벻ƥ��"),
	//ע��ģ��
	USERNAME_EXIST(15,"�û����Ѵ���"),
	PASSWORD_NOT_SAME(16,"������������벻��ͬ"),
	EMAIL_NOT_NULL(17,"���䲻��Ϊ��"),
	REGISTER_SUCCES(18,"ע��ɹ�"),
	//����ѧ����Ϣģ��
	ID_EXIST(19,"ѧ��id�Ѵ���"),
	ID_NOT_NULL(20,"id����Ϊ��"),
	NAME_NOT_NULL(21,"��������Ϊ��"),
	SCORE_NOT_NULL(22,"��������Ϊ��"),
	PHONE_NOT_NULL(23,"�绰���벻��Ϊ��"),
	ID_NOT_EXIST(24,"id������"),
	OPERATION_NOT_NULL(3,"����������Ϊ��"),
	OPERATION_IS_NUM(4,"����������������"),
	NO_LOGIN(5,"δ��¼"),
	CID_EXIST(25,"�γ�id�Ѵ���"),
	CID_NOT_EXIST(26,"�γ�id������"),
	CNAME_NOT_NULL(27,"�γ�������Ϊ��"),
	CANME_IS_EXIST(28,"�γ����Ѵ���"),
	CNAME_NOT(29,"�γ̲����ڻ����¼�"),
	CNAME_NO_CHOISE(30,"��û��ѡ��ÿγ�"),
	CART_IS_NULL(31,"���Ĺ��ﳵ�ǿյ�")
	;
	private int type;
	private String desc;
	private 
	ConstEnum(int type,String desc) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.desc = desc;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
