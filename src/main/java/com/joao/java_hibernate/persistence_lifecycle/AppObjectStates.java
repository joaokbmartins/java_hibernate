package com.joao.java_hibernate.persistence_lifecycle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.joao.java_hibernate.relations.oneToOne.Book;

public class AppObjectStates {

	public static void main(String[] args) {

		Configuration config = new Configuration().addAnnotatedClass(Book.class).configure();
		SessionFactory sf = config.buildSessionFactory();
		Session session = sf.openSession();

		session.getTransaction().begin();

//		DataLoader.loadBooksPack(session);

		int id = 56;

		/**
		 * Transient State: object not related to database:
		 */
		Book b1 = new Book();
		b1.setId(id);
		b1.setAuthor("Assis");
		b1.setName("Dom Casmurro");

		/**
		 * Persistence State: object present in database:
		 */
		session.save(b1);

		Query<Book> query = session.createQuery("FROM Book WHERE id = :id");
		query.setParameter("id", id);
		Book b2 = (Book) query.uniqueResult();

		session.getTransaction().commit();

		/**
		 * Detached State: object not related to database after transaction has been
		 * committed:
		 */
		b2.setAuthor("Graciliano");

		System.out.println(b2);

		session.close();

	}

}
