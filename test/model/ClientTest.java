package model;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ClientTest {
Client c1;

void setUpScenario1() {
	c1 = new Client("12a", "normal", "jhonny", "calle 1", "1234");
}

	@Test
	void creationTest() {
		setUpScenario1();
		assertNotNull(c1, "papa");
	}

}
