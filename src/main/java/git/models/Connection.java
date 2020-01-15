package git.models;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the connection database table.
 * 
 */
@Entity
@Table(name="connection")
@NamedQuery(name="Connection.findAll", query="SELECT c FROM Connection c")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Connection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable=false)
	private int id;

	@Column(nullable=false, length=45)
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@Column(nullable=false, length=100)
	@NotBlank(message = "URL is mandatory")
	private String url;

	@Column(nullable=false, length=60)
	@NotBlank(message = "Token is mandatory")
	private String token;
	
	@Column(nullable=false, length=50)
	@NotBlank(message = "Project is mandatory")
	private String project;
	
	@Column(nullable=false, length=60)
	@NotBlank(message = "Owner is mandatory")
	private String owner;
	
	@Column(name="branch_name", nullable=false, length=45)
	@NotBlank(message = "Branch name is mandatory")
	private String branchName;

	//bi-directional many-to-one association to Application
	@ManyToOne
	@JoinColumn(name="app_id", referencedColumnName="id", nullable=false)
	private Application application;

	public Connection() {
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getProject() {
		return this.project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Application getApplication() {
		return this.application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}


}