package Practicas.Criptado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio1 {
    // Función para generar una clave repetida del mismo tamaño que el texto en claro
    public static String generarClave(String texto, String clave) {
        StringBuilder claveExtendida = new StringBuilder(clave);
        while (claveExtendida.length() < texto.length()) {
            claveExtendida.append(clave);
        }
        return claveExtendida.substring(0, texto.length());
    }

    // Función para cifrar el texto usando la clave
    public static String cifrarTexto(String texto, String clave) {
        StringBuilder textoCifrado = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char letraTexto = texto.charAt(i);

            // Solo ciframos letras del alfabeto
            if (Character.isLetter(letraTexto)) {
                char letraClave = clave.charAt(i);

                // Convierte las letras en sus posiciones en el alfabeto
                int posicionTexto = Character.toUpperCase(letraTexto) - 'A';
                int posicionClave = letraClave - 'A';

                // Suma las posiciones y ajusta si supera 25
                int posicionCifrada = (posicionTexto + posicionClave) % 26;

                // Convierte de nuevo a letra
                char letraCifrada = (char) (posicionCifrada + 'A');
                textoCifrado.append(letraCifrada);
            } else {
                textoCifrado.append(letraTexto); // Mantiene caracteres no alfabéticos
            }
        }
        return textoCifrado.toString();
    }

    // Función para descifrar el texto cifrado usando la clave
    public static String descifrarTexto(String textoCifrado, String clave) {
        StringBuilder textoDescifrado = new StringBuilder();
        for (int i = 0; i < textoCifrado.length(); i++) {
            char letraCifrada = textoCifrado.charAt(i);

            // Solo desciframos letras del alfabeto
            if (Character.isLetter(letraCifrada)) {
                char letraClave = clave.charAt(i);

                // Convierte las letras en sus posiciones en el alfabeto
                int posicionCifrada = letraCifrada - 'A';
                int posicionClave = letraClave - 'A';

                // Resta las posiciones y ajusta si es negativo
                int posicionDescifrada = (posicionCifrada - posicionClave + 26) % 26;

                // Convierte de nuevo a letra
                char letraDescifrada = (char) (posicionDescifrada + 'A');
                textoDescifrado.append(letraDescifrada);
            } else {
                textoDescifrado.append(letraCifrada); // Mantiene caracteres no alfabéticos
            }
        }
        return textoDescifrado.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Lectura del fichero de texto y clave
        System.out.println("Ingrese el nombre del fichero a cifrar:");
        String nombreFichero = sc.nextLine();
        System.out.println("Ingrese la clave:");
        String clave = sc.nextLine().toUpperCase();

        // Leer el contenido del fichero
        StringBuilder contenido = new StringBuilder();
        try {
            BufferedReader lector = new BufferedReader(new FileReader("ficheroLeer.txt"));
            String linea;
            while ((linea = lector.readLine()) != null) {
                contenido.append(linea);
            }
            lector.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
            return; // Salimos si no se encuentra el archivo
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        // Preparar el texto y clave para el cifrado
        String texto = contenido.toString().replaceAll("[^A-Za-z]", "").toUpperCase(); // Elimina caracteres no alfabéticos
        String claveGenerada = generarClave(texto, clave);

        // Cifrar el texto
        String textoCifrado = cifrarTexto(texto, claveGenerada);
        System.out.println("Texto Cifrado: " + textoCifrado);

        // Guardar el texto cifrado en un fichero
        try {
            BufferedWriter escritorCifrado = new BufferedWriter(new FileWriter("ficheroCifrado.txt"));
            escritorCifrado.write(textoCifrado);
            escritorCifrado.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo cifrado: " + e.getMessage());
        }

        // Descifrar el texto
        String textoDescifrado = descifrarTexto(textoCifrado, claveGenerada);
        System.out.println("Texto Descifrado: " + textoDescifrado);

        // Guardar el texto descifrado en un fichero
        try {
            BufferedWriter escritorDescifrado = new BufferedWriter(new FileWriter("ficheroDescifrado.txt"));
            escritorDescifrado.write(textoDescifrado);
            escritorDescifrado.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo descifrado: " + e.getMessage());
        }
    }
}
