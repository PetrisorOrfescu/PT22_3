package dao;

import java.util.List;

import model.Client;

public class ClientDAO extends AbstractDAO<Client> {
    public Client findById(int id) {
        return super.findById(id);
    }

    public List<Client> findAll() {
        return super.findAll();
    }

    public Client deleteByID(int id) {
        return super.deleteByID(id);
    }

    public Client insert(Client client) {
        return super.insert(client);
    }
}
