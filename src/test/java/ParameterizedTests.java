import com.example.Animal;
import com.example.Feline;
import com.example.Lion;
import com.example.Predator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ParameterizedTests {
    @Mock
    private Predator predator;

    @ParameterizedTest
    @CsvSource({
            "Самец, true",
            "Самка, false"
    })
    void lionDoesHaveManeReturnsCorrectValue(String sex, boolean expected) throws Exception {
        Lion lion = new Lion(sex, predator);
        assertEquals(expected, lion.doesHaveMane());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "Неизвестно", "Male", "Female"})
    void lionConstructorThrowsExceptionForInvalidSex(String invalidSex) {
        Exception exception = assertThrows(Exception.class, () -> new Lion(invalidSex, predator));
        assertEquals("Используйте допустимые значения пола животного - самец или самка", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({"0, 0", "1, 1", "5, 5"})
    void felineGetKittensWithArgumentsReturnsCorrectCount(int input, int expected) {
        Feline feline = new Feline();
        assertEquals(expected, feline.getKittens(input));
    }

    @ParameterizedTest
    @MethodSource("provideValidAnimalKinds")
    void animalGetFoodReturnsExpectedFood(String kind, List<String> expected) throws Exception {
        Animal animal = new Animal();
        assertEquals(expected, animal.getFood(kind));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidAnimalKinds")
    void animalGetFoodThrowsExceptionForInvalidInput(String kind) {
        Animal animal = new Animal();
        Exception exception = assertThrows(Exception.class, () -> animal.getFood(kind));
        assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", exception.getMessage());
    }

    private static Stream<Arguments> provideValidAnimalKinds() {
        return Stream.of(
                Arguments.of("Травоядное", List.of("Трава", "Различные растения")),
                Arguments.of("Хищник", List.of("Животные", "Птицы", "Рыба"))
        );
    }

    private static Stream<String> provideInvalidAnimalKinds() {
        return Stream.of("Всеядное", "Насекомоядное", "123", "!@#", "", null, "хищник", "ТРАВОЯДНОЕ");
    }
}
