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

// Demo5 Created by Zachary Liu to test Ingredients class

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
public class IngredientTests {
	private final static Logger log = LoggerFactory.getLogger(Demo5.class);

	@Autowired
	private IngredientsRepository ingredientsRepository;

	private final Ingredients ingredients = new Ingredients("Apple", 10);

	@BeforeEach
	public void setup() {
		ingredientsRepository.saveAndFlush(ingredients);
	}

	@Test
	@Order(1)
	public void testSaveIngredients() {
		Ingredients i2 = ingredientsRepository.findByName("Apple");

		log.info(i2.toString());

		assertNotNull(i2);
		assertEquals(i2.getName(), ingredients.getName());
		assertEquals(i2.getAmount(), ingredients.getAmount());
	}

	@Test
	@Order(2)
	public void testGetIngredients() {
		Ingredients i2 = ingredientsRepository.findByName("");
		assertNotNull(ingredients);
		assertEquals(i2.getName(), ingredients.getName());
		assertEquals(i2.getAmount(), ingredients.getAmount());
	}

	@Test
	@Order(3)
	public void testDeleteIngredients() {
		ingredientsRepository.delete(ingredients);
		ingredientsRepository.flush();
	}

	@Test
	@Order(4)
	public void testFindAllIngredients() {
		assertNotNull(ingredientsRepository.findAll());
	}
}
