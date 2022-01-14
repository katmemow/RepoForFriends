package ca.sheridancollege.misbahsh.beans;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
	
	private int id;
	private String name;
	private double grade;
	private String letterGrade;
	
	private void setLetterGrade() {
		if (grade >=80) letterGrade = "A"
		else if (grade >=70) letterGrade = "B"
	}
	
	

	public Student(String name, double grade) {
		this.name = name;
		this.grade = grade;
	}
	
}
