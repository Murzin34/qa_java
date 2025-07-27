import com.example.Animal;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AnimalTest {

    @InjectMocks
    protected Animal animal;

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class PositiveTests {

        @Test
        void getFamily_ShouldReturnCorrectString() {
            assertEquals(TestData.FAMILY_ALL, animal.getFamily());
        }

        @Test
        void getFood_ShouldReturnImmutableList() throws Exception {
            List<String> food = animal.getFood(TestData.HERBIVORE);
            assertThrows(UnsupportedOperationException.class, () -> food.add("Новая еда"));
        }

        @ParameterizedTest
        @MethodSource("provideValidAnimalKinds")
        void getFood_ShouldReturnExpectedFood(String kind, List<String> expected) throws Exception {
            assertEquals(expected, animal.getFood(kind));
        }

        private Stream<Arguments> provideValidAnimalKinds() {
            return Stream.of(
                    Arguments.of(TestData.HERBIVORE, TestData.HERBIVORE_FOOD),
                    Arguments.of(TestData.PREDATOR, TestData.PREDATOR_FOOD)
            );
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class NegativeTests {

        @ParameterizedTest
        @MethodSource("provideInvalidAnimalKinds")
        void getFood_ShouldThrowExceptionForInvalidInput(String kind) {
            Exception exception = assertThrows(Exception.class, () -> animal.getFood(kind));
            assertEquals(TestData.UNKNOWN_ANIMAL_KIND_ERROR, exception.getMessage());
        }

        private Stream<String> provideInvalidAnimalKinds() {
            return Stream.of("Всеядное", "Насекомоядное", "123", "!@#", "", null, "хищник", "ТРАВОЯДНОЕ");
        }
    }
}
