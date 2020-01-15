package git.controllers;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import git.dto.Commit;
import git.dto.PullRequest;
import git.httpConnection.HttpConnectionCommit;
import git.httpConnection.HttpConnectionPullRequest;
import git.models.Application;
import git.models.Connection;
import git.repositories.AppRepository;
import git.repositories.ConnectionRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class GitRestController {
	
	@Autowired
	private AppRepository appRepository;
	
	@Autowired
	private ConnectionRepository connectionRepository;
	
	@RequestMapping(value = "/application", method = RequestMethod.GET)
	public Collection<Application> getAllApplications(){
		return appRepository.findAll();
	}
	
	@RequestMapping(value = "/application/{id}", method = RequestMethod.GET)
	public ResponseEntity<Application> getApplication (@PathVariable ("id") Integer id) {
		Application app=appRepository.getOne(id);
		return new ResponseEntity<Application>(app,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/application/{name}", method = RequestMethod.GET)
	public Collection<Application> getApplicationsByName(@PathVariable ("name") String name){
		return appRepository.findByNameContainingIgnoreCase(name);
	}
	@CrossOrigin
	@RequestMapping(value = "/connection", method = RequestMethod.GET)
	public Collection<Connection> getAllConnections(){
		return connectionRepository.findAll();
	}
	
	@RequestMapping(value = "/connection/{id}", method = RequestMethod.GET)
	public ResponseEntity<Connection> getConnection(@PathVariable ("id") Integer id) {
		Connection connection=connectionRepository.getOne(id);
		return new ResponseEntity<Connection>(connection, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/connection/{name}", method = RequestMethod.GET)
	public Collection<Connection> getConnectionsByName(@PathVariable ("name") String name){
		return connectionRepository.findByNameContainingIgnoreCase(name);
	}
	
	@RequestMapping(value = "/connection/project/{project}", method = RequestMethod.GET)
	public Collection<Connection> getConnectionsByProject(@PathVariable ("project") String project){
		return connectionRepository.findByProject(project);
	}
	
	@RequestMapping(value = "/connection/owner/{owner}", method = RequestMethod.GET)
	public Collection<Connection> getConnectionsByOwner(@PathVariable ("owner") String owner){
		return connectionRepository.findByOwner(owner);
	}
	@CrossOrigin
	@RequestMapping(value = "/appconnections/{id}", method = RequestMethod.GET)
	public Collection<Connection> getConnectionsForApplication(@PathVariable("id") int id){
		Application a = appRepository.getOne(id);
		return connectionRepository.findByApplication(a);
	}
	
	//GET request for pulls
	@CrossOrigin
	@RequestMapping(value = "/connection/pulls/{id}", method = RequestMethod.GET)
	public List<PullRequest> getPullsForConnection(@PathVariable ("id") Integer id) throws Exception {
		Connection connection=connectionRepository.getOne(id);
		return  HttpConnectionPullRequest.getPullRequests(HttpConnectionPullRequest.sendGetRequest(connection));
	}
	
	//GET request for commits
	@CrossOrigin
	@RequestMapping(value = "/connection/commits/{id}", method = RequestMethod.GET)
	public List<Commit> getCommitsForConnection(@PathVariable ("id") Integer id) throws Exception {
		Connection connection=connectionRepository.getOne(id);
		return  HttpConnectionCommit.getCommits(HttpConnectionCommit.sendGetCommit(connection));
	}
	
	
	//POST, PUT AND DELETE requests for Application and Connection
	@CrossOrigin
	@RequestMapping(value = "/application", method = RequestMethod.POST)
	public ResponseEntity<Application> insertApplication(@Valid @RequestBody Application application) {
		if (appRepository.existsById(application.getId()))
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		appRepository.save(application);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@CrossOrigin
	@RequestMapping(value = "/application", method = RequestMethod.PUT)
	public ResponseEntity<Application> updateApplication(@RequestBody Application application){
		if(!appRepository.existsById(application.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		appRepository.save(application);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@CrossOrigin
	@RequestMapping(value = "/application/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Application> deleteApplication(@PathVariable("id") Integer id){
		if(appRepository.existsById(id)){
			appRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@CrossOrigin
	@RequestMapping(value = "/connection", method = RequestMethod.POST)
	public ResponseEntity<Connection> insertConnection(@Valid @RequestBody Connection connection) {
		if (connectionRepository.existsById(connection.getId()))
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		connectionRepository.save(connection);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@CrossOrigin
	@RequestMapping(value = "/connection", method = RequestMethod.PUT)
	public ResponseEntity<Connection> updateConnection(@RequestBody Connection connection){
		if(!connectionRepository.existsById(connection.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		connectionRepository.save(connection);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@CrossOrigin
	@RequestMapping(value = "/connection/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Connection> deleteConnection(@PathVariable("id") Integer id){
		if(!connectionRepository.existsById(id))
			return new ResponseEntity<Connection>(HttpStatus.NO_CONTENT);
		connectionRepository.deleteById(id);
		return new ResponseEntity<Connection>(HttpStatus.OK);
	}	
	

}
