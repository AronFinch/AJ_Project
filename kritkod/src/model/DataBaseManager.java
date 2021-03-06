package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
/*
 * ����� ��� ������ � ����� ������
 */
public class DataBaseManager {
	private static Connection conn;
	private static ResultSet resSet;
	
	// ������������ ����������
	public static void Connect(){
		try {
			Class.forName("org.sqlite.JDBC"); // ����������� ��������
			conn = DriverManager.getConnection("jdbc:sqlite:testBD.db3");
			//��������� ����������
		} catch(ClassNotFoundException CNFex) {
			CNFex.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//������ ��������� ������������ �� ���� ������ �� ������ � ������ 
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
	//����� ������ ������������ � ���� ������
	public static boolean BDAddUser(String login,
								 String password,
								 String secriteQuestion,
								 String answer,
								 User user) throws SQLException{
		Statement statmt = conn.createStatement();
		String query = "SELECT * FROM users where login_user='" + login + "'";
		resSet = statmt.executeQuery(query);
		if(resSet.next())
			return false; // ����� ������������ ��� ����
		
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
		statmt.execute(query); // ��� �� ��������� �� 
		query = "SELECT id_user FROM users "
					+ "ORDER BY id_user DESC";
		resSet = statmt.executeQuery(query);
		if(resSet.next()) {
			user.setId(resSet.getInt("id_user")); // ��� �� �������� id ������������ ������������
			return true;
		} else
			return false;
	}
	// �������� �� �� �������
	public static String BDGetQuestion(String login) throws SQLException {
		Statement statmt = conn.createStatement();
		String query = "SELECT secrite_question FROM users "
				+ "where login_user='" + login + "'";
		resSet = statmt.executeQuery(query);
		if(resSet.next()) {
			return resSet.getString("secrite_question");
		} else {
			return "������ �� ������";
		}
	}
	//�������� �� �� ������
	public static String BDGetAnswer(String login) throws SQLException {
		Statement statmt = conn.createStatement();
		String query = "SELECT answer FROM users "
				+ "where login_user='" + login + "'";
		resSet = statmt.executeQuery(query);
		if(resSet.next()) {
			return resSet.getString("answer");
		} else {
			return null;
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
	// �������� ���� ����� � id ������������
	public static void BDLoadAllTargets(int id_user, LinkedHashSet<Target> targetList) throws SQLException {
		Statement statmt = conn.createStatement();
		String query = "SELECT * FROM targets "
				+ "WHERE id_user=" + id_user + " "
				+ "ORDER BY date_begin_target ASC, date_end_target ASC";
		resSet = statmt.executeQuery(query);
		while(resSet.next()) {
			Target target = new Target();
			target.setId(resSet.getInt("id_target"));
			target.setLabel(resSet.getString("text_target"));
			target.setIMG(resSet.getString("img_target"));
			target.setStartDate(resSet.getDate("date_begin_target").toLocalDate());
			target.setEndDate(resSet.getDate("date_end_target").toLocalDate());
			target.setReward(resSet.getString("reward"));
			target.setApproved(resSet.getBoolean("approved_target"));
			// ��� ���������� �����
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
			task.setDescription(resSet.getString("text_task"));
			task.setStartDate(resSet.getDate("date_begin_task").toLocalDate());
			task.setEndDate(resSet.getDate("date_end_task").toLocalDate());
			task.setLevel(resSet.getInt("level_task"));
			task.Done(resSet.getBoolean("done"));
			task.setApproved(resSet.getBoolean("approved_task"));
			taskList.add(task);
		}
		resSet.close();
	}
	//��������� ���� ������� ����� �������� ������������
	public static ArrayList<String> BDGetOtherLogins(int id_mainUser) throws SQLException {
		ArrayList<String> res = new ArrayList<String>();
		Statement statmt = conn.createStatement();
		String query = "SELECT login_user FROM users "
				+ "WHERE id_user<>" + id_mainUser + " "
				+ "ORDER BY rating DESC";
		resSet = statmt.executeQuery(query);
		while(resSet.next()) {
			res.add(resSet.getString("login_user"));
		}
		resSet.close();
		return res;
	}
//���������� ���� � ��
	public static boolean BDAddTarget(int id_user, Target target) throws SQLException {
		Statement statmt = conn.createStatement();
		String query = "INSERT INTO 'targets' "
				+ "(text_target, "
				+ "img_target, "
				+ "date_begin_target, "
				+ "date_end_target, "
				+ "reward,"
				+ "approved_target, "
				+ "id_user) "
				+ "VALUES "
				+ "('" + target.getLabel() + "', "
				+ "'" + target.getIMG() + "', "
				+ "'" + target.getStartDate() + "', "
				+ "'" + target.getEndDate() + "', "
				+ "'" + target.getReward() + "', "
				+ "'" + target.getApproved() + "', "
				+ "" + id_user + ")";
		statmt.execute(query);
		
		query = "SELECT id_target FROM targets "
				+ "ORDER BY id_target DESC";
		resSet = statmt.executeQuery(query);
		if(resSet.next()) {
			target.setId(resSet.getInt("id_target")); // ��� �� �������� id 
			return true;
		} else 
			return false;
	}

	public static boolean BDAddTask(int id_target, Task task) throws SQLException {
		Statement statmt = conn.createStatement();
		String query = "INSERT INTO 'tasks' "
				+ "(text_task, "
				+ "date_begin_task, "
				+ "date_end_task, "
				+ "level_task, "
				+ "done, "
				+ "approved_task, "
				+ "id_target) "
				+ "VALUES "
				+ "('" + task.getDescription() + "', "
				+ "'" + task.getStartDate() + "', "
				+ "'" + task.getEndDate() + "', "
				+ "" + task.getLevel() + ", "
				+ "'" + task.isDone() + "', "
				+ "'" + task.getApproved() + "', "
				+ "" + id_target + ")";
		statmt.execute(query);
		query = "SELECT id_task FROM tasks "
				+ "ORDER BY id_task DESC";
		resSet = statmt.executeQuery(query);
		if(resSet.next()) {
			task.setId(resSet.getInt("id_task")); // ��� �� �������� id 
			return true;
		} else 
			return false;
	}

	public static void BDLoadAllNotice(int id_user, LinkedHashSet<Notice> noticeList) throws SQLException {
		Statement statmt = conn.createStatement();
		String query = "SELECT * FROM notices "
				+ "WHERE id_user=" + id_user + " "
				+ "ORDER BY date_notice DESC";
		resSet = statmt.executeQuery(query);
		while(resSet.next()) {
			Notice notice = new Notice();
			notice.setId(resSet.getInt("id_notice"));
			notice.setText(resSet.getString("text_notice"));
			notice.setDate(resSet.getDate("date_notice").toLocalDate());
			noticeList.add(notice);
		}
	}

	public static boolean BDCheckUser(String login) throws SQLException {
		Statement statmt = conn.createStatement();
		String query = "SELECT * FROM users "
				+ "WHERE login_user='" + login + "' ";
		resSet = statmt.executeQuery(query);
		if(resSet.next()) {
			return true;
		} else
			return false;
	}

	public static void BDUpdateTarget(Target target) throws SQLException {
		Statement statmt = conn.createStatement();
		String query = "UPDATE 'targets' SET "
				+ "text_target='" + target.getLabel() + "', "
				+ "img_target='" + target.getIMG() + "', "
				+ "date_begin_target='" + target.getStartDate() + "', "
				+ "date_end_target='" + target.getEndDate() + "', "
				+ "reward='" + target.getReward() + "', "
				+ "approved_target=" + target.getApproved() + " ";
		statmt.executeUpdate(query);
	}

	public static void BDUpdateTask(Task task) throws SQLException {
		Statement statmt = conn.createStatement();
		String query = "UPDATE 'tasks' SET "
				+ "text_task='" + task.getDescription() + "', "
				+ "date_begin_task='" + task.getStartDate() + "', "
				+ "date_end_task='" + task.getEndDate() + "', "
				+ "level_task=" + task.getLevel() + ", "
				+ "done=" + task.isDone() + ", "
				+ "approwed_task=" + task.getApproved() + " ";
		statmt.executeUpdate(query);
	}

	public static void BDUpdateUser(User user) throws SQLException {
		Statement statmt = conn.createStatement();
		String query = "UPDATE 'users' SET "
				+ "name_user='" + user.getName() + "', "
				+ "date_of_birth='" + user.getBirthDate() + "', "
				+ "gender='" + user.getGender() + "', "
				+ "rating=" + user.getRating() + ", ";
//				+ "secrite_question=" + task.isDone() + ", "
//				+ "answer=" + task.getApproved() + " ";
		statmt.executeUpdate(query);
	}

	public static void BDDeleteUser(int id) throws SQLException {
		Statement statmt = conn.createStatement();
		String query = "DELETE FROM 'users' "
				+ "WHERE id_user=" + id;
		statmt.executeUpdate(query);
	}

	public static void BDDeleteTarget(int id) throws SQLException {
		Statement statmt = conn.createStatement();
		String query = "DELETE FROM 'targets' "
				+ "WHERE id_target=" + id;
		statmt.executeUpdate(query);
	}

	public static void BDDeleteTask(int id) throws SQLException {
		Statement statmt = conn.createStatement();
		String query = "DELETE FROM 'tasks' "
				+ "WHERE id_task=" + id;
		statmt.executeUpdate(query);
	}

	public static boolean BDAddNotice(int id_user, Notice notice) throws SQLException {
		Statement statmt = conn.createStatement();
		String query = "INSERT INTO 'notices' "
				+ "(text_notice, "
				+ "date_notice, "
				+ "id_user) "
				+ "VALUES "
				+ "('" + notice.getText() + "', "
				+ "'" + notice.getDate() + "', "
				+ "" + id_user + ") ";
		statmt.execute(query);
		
		query = "SELECT id_notice FROM notices "
				+ "ORDER BY id_notice DESC";
		resSet = statmt.executeQuery(query);
		if(resSet.next()) {
			notice.setId(resSet.getInt("id_user")); // ��� �� �������� id ������������ ������������
				return true;
		} else
			return false;
	}

	public static void BDDeleteNotice(int id) throws SQLException {
		Statement statmt = conn.createStatement();
		String query = "DELETE FROM 'notices' "
				+ "WHERE id_notice=" + id;
		statmt.executeUpdate(query);
	}
}

