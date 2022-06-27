package bll;

import bll.validators.*;
import dao.ClientDAO;
import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {
    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new ClientEmailValidator());
        validators.add(new ClientAgeValidator());
        clientDAO = new ClientDAO();
    }
    public Client findClientById(int id) {
        Client ct = clientDAO.findById(id);
        if (ct == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return ct;
    }
    public List<Client> findAll(){
        return clientDAO.findAll();
    }
    public Client deleteClientById(int id) {
        Client ct = clientDAO.deleteByID(id);
        if (ct == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return ct;
    }

    public Client insertClient(Client client) {

        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        Client ct = clientDAO.insert(client);
        if (ct == null) {
            throw new NoSuchElementException("The client with id =" + client.getId() + " could not be inserted!");
        }
        return ct;

    }

}
