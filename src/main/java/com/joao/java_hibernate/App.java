package com.joao.java_hibernate;

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

		User user = new User();

		user.setId(1);
		user.setName("David");

		System.out.println(user);

		Configuration configuration = new Configuration().configure().addAnnotatedClass(User.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();

		session.save(user);

		transaction.commit();

	}
}
