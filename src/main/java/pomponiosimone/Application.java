package pomponiosimone;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.Month;
import java.util.Random;

public class Application {

    public static void main(String[] args) {
        Random random = new Random();
        Faker faker = new Faker();


        System.out.println("Libri inseriti:");
        for (int i = 0; i < 5; i++) {
            Libri libro = new Libri(
                    random.nextInt(1000000000),
                    faker.book().title(),
                    LocalDate.of(
                            random.nextInt(1990, 2024),
                            Month.values()[random.nextInt(Month.values().length)],
                            random.nextInt(28) + 1
                    ),
                    random.nextInt(20, 400),
                    faker.book().author(),
                    faker.book().genre()
            );
            System.out.println(libro);
        }


        System.out.println("Riviste inserite:");
        for (int i = 0; i < 5; i++) {
            Riviste rivista = new Riviste(
                    random.nextInt(1000000000),
                    faker.book().title(),
                    LocalDate.of(
                            random.nextInt(1990, 2024),
                            Month.values()[random.nextInt(Month.values().length)],
                            random.nextInt(28) + 1
                    ),
                    random.nextInt(20, 500),
                    Periodicità.values()[random.nextInt(Periodicità.values().length)]
            );
            System.out.println(rivista);
        }
    }
}
