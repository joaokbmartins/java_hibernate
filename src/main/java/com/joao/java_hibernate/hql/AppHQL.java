package com.joao.java_hibernate.hql;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.joao.java_hibernate.relations.oneToOne.Book;

public class AppHQL {

	public static void main(String[] args) {

		Configuration config = new Configuration().addAnnotatedClass(Book.class).configure();
		SessionFactory sf = config.buildSessionFactory();
		Session session = sf.openSession();

		session.getTransaction().begin();
//		DataLoader.loadShipsPack(s1);

		System.out.println("==================================================");

		Query<Book> q1 = session.createQuery("FROM Book");
		List<Book> books = q1.getResultList();

		for (Book b : books) {
			System.out.println(b);
		}

		System.out.println("==================================================");

		Query q2 = session.createQuery("FROM Book WHERE id > 7 and id < 12");
		List<Book> books2 = q2.getResultList();

		for (Book b : books2) {
			System.out.println(b);
		}

		System.out.println("==================================================");

		Query q3 = session.createQuery("SELECT author, name FROM Book WHERE id = 39");
		System.out.println(q3.uniqueResult());
		Object[] b = (Object[]) q3.uniqueResult();

		for (Object s : b) {
			System.out.println(s);
		}

		System.out.println("==================================================");

		Query q4 = session.createQuery("SELECT id, name FROM Book WHERE id < 5");
		List<Object[]> info = (List<Object[]>) q4.getResultList();

		for (Object[] data : info) {
			System.out.println(data[0] + ", " + data[1]);
		}

		System.out.println("==================================================");

		Query q5 = session.createQuery("SELECT id, author FROM Book b WHERE b.id > 45");
		List<Object[]> bookList = (List<Object[]>) q5.list();

		for (Object[] o : bookList) {
			System.out.println("Id: " + o[0] + ", Writer: " + o[1]);
		}

		System.out.println("==================================================");

		Query q6 = session.createQuery("SELECT SUM(id) FROM Book");
		Long idSum = (Long) q6.uniqueResult();
		System.out.println(idSum);

		System.out.println("==================================================");

		Query q7 = session.createQuery("SELECT AVG(id) FROM Book");
		Double avrgId = (Double) q7.uniqueResult();
		System.out.println(avrgId);

		System.out.println("==================================================");

		int param = 30;
		Query q8 = session.createQuery("SELECT MAX(id) FROM Book WHERE id < :limit");
		q8.setParameter("limit", param);
		Integer maxId = (Integer) q8.uniqueResult();
		System.out.println(maxId);

		System.out.println("==================================================");

		NativeQuery<Book> q9 = session.createNativeQuery("SELECT * FROM books WHERE id > 40");
		q9.addEntity(Book.class);
		List<Book> sqlBooks = (List<Book>) q9.list();

		for (Book book : sqlBooks) {
			System.out.println(book);
		}

		System.out.println("==================================================");

		int min = 30, max = 35;
		NativeQuery q0 = session.createNativeQuery("SELECT author, name FROM books WHERE id > :min AND id < :max");
		q0.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		q0.setParameter("min", min);
		q0.setParameter("max", max);
		List inf = q0.list();

		for (Object o : inf) {
			Map map = (Map) o;
			System.out.println(map.get("author") + " " + map.get("name"));
		}

		System.out.println("==================================================");

		session.getTransaction().commit();
		session.close();

	}

}
