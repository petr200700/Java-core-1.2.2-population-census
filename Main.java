import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long count = persons.stream()
                .filter(p -> p.getAge() < 18)
                .count();
        System.out.println("1. Количество несовершеннолетних: " + count);

        List<String> conscriptsFamilies = persons.stream()
                .filter(p -> p.getSex().equals(Sex.MAN))
                .filter(p -> p.getAge() >= 18 && p.getAge() <= 27)
                .map(Person::getFamily)
                .limit(10)
                .collect(Collectors.toList());
        System.out.println("2. Cписок фамилий призывников c лимитом 10: " + conscriptsFamilies);

        List<Person> ableBodiedPeople = persons.stream()
                .filter(p -> p.getEducation().equals(Education.HIGHER))
                .filter(p -> (p.getSex().equals(Sex.MAN) && p.getAge() >= 18 && p.getAge() < 65)
                        || (p.getSex().equals(Sex.WOMAN) && p.getAge() >= 18 && p.getAge() < 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .limit(5)
                .collect(Collectors.toList());
        System.out.println("3. Отсортированный по фамилии список потенциально работоспособных людей с высшим образованием с лимитом 5: " + ableBodiedPeople);

    }
}
