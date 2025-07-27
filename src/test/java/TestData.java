import java.util.List;

public class TestData {

    public static final String HERBIVORE = "Травоядное";
    public static final String PREDATOR = "Хищник";
    public static final String UNKNOWN_ANIMAL_KIND_ERROR =
            "Неизвестный вид животного, используйте значение Травоядное или Хищник";

    public static final List<String> HERBIVORE_FOOD = List.of("Трава", "Различные растения");
    public static final List<String> PREDATOR_FOOD = List.of("Животные", "Птицы", "Рыба");

    public static final String FAMILY_ALL =
            "Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи";
    public static final String FAMILY_FELINE = "Кошачьи";

    private TestData() {
    }
}