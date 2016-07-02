package model;
import java.sql.*;
/*
 * Класс для работы с базой данных
 */
public class DataBaseManager {
	private static Connection conn;
	private static ResultSet resSet;
	
	// Установление соеденения
	public static void Connect(){
		try {
			Class.forName("org.sqlite.JDBC"); // регистрация драйвера
			conn = DriverManager.getConnection("jdbc:sqlite:testBD.db3");
			//получение соединения
		} catch(ClassNotFoundException CNFex) {
			CNFex.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//методы получения пользователя из базы данных по логину и паролю 
	public static void BDGetUser(String login, String password, User user) throws SQLException{
		Statement statmt = conn.createStatement();
		String query = "SELECT id_user, name_user, date_of_birth, gender, rating "
				+ "FROM users "
				+ "where login_user='" + login + "' AND password_user='" + password +"'";
		resSet = statmt.executeQuery(query);
		if(resSet.next()) {
			user.setId(resSet.getInt("id_user"));
			user.setName(resSet.getString("name_user"));
			user.setBirthDate(resSet.getDate("date_of_birth").toLocalDate());
			user.setGender(resSet.getString("gender").charAt(0));
			user.setRating(resSet.getInt("rating"));
		}
	}
	public static void BDGetUser(String login, User user) throws SQLException {
		Statement statmt = conn.createStatement();
		String query = "SELECT id_user, name_user, date_of_birth, gender, rating "
				+ "FROM users "
				+ "where login_user='" + login + "'";
		resSet = statmt.executeQuery(query);
		if(resSet.next()) {
			user.setId(resSet.getInt("id_user"));
			user.setName(resSet.getString("name_user"));
			user.setBirthDate(resSet.getDate("date_of_birth").toLocalDate());
			user.setGender(resSet.getString("gender").charAt(0));
			user.setRating(resSet.getInt("rating"));
		}
	}
	//метод записи пользователя в базу данных
	public static void BDAddUser(String login,
								 String password,
								 String secriteQuestion,
								 String answer,
								 User user) throws SQLException{
		
		Statement statmt = conn.createStatement();
		String query = "INSERT INTO 'users' "
				+ "(login_user, "
				+ "password_user, "
				+ "name_user, "
				+ "date_of_birth, "
				+ "gender, "
				+ "rating, "
				+ "secrite_question, "
				+ "answer) "
				+ "VALUES "
				+ "('" + login + "', "
				+ "'" + password + "', "
				+ "'" + user.getName() + "', "
				+ "'" + java.sql.Date.valueOf(user.getBirthDate()) + "', "
				+ "'" + user.getGender() + "', "
				+ "" + user.getRating() + ", "
				+ "'" + secriteQuestion + "', "
				+ "'" + answer + "')";
		statmt.execute(query); // тут мы сохраняем бд 
		query = "SELECT id_user FROM users "
					+ "where login_user='" + login + "'";
		resSet = statmt.executeQuery(query);
		if(resSet.next()) {
			user.setId(resSet.getInt("id_user")); // тут мы получаем id сохранённого пользователя
		}
	}
	// загрузка из БД вопроса
	public static String BDGetQuestion(int id_user) throws SQLException {
		Statement statmt = conn.createStatement();
		String query = "SELECT secrite_question FROM users "
				+ "where id_user=" + id_user;
		resSet = statmt.executeQuery(query);
		if(resSet.next()) {
			return resSet.getString("secrite_question");
		} else {
			return "";
		}
	}
	//загрузка из БД ответа
	public static String BDGetAnswer(int id_user) throws SQLException {
		Statement statmt = conn.createStatement();
		String query = "SELECT answer FROM users "
				+ "where id_user=" + id_user;
		resSet = statmt.executeQuery(query);
		if(resSet.next()) {
			return resSet.getString("answer");
		} else {
			return "";
		}
	}
	public static void BDUpdatePassword(String newPassword, int id_user) throws SQLException { 
		Statement statmt = conn.createStatement();
		String query = "UPDATE 'users' "
				+ "SET password_user='" + newPassword + "' "
				+ "WHERE id_user='" + id_user + "'";
		statmt.executeUpdate(query);
	}
	public static void Disconnect() {
		try {
			conn.close();
		} catch(SQLException SQLex) {
			SQLex.printStackTrace();
		}
	}
}

