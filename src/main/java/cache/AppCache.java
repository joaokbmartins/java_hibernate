package cache;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import relations.oneToOne.Book;
import relations.oneToOne.Reader;

/*
 * *
 * TODO: FIRST LEVEL CACHE IS ALREADY ENABLED BY DEFAULT. Same requests from same session call's DB only once.
 * *
 * TODO: TO ENABLE TWO LEVEL CACHE:
 * *
 * ADD DEPENDENCIES TO POM:
 * <dependency>
 * <groupId>org.ehcache</groupId>
 * <artifactId>ehcache</artifactId>
 * <version>3.8.1</version>
 * </dependency>
 * *
 * <dependency>
 * <groupId>org.hibernate</groupId>
 * <artifactId>hibernate-ehcache</artifactId>
 * <version>5.4.10.Final</version> // SAVE VERSION OF HIBERNATE DEPENDENCY
 * </dependency>
 * *
 * SET PROPERTIES ON hibernate.cfg.xml:
 *  <property name="hibernate.cache.use_second_level_cache">true</property>
 *  <property name="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.EhCacheRegionFactory</property>
 * *
 * SET CLASS AS CACHEABLE:
 * @Cacheable
 * @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
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

		Book b = (Book) session.get(Book.class, 1);
		System.out.println(b);

		session.getTransaction().commit();
		session.close();

		Session session1 = sf.openSession();
		session1 = sf.openSession();
		session1.getTransaction().begin();

		Book b1 = (Book) session1.get(Book.class, 1);
		System.out.println(b1);

		session1.getTransaction().commit();
		session1.close();

	}

}
