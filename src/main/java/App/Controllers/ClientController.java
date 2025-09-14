package App.Controllers;

import App.Entities.Client;
import App.Service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("NullableProblems")
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client client) {
        Client savedClient = service.save(client);
        return ResponseEntity.status(201).body(savedClient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable(name="id") Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable(name = "id") Long id, @RequestBody Client client) {
        return service.update(id, client) != null
                ? ResponseEntity.ok(client)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name="id") Long id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
