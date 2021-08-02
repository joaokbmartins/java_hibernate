package relations.manyToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import relations.DataLoader;

public class AppManyToMany {

	public static void main(String[] args) {

		Configuration config = new Configuration().addAnnotatedClass(Ship.class).addAnnotatedClass(Container.class)
		    .configure();
		SessionFactory sf = config.buildSessionFactory();
		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();
		DataLoader.loadManyToMany(session);
		tx.commit();

	}

}
