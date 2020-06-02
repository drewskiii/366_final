package csc366.jpademo;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class CustomerTests {
    private final static Logger log = LoggerFactory.getLogger(CustomerTests.class);

    @Autowired
    private CustomerRepository customerRepository;

    private final Customer customer1 = new Customer("Andrew", "Lor", "Salem", "OR");
    private final Customer customer2 = new Customer("Billy", "Lee", "Salem", "MA");
    private final Customer customer3 = new Customer("Casey", "Liu", "San Ramon", "CA");
    private final Customer customer4 = new Customer("Kim", "Rend", "Alameda", "CA");
    private final Customer customer5 = new Customer("Lily", "High", "Oakland", "CA");

        

    // Date date = Calendar.getInstance().getTime();
    Date date = new Date();
    private final Transaction t1 = new Transaction(11.31, date);
    private final Transaction t2 = new Transaction(0.33, date);
    private final Transaction t3 = new Transaction(99.99, date);
    private final Transaction t4 = new Transaction(9.10, date);
    private final Transaction t5 = new Transaction(4.55, date);
    private final Transaction t6 = new Transaction(1.00, date);
    private final Transaction t7 = new Transaction(200.00, date);
    private final Transaction t8 = new Transaction(55.50, date);
    private final Transaction t9 = new Transaction(12.20, date);
    private final Transaction t10 = new Transaction(20.20, date);


    @BeforeEach
    private void setup() {
        
        customerRepository.saveAndFlush(customer1);
        customerRepository.saveAndFlush(customer2);
        customerRepository.saveAndFlush(customer3);
        customerRepository.saveAndFlush(customer4);
        customerRepository.saveAndFlush(customer5);
        customer1.addTransaction(t1);
        customer2.addTransaction(t2);
        customer3.addTransaction(t3);
        customer3.addTransaction(t4);
        customer3.addTransaction(t7);
        customer4.addTransaction(t10);
        customer5.addTransaction(t9);
        customerRepository.saveAndFlush(customer1);
        customerRepository.saveAndFlush(customer2);
        customerRepository.saveAndFlush(customer3);
        customerRepository.saveAndFlush(customer4);
        customerRepository.saveAndFlush(customer5);

    }

    @Test
    @Order(1)
    public void testCustomerWithMostTransactions2020() {
        Customer c = customerRepository.findMostTransactions();
        log.info(c.toString());
        assertEquals(c.getLastName(), customer3.getLastName());
    }
    
    @Test
    @Order(2)
    public void testCustomerWhoSpentTheMost() {
        // double c = customerRepository.findMostSpent();
        Customer c = customerRepository.findMostSpent();
        log.info(c.toString());
        assertEquals(c.getLastName(), customer3.getLastName());
    }

    @Test
    @Order(3)
    public void testStateWithMostCustomers() {
        List<Customer> states = customerRepository.findMostState();
        System.out.println(states);
        // log.info(state);
        // assertEquals(state, "CA");
    }
    // @Test
    // @Order(3)
    // public void testCustomerWhoSpentTheMostCoffee() {

    // }
    

    


}