package csc366.jpademo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.TestPropertySource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Demo0: Add, list, and remove Person instances

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
	"spring.main.banner-mode=off",
	"spring.jpa.hibernate.ddl-auto=update",
	"logging.level.root=ERROR",
	"logging.level.csc366=DEBUG",

	"logging.level.org.hibernate.SQL=DEBUG",
	"logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE", // display prepared statement parameters
	"spring.jpa.properties.hibernate.format_sql=true",
	"spring.jpa.show-sql=false",   // prevent duplicate logging
	"spring.jpa.properties.hibernate.show_sql=false",	
	
	"logging.pattern.console= %d{yyyy-MM-dd HH:mm:ss} - %msg%n"
})
@TestMethodOrder(OrderAnnotation.class)
public class Demo0 {

    private final static Logger log = LoggerFactory.getLogger(Demo0.class);
    
    @Autowired
    private PersonRepository personRepository;

    private final Person person = new Person("test", "test", "test@calpoly.edu");  // "reference" person
    
    @BeforeEach
    private void setup() {
	personRepository.saveAndFlush(person);
    }
    
    /* For reference, needs to be removed
    @Test
    @Order(1)
    public void testSavePerson() {
	Person person2 = personRepository.findByFirstName("test");

	log.info(person2.toString());
	
	assertNotNull(person);
	assertEquals(person2.getFirstName(), person.getFirstName());
	assertEquals(person2.getLastName(), person.getLastName());
    }
    
    @Test
    @Order(2)
    public void testGetPerson() {
	Person person2 = personRepository.findByFirstName("test");
	assertNotNull(person);
	assertEquals(person2.getFirstName(), person.getFirstName());
	assertEquals(person2.getLastName(), person.getLastName());
    }

    @Test
    @Order(3)
    public void testDeletePerson() {
	personRepository.delete(person);
	personRepository.flush();
    }
    
    @Test
    @Order(4)
    public void testFindAllPersons() {
	assertNotNull(personRepository.findAll());
    }
    
    @Test
    @Order(5)
    public void testDeletByPersonId() {
	Person e = personRepository.findByFirstName("test");
	personRepository.deleteById(e.getId());
	personRepository.flush();
    }

    @Test
    @Order(6)
    public void testJpqlFinder() {
	Person e = personRepository.findByNameJpql("test");
	assertEquals(e.getFirstName(), person.getFirstName());
    }

    @Test
    @Order(7)
    public void testSqlFinder() {
	Person p = personRepository.findByNameSql("test");
	assertEquals(p.getFirstName(), person.getFirstName());
    }
    */
}
