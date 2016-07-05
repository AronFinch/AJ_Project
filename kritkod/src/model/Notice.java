package model;

import java.sql.SQLException;
import java.time.LocalDate;

//�����������
public class Notice {

	private int id;
	private String text;
	private LocalDate date;
	
	public Notice() {
		id = 0;
		text = "������ ���������!";
		date = LocalDate.of(1000, 1, 1);
	}
	public void clear() {
		id = 0;
		text = "";
		date = LocalDate.of(1000, 1, 1);
	}
	//��������� ����� �����������
	public void setId(int ID) {
		id = ID;
	}
	public void setText(String Text) {
		text = Text;	
	}
	public void setDate(LocalDate Date) {
		date = Date;
	}
	public int getId() {
		return id;
	}
	//�������� ����� �����������
	public String getText() {
		return text;	
	}
	public LocalDate getDate() {
		return date;
	}
	public boolean saveNotice(int id_user) throws SQLException {
		boolean res;
		DataBaseManager.Connect();
		res = DataBaseManager.BDAddNotice(id_user, this);
		DataBaseManager.Disconnect();
		return false;
	}
	public void delete() throws SQLException {
		DataBaseManager.Connect();
		DataBaseManager.BDDeleteNotice(id);
		DataBaseManager.Disconnect();
		clear();
	}
}
