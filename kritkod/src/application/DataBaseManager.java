package application;
import java.sql.*;
import java.time.LocalDate;
/*
 * ����� ��� ������ � ����� ������
 */
public class DataBaseManager {
	private static Connection conn;
	private static Statement statmt;
	private static ResultSet resSet;
	
	// ����������� � ���� ������
	public static void Connect() throws ClassNotFoundException, SQLException {
		conn = null;
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:testBD.db3");
		statmt = conn.createStatement();
	}
	
	//����� ��������� ������������ �� ���� ������ �� ������ � ������ � resSet �������� ������������ ��� ���������
	public static ResultSet BDGetUser(String login, String password) throws SQLException {
		String query = "SELECT id_user, name_user, date_of_birth, gender FROM users "
				+ "where login_user='" + login + "' AND password_user='" + password +"'";
		resSet = statmt.executeQuery(query);
		return resSet;
	}
	//����� ������ ������������ � ���� ������
	public static void BDAddUser(String login, String password, String secriteQuestion, String answer, User user) throws SQLException {
		String query = "INSERT INTO 'users' "
				+ "(login_user, "
				+ "password_user, "
				+ "name_user, "
				+ "date_of_birth, "
				+ "gender, "
				+ "secrite_question, "
				+ "answer) "
				+ "VALUES "
				+ "('" + login + "', "
				+ "'" + password + "', "
				+ "'" + user.getName() + "', "
				+ "'" + java.sql.Date.valueOf(user.getBirthDate()) + "', "
				+ "'" + user.getGender() + "', "
				+ "'" + secriteQuestion + "', "
				+ "'" + answer + "')";
		statmt.execute(query);
	}

	public static void Disconnect() throws SQLException {
		// �� ����� � ������ ���� �����
	}
}

