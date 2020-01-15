package git.dto;

public class PullRequest {
	
	private String title;
	private String userWhoOpenedPull;
	private String user;
	
	public PullRequest() {
		
	}
	
	public PullRequest(String userWhoOpenedPull, String user, String title) {
		
		this.userWhoOpenedPull=userWhoOpenedPull;
		this.user=user;	
		this.title=title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserWhoOpenedPull() {
		return userWhoOpenedPull;
	}

	public void setUserWhoOpenedPull(String userWhoOpenedPull) {
		this.userWhoOpenedPull = userWhoOpenedPull;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	

}
