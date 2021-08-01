package com.joao.java_hibernate.basics;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		Phone phone = new Phone();
		phone.setCountryCode(55);
		phone.setAreaCode(61);
		phone.setPhonePrefix(99595);
		phone.setLineNumber(5656);

		User user = new User();
		user.setId(1);
		user.setName("David");
		user.setEmail("david@email.com");
		user.setPassword("password123");
		user.setNotStoredField(12345);
		user.setPhone(phone);

		User user2 = new User();
		user2.setId(2);
		user2.setName("Mary");
		user2.setEmail("mary@email.com");
		user2.setPassword("password123");
		user2.setNotStoredField(12345);
		user2.setPhone(phone);

		User david = null;

		Configuration configuration = new Configuration().configure().addAnnotatedClass(User.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();
		session.save(user);
		session.save(user2);
		transaction.commit();

		transaction = session.beginTransaction();
//	=================
		david = (User) session.get(User.class, 2);
//	=================
		transaction.commit();

		System.out.println(david);

	}
}
