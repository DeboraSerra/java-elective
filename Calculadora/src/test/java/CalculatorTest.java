import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test the calculator class")
public class CalculatorTest {

  Calculator cal = new Calculator();

  @Test
  @DisplayName("Test the add method")
  public void testAdd() {

    Assertions.assertEquals(5, cal.add(2, 3));
  }

  @Test
  @DisplayName("Test the subtract method")
  public void testSubtract() {
    Assertions.assertEquals(5, cal.subtract(10, 5));
  }

  @Test
  @DisplayName("Test the divide method")
  public void testDivide() {
    Assertions.assertEquals(5, cal.divide(10, 2));
  }

  @Test
  @DisplayName("The divide method should throw an exception")
  public void testDivideException() {
    Assertions.assertThrows(ArithmeticException.class, () -> {
      cal.divide(5, 0);
    });
  }

  @Test
  @DisplayName("Test the multiply method")
  public void testMultiply() {
    Assertions.assertEquals(10, cal.multiply(5, 2));
  }
}
