package com.example.ioc;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;

class DummyTest {
	@Nested
	@DisplayName("Tratamiento extricto de nulos")
	class Nulos {
		Dummy dummy;

		@BeforeAll
		static void setUpBeforeClass() throws Exception {
		}

		@AfterAll
		static void tearDownAfterClass() throws Exception {
		}

		@BeforeEach
		void setUp() throws Exception {
			dummy = new Dummy();
		}

		@AfterEach
		void tearDown() throws Exception {
		}

		@Test
		void testHasDesontroladoTrue() {
			dummy.setDescontrolado("valor");
			assertNotNull(dummy.getDescontrolado());
			assertTrue(dummy.hasDescontrolado());
		}

		@Test
		void testHasDesontroladoFalse() {
			dummy.setDescontrolado(null);
			assertNull(dummy.getDescontrolado());
			assertFalse(dummy.hasDescontrolado());
		}

		@ParameterizedTest
		@ValueSource(strings = { "hola", "adios" })
		@NullAndEmptySource
		void testSetDesontrolado(String input) {
			assertDoesNotThrow(() -> dummy.setDescontrolado(input));
			assertEquals(input.toUpperCase(), dummy.getDescontrolado());
		}

		@ParameterizedTest
		@CsvSource({ "hola, HOLA", "adios, ADIOS", ",", "'',''" })
		void testGetDesontroladoKO(String input, String expected) {
			dummy.setDescontrolado(input);
			assertDoesNotThrow(() -> assertEquals(expected, dummy.getDescontrolado().toUpperCase()));
		}

		@ParameterizedTest
		@CsvSource({ "hola, HOLA", "adios, ADIOS", ",", "'',''" })
		void testGetDesontroladoOK(String input, String expected) {
			dummy.setDescontrolado(input);
			assertDoesNotThrow(() -> {
				if (dummy.hasDescontrolado())
					assertEquals(expected, dummy.getDescontrolado().toUpperCase());
			});
		}

		@Test
		void testSetControladoOK() {
			dummy.setControlado("valor");
			assertTrue(dummy.getControlado().isPresent());
			assertEquals("valor", dummy.getControlado().orElseThrow());
		}

		@Test
		void testSetControladoKO() {
			var ex = assertThrows(IllegalArgumentException.class, () -> dummy.setControlado(null));
			assertEquals("Valor no valido", ex.getMessage());
			assertNotNull(dummy.getControlado());
			assertTrue(dummy.getControlado().isEmpty());
		}

		@Test
		void testClearControlado() {
			assertDoesNotThrow(() -> dummy.setControlado("valor"));
			assertTrue(dummy.getControlado().isPresent());
			assertDoesNotThrow(() -> dummy.clearControlado());
			assertNotNull(dummy.getControlado());
			assertTrue(dummy.getControlado().isEmpty());
		}

		@ParameterizedTest
		@CsvSource({ "hola, HOLA", "adios, ADIOS", "'',''" })
		void testGetControladoOk(String input, String expected) {
			dummy.setDescontrolado(input);
			assertDoesNotThrow(() -> assertEquals(expected, dummy.getDescontrolado().toUpperCase()));
		}

		@Test
		void testGetControladoKO() {
			assertNotNull(dummy.getControlado());
			assertTrue(dummy.getControlado().isEmpty());
			assertThrows(NoSuchElementException.class, () -> dummy.getControlado().get());
		}

		@Test
		@Timeout(value = 30, unit = TimeUnit.SECONDS)
		void testCalcularResultado() throws InterruptedException, ExecutionException {
			var futuro = dummy.calcularResultado(1, 2, 3);
			String resultado = futuro.get();
			assertTrue(futuro.isDone());
			assertEquals("La media de [1, 2, 3] es 2,00", resultado);
		}

	}

//	@Nested
//	@EnableAsync
//	@DisplayName("Metodos asíncronos")
//	@SpringBootTest
//	class Asincronas {
//		@TestConfiguration
//		@Import(Dummy.class)
//		public class AsyncTestConfig {
//			// Sobrescribe el TaskExecutor configurado en la clase principal
//			@Bean
//			@Primary
//			public TaskExecutor taskExecutor() {
//				// En un entorno de prueba, es mejor usar un executor simple
//				// para forzar la ejecución síncrona y hacer que el test sea predecible.
//				return new SimpleAsyncTaskExecutor();
//			}
//		}
//
//		@Autowired
//		Dummy dummy;
//
//		@Test
//		@Timeout(value = 30, unit = TimeUnit.SECONDS)
//		void testCalcularResultado() throws InterruptedException, ExecutionException {
//			var futuro = dummy.calcularResultado(1, 2, 3);
//			String resultado = futuro.get();
//			assertTrue(futuro.isDone());
//			assertEquals("La media de [1, 2, 3] es 2,00", resultado);
//		}
//
//	}
}
