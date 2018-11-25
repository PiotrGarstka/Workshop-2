package warsztaty_2.SchoolOfProgramming.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class    Solution {

	private int id;
	private String created;
	private String updated;
	private String description;
	private int exerciseId;
	private int usersId;
	
	public Solution(){
		
		}
	
	public Solution(int id, String created, String updated, String description, int exerciseId, int usersId){
		this.id=id;
		this.created=created;
		this.updated=updated;
		this.description=description;
		this.exerciseId=exerciseId;
		this.usersId=usersId;
		
	}

	public int getId() {
		return id;
	}

	public String getCreated() {
		return created;
	}

	public String getUpdated() {
		return updated;
	}

	public String getDescription() {
		return description;
	}

	public int getExerciseId() {
		return exerciseId;
	}

	public int getUsersId() {
		return usersId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setExerciseId(int exerciseId) {
		this.exerciseId = exerciseId;
	}

	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}
	
	public void saveToDB(Connection conn) throws SQLException {
		  if (this.id == 0) {
		    String sql = "INSERT INTO solution(created, updated, description, exercise_id, users_id) VALUES (?, ?, ?, ?, ?)";
		    String generatedColumns[] = { "ID" };
		    PreparedStatement preparedStatement;
		    preparedStatement = conn.prepareStatement(sql, generatedColumns);
		    preparedStatement.setString(1, this.created);
		    preparedStatement.setString(2, this.updated);
		    preparedStatement.setString(3, this.description);
		    preparedStatement.setInt(4, this.exerciseId);
		    preparedStatement.setInt(5, this.usersId);
		    preparedStatement.executeUpdate();
		    ResultSet rs = preparedStatement.getGeneratedKeys();
		    if (rs.next()) {
		      this.id = rs.getInt(1);
		    }
		  }else {
			    String sql = "UPDATE exercise SET created=?, updated=?, description=?, exercise_id=?, users_id=? where id = ?";
			    PreparedStatement preparedStatement;
			    preparedStatement = conn.prepareStatement(sql);
			    preparedStatement.setString(1, this.created);
			    preparedStatement.setString(2, this.updated);
			    preparedStatement.setString(3, this.description);
			    preparedStatement.setInt(4, this.exerciseId);
			    preparedStatement.setInt(5, this.usersId);
			    preparedStatement.setInt(6, this.id);
			    preparedStatement.executeUpdate();
			}
		}
	
	static public Solution loadSolutionsById(Connection conn, int id) throws SQLException {
	    String sql = "SELECT * FROM solution where id=?";
	    PreparedStatement preparedStatement;
	    preparedStatement = conn.prepareStatement(sql);
	    preparedStatement.setInt(1, id);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    if (resultSet.next()) {
	        Solution loadedSolution = new Solution();
	        loadedSolution.id = resultSet.getInt("id");
	        loadedSolution.created = resultSet.getString("created");
	        loadedSolution.updated = resultSet.getString("updated");
	        loadedSolution.description = resultSet.getString("description");
	        loadedSolution.exerciseId = resultSet.getInt("exercise_id");
	        loadedSolution.usersId = resultSet.getInt("users_id");
	        return loadedSolution;}
	    return null;}
	
	static public Solution[] loadAllSolutions(Connection conn) throws SQLException {
	    ArrayList<Solution> solutions = new ArrayList<Solution>();
	    String sql = "SELECT * FROM solution"; PreparedStatement preparedStatement;
	    preparedStatement = conn.prepareStatement(sql);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    while (resultSet.next()) {
	        Solution loadedSolution = new Solution();
	        loadedSolution.id = resultSet.getInt("id");
	        loadedSolution.created = resultSet.getString("created");
	        loadedSolution.updated = resultSet.getString("updated");
	        loadedSolution.description = resultSet.getString("description");
	        loadedSolution.exerciseId = resultSet.getInt("exercise_id");
	        loadedSolution.usersId = resultSet.getInt("users_id");
	        solutions.add(loadedSolution);}
	    Solution[] uArray = new Solution[solutions.size()]; uArray = solutions.toArray(uArray);
	    return uArray;}
	
	public void delete(Connection conn) throws SQLException {
	    if (this.id != 0) {
	        String sql = "DELETE FROM solution WHERE id= ?";
	        PreparedStatement preparedStatement;
	        preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setInt(1, this.id);
	        preparedStatement.executeUpdate();
	        this.id=0;
	    }
	}
	
	 public Solution[] loadAllByUserId (Connection conn) throws SQLException {
		 ArrayList<Solution> solutionsByUserId = new ArrayList<Solution>();
		    String sql = "SELECT * FROM solution WHERE users_id=?"; PreparedStatement preparedStatement;
		    preparedStatement = conn.prepareStatement(sql);
		    preparedStatement.setInt(1, usersId);
		    ResultSet resultSet = preparedStatement.executeQuery();
		    while (resultSet.next()) {
		        Solution loadedSolutionByUserId = new Solution();
		        loadedSolutionByUserId.id = resultSet.getInt("id");
		        loadedSolutionByUserId.created = resultSet.getString("created");
		        loadedSolutionByUserId.updated = resultSet.getString("updated");
		        loadedSolutionByUserId.description = resultSet.getString("description");
		        loadedSolutionByUserId.exerciseId = resultSet.getInt("exercise_id");
		        loadedSolutionByUserId.usersId = resultSet.getInt("users_id");
		        solutionsByUserId.add(loadedSolutionByUserId);}
		    Solution[] uArray = new Solution[solutionsByUserId.size()]; uArray = solutionsByUserId.toArray(uArray);
		    return uArray;
	}
	

	 public Solution[]  loadAllByExerciseId (Connection conn) throws SQLException {
		 ArrayList<Solution> solutionsByExerciseId = new ArrayList<Solution>();
		    String sql = "SELECT * FROM solution WHERE exercise_id=?"; PreparedStatement preparedStatement;
		    preparedStatement = conn.prepareStatement(sql);
		    preparedStatement.setInt(1, exerciseId);
		    ResultSet resultSet = preparedStatement.executeQuery();
		    while (resultSet.next()) {
		        Solution loadedSolutionByExerciseId = new Solution();
		        loadedSolutionByExerciseId.id = resultSet.getInt("id");
		        loadedSolutionByExerciseId.created = resultSet.getString("created");
		        loadedSolutionByExerciseId.updated = resultSet.getString("updated");
		        loadedSolutionByExerciseId.description = resultSet.getString("description");
		        loadedSolutionByExerciseId.exerciseId = resultSet.getInt("exercise_id");
		        loadedSolutionByExerciseId.usersId = resultSet.getInt("users_id");
		        solutionsByExerciseId.add(loadedSolutionByExerciseId);}
		    Solution[] uArray = new Solution[solutionsByExerciseId.size()]; uArray = solutionsByExerciseId.toArray(uArray);
		    return uArray;
	 }
	
}
