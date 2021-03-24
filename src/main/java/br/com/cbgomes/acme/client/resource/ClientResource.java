/**
 * 
 */
package br.com.cbgomes.acme.client.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cbgomes.acme.client.domain.Client;
import br.com.cbgomes.acme.client.repository.ClientRepository;
import br.com.cbgomes.acme.exceptions.ClientNotFoundException;

/**
 * @author cbgomes
 *
 */
@RestController
@RequestMapping("/api/clients")
public class ClientResource {
	
	@Autowired
	private ClientRepository repository;
	
	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable("id") Long id) {
		this.repository.deleteById(id);
	}
	
	@PutMapping()
	public void updateClient(@RequestBody Client client,
		@RequestParam("id")Long id) throws Exception {
		this.repository.save(this.repository.findById(id).orElseThrow(() -> new ClientNotFoundException(HttpStatus.NOT_FOUND, "CLIENT NOT FOUND")));
	}
	
	@GetMapping("/id")
	public Client getClientById(@PathVariable("id") Long id) {
		return repository.findById(id).get();
		
	}
	
	@GetMapping
	public List<Client> getAllClients(){
		List<Client> clients = this.repository.findAll();
		return clients;
	}
	
	@PostMapping
	public void createClient(@RequestBody Client client) {
		this.repository.save(client);
	}
}











