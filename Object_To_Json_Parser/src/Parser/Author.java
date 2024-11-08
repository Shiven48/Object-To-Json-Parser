package Parser;

import java.time.LocalDate;

public class Author {
	private String name;
	private String nationality;
	private LocalDate birthdate;
	private boolean intern;
	
	public Author () {}
	
	public Author(String name, String nationality, LocalDate birthdate, boolean intern) {
		super();
		this.name = name;
		this.nationality = nationality;
		this.birthdate = birthdate;
		this.intern = intern;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	public boolean isIntern() {
		return intern;
	}

	public void setIntern(boolean intern) {
		this.intern = intern;
	}

	@Override
	public String toString() {
		return "{name=" + name + ", nationality=" + nationality + ", birthdate=" + birthdate + ", intern="
				+ intern + "}";
	}

	
}

