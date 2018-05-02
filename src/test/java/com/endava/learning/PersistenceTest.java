package com.endava.learning;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.FileSystemResourceAccessor;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;

import static org.assertj.core.api.Assertions.assertThat;

public class PersistenceTest {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeClass
    public static void init() throws Exception {
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/client_schema?serverTimezone=UTC", "root", "root");

        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
        Liquibase liquibase = new Liquibase("src/main/resources/db/changelog_master.xml", new FileSystemResourceAccessor(), database);

        liquibase.update(new Contexts());

        entityManagerFactory = Persistence.createEntityManagerFactory("persistence");

    }

    @Before
    public void setUp() throws Exception {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    @Ignore
    @Test
    public void tryToAddNewClientToDB() throws Exception {
        Client client = new Client("Dmitrii", "Nedealcov");

        entityManager.persist(client);
        entityManager.flush();

        entityManager.getTransaction().rollback();
    }

    @Ignore
    @Test
    public void tryToAddNewAccountToDB() throws Exception {
        Client client = new Client("Dmitrii", "Nedealcov");
        Account account = new Account("hello", "world", "today", client);



        entityManager.persist(client);
        entityManager.persist(account);
        entityManager.getTransaction().commit();
    }

    @Ignore
    @Test
    public void persistANewClient() throws Exception {


    }




    @After
    public void cleanUp() throws Exception {
//        entityManager.getTransaction().rollback();
        entityManager.close();
    }

    @AfterClass
    public static void destroy() throws Exception {
        entityManagerFactory.close();
    }
}