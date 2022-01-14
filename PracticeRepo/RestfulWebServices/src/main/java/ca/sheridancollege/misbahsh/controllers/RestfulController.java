package ca.sheridancollege.misbahsh.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.misbahsh.beans.Student;
import ca.sheridancollege.misbahsh.repositories.StudentRepo;

//not a Controller but a web service; when you make a request, returns any object you want
@RestController
public class RestfulController {
	
	@Autowired
	private StudentRepo repo;
	
	@GetMapping("/")
	public String goHome() {
		return "home";
	}
	
	//@GetMapping("/meow")
	//public String[] goMeow() {
		//String [] meow = {"purr","whiskers"};
		//return meow;
	//}
	
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		return repo.getStudents();
	}
	
	@GetMapping("/students/{id}")
	public Student getStudentById(@PathVariable int id){
		return repo.getStudentById(id);
	}
	
	//this is a web service; need to let user know about if code was successful
	@PostMapping(value="/students", headers={"Content-type=application/json"})
	public String addStudent(@RequestBody Student student) {
		repo.addStudent(student);
		return "Student added successfully!";
	}
	
	@PutMapping(value="/students", headers={"Content-type=application/json"})
	public void replaceStudent(@RequestBody List<Student> studentList) {
		repo.deleteStudents();
		repo.resetCounter();
		for (Student s : studentList) {
			repo.addStudent(s);
		}
	}

}


//CRUD - COLLECTION

//GET - READ localhost:8080/students

//POST - ADD localhost:8080/students

//PUT - EDIT localhost:8080/students

//DELETE localhost:8080/students


//CRUD - SINGLE ELEMENT

//GET - READ localhost:8080/students/{id}/courses/{id} --> e.g. courses had collection of grades

//**Not done for a single element** POST - ADD

//PUT - EDIT

//DELETE 
