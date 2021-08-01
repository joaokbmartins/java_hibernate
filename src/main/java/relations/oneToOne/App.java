package relations.oneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import relations.DataLoader;

public class App {

	public static void main(String[] args) {

		Reader david = null;

		Configuration config = new Configuration().addAnnotatedClass(Book.class).addAnnotatedClass(Reader.class)
		    .configure();

		SessionFactory sf = config.buildSessionFactory();
		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();

		DataLoader.load(session);
		
		david = session.get(Reader.class, 1);

		tx.commit();

		System.out.println(david);

	}

}
