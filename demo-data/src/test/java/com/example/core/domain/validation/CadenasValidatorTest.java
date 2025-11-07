package com.example.core.domain.validation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CadenasValidatorTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testIsNotOnlyBlankOK() {
		// Preparar
		
		// Actuacion
		boolean actual = CadenasValidator.isNotOnlyBlank("xxx");
		// Asercion
		assertTrue(actual);
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "   " })
	@EmptySource
	void testIsNotOnlyBlankKO(String caso) {
		// Preparar
		
		// Actuacion
		boolean actual = CadenasValidator.isNotOnlyBlank(caso);
		// Asercion
		assertFalse(actual);
	}
	
	@Test
	void testIsNotOnlyBlankNull() {
		var ex = assertThrowsExactly(IllegalArgumentException.class, () -> CadenasValidator.isNotOnlyBlank(null));
		assertEquals("No puede ser nulo", ex.getMessage());
	}
	
	// @Test
	void testIsNotOnlyBlank_Mala_Prueba() {
		assertFalse(CadenasValidator.isNotOnlyBlank(""));
		assertFalse(CadenasValidator.isNotOnlyBlank(null));
		assertFalse(CadenasValidator.isNotOnlyBlank("  "));
	}

	@Test
	void testIsOnlyBlankOK() {
		assertTrue(CadenasValidator.isOnlyBlank("   "));
	}

	@Test
	void testIsOnlyBlankKO() {
		assertFalse(CadenasValidator.isOnlyBlank("KKK"));
	}

}
