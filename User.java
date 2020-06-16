import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected String username;
	protected String password;
	protected String firstName;
	protected String lastName;
	
	public User(String username, String password, String firstName, String lastName) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}	
}