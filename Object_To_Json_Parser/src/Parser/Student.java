
package Parser;

public class Student {
	private int id;
	private String name;
	private int roll_no;
	
	public Student(int id,String name,int roll_no)
	{
		this.id = id;
		this.name = name;
		this.roll_no = roll_no;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRoll_no() {
		return roll_no;
	}
	public void setRoll_no(int roll_no) {
		this.roll_no = roll_no;
	}
	
	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", roll_no=" + roll_no + "]";
	}
	
}