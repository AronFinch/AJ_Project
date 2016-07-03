package model;
import java.sql.*;
import java.util.LinkedHashSet;
/*
 *  ласс дл€ работы с базой данных
 */
public class DataBaseManager {
	private static Connection conn;
	private static ResultSet resSet;
	
	// ”становление соеденени€
	public static void Connect(){
		try {
			Class.forName("org.sqlite.JDBC"); // регистраци€ драйвера
			conn = DriverManager.getConnection("jdbc:sqlite:testBD.db3");
			//получение соединени€
		} catch(ClassNotFoundException CNFex) {
			CNFex.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//методы получени€ пользовател€ из базы данных по логину и паролю 
	public static boolean BDGetUser(String login, String password, User user) throws SQLException{
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
			return true;
		} else {
			return false;
		}
	}
	public static boolean BDGetUser(String login, User user) throws SQLException {
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
			return true;
		} else {
			return false;
		}
	}
	//метод записи пользовател€ в базу данных
	public static boolean BDAddUser(String login,
								 String password,
								 String secriteQuestion,
								 String answer,
								 User user) throws SQLException{
		Statement statmt = conn.createStatement();
		String query = "SELECT * FROM users where login_user='" + login + "'";
		resSet = statmt.executeQuery(query);
		if(resSet.next())
			return false; // такой пользователь уже есть
		
		query = "INSERT INTO 'users' "
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
		statmt.execute(query); // тут мы сохран€ем бд 
		query = "SELECT id_user FROM users "
					+ "where login_user='" + login + "'";
		resSet = statmt.executeQuery(query);
		if(resSet.next()) {
			user.setId(resSet.getInt("id_user")); // тут мы получаем id сохранЄнного пользовател€
		}
		return true;
	}
	// загрузка из Ѕƒ вопроса
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
	//загрузка из Ѕƒ ответа
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
	// загрузка всех целей с id пользовател€
	public static void BDLoadAllTargets(int id_user, LinkedHashSet<Target> targetList) throws SQLException {
		Statement statmt = conn.createStatement();
		String query = "SELECT * FROM targets "
				+ "WHERE id_user=" + id_user + " "
				+ "ORDER BY date_begin_target ASC, date_end_target ASC";
		resSet = statmt.executeQuery(query);
		while(resSet.next()) {
			Target target = new Target();
			target.setId(resSet.getInt("id_target"));
			target.setLabel(resSet.getString("name_target"));
			target.setDescription(resSet.getString("description_target"));
			target.setIMG(resSet.getString("img_target"));
			target.setStartDate(resSet.getDate("date_begin_target").toLocalDate());
			target.setEndDate(resSet.getDate("date_end_target").toLocalDate());
			target.setLevel(resSet.getInt("level_target"));
			// тут добавление целей
			targetList.add(target);
		}
		resSet.close();
	}
}

