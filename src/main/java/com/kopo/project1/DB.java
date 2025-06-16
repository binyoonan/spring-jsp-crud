package com.kopo.project1;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.sqlite.SQLiteConfig;

public class DB {
	
	private Connection connection;

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void open() {
	    try {
	        String dbFileName = "c:/Users/SMART-01/sts-workspace/tomcat.sqlite";
	        SQLiteConfig config = new SQLiteConfig();
	        // busy_timeout 추가 (3초 대기)
	        this.connection = DriverManager.getConnection(
	            "jdbc:sqlite:/" + dbFileName + "?busy_timeout=3000", 
	            config.toProperties()
	        );
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	private void close() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createTable() {
		this.open();
		String query = "CREATE TABLE user (idx INTEGER PRIMARY KEY AUTOINCREMENT, user_type TEXT, id TEXT, pwd TEXT, name TEXT, phone TEXT, address TEXT, created TEXT, last_updated TEXT);";
		try {
			Statement statement = this.connection.createStatement();
			statement.executeQuery(query);
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}
	
    public static String sha512(String input) {
        try {
            // SHA-512 해시 알고리즘 사용
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashedBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));

            // 바이트 배열을 16진수 문자열로 변환
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 알고리즘을 사용할 수 없습니다.", e);
        }
    }
	
	public void insertData(User user) {
		this.open();
		String query = "INSERT INTO user (user_type, id, pwd, name, phone, address, created, last_updated) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setString(1, "guest");
			statement.setString(2, user.id);
			user.pwd = sha512(user.pwd);
			statement.setString(3, user.pwd);
			statement.setString(4, user.name);
			statement.setString(5, user.phone);
			statement.setString(6, user.address);
			String now = (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new java.util.Date());
			statement.setString(7, now);
			statement.setString(8, now);
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}
	
	public ArrayList<User> selectAll() {
		this.open();
		ArrayList<User> data = new ArrayList<>();
		try {
			String query = "SELECT * FROM user";
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				int idx = result.getInt("idx");
				String userType = result.getString("user_type");
				String id = result.getString("id");
				String pwd = result.getString("pwd");
				String name = result.getString("name");
				String phone = result.getString("phone");
				String address = result.getString("address");
				String created = result.getString("created");
				String lastUpdated = result.getString("last_updated");
				data.add(new User(idx, userType, id, pwd, name, phone, address, created, lastUpdated));
			}
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return data;
	}

	public User login(User user) {
		this.open();
		User returnData = new User();
		try {
			String query = "SELECT * FROM user WHERE id=? AND pwd=?";
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setString(1, user.id);
			user.pwd = sha512(user.pwd);
			statement.setString(2, user.pwd);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				int idx = result.getInt("idx");
				String userType = result.getString("user_type");
				String id = result.getString("id");
				String pwd = result.getString("pwd");
				String name = result.getString("name");
				String phone = result.getString("phone");
				String address = result.getString("address");
				String created = result.getString("created");
				String lastUpdated = result.getString("last_updated");
				returnData = new User(idx, userType, id, pwd, name, phone, address, created, lastUpdated);
			}
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return returnData;
	}
	
	public User findById(String id) {
		this.open();
		User user = new User();
		try {
			String query = "SELECT * FROM user WHERE id = ?";
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setString(1, id);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				int idx = result.getInt("idx");
				String userType = result.getString("user_type");
				String userId = result.getString("id");
				String pwd = result.getString("pwd");
				String name = result.getString("name");
				String phone = result.getString("phone");
				String address = result.getString("address");
				String created = result.getString("created");
				String lastUpdated = result.getString("last_updated");
				user = new User(idx, userType, userId, pwd, name, phone, address, created, lastUpdated);
				statement.close();
				return user;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		this.close();
		return user;
	}

	public void updateUser(User user) {
	    this.open();
	    try {
	        String query = "UPDATE user SET name = ?, phone = ?, address = ?, last_updated = ?, user_type = ? WHERE id = ?";
	        PreparedStatement statement = this.connection.prepareStatement(query);
	        statement.setString(1, user.getName());
	        statement.setString(2, user.getPhone());
	        statement.setString(3, user.getAddress());
	        String now = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
	        statement.setString(4, now);
	        statement.setString(5, user.getUserType());
	        statement.setString(6, user.getId());
	        statement.execute();
	        statement.close();
	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        this.close();
	    }
	    this.close();
	}

	public int getTotalUserCount() {
	    this.open();
	    try {
	        String query = "SELECT COUNT(*) FROM user";
	        PreparedStatement statement = this.connection.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            return resultSet.getInt(1);
	        }
	        statement.close();
	        resultSet.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        this.close();
	    }
	    return 0; 
	}

	public int getSignUpTodayUserCount() {
		this.open();
		try {
			String query = "SELECT COUNT(*) FROM user WHERE date(created) = date('now')";
	        PreparedStatement statement = this.connection.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            return resultSet.getInt(1);
	        }
	        statement.close();
	        resultSet.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


}
