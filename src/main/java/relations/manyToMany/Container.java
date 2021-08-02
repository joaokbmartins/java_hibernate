package relations.manyToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "containers")
public class Container {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "color")
	private String color;

	@ManyToMany
	private List<Ship> ships = new ArrayList<Ship>();

	public List<Ship> getShips() {
		return ships;
	}

	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Container [id=" + id + ", color=" + color + ", ships=" + ships + "]";
	}

}
