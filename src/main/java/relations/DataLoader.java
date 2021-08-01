package relations;

import org.hibernate.Session;

import relations.oneToOne.Book;
import relations.oneToOne.Reader;

public class DataLoader {

	public static void load(Session session) {
		Book b1 = new Book();
		b1.setId(1);
		b1.setName("Dom Casmurro");
		b1.setAuthor("Assis");
		session.save(b1);
		created(b1);

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

	public static void created(Object obj) {
		System.out.println(obj.getClass().getName() + " created: " + obj.toString());
	}

}
