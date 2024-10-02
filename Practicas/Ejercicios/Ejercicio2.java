import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Ejercicio2 {
    private Scanner scanner = new Scanner(System.in);
    private final String athleteEventsFile = "athlete_events.csv"; // Ruta del archivo CSV de eventos de atletas
    private final String olympicsFile = "olimpiadas.csv"; // Ruta del archivo CSV de olimpiadas
    public void mostrar_menu()
    {
        int opcion=0;
        while(opcion!=5)
        {
            System.out.println("1 - Generar fichero CSV de olimpiadas");
            System.out.println("2 - Buscar deportista");
            System.out.println("3 - Buscar deportistas por deporte y olimpiada");
            System.out.println("4 - Añadir deportista");
            System.out.println("5 - Salir"); 
            opcion = scanner.nextInt(); 
        }
        while (opcion<1 || opcion>5) 
        {
            System.out.println("Opcion no valida");
            System.out.println("Elige una opcion(1-5):");
            opcion = scanner.nextInt();
        }
        if(opcion==5)
        {
            System.out.println("Saliendo del programa");
        }
        if (opcion==1) 
        {
            generarFicheroCSV();
        }
        if (opcion==2) 
        {
            buscarDeportista();
        }
        if(opcion==3)
        {
            buscarDeportistasPorDeporte();
        }
        if(opcion==4)
        {
            anadirDeportista();
        }
    }

    private void generarFicheroCSV() {
        try (CSVReader reader = new CSVReader(new FileReader(athleteEventsFile));
             CSVWriter writer = new CSVWriter(new FileWriter(olympicsFile))) {
            String[] nextLine;
            List<String[]> data = new ArrayList<>();

            // Leer el archivo de atletas y generar el archivo de olimpiadas
            while ((nextLine = reader.readNext()) != null) {
                String games = nextLine[0]; // Cambia esto según la estructura de tu CSV
                String year = nextLine[1];
                String season = nextLine[2];
                String city = nextLine[3];
                String[] record = {games, year, season, city};
                data.add(record);
            }
            writer.writeAll(data);
            System.out.println("Fichero CSV de olimpiadas generado: " + olympicsFile);
        } catch (IOException e) {
            System.out.println("Error al generar el fichero CSV: " + e.getMessage());
        }
    }

    private void buscarDeportista() {
        System.out.println("Introduce el nombre del deportista a buscar:");
        scanner.nextLine(); // Limpiar el buffer
        String nombreBuscado = scanner.nextLine();

        try (CSVReader reader = new CSVReader(new FileReader(athleteEventsFile))) {
            String[] nextLine;
            boolean encontrado = false;

            while ((nextLine = reader.readNext()) != null) {
                String nombre = nextLine[0]; // Cambia esto según la estructura de tu CSV
                if (nombre.contains(nombreBuscado)) {
                    System.out.println("Deportista encontrado: " + nombre);
                    // Aquí podrías imprimir más datos si fuera necesario
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("No se encontraron deportistas con ese nombre.");
            }
        } catch (IOException e) {
            System.out.println("Error al buscar el deportista: " + e.getMessage());
        }
    }

    private void buscarDeportistasPorDeporte() {
        System.out.println("Introduce el deporte:");
        scanner.nextLine(); // Limpiar el buffer
        String deporte = scanner.nextLine();

        System.out.println("Introduce el año olímpico:");
        int year = scanner.nextInt();

        System.out.println("Introduce la temporada (Summer/Winter):");
        scanner.nextLine(); // Limpiar el buffer
        String season = scanner.nextLine();

        try (CSVReader reader = new CSVReader(new FileReader(athleteEventsFile))) {
            String[] nextLine;
            boolean encontrado = false;

            while ((nextLine = reader.readNext()) != null) {
                String sport = nextLine[2]; // Cambia esto según la estructura de tu CSV
                String yearOlympic = nextLine[1];
                String seasonOlympic = nextLine[3];

                if (sport.equalsIgnoreCase(deporte) && Integer.parseInt(yearOlympic) == year && seasonOlympic.equalsIgnoreCase(season)) {
                    System.out.println("Deportista: " + nextLine[0] + ", Evento: " + nextLine[4] + ", Medalla: " + nextLine[5]);
                    encontrado = true;
                }
            }

            if (!encontrado) {
                System.out.println("No se encontraron deportistas en ese deporte y año olímpico.");
            }
        } catch (IOException e) {
            System.out.println("Error al buscar deportistas por deporte: " + e.getMessage());
        }
    }

    private void anadirDeportista() {
        System.out.println("Introduce el nombre del deportista:");
        scanner.nextLine(); // Limpiar el buffer
        String nombre = scanner.nextLine();

        // Aquí puedes pedir más información sobre el deportista
        System.out.println("Introduce el deporte del deportista:");
        String deporte = scanner.nextLine();

        System.out.println("Introduce el evento del deportista:");
        String evento = scanner.nextLine();

        System.out.println("Introduce el año de participación:");
        int year = scanner.nextInt();

        System.out.println("Introduce la medalla (Gold/Silver/Bronze/None):");
        scanner.nextLine(); // Limpiar el buffer
        String medalla = scanner.nextLine();

        // Añadir el nuevo deportista al archivo CSV
        try (CSVWriter writer = new CSVWriter(new FileWriter(athleteEventsFile, true))) {
            String[] nuevoDeportista = {nombre, String.valueOf(year), deporte, evento, medalla}; // Cambia esto según la estructura del CSV
            writer.writeNext(nuevoDeportista);
            System.out.println("Deportista añadido: " + nombre);
        } catch (IOException e) {
            System.out.println("Error al añadir deportista: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Ejercicio2 ej2 = new Ejercicio2();
        ej2.mostrar_menu();
    }
}