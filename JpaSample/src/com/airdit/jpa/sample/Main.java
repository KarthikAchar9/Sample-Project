package com.airdit.jpa.sample;

import org.hibernate.*;
import org.hibernate.cfg.*;

public class Main {
public static void main(String[] args) {
	/*Configuration config=new Configuration();
	SessionFactory sessionFactory=config.buildSessionFactory();
	Session session=factory.openSession();*/
	/*Configuration cfg = new Configuration();
	SessionFactory sessionFactory = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
	Session session=sessionFactory.openSession();*/
	Session session=new Configuration()  
	         .configure().buildSessionFactory().openSession();  	 
	
	Transaction t=session.beginTransaction();
	
	Student s1=new Student();
	s1.setRegno(6);
	s1.setName("Raju");
	s1.setAge(25);

	Student s2=new Student();
	s2.setRegno(7);
	s2.setName("Sanath");
	s2.setAge(24);
	
	session.persist(s1);
	session.persist(s2);

	
	t.commit();
	session.close();
	System.out.println("successfully saved");
}
}
