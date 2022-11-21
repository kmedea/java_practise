package streampractise;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamPractise {

    //    /Írj egy Stream kifejezést, hogy megkapd a páros számokat az alábbi listából:
    public List<Integer> evenNumbers(List<Integer> numbers) {
        return numbers.stream()
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toList());
    }

    //    Írj egy Stream kifejezést, hogy megkapd a pozitív számok négyzetét az alábbi listából:
    public List<Integer> positiveNumbersBringToSquare(List<Integer> numbers) {
        return numbers.stream()
                .filter(x -> x > 0)
                .map(x -> x * x)
                .collect(Collectors.toList());
    }

    //Írj egy Stream kifejezést, hogy megkapd, melyik szám négyzete nagyobb mint 20 az alábbi listából:
    public List<Double> powNumberBiggerThanTwenty(List<Integer> numbers) {
        return numbers.stream()
                .map(x -> x * x)
                .filter(x -> x > 20)
                .map(Math::sqrt)
                .collect(Collectors.toList());
    }

    //    Írj egy Stream kifejezést, hogy megkapd a páratlan számok átlag értékét az alábbi listából:
    public Double oddNumbersAverage(List<Integer> numbers) {
        return numbers.stream()
                .filter(x -> x % 2 == 1 || x % 2 == -1)
                .mapToDouble(x -> x)
                .average()
                .orElse(0.0);
    }

//Írj egy Stream kifejezést, hogy megkapd a páratlan számok összegét az alábbi listából:

    public Integer sumOddNumbers(List<Integer> numbers) {
        return numbers.stream()
                .filter(x -> x % 2 == 1)
                .mapToInt(x -> x)
                .sum();
    }

    //Írj egy Stream kifejezést, hogy megkapd a nagybetűs karaktereket egy stringből! (pl: sdAbcaLasMsaAa --> ALMA)
    public String onlyUpperCaseChar(String str) {
        StringBuilder upperCaseString = new StringBuilder();
        str.chars()
                .filter(Character::isUpperCase)
                .mapToObj(c -> (char) c)
                .forEach(upperCaseString::append);
        return upperCaseString.toString();
    }

    //Írj egy Stream kifejezést, hogy megtaláld azokat a stringeket, amik a paraméterként megadott karakterrel kezdődnek.
    public List<String> stringsStartsParamChar(List<String> strings, Character character) {
        return strings.stream()
                .filter(str -> str.charAt(0) == character)
                .collect(Collectors.toList());
    }

