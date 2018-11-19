package warsztaty_2.SchoolOfProgramming;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Exercise {
	
	private int id;
	private String title;
	private String description;
	
	public Exercise(){
		
	}
	
	public Exercise(int id, String title, String description){
		this.id=id;
		this.title=title;
		this.description=description;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void saveToDB(Connection conn) throws SQLException {
		  if (this.id == 0) {
		    String sql = "INSERT INTO exercise(title, description) VALUES (?, ?)";
		    String generatedColumns[] = { "ID" };
		    PreparedStatement preparedStatement;
		    preparedStatement = conn.prepareStatement(sql, generatedColumns);
		    preparedStatement.setString(1, this.title);
		    preparedStatement.setString(2, this.description);
		    preparedStatement.executeUpdate();
		    ResultSet rs = preparedStatement.getGeneratedKeys();
		    if (rs.next()) {
		      this.id = rs.getInt(1);
		    }
		  }else {
			    String sql = "UPDATE exercise SET title=?, description=? where id = ?";
			    PreparedStatement preparedStatement;
			    preparedStatement = conn.prepareStatement(sql);
			    preparedStatement.setString(1, this.title);
			    preparedStatement.setString(2, this.description);
			    preparedStatement.setInt(3, this.id);
			    preparedStatement.executeUpdate();
			}
		}
	
	static public Exercise loadExerciseById(Connection conn, int id) throws SQLException {
	    String sql = "SELECT * FROM exercise where id=?";
	    PreparedStatement preparedStatement;
	    preparedStatement = conn.prepareStatement(sql);
	    preparedStatement.setInt(1, id);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    if (resultSet.next()) {
	        Exercise loadedExercise = new Exercise();
	        loadedExercise.id = resultSet.getInt("id");
	        loadedExercise.title = resultSet.getString("title");
	        loadedExercise.description = resultSet.getString("description");
	        return loadedExercise;}
	    return null;}
	
	static public Exercise[] loadAllExercises(Connection conn) throws SQLException {
	    ArrayList<Exercise> exercises = new ArrayList<Exercise>();
	    String sql = "SELECT * FROM exercise"; PreparedStatement preparedStatement;
	    preparedStatement = conn.prepareStatement(sql);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    while (resultSet.next()) {
	        Exercise loadedExercise = new Exercise();
	        loadedExercise.id = resultSet.getInt("id");
	        loadedExercise.title = resultSet.getString("title");
	        loadedExercise.description = resultSet.getString("description");
	        exercises.add(loadedExercise);}
	    Exercise[] uArray = new Exercise[exercises.size()]; uArray = exercises.toArray(uArray);
	    return uArray;}
	
	public void delete(Connection conn) throws SQLException {
	    if (this.id != 0) {
	        String sql = "DELETE FROM exercise WHERE id= ?";
	        PreparedStatement preparedStatement;
	        preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setInt(1, this.id);
	        preparedStatement.executeUpdate();
	        this.id=0;
	    }
	}
	
	
}
