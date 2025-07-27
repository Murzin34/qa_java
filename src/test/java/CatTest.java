import com.example.Cat;
import com.example.Feline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatTest {
    @Mock
    private Feline feline;
    @InjectMocks
    private Cat cat;

    @Test
    void getSoundReturnsMeow() {
        assertEquals(TestData.CAT_SOUND, cat.getSound());
    }

    @Test
    void getFoodReturnsPredatorFood() throws Exception {
        when(feline.eatMeat()).thenReturn(TestData.PREDATOR_FOOD);
        assertEquals(TestData.PREDATOR_FOOD, cat.getFood());
        verify(feline).eatMeat();
    }

    @Test
    void getFoodThrowsExceptionWhenFelineThrows() throws Exception {
        when(feline.eatMeat()).thenThrow(new Exception(TestData.FOOD_ERROR_MESSAGE));
        Exception exception = assertThrows(Exception.class, cat::getFood);
        assertEquals(TestData.FOOD_ERROR_MESSAGE, exception.getMessage());
    }
}
