package com.joao.java_hibernate.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.joao.java_hibernate.relations.DataLoader;
import com.joao.java_hibernate.relations.oneToOne.Book;

public class AppHQL {

	public static void main(String[] args) {

		Configuration config = new Configuration().addAnnotatedClass(Book.class).configure();
		SessionFactory sf = config.buildSessionFactory();
		Session s1 = sf.openSession();

		s1.getTransaction().begin();
		DataLoader.loadShipsPack(s1);

		Query<Book> q1 = s1.createQuery("from Book");
		List<Book> books = q1.getResultList();

		for (Book b : books) {
			System.out.println(b);
		}

		System.out.println("==================================================");

		Query q2 = s1.createQuery("from Book where id > 18 and id < 44");
		List<Book> books2 = q2.getResultList();
		for (Book b : books2) {
			System.out.println(b);
		}

		s1.getTransaction().commit();
		s1.close();

	}

}
