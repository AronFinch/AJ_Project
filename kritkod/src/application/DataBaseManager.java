package application;
import java.sql.*;
/*
 * Класс для работы с базой данных
 */
public class DataBaseManager {
	private static Connection conn;
	private static Statement statmt;
	private static ResultSet resSet;
	
	public static void Connect() throws ClassNotFoundException, SQLException {
		conn = null;
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:testBD.db3");
		statmt = conn.createStatement();
	}
	
	//метод получения пользователя из базы данных по логину и паролю
	public static ResultSet GetUser(String login, String password) throws SQLException {
		// TODO Auto-generated method stub
		String query = "SELECT id_user, name_user, date_of_birth, gender FROM users "
				+ "where login_user='" + login + "' AND password_user='" + password +"'";
		resSet = statmt.executeQuery(query);
		return resSet;
	}
}

