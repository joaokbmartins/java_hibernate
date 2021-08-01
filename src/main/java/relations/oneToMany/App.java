package relations.oneToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import relations.DataLoader;

public class App {

	public static void main(String[] args) {

		Bank gCBank = null;

		Configuration config = new Configuration().addAnnotatedClass(Bank.class).addAnnotatedClass(Account.class)
		    .configure();
		SessionFactory sf = config.buildSessionFactory();
		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();
		DataLoader.loadOneToMany(session);
		gCBank = (Bank) session.get(Bank.class, 1);
		tx.commit();

		System.out.println(gCBank);

	}

}
