package pomponiosimone;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Application {

    private static Map<Integer, Libri> libriMap = new HashMap<>();
    private static Map<Integer, Riviste> rivisteMap = new HashMap<>();
    private static Random random = new Random();
    private static Faker faker = new Faker();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            int libroIsbn = random.nextInt(1000000000);
            Libri libro = new Libri(
                    libroIsbn,
                    faker.book().title(),
                    LocalDate.of(
                            random.nextInt(1990, 2024),
                            Month.values()[random.nextInt(Month.values().length)],
                            random.nextInt(28) + 1
                    ),
                    random.nextInt(1, 500),
                    faker.book().author(),
                    faker.book().genre()
            );
            libriMap.put(libroIsbn, libro);

            int rivistaIsbn = random.nextInt(1000000000);
            Riviste rivista = new Riviste(
                    rivistaIsbn,
                    faker.book().title(),
                    LocalDate.of(
                            random.nextInt(1990, 2024),
                            Month.values()[random.nextInt(Month.values().length)],
                            random.nextInt(1, 30)
                    ),
                    random.nextInt(1, 500),
                    Periodicità.values()[random.nextInt(Periodicità.values().length)]
            );
            rivisteMap.put(rivistaIsbn, rivista);
        }

        System.out.println("Libri:");
        libriMap.values().forEach(System.out::println);

        System.out.println("Riviste:");
        rivisteMap.values().forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------------------------Esercizio 1--------------------------------------");

        try {
            System.out.print("Inserisci l'ISBN del nuovo libro: ");
            int newLibroISBN = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Inserisci il titolo del libro: ");
            String newLibroTitolo = scanner.nextLine();
            System.out.print("Inserisci l'anno di pubblicazione (yyyy-mm-dd): ");
            LocalDate newLibroAnnoPubblicazione = LocalDate.parse(scanner.nextLine());
            System.out.print("Inserisci il numero di pagine: ");
            int newLibroNumeroPagine = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Inserisci l'autore del libro: ");
            String newLibroAutore = scanner.nextLine();
            System.out.print("Inserisci il genere del libro: ");
            String newLibroGenere = scanner.nextLine();
            Libri newLibro = new Libri(newLibroISBN, newLibroTitolo, newLibroAnnoPubblicazione, newLibroNumeroPagine, newLibroAutore, newLibroGenere);
            libriMap.put(newLibroISBN, newLibro);

            System.out.print("Inserisci l'ISBN della nuova rivista: ");
            int newRivistaISBN = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Inserisci il titolo della rivista: ");
            String newRivistaTitolo = scanner.nextLine();
            System.out.print("Inserisci l'anno di pubblicazione (anno-mese-giorno): ");
            LocalDate newRivistaAnnoPubblicazione = LocalDate.parse(scanner.nextLine());
            System.out.print("Inserisci il numero di pagine: ");
            int newRivistaNumeroPagine = scanner.nextInt();
            scanner.nextLine();  // Consuma il newline
            System.out.print("Inserisci la periodicità (SETTIMANALE, MENSILE, TRIMESTRALE): ");
            Periodicità newRivistaPeriodicità = Periodicità.valueOf(scanner.nextLine().toUpperCase());
            Riviste newRivista = new Riviste(newRivistaISBN, newRivistaTitolo, newRivistaAnnoPubblicazione, newRivistaNumeroPagine, newRivistaPeriodicità);
            rivisteMap.put(newRivistaISBN, newRivista);
        } catch (NumberFormatException e) {
            System.out.println("Errore nel formato del numero: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Errore nella data di pubblicazione: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Errore nella periodicità: " + e.getMessage());
        }

        System.out.println("Libri dopo aggiunta:");
        libriMap.values().forEach(System.out::println);

        System.out.println("Riviste dopo aggiunta:");
        rivisteMap.values().forEach(System.out::println);

        System.out.println("---------------------------------Esercizio 2--------------------------------------");

        try {
            System.out.print("Inserisci l'ISBN del libro da rimuovere: ");
            int isbnRemoveLibri = scanner.nextInt();
            scanner.nextLine();
            if (libriMap.remove(isbnRemoveLibri) != null) {
                System.out.println("Libro con ISBN " + isbnRemoveLibri + " eliminato.");
            } else {
                System.out.println("Libro con ISBN " + isbnRemoveLibri + " non trovato.");
            }

            System.out.print("Inserisci l'ISBN della rivista da rimuovere: ");
            int isbnRemoveRiviste = scanner.nextInt();
            if (rivisteMap.remove(isbnRemoveRiviste) != null) {
                System.out.println("Rivista con ISBN " + isbnRemoveRiviste + " eliminata.");
            } else {
                System.out.println("Rivista con ISBN " + isbnRemoveRiviste + " non trovata.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Errore nel formato del numero: " + e.getMessage());
        }

        System.out.println("Libri dopo la rimozione:");
        libriMap.values().forEach(System.out::println);

        System.out.println("Riviste dopo la rimozione:");
        rivisteMap.values().forEach(System.out::println);

        System.out.println("---------------------------------Esercizio 3--------------------------------------");

        try {
            System.out.print("Inserisci l'ISBN del libro da cercare: ");
            int isbnToFindLibri = scanner.nextInt();
            scanner.nextLine();
            Libri libroFound = libriMap.get(isbnToFindLibri);
            if (libroFound != null) {
                System.out.println("Libro trovato: " + libroFound);
            } else {
                System.out.println("Libro con ISBN " + isbnToFindLibri + " non trovato.");
            }

            System.out.print("Inserisci l'ISBN della rivista da cercare: ");
            int isbnToFindRiviste = scanner.nextInt();
            scanner.nextLine();
            Riviste rivistaFound = rivisteMap.get(isbnToFindRiviste);
            if (rivistaFound != null) {
                System.out.println("Rivista trovata: " + rivistaFound);
            } else {
                System.out.println("Rivista con ISBN " + isbnToFindRiviste + " non trovata");
            }
        } catch (NumberFormatException e) {
            System.out.println("Errore nel formato del numero: " + e.getMessage());
        }

        System.out.println("---------------------------------Esercizio 4--------------------------------------");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            System.out.print("Inserisci la data di pubblicazione del libro o rivista da cercare (es 2024-05-14): ");
            String input = scanner.nextLine();
            LocalDate dataPubblicazione = LocalDate.parse(input, formatter);

            Libri libroFoundDate = null;
            for (Libri libro : libriMap.values()) {
                if (libro.getAnnoPubblicazione().getYear() == dataPubblicazione.getYear()) {
                    libroFoundDate = libro;
                    break;
                }
            }

            if (libroFoundDate != null) {
                System.out.println("Libro trovato: " + libroFoundDate);
            } else {
                System.out.println("Libro con anno di pubblicazione " + dataPubblicazione.getYear() + " non trovato.");
            }

            Riviste rivistaFoundDate = null;
            for (Riviste rivista : rivisteMap.values()) {
                if (rivista.getAnnoPubblicazione().getYear() == dataPubblicazione.getYear()) {
                    rivistaFoundDate = rivista;
                    break;
                }
            }

            if (rivistaFoundDate != null) {
                System.out.println("Rivista trovata: " + rivistaFoundDate);
            } else {
                System.out.println("Rivista con anno di pubblicazione " + dataPubblicazione.getYear() + " non trovata.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Errore nella data di pubblicazione: " + e.getMessage());
        }

        System.out.println("---------------------------------Esercizio 5--------------------------------------");

        System.out.print("Inserisci l'autore del libro da cercare: ");
        String autoreDaCercare = scanner.nextLine();

        boolean found = false;
        for (Libri libro : libriMap.values()) {
            if (libro.getAutore().equalsIgnoreCase(autoreDaCercare)) {
                System.out.println("Libro trovato: " + libro);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Nessun libro trovato per autore: " + autoreDaCercare);
        }

        System.out.println("---------------------------------Esercizio 6--------------------------------------");
        scanner.close();
    }
}
