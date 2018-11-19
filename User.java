package warsztaty_2.SchoolOfProgramming;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

public class User {
	private int id;
    private String username;
    private String password;
    private String email;
    private int userGroupId;
    
    public User() {}
    
    public User(String username, String email, String password, int userGroupId) {
        this.username = username;
        this.email = email;
        setPassword(password);
        this.userGroupId=userGroupId;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setUserGroupId(int userGroupId) {
		this.userGroupId = userGroupId;
	}

	public int getUserGroupId() {
		return userGroupId;
	}
	
	public void saveToDB(Connection conn) throws SQLException{
		if(this.id == 0){
			String sql = "INSERT INTO users(username, email, password, user_group_id) VALUES (?, ?, ?, ?)";
		    String generatedColumns[] = { "ID" };
		    PreparedStatement preparedStatement;
		    preparedStatement = conn.prepareStatement(sql, generatedColumns);
		    preparedStatement.setString(1, this.username);
		    preparedStatement.setString(2, this.email);
		    preparedStatement.setString(3, this.password);
		    preparedStatement.setInt(4, this.userGroupId);
		    preparedStatement.executeUpdate();
		    ResultSet rs = preparedStatement.getGeneratedKeys();
		    if (rs.next()) {
		      this.id = rs.getInt(1);
		    }
		} else {
		    String sql = "UPDATE users SET username=?, email=?, password=?, user_group_id=? where id = ?";
		    PreparedStatement preparedStatement;
		    preparedStatement = conn.prepareStatement(sql);
		    preparedStatement.setString(1, this.username);
		    preparedStatement.setString(2, this.email);
		    preparedStatement.setString(3, this.password);
		    preparedStatement.setInt(4, this.userGroupId);
		    preparedStatement.setInt(5, this.id);
		    preparedStatement.executeUpdate();
		}
	}
	
	static public User loadUserById(Connection conn, int id) throws SQLException {
	    String sql = "SELECT * FROM users where id=?";
	    PreparedStatement preparedStatement;
	    preparedStatement = conn.prepareStatement(sql);
	    preparedStatement.setInt(1, id);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    if (resultSet.next()) {
	        User loadedUser = new User();
	        loadedUser.id = resultSet.getInt("id");
	        loadedUser.username = resultSet.getString("username");
	        loadedUser.password = resultSet.getString("password");
	        loadedUser.email = resultSet.getString("email");
	        loadedUser.userGroupId = resultSet.getInt("user_group_id");
	        return loadedUser;}
	    return null;}
	
	
	static public User[] loadAllUsers(Connection conn) throws SQLException {
	    ArrayList<User> users = new ArrayList<User>();
	    String sql = "SELECT * FROM users"; PreparedStatement preparedStatement;
	    preparedStatement = conn.prepareStatement(sql);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    while (resultSet.next()) {
	        User loadedUser = new User();
	        loadedUser.id = resultSet.getInt("id");
	        loadedUser.username = resultSet.getString("username");
	        loadedUser.password = resultSet.getString("password");
	        loadedUser.email = resultSet.getString("email");
	        users.add(loadedUser);}
	    User[] uArray = new User[users.size()]; uArray = users.toArray(uArray);
	    return uArray;}
	
	public void delete(Connection conn) throws SQLException {
	    if (this.id != 0) {
	        String sql = "DELETE FROM users WHERE id= ?";
	        PreparedStatement preparedStatement;
	        preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setInt(1, this.id);
	        preparedStatement.executeUpdate();
	        this.id=0;
	    }
	}
	

	 public User[] loadAllByGrupId  (Connection conn) throws SQLException {
		 ArrayList<User> usersByGroupId = new ArrayList<User>();
		    String sql = "SELECT * FROM users WHERE user_group_id=?"; PreparedStatement preparedStatement;
		    preparedStatement = conn.prepareStatement(sql);
		    preparedStatement.setInt(1, userGroupId);
		    ResultSet resultSet = preparedStatement.executeQuery();
		    while (resultSet.next()) {
		        User loadUserByGroupId = new User();
		        loadUserByGroupId.id = resultSet.getInt("id");
		        loadUserByGroupId.username = resultSet.getString("username");
		        loadUserByGroupId.email = resultSet.getString("email");
		        loadUserByGroupId.password = resultSet.getString("password");
		        loadUserByGroupId.userGroupId = resultSet.getInt("user_group_id");
		        usersByGroupId.add(loadUserByGroupId);}
		    User[] uArray = new User[ usersByGroupId.size()]; uArray =  usersByGroupId.toArray(uArray);
		    return uArray;
	 }
	
}
