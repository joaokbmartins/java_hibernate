package com.joao.java_hibernate.dataFetch;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.joao.java_hibernate.relations.DataLoader;
import com.joao.java_hibernate.relations.manyToMany.Container;
import com.joao.java_hibernate.relations.manyToMany.Ship;
import com.joao.java_hibernate.relations.oneToMany.Account;
import com.joao.java_hibernate.relations.oneToMany.Bank;
import com.joao.java_hibernate.relations.oneToOne.Book;
import com.joao.java_hibernate.relations.oneToOne.Reader;

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
