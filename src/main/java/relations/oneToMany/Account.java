package relations.oneToMany;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {

	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "number", unique = true, nullable = false)
	private int number;
	@Column(name = "owner")
	private String owner;

	@ManyToOne(targetEntity = Bank.class, optional = false)
	private Bank bank;

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", number=" + number + ", owner=" + owner + "]";
	}

}
