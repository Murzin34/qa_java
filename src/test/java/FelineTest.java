import com.example.Feline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
class FelineTest {

    private Feline feline;
    private Feline felineSpy;

    @BeforeEach
    void setup() {
        feline = new Feline();
        felineSpy = spy(new Feline());
    }

    @Nested
    class PositiveTests {

        @Test
        void getFamily_ShouldReturnFelineFamily() {
            assertEquals(TestData.FAMILY_FELINE, feline.getFamily());
        }

        @Test
        void getKittens_WithoutArguments_ShouldReturnOne() {
            assertEquals(1, feline.getKittens());
        }

        @ParameterizedTest
        @CsvSource({"0, 0", "1, 1", "5, 5"})
        void getKittens_WithArguments_ShouldReturnCorrectCount(int input, int expected) {
            assertEquals(expected, feline.getKittens(input));
        }

        @Test
        void eatMeat_ShouldReturnFoodForPredator() throws Exception {
            doReturn(TestData.PREDATOR_FOOD).when(felineSpy).getFood(TestData.PREDATOR);
            assertEquals(TestData.PREDATOR_FOOD, felineSpy.eatMeat());
        }
    }

    @Nested
    class NegativeTests {

        @Test
        void eatMeat_ShouldThrowException_WhenGetFoodFails() throws Exception {
            doThrow(new Exception(TestData.UNKNOWN_ANIMAL_KIND_ERROR))
                    .when(felineSpy).getFood(TestData.PREDATOR);
            Exception exception = assertThrows(Exception.class, felineSpy::eatMeat);
            assertEquals(TestData.UNKNOWN_ANIMAL_KIND_ERROR, exception.getMessage());
        }
    }
}
