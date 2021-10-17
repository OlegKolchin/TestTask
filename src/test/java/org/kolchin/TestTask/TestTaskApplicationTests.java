package org.kolchin.TestTask;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.kolchin.TestTask.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@SpringBootTest
class TestTaskApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private Validator validator;

	@Test
	public void whenValidUser() {
		User user =  new User("admin2", "Petr", "12Aasdasda");
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		MatcherAssert.assertThat(violations, is(empty()));
	}

	@Test
	public void whenEmptyLogin() {
		User user =  new User("", "Petr", "12Aasdasda");
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		MatcherAssert.assertThat(violations.isEmpty(), is(false));
	}

	@Test
	public void whenEmptyName() {
		User user =  new User("admin", "", "12Aasdasda");
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		MatcherAssert.assertThat(violations.isEmpty(), is(false));
	}

	@Test
	public void whenEmptyPassword() {
		User user =  new User("admin", "Petr", "");
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		MatcherAssert.assertThat(violations.isEmpty(), is(false));
	}

	@Test
	public void whenPasswordWithoutDigits() {
		User user =  new User("admin", "Petr", "AasdasdaAsd");
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		MatcherAssert.assertThat(violations.isEmpty(), is(false));
	}

	@Test
	public void whenPasswordWithoutUpperCase() {
		User user =  new User("admin", "Petr", "asdasdaf12");
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		MatcherAssert.assertThat(violations.isEmpty(), is(false));
	}

	@Test
	public void whenLoginHasOnlyWhiteSpaces() {
		User user =  new User("   ", "Petr", "asdasdaf12");
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		MatcherAssert.assertThat(violations.isEmpty(), is(false));
	}

	@Test
	public void whenNameHasOnlyWhiteSpaces() {
		User user =  new User("admin", "   ", "asdasdaf12");
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		MatcherAssert.assertThat(violations.isEmpty(), is(false));
	}
}
