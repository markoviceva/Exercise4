package git.models;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the application database table.
 * 
 */
@Entity
@Table(name="application")
@NamedQuery(name="Application.findAll", query="SELECT a FROM Application a")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Application implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable=false)
	private int id;

	@Column(nullable=false, length=20)
	@NotBlank(message = "Name is mandatory")
	private String name;

	//bi-directional many-to-one association to Connection
	@OneToMany(cascade = CascadeType.ALL, mappedBy="application")
	@JsonIgnore
	private List<Connection> connections;

	public Application() {
	}

	public int getId() {
		return id;
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

	public List<Connection> getConnections() {
		return this.connections;
	}

	public void setConnections(List<Connection> connections) {
		this.connections = connections;
	}

	public Connection addConnection(Connection connection) {
		getConnections().add(connection);
		connection.setApplication(this);

		return connection;
	}

	public Connection removeConnection(Connection connection) {
		getConnections().remove(connection);
		connection.setApplication(null);

		return connection;
	}

}