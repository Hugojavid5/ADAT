import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Ejercicio1 {
	
	public static void crearDirectorio(String ruta,String nombre) {
		File directorio=new File(ruta+File.separator+nombre);
		directorio.mkdir();
	}
	
	public static void listarDirectorio(String ruta) {
		File directorio=new File(ruta);
		File[] contenido = directorio.listFiles();
		for(File archivo:contenido) {
			if (archivo.isDirectory()) {
                System.out.println("Directorio "+archivo.getName());
            } else if (archivo.isFile()) {
                System.out.println("Archivo "+archivo.getName());
            }
		}
	}

	public static void copiarArchivo(String origen,String destino) {
		Path pathOrigen = Paths.get(origen);
        Path pathDestino = Paths.get(destino);
        Path destinoFinal = pathDestino.resolve(pathOrigen.getFileName());
        try {
			Files.copy(pathOrigen, destinoFinal);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void moverArchivo(String origen,String destino) {
		copiarArchivo(origen, destino);
		borrarArchivo(origen);
	}
	
	public static void borrarArchivo(String ruta) {
		File f=new File(ruta);
		if(f.isFile()||f.isDirectory()&&f.listFiles().length==0) {
			f.delete();
		}
		else {
			System.out.println("No se puede borrar");
		}
	}
	public static void menu(int numeroElegido) {
		Scanner scanner=new Scanner(System.in);
		switch (numeroElegido) {
			case 1:
				System.out.println("Dime la ruta del directorio");
				String ruta=scanner.nextLine();
				System.out.println("Dime su nombre");
				String nombre=scanner.nextLine();
				crearDirectorio(ruta, nombre);
				break;
			case 2:
				System.out.println("Dime la ruta del directorio");
				ruta=scanner.nextLine();
				listarDirectorio(ruta);
				break;
			case 3:
				System.out.println("Dime la ruta del directorio a copiar");
				String rutaOrigen=scanner.nextLine();
				System.out.println("Dime la ruta del directorio donde "
						+ "quieres copiar el archivo");
				String rutaDestino=scanner.nextLine();
				copiarArchivo(rutaOrigen, rutaDestino);
				break;
			case 4:
				System.out.println("Dime la ruta del directorio a mover");
				rutaOrigen=scanner.nextLine();
				System.out.println("Dime la ruta del directorio donde "
						+ "quieres mover el archivo");
				rutaDestino=scanner.nextLine();
				moverArchivo(rutaOrigen, rutaDestino);
				break;
			case 5:
				System.out.println("Dime la ruta del directorio/fichero "
						+ "a borrar");
				ruta=scanner.nextLine();
				borrarArchivo(ruta);
				break;
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		int numeroElegido=0;
		while(numeroElegido!=6) {
			System.out.println("1. Crear un directorio");
			System.out.println("2. Listar directorio");
			System.out.println("3. Copiar archivo");
			System.out.println("4. Mover archivo");
			System.out.println("5. Eliminar fichero/directorio");
			System.out.println("6. Salir");
			System.out.println("Elige una opcion");
			numeroElegido=scanner.nextInt();
			menu(numeroElegido);
			
		}
	}
}