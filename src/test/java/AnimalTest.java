import com.example.Animal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AnimalTest {
    @InjectMocks
    private Animal animal;

    @Test
    void getFamilyReturnsCorrectString() {
        assertEquals(TestData.FAMILY_ALL, animal.getFamily());
    }

    @Test
    void getFoodReturnsImmutableList() throws Exception {
        List<String> food = animal.getFood(TestData.HERBIVORE);
        assertThrows(UnsupportedOperationException.class, () -> food.add("Новая еда"));
    }
}
