package application;


//�����������
public class Notice {

	private String text = "";
	
	//��������� ����� �����������
	public void setText(String string) {
		
		text = string;
		
	}
	//�������� ����� �����������
	public String getText(Notice notice) {
		
		return notice.text;
		
	}
	
}
