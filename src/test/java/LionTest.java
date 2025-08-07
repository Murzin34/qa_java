import com.example.Feline;
import com.example.Lion;
import com.example.Predator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LionTest {
    @Mock
    private Predator predator;

    @Test
    void getKittensReturnsOneWhenPredatorIsFeline() throws Exception {
        Feline feline = mock(Feline.class);
        when(feline.getKittens()).thenReturn(1);
        Lion lion = new Lion("Самец", feline);
        assertEquals(1, lion.getKittens());
    }

    @Test
    void getKittensReturnsDefaultWhenPredatorIsNotFeline() throws Exception {
        Lion lion = new Lion("Самец", predator);
        assertEquals(1, lion.getKittens());
    }

    @Test
    void getFoodReturnsPredatorFood() throws Exception {
        when(predator.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        Lion lion = new Lion("Самка", predator);
        assertEquals(List.of("Животные", "Птицы", "Рыба"), lion.getFood());
        verify(predator).eatMeat();
    }
}
