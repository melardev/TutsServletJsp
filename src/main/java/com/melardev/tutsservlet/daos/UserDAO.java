package com.melardev.tutsservlet.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.melardev.tutsservlet.beans.User;
import com.melardev.tutsservlet.dbfactory.DBConnectionFactory;

public class UserDAO {

	public static int addUser(User user) throws SQLException, IOException {
		Connection con = null;
		int newUserId = -1;
		try {
			con = DBConnectionFactory.getInstance().getConnection();
			final String sql = "INSERT into users (first_name, last_name, username) values (?,?,?)";
			//create prepared statement with option to get auto generated keys
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getUserName());


			stmt.execute();

			//Get auto generated keys
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next())
				{
				newUserId = rs.getInt(1);
				user.setId(newUserId); //get first column, which in the database is id}
				}
			rs.close();
			stmt.close();
		} finally {
			con.close();
		}

		return newUserId;
	}

	public static ArrayList<User> getUsers() throws SQLException, IOException {

		//get connection from connection pool
		Connection con = DBConnectionFactory.getInstance().getConnection();

		ArrayList<User> users = new ArrayList<User>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * from users");

			//iterate over resultset and create User objects
			while (rs.next()) {

				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String userName = rs.getString("username");

				User user = new User(id, firstName, lastName, userName);
				users.add(user);
			}

			return users;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
	}

	public static int checkUserCredentials(String username, String password) throws SQLException, IOException {
		Connection conn = DBConnectionFactory.getInstance().getConnection();
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
		ResultSet result = stmt.executeQuery(sql);
		if (result.next())
			return result.getInt("id");
		return -1;
	}
}
