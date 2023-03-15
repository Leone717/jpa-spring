package com.sebok.jpaspring;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(statements = {"delete from clients", "delete from log_entry"})
public class ClientDaoTest {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private LogEntryDao logEntryDao;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testSaveThenFind() {
        Client client = new Client("Jack Doe");
        clientDao.saveClient(client);

        long id = client.getId();

        assertEquals("Jack Doe", clientDao.findClientById(id).getName());
    }

    @Test
    public void testSaveWithEmptyName() {
        expectedException.expect(IllegalArgumentException.class);
        Client client = new Client("");
        clientDao.saveClient(client);
    }

    @Test
    public void testSaveLog() {
        LogEntry logEntry = new LogEntry("Begin");
        logEntryDao.save(logEntry);
        assertEquals(1, logEntryDao.list().size());
    }
}
