package model;


//�����������
public class Comment {

	private int id;
	private String text = "";
	private int userId;
	
	//��������� ���� �����������
	public void setId(int idNumber) {
		
		id = idNumber;
		
	}
	//��������� ����� �����������
	public void setText(String string) {
		
		text = string;
		
	}
	//��������� ���� ������������, ����������� �����������
	public void setUserId(int idNumber) {
		
		userId = idNumber;
		
	}
	//�������� ���� �����������
	public int getId(Comment comment) {
		
		return comment.id;
		
	}
	//�������� ����� �����������
	public String getText(Comment comment) {
		
		return comment.text;
		
	}
	//�������� ���� ������������, ����������� �����������
	public int getUserId(Comment comment) {
		
		return comment.userId;
		
	}
	
}
