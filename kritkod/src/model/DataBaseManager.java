package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
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
	//метод записи пользователя в базу данных
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
		statmt.execute(query); // тут мы сохраняем бд 
		query = "SELECT id_user FROM users "
					+ "where login_user='" + login + "'";
		resSet = statmt.executeQuery(query);
		if(resSet.next()) {
			user.setId(resSet.getInt("id_user")); // тут мы получаем id сохранённого пользователя
		}
		return true;
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
	// загрузка всех целей с id пользователя
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

	public static void BDloadAllTasks(int id_target, LinkedHashSet<Task> taskList) throws SQLException {
		Statement statmt = conn.createStatement();
		String query = "SELECT * FROM tasks "
				+ "WHERE id_target=" + id_target + " "
				+ "ORDER BY date_begin_task ASC, date_end_task ASC";
		resSet = statmt.executeQuery(query);
		while(resSet.next()) {
			Task task = new Task();
			task.setId(resSet.getInt("id_task"));
			task.setLabel(resSet.getString("name_task"));
			task.setDescription(resSet.getString("description_task"));
			task.setStartDate(resSet.getDate("date_begin_task").toLocalDate());
			task.setEndDate(resSet.getDate("date_end_task").toLocalDate());
			task.setLevel(resSet.getInt("level_task"));
			task.Done(resSet.getBoolean("done"));
			taskList.add(task);
		}
		resSet.close();
	}
	//получение всех логинов кроме главного пользователя
	public static ArrayList<String> BDGetOtherLogins(int id_mainUser) throws SQLException {
		ArrayList<String> res = new ArrayList<String>();
		Statement statmt = conn.createStatement();
		String query = "SELECT login_user FROM users "
				+ "WHERE id_user<>" + id_mainUser + " "
				+ "ORDER BY rating ASC";
		resSet = statmt.executeQuery(query);
		while(resSet.next()) {
			res.add(resSet.getString("login_user"));
		}
		resSet.close();
		return res;
	}

	public static boolean BDAddTarget(int id_user, Target target) throws SQLException {
		Statement statmt = conn.createStatement();
		String query = "INSERT INTO 'targets' "
				+ "(name_target, "
				+ "description_target, "
				+ "img_target, "
				+ "date_begin_target, "
				+ "date_end_target, "
				+ "level_target, "
				+ "id_user) "
				+ "VALUES "
				+ "('" + target.getLabel() + "', "
				+ "'" + target.getDescription() + "', "
				+ "'" + target.getIMG() + "', "
				+ "'" + target.getStartDate() + "', "
				+ "'" + target.getEndDate() + "', "
				+ "" + id_user + ")";
		statmt.execute(query);
		query = "SELECT id_target FROM targets "
				+ "where name_target='" + target.getLabel() + "'";
		resSet = statmt.executeQuery(query);
		if(resSet.next()) {
			target.setId(resSet.getInt("id_target")); // тут мы получаем id 
			return true;
		} else 
			return false;
	}

	public static boolean BDaddTask(int id_target, Task task) throws SQLException {
		Statement statmt = conn.createStatement();
		String query = "INSERT INTO 'tasks' "
				+ "(name_task, "
				+ "description_task, "
				+ "date_begin_task, "
				+ "date_end_task, "
				+ "level_task, "
				+ "done, "
				+ "id_target) "
				+ "VALUES "
				+ "('" + task.getLabel() + "', "
				+ "'" + task.getDescription() + "', "
				+ "'" + task.getStartDate() + "', "
				+ "'" + task.getEndDate() + "', "
				+ "" + task.getLevel() + ", "
				+ "" + id_target + ")";
		statmt.execute(query);
		query = "SELECT id_task FROM task "
				+ "where name_task='" + task.getLabel() + "'";
		resSet = statmt.executeQuery(query);
		if(resSet.next()) {
			task.setId(resSet.getInt("id_target")); // тут мы получаем id 
			return true;
		} else 
			return false;
	}
}

