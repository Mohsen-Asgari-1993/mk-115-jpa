package ir.maktabsharif115.jpa.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class MyMathTest {

    @Mock
    MyService myService;

    MyMath myMath;

    @BeforeAll
    static void beforeAll() {
        System.out.println("beforeAll");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("afterAll");
    }

    @BeforeEach
    void beforeEach() {
        myMath = new MyMath(myService);
        System.out.println("beforeEach");
        lenient().when(myService.test()).thenReturn(5);
    }

    @AfterEach
    void afterEach() {
        System.out.println("afterEach");
    }

    @Test
    void add() {
        System.out.println("add()");
        assertEquals(15, myMath.add(5, 5));
        assertEquals(5, myMath.add(0, 0));
        assertEquals(9, myMath.add(3, 1));
    }

    @Test
    void divide() {
        System.out.println("divide()");
        assertEquals(10, myMath.divide(20, 2));
        assertEquals(0, myMath.divide(0, 2));
        assertThrows(ArithmeticException.class, () -> myMath.divide(10, 0));
    }

    @Test
    void multiply() {
        System.out.println("multiply()");
        assertEquals(10, myMath.multiply(5, 2));
        assertEquals(100, myMath.multiply(10, 10));
    }
}