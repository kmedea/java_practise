import java.util.List;
import java.util.stream.Collectors;

public class StreamPractise {

//    /Írj egy Stream kifejezést, hogy megkapd a páros számokat az alábbi listából:
    public static List<Integer> evenNumbers (List<Integer> numbers){
        return numbers.stream()
            .filter(x -> x % 2 == 0)
            .collect(Collectors.toList());
    }

//    Írj egy Stream kifejezést, hogy megkapd a pozitív számok négyzetét az alábbi listából:
    public static List<Integer> positiveNumbersBringToSquare(List<Integer> numbers){
        return numbers.stream()
                .filter(x -> x > 0)
                .map (x -> x*x)
                .collect(Collectors.toList());
    }

    //Írj egy Stream kifejezést, hogy megkapd, melyik szám négyzete nagyobb mint 20 az alábbi listából:
    public static List<Integer> powNumberBiggerThanTwenty(List<Integer> numbers){
        return numbers.stream()
                .map(x -> x*x)
                .filter(x -> x>20)
                .collect(Collectors.toList());
    }

//    Írj egy Stream kifejezést, hogy megkapd a páratlan számok átlag értékét az alábbi listából:
    public static Double oddNumbersAverage(List<Integer> numbers){
        return numbers.stream()
                    .filter(x -> x%2 ==1 || x%2==-1)
                    .mapToDouble(x -> x)
                    .average()
                    .orElse(0.0);
    }

}
