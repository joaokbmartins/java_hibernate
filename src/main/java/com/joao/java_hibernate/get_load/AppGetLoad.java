package com.joao.java_hibernate.get_load;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.joao.java_hibernate.relations.oneToOne.Book;

/**
 * Session.get(Entity.class, id);
 * __ always reaches database;
 * __ returns null for not found id;
 * *
 * Session.load(Entity.class, id);
 * __ reaches the database only when needed;
 * __ throws exception: ObjectNotFoundException;
 * *
 */

public class AppGetLoad {

	public static void main(String[] args) {

		SessionFactory sf = new Configuration().addAnnotatedClass(Book.class).configure().buildSessionFactory();
		Session session = sf.openSession();

		session.getTransaction().begin();
//		DataLoader.loadBooksPack(session);
		System.out.print("Session.load(): ");
		Book b1 = session.load(Book.class, 20);

		System.out.print("\nSession.get(): ");
		Book b2 = session.get(Book.class, 30);
		session.getTransaction().commit();
		session.close();

		System.out.println("=============================");

		session = sf.openSession();
		session.getTransaction().begin();

		System.out.print("Session.load(): ");
		Book b3 = session.load(Book.class, 40);
		System.out.println("b3: " + b3);

		System.out.print("Session.get(): ");
		Book b4 = session.get(Book.class, 50);
		System.out.println("b4: " + b4);

		session.getTransaction().commit();
		session.close();

		System.out.println("=============================");

		session = sf.openSession();
		session.getTransaction().begin();

		/**
		 * Object id's not in the database 60 / 70
		 */
		System.out.print("Session.get(): ");
		Book b6 = session.get(Book.class, 60);
		System.out.println("b6: " + b6);

		System.out.print("Session.load(): ");
		Book b5 = session.load(Book.class, 70);
		System.out.println("b5: " + b5);

		session.getTransaction().commit();
		session.close();

	}

}
