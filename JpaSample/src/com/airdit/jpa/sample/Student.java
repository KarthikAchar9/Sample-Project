package com.airdit.jpa.sample;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "student_info")
public class Student {
@Id
private int regno;
private int age;
private String Name;
public int getRegno() {
	return regno;
}
public void setRegno(int regno) {
	this.regno = regno;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}

}
