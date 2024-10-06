import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
public class Ejercicio2 {
    private Scanner scanner=new Scanner(System.in);

    //Mostrara el menu hasta que se introduca 5
    public void mostrarMenu()
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

                //Lee distintas opciones
                if (opcion==1) 
                {
                    generar_fichero_CSV();
                }
                else 
                    if(opcion==2){
                        System.out.println("Introduce el nombre del deportista ");
                        String nombre=scanner.nextLine();
                        buscar_deportista(nombre);
                    }   
                    else 
                        if(opcion==3) 
                        {
                            buscar_deportista_olimpiada(deporte,anio,temporada);
                        }
                        else 
                            if (opcion==4)
                            {
                                aniadir_deportista(nombre,sexo,edad,altura,peso,equipo,noc,juegos,anio,temporada,ciudad,deporte,evento,medalla);
                            }   
                            else 
                                if (opcion==5)
                                {
                                    System.out.println("Saliendo del programa");
                                }
                
    }

    //Generar fichero CSV
    public void generar_fichero_CSV(){
        ArrayList<String> olimpiadas = new ArrayList<String>();
        //Leer Archivo CSV
        try {
            BufferedReader br = new BufferedReader(new FileReader("Practicas/Ejercicios/athlete_events.csv"));
            String linea;
            br.readLine();//Para no coger los encabezados leo la primera linea

            while ((linea = br.readLine()) != null) {
                // Dividir la línea por comas
                String[] campos = linea.split(",");

                String games = campos[8].trim(); //Hay que tener cuidado con que parametro se escoje,si en el csv es la columna 9,aqui hay que restar 1 porque se empieza por 0
                String year = campos[9].trim();
                String season = campos[10].trim();
                String city = campos[11].trim();

                // Formar una cadena para agregar al ArrayList
                String olimpiada = String.join(",", games, year, season, city); //Esto creara un string con cada varibale separadas por comas
                if (!olimpiadas.contains(olimpiada)) {
                    olimpiadas.add(olimpiada);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Escribir CSV
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Practicas/Practica_1/Ejercicios/ficheros/olimpiadas.csv"));
            //Escribir encabezado
            bw.write("Games,Year,Season,City");
            bw.newLine();

            for (String olimpiada : olimpiadas) {
                bw.write(olimpiada);
                bw.newLine();
            }
        
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("El archivo olimpiadas.csv ha sido generado correctamente.");
    }


    //Buscar deportista por nombre
    public void buscar_deportista(String nombre){
        ArrayList<String> participaciones = new ArrayList<String>();
        String datosPersonales="";
        //Leer Archivo CSV
        try {
            BufferedReader br = new BufferedReader(new FileReader("Practicas/Practica_1/Ejercicios/ficheros/athlete_events.csv"));

            String linea;
            br.readLine();//Para no coger los encabezados leo la primera linea

            while ((linea = br.readLine()) != null) {
                // Dividir la línea por comas
                String[] campos = linea.split(",");

                String name = campos[1].trim().replaceAll("\"", ""); //Se utiliza para eliminar las "" de los strings de los campos.Si no se usa name estaria asi "mikel" en vez de mikel
                if (name.equals(nombre)) {
                    //Datos Personales
                    String sex = campos[2].trim().replaceAll("\"", "");
                    String age = campos[3].trim().replaceAll("\"", "");
                    String height = campos[4].trim().replaceAll("\"", "");
                    String weight = campos[5].trim().replaceAll("\"", "");
                    String team = campos[6].trim().replaceAll("\"", "");
                    datosPersonales = String.join(",", sex, age, height, weight, team);
                    
                    //Participacion
                    String games = campos[8].trim().replaceAll("\"", "");
                    String city = campos[11].trim().replaceAll("\"", "");
                    String event = campos[13].trim().replaceAll("\"", "");
                    String medal = campos[14].trim().replaceAll("\"", "");
                    String participacion = String.join(",", games, city, event, medal);
                    participaciones.add(participacion);
                } 
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (participaciones.size()==0) {
            System.out.println("No se ha encontrado ningun deportista con el nombre "+nombre);
        } else {
            //DATOS PERSONALES
            String[] datos = datosPersonales.split(",");
            System.out.println("\nDATOS PERSONALES");
            System.out.println("NOMBRE "+nombre);
            System.out.println("SEXO "+datos[0]);
            System.out.println("EDAD "+datos[1]);
            System.out.println("ALTURA "+datos[2]);
            System.out.println("PESO "+datos[3]);
            System.out.println("EQUIPO "+datos[4]);
            System.out.println("\n--------------------------------");

            //PARTICIPACIONES
            System.out.println("PARTICIPACIONES");
            for (String participacion : participaciones) {
                String[] parti = participacion.split(",");
                System.out.println("\nJUEGOS "+parti[0]);
                System.out.println("CIUDAD "+parti[1]);
                System.out.println("EVENTO "+parti[2]);
                System.out.println("MEDALLA "+parti[3]);

                System.out.println("\n--------------------------------");
            }
        }
    }

    //Buscar deportista por olimpiada
    String deporte=scanner.nextLine();
    String anio=scanner.nextLine();
    String temporada=scanner.nextLine();
    public void buscar_deportista_olimpiada(String deporte,String anio,String temporada)
    {
        System.out.println("Introduce un deporte ");
        System.out.println("Introduce un Año ");
        System.out.println("Introduce una temporada (Summer o Winter) ");

        ArrayList<String> deportistas = new ArrayList<String>();
        String datosOlimpiada="";
        //Leer Archivo CSV
        try {
            BufferedReader br = new BufferedReader(new FileReader("Practicas/Practica_1/Ejercicios/ficheros/athlete_events.csv"));

            String linea;
            br.readLine();//Para no coger los encabezados leo la primera linea

            while ((linea = br.readLine()) != null) {
                // Dividir la línea por comas
                String[] campos = linea.split(",");

                String sport = campos[12].trim().replaceAll("\"", ""); //Se utiliza para eliminar las "" de los strings de los campos.Si no se usa name estaria asi "mikel" en vez de mikel
                String year = campos[9].trim();
                String season = campos[10].trim().replaceAll("\"", "");
                
                if (deporte.equalsIgnoreCase(sport) && temporada.equalsIgnoreCase(season) && anio.equalsIgnoreCase(year)) {
                    //Detalles Olimpiada
                    String games = campos[8].trim().replaceAll("\"", "");
                    String city = campos[11].trim().replaceAll("\"", "");

                    datosOlimpiada = String.join(",", games, city, sport);

                    //Deportistas Participantes
                    String name = campos[1].trim().replaceAll("\"", "");
                    String event = campos[13].trim().replaceAll("\"", "");
                    String medal = campos[14].trim().replaceAll("\"", "");
                    String deportista = String.join(",", name, event, medal);
                    deportistas.add(deportista);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (deportistas.size()==0) {
            System.out.println("No se han encontrado ningun dato sobre esa Olimpiada");
        } else {
            //DETALLES OLIMPIADAS
            String[] datos = datosOlimpiada.split(",");
            System.out.println("\nDETALLES OLIMPIADAS");
           
            System.out.println("JUEGOS "+datos[0]);
            System.out.println("CIUDAD "+datos[1]);
            System.out.println("DEPORTE "+datos[2]);
           
            //DEPORTISTAS
            System.out.println("DEPORTISTAS\n");
            for (String deportista : deportistas) {
                String[] depor = deportista.split(",");
                System.out.println("NOMBRE "+depor[0]);
                System.out.println("EVENTO "+depor[1]);
                System.out.println("MEDALLA "+depor[2]);
            }
        }
    }

    String nombre = "\"" + scanner.nextLine() + "\""; 
    String sexo = "\"" + scanner.nextLine() + "\"";
    String edad = scanner.nextLine();
    String altura = scanner.nextLine();
    String peso = scanner.nextLine();
    String equipo = "\"" + scanner.nextLine() + "\"";
    String noc = "\"" + scanner.nextLine() + "\""; 
    String juegos = "\"" + scanner.nextLine() + "\""; 
    String anios = scanner.nextLine();
    String temporadas = "\"" + scanner.nextLine() + "\""; 
    String ciudad = "\"" + scanner.nextLine() + "\""; 
    String deportes = "\"" + scanner.nextLine() + "\""; 
    String evento = "\"" + scanner.nextLine() + "\""; 
    String medalla = scanner.nextLine();
    //Añadir deportista al CSV
    public void aniadir_deportista(String nombre,String sexo,String edad,String altura,String peso,String equipo,String noc,String juegos,String anios,String temporadas,String ciudad,String deportes,String evento, String medalla){
        System.out.print("Nombre: ");
        System.out.print("Sexo (M/F): ");
        System.out.print("Edad: ");
        System.out.print("Altura (NA si desconocida): ");
        System.out.print("Peso (NA si desconocido): ");
        System.out.print("Equipo: ");
        System.out.print("Código NOC: ");
        System.out.print("Equipo: ");
        System.out.print("Juegos (Año y Temporada, ej: 1992 Summer): ");
        System.out.print("Año: ");
        System.out.print("Temporada (Summer/Winter): ");
        System.out.print("Ciudad: ");
        System.out.print("Deporte: ");
        System.out.print("Evento: ");
        System.out.print("Medalla (NA si no tiene): ");
        //Comprobar medalla
        if (!medalla.equalsIgnoreCase("NA")) {
            medalla = "\"" + medalla + "\"";
        }

        // Obtener el ID más alto y sumarle 1 para generar un nuevo ID
        String nuevoID = "\"" + obtenerNuevoID() + "\"";
        
        //Linea que se va a añadir al csv
        String nuevaLinea = String.join(",", String.valueOf(nuevoID), nombre, sexo, edad, altura, peso, equipo, noc, juegos, anio, temporada, ciudad, deporte, evento, medalla);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Practicas/Practica_1/Ejercicios/ficheros/athlete_events.csv", true)); // Abrir en modo append para no sobreescribir el archivo

            // Escribir la nueva línea al final del archivo CSV
            bw.write(nuevaLinea);
            bw.newLine(); // Asegurarse de que se añade una nueva línea

            bw.close();
            System.out.println("Deportista añadido correctamente.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para obtener el siguiente ID disponible
    private int obtenerNuevoID() {
        int idMax = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("Practicas/Practica_1/Ejercicios/ficheros/athlete_events.csv"));
            String linea;
            br.readLine(); // Saltar la cabecera

            // Leer cada línea y obtener el ID más alto
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                int idActual = Integer.parseInt(campos[0].replaceAll("\"", ""));
                if (idActual > idMax) {
                    idMax = idActual;
                }
            }
            br.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return idMax + 1; // Devolver el siguiente ID disponible
    }

    public static void main(String[] args) {
        Ejercicio2 e =new Ejercicio2();
        e.mostrarMenu();
    }
}
