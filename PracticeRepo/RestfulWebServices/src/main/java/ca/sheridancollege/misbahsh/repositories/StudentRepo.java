package ca.sheridancollege.misbahsh.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.misbahsh.beans.Student;

@Repository
public class StudentRepo {
	
	 @Autowired
	    private NamedParameterJdbcTemplate jdbc;

	    public void addStudent(Student student) {
	        MapSqlParameterSource parameters = new MapSqlParameterSource();
	            String query = "INSERT INTO students (name) VALUES (:name)";
	            parameters.addValue("name", student.getName());
	            jdbc.update(query, parameters);
	        }
	    
	    
	    public ArrayList<Student> getStudents() {
	        ArrayList<Student> students = new ArrayList<Student>();
	        MapSqlParameterSource parameters = new MapSqlParameterSource();
	        String query = "SELECT * FROM STUDENTS";
	        List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
	        for (Map<String, Object> row : rows) {
	            Student d = new Student();
	            d.setId((Integer)row.get("id"));
	            d.setName((String)row.get("name"));
	            students.add(d);
	        }
	        return students;
	    }
	    
	    
	    public Student getStudentById(int id) {
	        ArrayList<Student> students = new ArrayList<Student>();
	        MapSqlParameterSource parameters = new MapSqlParameterSource();
	        String query ="SELECT * FROM STUDENTS WHERE id=:id";
	        parameters.addValue("id", id);
	        List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
	        
	        for(Map<String, Object> row:rows) {
	            Student d = new Student();
	            d.setId((Integer)row.get("id"));
	            d.setName((String)row.get("name"));
	        
	            students.add(d);
	        }
	        if(students.size() > 0) {
	            return students.get(0);
	        }else {
	            return null;
	        }
	    }
	    
	    public void deleteStudents() {
	        MapSqlParameterSource parameters = new MapSqlParameterSource();
	        String query = "TRUNCATE TABLE STUDENTS";
            jdbc.update(query, parameters);
	    }
	    
	    public void resetCounter() {
	        MapSqlParameterSource parameters = new MapSqlParameterSource();
	        String query = "ALTER TABLE STUDENTS ALTER COLUMN ID RESTART WITH 1";
            jdbc.update(query, parameters);
	    }
	    
	    

}
