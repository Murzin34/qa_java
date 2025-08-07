import com.example.Feline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FelineTest {
    private final Feline feline = new Feline();

    @Test
    void getFamilyReturnsFelineFamily() {
        assertEquals(TestData.FAMILY_FELINE, feline.getFamily());
    }

    @Test
    void getKittensWithoutArgumentsReturnsOne() {
        assertEquals(1, feline.getKittens());
    }

    @Test
    void eatMeatReturnsPredatorFood() throws Exception {
        assertEquals(TestData.PREDATOR_FOOD, feline.eatMeat());
    }

    @Test
    void getKittensWithArgumentReturnsSameValue() {
        int expectedCount = 3;
        assertEquals(expectedCount, feline.getKittens(expectedCount));
    }
}
