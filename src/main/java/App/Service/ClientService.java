package App.Service;

import App.Entities.Client;
import App.Repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Optional<Client> findById(Long id) {
        return repository.findById(id);
    }

    public Client save(Client client) {
        return repository.save(client);
    }

    public Client update(Long id, Client client) {
        client.setId(id);
        return repository.save(client);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}