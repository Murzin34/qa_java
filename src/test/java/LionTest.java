import com.example.Feline;
import com.example.Lion;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LionTest {

    private static final String INVALID_SEX_ERROR =
            "Используйте допустимые значения пола животного - самей или самка";
    private static final List<String> PREDATOR_FOOD = List.of("Животные", "Птицы", "Рыба");

    @Nested
    class PositiveTests {

        @Test
        void shouldSetManeTrue_WhenSexIsMale() throws Exception {
            Lion lion = new Lion("Самец");
            assertTrue(lion.doesHaveMane());
        }

        @Test
        void shouldSetManeFalse_WhenSexIsFemale() throws Exception {
            Lion lion = new Lion("Самка");
            assertFalse(lion.doesHaveMane());
        }

        @ParameterizedTest
        @CsvSource({
                "Самец, true",
                "Самка, false"
        })
        void shouldReturnCorrectManeStatus(String sex, boolean expectedHasMane) throws Exception {
            Lion lion = new Lion(sex);
            assertEquals(expectedHasMane, lion.doesHaveMane());
        }

        @Test
        void getKittens_ShouldCallFelineAndReturnValue() throws Exception {
            try (MockedConstruction<Feline> mocked = mockConstruction(Feline.class,
                    (mock, context) -> when(mock.getKittens()).thenReturn(3))) {

                Lion lion = new Lion("Самец");

                assertEquals(3, lion.getKittens());
                verify(mocked.constructed().get(0)).getKittens();
            }
        }

        @Test
        void getFood_ShouldCallFelineAndReturnPredatorFood() throws Exception {
            try (MockedConstruction<Feline> mocked = mockConstruction(Feline.class,
                    (mock, context) -> when(mock.getFood("Хищник")).thenReturn(PREDATOR_FOOD))) {

                Lion lion = new Lion("Самка");

                assertEquals(PREDATOR_FOOD, lion.getFood());
                verify(mocked.constructed().get(0)).getFood("Хищник");
            }
        }
    }

    @Nested
    class NegativeTests {

        @ParameterizedTest
        @ValueSource(strings = {"", "Неизвестно", "Male", "Female"})
        void shouldThrowException_WhenSexIsInvalid(String invalidSex) {
            Exception exception = assertThrows(Exception.class, () -> new Lion(invalidSex));
            assertEquals(INVALID_SEX_ERROR, exception.getMessage());
        }

        @Test
        void getFood_ShouldPropagateExceptionFromFeline() throws Exception {
            try (MockedConstruction<Feline> ignored = mockConstruction(Feline.class,
                    (mock, context) -> when(mock.getFood("Хищник")).thenThrow(new Exception("Ошибка получения еды")))) {

                Lion lion = new Lion("Самец");
                Exception exception = assertThrows(Exception.class, lion::getFood);
                assertEquals("Ошибка получения еды", exception.getMessage());
            }
        }
    }
}
