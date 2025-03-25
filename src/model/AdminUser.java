package model;

public class AdminUser implements IUser {
	
	private final String username;
	private final String password;
	
	public AdminUser() {
		this.username = "";
		this.password = "";
	}
	
	public AdminUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUserType() {
		return "Admin";
	}

}
