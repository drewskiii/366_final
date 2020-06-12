package csc366.jpademo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// Demo4 Created by Zachary Liu to test Regulator class

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
public class RegulatorTests {
	private final static Logger log = LoggerFactory.getLogger(Demo4.class);

	@Autowired
	private RegulatorRepository regulatorRepository;

	private final Regulator regulator = new Regulator("mark", "zuckerberg", "mark@facebook.com");

	@BeforeEach
	private void setup() {
		regulatorRepository.saveAndFlush(regulator);
	}

	@Test
	@Order(1)
	public void testSaveRegulator() {
		Regulator r2 = regulatorRepository.findByFirstName("mark");

		log.info(r2.toString());

		assertNotNull(r2);
		assertEquals(r2.getFirstName(), regulator.getFirstName());
		assertEquals(r2.getLastName(), regulator.getLastName());
	}

	@Test
	@Order(2)
	public void testGetRegulator() {
		Regulator r2 = regulatorRepository.findByFirstName("mark");
		assertNotNull(regulator);
		assertEquals(r2.getFirstName(), regulator.getFirstName());
		assertEquals(r2.getLastName(), regulator.getLastName());
	}

	@Test
	@Order(3)
	public void testDeleteRegulator() {
		regulatorRepository.delete(regulator);
		regulatorRepository.flush();
	}

	@Test
	@Order(4)
	public void testFindAllRegulators() {
		assertNotNull(regulatorRepository.findAll());
	}

}