//    Írj egy Stream kifejezést, hogy hozzácsatolj egy Character listát egy Stringhez! pl: `concatChars("alma", Arrays.asList('f', 'a')) --> "almafa"

    public String concatCharsToString(String str, List<Character> characters) {
        StringBuilder concatString = new StringBuilder(str);
        characters
                .forEach(concatString::append);
        return concatString.toString();
    }

    //    Írj egy Stream kifejezést, ami összeszámolja, hogy a megadott Stringben melyik karakter hányszor fordul elő!
    public Map<Character, Long> sumCharsInString(String str) {
        return str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    /*Készíts egy Fox classt 3 tulajdonsággal: name, color és age. Tölts meg egy listát legalább 5 rókával és :

    Írj egy Stream kifejezést, hogy megtaláld a rókákat zöld színnel!
    Írj egy Stream kifejezést, hogy megtaláld azokat a rókákat, akiknek a színe zöld és fiatalabbak mint 5 év.
    Írj egy Stream kifejezést, hogy megtudd, milyen színű róka milyen gyakorisággal fordul elő!*/


    public List<Fox> foxesWithGreenColor(List<Fox> foxes) {
        return foxes.stream()
                .filter(f -> f.getColor().equals("green"))
                .collect(Collectors.toList());
    }

    public List<Fox> foxesWithGreenColorAndYoungerFiveYears(List<Fox> foxes) {
        return foxesWithGreenColor(foxes).stream()
                .filter(f -> f.getAge() < 5)
                .collect(Collectors.toList());
    }

    public Map<String, Long> foxesColors(List<Fox> foxes) {
        return foxes.stream()
                .collect(Collectors.groupingBy((Fox::getColor), Collectors.counting()));
    }

    /*Találj egy random Wikipédia cikket és másold be a tartalmát egy txt fájlba.

    Írj egy Stream kifejezést, ami beolvassa az összes szöveget a fájlból és kiírja a 100 leggyakrabban előforduló
     karaktert csökkenő sorrendben. Ne feledd, a szöveg tartalmaz írásjeleket, amiket ignorálni kell.
    Az eredmény legyen valami hasonló mint az alábbi:

    the: 131
    of: 74
    and: 48
    to: 45
    a: 43
    in: 43
    was: 30
    as: 21
    German: 18
            for: 16
    his: 15
    by: 13
    he: 11
    that: 11*/

    public Map<String, Long> hundredMostOftenWord(String fileName) {

        List<String> fileContent;
        Path path = Paths.get(fileName);

        try {
            fileContent = Files.readAllLines(path);
        } catch (IOException e) {
            throw new IllegalStateException("A fájl beolvasása nem sikerült", e);
        }

        fileContent
                .forEach(str -> str.replaceAll("\\p{Punct}", ""));

        Map<String, Long> counted = fileContent.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return counted.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(100)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    /*A swcharacters.csv fájl tartalmazza a Star Wars univerzumában megjelenő karaktereket.

Írj Stream kifejezéseket, ami elvégzik az alább feladatokat:

    Írasd ki a legnehezebb karakter nevét (ha a tömege ismeretlen, hagyja figyelmen kívül)
    Írja ki a férfi karakterek átlag magasságát
    Írja ki a női karakterek átlag magasságát */


    public String theHeaviestCharacter(String fileName) {

        List<String[]> fileContent = readFile(fileName);

        int maxHeavy = fileContent.stream()
                .filter(f -> f[2].matches("\\d+$"))
                .map(f -> Integer.valueOf(f[2]))
                .mapToInt(f -> f)
                .max()
                .orElse(Integer.MIN_VALUE);

        return fileContent.stream()
                .filter(f -> f[2].matches("\\d+$"))
                .filter(f -> Integer.parseInt(f[2]) == maxHeavy)
                .map(f -> f[0])
                .findFirst()
                .orElse("kismalac");
    }

    public Double avgHeight(String fileName, String gender) {

        List<String[]> fileContent = readFile(fileName);

        return fileContent.stream()
                .filter(f -> f[1].matches("\\d+$"))
                .filter(f -> f[7].equals(gender))
                .mapToInt(f -> Integer.parseInt(f[1]))
                .average()
                .orElse(0.0);
    }

   /* Írasd ki a karakterek nem szerinti kormegoszlását (ahol a nem lehet "male", "female", "other")
    Az életkor csoportok : "below 21", "between 21 and 40", "above 40" és "unknown"
    Az eredmény legyen egy Map<String, Map<String, Integer>> */


    public Map<String, Map<String, Long>> ageDistribution(String fileName) {

        List<String[]> fileContent = readFile(fileName);
        Map<String, Map<String, Integer>> genderAndAgeDistribution;

        List<SWCharacter> characters = fileContent.stream()
                .map(SWCharacter::new)
                .collect(Collectors.toList());

        return characters.stream()
                .collect(Collectors.groupingBy(
                        c -> groupByAge(c.getBirthYear()),
                        Collectors.groupingBy(c -> groupByGender(c.getGender()), Collectors.counting())
                ));
    }

    private String groupByGender(String gender) {
        if (gender.toLowerCase().equals("male")) {
            return "male";
        }
        if (gender.toLowerCase().equals("female")) {
            return "female";
        }
        return "other";
    }

    private String groupByAge(Integer birthYear) {
        if (birthYear == null) {
            return "unknown";
        }
        if (birthYear < 21) {
            return "below 21";
        }
        if (birthYear >= 21 && birthYear <= 40) {
            return "between 21 and 40";
        }
        return "above 40";
    }

    private List<String[]> readFile(String fileName) {

        List<String[]> fileContent;
        Path path = Paths.get(fileName);

        try {
            fileContent = Files.readAllLines(path).stream().skip(1).map(str -> str.split(";")).collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalStateException("A fájl beolvasása nem sikerült", e);
        }
        return fileContent;
    }

    public static void main(String[] args) {
        StreamPractise streamPractise = new StreamPractise();
        List<Integer> numbers = Arrays.asList(1, 3, -2, -4, -7, -3, -8, 12, 19, 6, 9, 10, 14);
        List<Integer> numbers2 = Arrays.asList(3, 9, 2, 8, 6, 5);
        System.out.println(streamPractise.evenNumbers(numbers));
        System.out.println(streamPractise.positiveNumbersBringToSquare(numbers));
        System.out.println(streamPractise.powNumberBiggerThanTwenty(numbers2));
        System.out.println(streamPractise.oddNumbersAverage(numbers));
        System.out.println(streamPractise.sumOddNumbers(numbers));
        System.out.println(streamPractise.onlyUpperCaseChar("sdAbcaLasMsaAa"));
        System.out.println(streamPractise.theHeaviestCharacter("swcharacters.csv"));
        System.out.println(streamPractise.avgHeight("swcharacters.csv", "male"));
        System.out.println(streamPractise.avgHeight("swcharacters.csv", "female"));
        System.out.println(streamPractise.ageDistribution("swcharacters.csv"));

    }
}
