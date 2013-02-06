package code;

import java.io.Serializable;

public abstract class Entiteta implements Serializable{
	int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
