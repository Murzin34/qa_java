import com.example.Lion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LionTest {
    @Test
    void constructorSetsManeForMale() throws Exception {
        Lion lion = new Lion("Самец");
        assertTrue(lion.doesHaveMane());
    }

    @Test
    void constructorSetsNoManeForFemale() throws Exception {
        Lion lion = new Lion("Самка");
        assertFalse(lion.doesHaveMane());
    }

    @Test
    void getKittensReturnsOne() throws Exception {
        Lion lion = new Lion("Самец");
        assertEquals(1, lion.getKittens());
    }

    @Test
    void getFoodReturnsPredatorFood() throws Exception {
        Lion lion = new Lion("Самка");
        assertEquals(TestData.PREDATOR_FOOD, lion.getFood());
    }
}
