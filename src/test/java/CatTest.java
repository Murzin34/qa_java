import com.example.Cat;
import com.example.Feline;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatTest {

    @Mock
    private Feline feline;

    @InjectMocks
    private Cat cat;

    @Nested
    class PositiveTests {

        @Test
        void getSound_ShouldReturnMeow() {
            assertEquals("Мяу", cat.getSound());
        }

        @Test
        void getFood_ShouldReturnPredatorFood() throws Exception {
            when(feline.eatMeat()).thenReturn(TestData.PREDATOR_FOOD);
            assertEquals(TestData.PREDATOR_FOOD, cat.getFood());
            verify(feline).eatMeat();
        }

        @Test
        void catShouldUseRealFeline_WhenProvided() throws Exception {
            Cat realCat = new Cat(new Feline());
            assertEquals(TestData.PREDATOR_FOOD, realCat.getFood());
        }
    }

    @Nested
    class NegativeTests {

        @Test
        void getFood_ShouldThrowException_WhenFelineThrows() throws Exception {
            when(feline.eatMeat()).thenThrow(new Exception("Ошибка получения еды"));
            Exception exception = assertThrows(Exception.class, cat::getFood);
            assertEquals("Ошибка получения еды", exception.getMessage());
        }
    }
}
