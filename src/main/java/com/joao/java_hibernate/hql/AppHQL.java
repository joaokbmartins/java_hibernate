package com.joao.java_hibernate.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.joao.java_hibernate.relations.oneToOne.Book;

public class AppHQL {

	public static void main(String[] args) {

		Configuration config = new Configuration().addAnnotatedClass(Book.class).configure();
		SessionFactory sf = config.buildSessionFactory();
		Session s1 = sf.openSession();

		s1.getTransaction().begin();
//		DataLoader.loadShipsPack(s1);

		System.out.println("==================================================");

		Query<Book> q1 = s1.createQuery("FROM Book");
		List<Book> books = q1.getResultList();

		for (Book b : books) {
			System.out.println(b);
		}

		System.out.println("==================================================");

		Query q2 = s1.createQuery("FROM Book WHERE id > 7 and id < 12");
		List<Book> books2 = q2.getResultList();

		for (Book b : books2) {
			System.out.println(b);
		}

		System.out.println("==================================================");

		Query q3 = s1.createQuery("SELECT author, name FROM Book WHERE id = 39");
		System.out.println(q3.uniqueResult());
		Object[] b = (Object[]) q3.uniqueResult();

		for (Object s : b) {
			System.out.println(s);
		}

		System.out.println("==================================================");

		Query q4 = s1.createQuery("SELECT id, name FROM Book WHERE id < 5");
		List<Object[]> info = (List<Object[]>) q4.getResultList();

		for (Object[] data : info) {
			System.out.println(data[0] + ", " + data[1]);
		}

		System.out.println("==================================================");

		Query q5 = s1.createQuery("SELECT id, author FROM Book b WHERE b.id > 45");
		List<Object[]> bookList = (List<Object[]>) q5.list();

		for (Object[] o : bookList) {
			System.out.println("Id: " + o[0] + ", Writer: " + o[1]);
		}

		System.out.println("==================================================");

		s1.getTransaction().commit();
		s1.close();

	}

}
