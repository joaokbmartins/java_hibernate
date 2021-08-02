package dataFetch;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import relations.DataLoader;
import relations.manyToMany.Container;
import relations.manyToMany.Ship;
import relations.oneToMany.Account;
import relations.oneToMany.Bank;
import relations.oneToOne.Book;
import relations.oneToOne.Reader;

public class AppDataFetch {

	public static void main(String[] args) {

		Configuration config = new Configuration().addAnnotatedClass(Ship.class).addAnnotatedClass(Container.class)
		    .addAnnotatedClass(Reader.class).addAnnotatedClass(Book.class).addAnnotatedClass(Bank.class)
		    .addAnnotatedClass(Account.class).configure();
		SessionFactory sf = config.buildSessionFactory();
		Session session = sf.openSession();

		session.beginTransaction();

		DataLoader.loadOneToOne(session);
		DataLoader.loadOneToMany(session);
		DataLoader.loadManyToMany(session);
		session.getTransaction().commit();
		session.close();

		System.out.println("==========================");

		session = sf.openSession();
		session.beginTransaction();

//		@OneToMany(mappedBy = "bank", fetch = FetchType.EAGER)
		Bank b = session.get(Bank.class, 1);
		System.out.println(b.getCity());

//		for (Account a : b.getAccounts()) {
//			System.out.println(a);
//		}

		session.getTransaction().commit();

	}

}
