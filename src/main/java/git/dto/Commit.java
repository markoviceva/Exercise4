package git.dto;

public class Commit {
	
	private String author;
	private String sha;
	private String commit_message;
	
	public Commit() {
		
	}
	
	public Commit(String author, String sha, String commit_message) {
		this.author=author;
		this.sha=sha;
		this.commit_message=commit_message;
	}

	

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSha() {
		return sha;
	}

	public void setSha(String sha) {
		this.sha = sha;
	}

	public String getCommit_message() {
		return commit_message;
	}

	public void setCommit_message(String commit_message) {
		this.commit_message = commit_message;
	}

}
