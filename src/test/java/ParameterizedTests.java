import com.example.Animal;
import com.example.Feline;
import com.example.Lion;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ParameterizedTests {
    @InjectMocks
    private Animal animal;

    @ParameterizedTest
    @CsvSource({
            "Самец, true",
            "Самка, false"
    })
    void doesHaveManeReturnsCorrectValue(String sex, boolean expected) throws Exception {
        Lion lion = new Lion(sex);
        assertEquals(expected, lion.doesHaveMane());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "Неизвестно", "Male", "Female"})
    void constructorThrowsExceptionForInvalidSex(String invalidSex) {
        Exception exception = assertThrows(Exception.class, () -> new Lion(invalidSex));
        assertEquals(TestData.INVALID_SEX_ERROR, exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({"0, 0", "1, 1", "5, 5"})
    void getKittensWithArgumentsReturnsCorrectCount(int input, int expected) {
        Feline feline = new Feline();
        assertEquals(expected, feline.getKittens(input));
    }

    @ParameterizedTest
    @MethodSource("provideValidAnimalKinds")
    void getFoodReturnsExpectedFood(String kind, List<String> expected) throws Exception {
        assertEquals(expected, animal.getFood(kind));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidAnimalKinds")
    void getFoodThrowsExceptionForInvalidInput(String kind) {
        Exception exception = assertThrows(Exception.class, () -> animal.getFood(kind));
        assertEquals(TestData.UNKNOWN_ANIMAL_KIND_ERROR, exception.getMessage());
    }

    private static Stream<Arguments> provideValidAnimalKinds() {
        return Stream.of(
                Arguments.of(TestData.HERBIVORE, TestData.HERBIVORE_FOOD),
                Arguments.of(TestData.PREDATOR, TestData.PREDATOR_FOOD)
        );
    }

    private static Stream<String> provideInvalidAnimalKinds() {
        return Stream.of("Всеядное", "Насекомоядное", "123", "!@#", "", null, "хищник", "ТРАВОЯДНОЕ");
    }
}
