package com.sebok.jpaspring;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class ClientDao {

    @PersistenceContext
    private EntityManager entityManager;

    private LogEntryDao logEntryDao;

    public ClientDao(LogEntryDao logEntryDao) {
        this.logEntryDao = logEntryDao;
    }

    @Transactional
    public void saveClient(Client client) throws IllegalArgumentException {
        logEntryDao.save(new LogEntry("Create client: " + client.getName()));

        entityManager.persist(client);

        if (client.getName().length() < 3) {
            throw new IllegalArgumentException("Name must be longer then 3 characters");
        }
    }

    public Client findClientById(long id) {
        return entityManager.find(Client.class, id);
    }
}
