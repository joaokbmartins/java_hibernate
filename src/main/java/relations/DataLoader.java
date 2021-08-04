package relations;

import org.hibernate.Session;

import relations.manyToMany.Container;
import relations.manyToMany.Ship;
import relations.oneToMany.Account;
import relations.oneToMany.Bank;
import relations.oneToOne.Book;
import relations.oneToOne.Reader;

public class DataLoader {

	public static void loadOneToOne(Session session) {
		Book b1 = new Book();
		b1.setId(1);
		b1.setName("Dom Casmurro");
		b1.setAuthor("Assis");
		session.save(b1);
		created(b1);

		Book b2 = new Book();
		b2.setId(2);
		b2.setName("Dom Casmurro");
		b2.setAuthor("Assis");
		session.save(b2);
		created(b2);

		Reader r1 = new Reader();
		r1.setId(1);
		r1.setName("David");
		r1.setPhone(998989898);
		r1.setBook(b1);
		session.save(r1);
		created(r1);

		Reader r2 = new Reader();
		r2.setId(2);
		r2.setName("Poul");
		r2.setPhone(859595959);
		r2.setBook(b1);
		session.save(r2);
		created(r2);
	}

	public static void loadOneToMany(Session session) {
		Account a = new Account();
		a.setId(1);
		a.setNumber(12345);
		a.setOwner("David");

		Account a1 = new Account();
		a1.setId(2);
		a1.setNumber(54321);
		a1.setOwner("Marta");

		Bank b = new Bank();
		b.setId(1);
		b.setCity("Gotham");
		b.setName("Gotham City Bank");

		a.setBank(b);
		a1.setBank(b);

		session.save(b);
		session.save(a);
		session.save(a1);

		created(a);
		created(a1);
		created(b);
	}

	public static void loadManyToMany(Session session) {
		Container c1 = new Container();
		c1.setId(1);
		c1.setColor("Blue");

		Ship s1 = new Ship();
		s1.setId(1);
		s1.setName("Titanic");
		s1.setCapacity(2435);

//		s1.getContainers().add(c1);
		c1.getShips().add(s1);

		session.save(c1);
		session.save(s1);

		created(c1);
		created(s1);
	}

	public static void created(Object obj) {
		System.out.println(obj.getClass().getName() + " created: " + obj.toString());
	}

}
