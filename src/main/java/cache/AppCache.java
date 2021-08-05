package cache;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import relations.oneToOne.Book;
import relations.oneToOne.Reader;

/*
 * *
 * TODO: FIRST LEVEL CACHE IS ALREADY ENABLED BY DEFAULT. Same requests from same session call's DB only once.ff
 * *
 * TODO: TO ENABLE TWO LEVEL CACHE:
 * *
 * ADD DEPENDENCIES TO POM:
 * ___<dependency>
 * ______<groupId>org.ehcache</groupId>
 * ______<artifactId>ehcache</artifactId>
 * ______<version>3.8.1</version>
 * ___</dependency>
 * *
 * ___<dependency>
 * ______<groupId>org.hibernate</groupId>
 * ______<artifactId>hibernate-ehcache</artifactId>
 * ______<version>5.4.10.Final</version> // SAVE VERSION OF HIBERNATE DEPENDENCY
 * ___</dependency>
 * *
 * SET PROPERTIES ON hibernate.cfg.xml:
 * ___<property name="hibernate.cache.use_second_level_cache">true</property>
 * ___<property name="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.EhCacheRegionFactory</property>
 * *
 * SET CLASS AS CACHEABLE:
 * ___@Cacheable
 * ___@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
 * *
 * TO ENABLE CACHE FOR HQL QUERY (org.hibernate.query.Query):
 * ___PROPERTY:
 * ______<property name="hibernate.cache.use_query_cache">true</property>
 * ___QUERY OBJs CONFIG:
 * ______query1.cacheable(true);  // STORE IN THE SECOND LEVEL CACHE
 * ______query2.cacheable(true);  // FECH FRON THE SECOND LEVEL CACHE
 * *
 */

public class AppCache {

	public static void main(String[] args) {

		Configuration config = new Configuration().addAnnotatedClass(Book.class).addAnnotatedClass(Reader.class)
		    .configure();
		SessionFactory sf = config.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		// DataLoader.loadOneToOne(session);
		// session.getTransaction().commit();
		// session.close();

		session = sf.openSession();
		session.getTransaction().begin();

//		Book b = (Book) session.get(Book.class, 1);
		Query<Book> q = session.createQuery("from Book where id = 1");
		q.setCacheable(true);
		Book b = (Book) q.getSingleResult();
		System.out.println(b);
		session.getTransaction().commit();
		session.close();

		Session session1 = sf.openSession();
		session1 = sf.openSession();
		session1.getTransaction().begin();

//		Book b1 = (Book) session1.get(Book.class, 1);
		Query<Book> q1 = session1.createQuery("from Book where id = 1");
		q1.setCacheable(true);
		Book b1 = (Book) q1.getSingleResult();
		System.out.println(b1);
		session1.getTransaction().commit();
		session1.close();

	}

}
