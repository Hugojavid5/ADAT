import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


class Ejercicio1 {

private Scanner scanner = new Scanner(System.in);
    public void mostrar_menu()
    {
        int opcion=0;
        while(opcion!=6)
        {
            System.out.println("Elige una opcion(1-6):");
            System.out.println("1-Crea un directorio");
            System.out.println("2-Listar un directorio");
            System.out.println("3-Copiar un archivo");
            System.out.println("4-Mover un archivo");
            System.out.println("5-Eliminar un archivo/directorio");
            System.out.println("6-Salir");
            opcion = scanner.nextInt(); 

            //Validamos la opcion y leemos la opcion
            while (opcion<1 || opcion>6) 
            {
                System.out.println("Opcion no valida");
                System.out.println("Elige una opcion(1-6):");
                opcion = scanner.nextInt();
            }
            if(opcion==1)
            {
                System.out.println("Crea un directorio");
                crearDirectorio("/ADAT/Practicas", "DirectorioPrueba");
            }
            else
                if(opcion ==2)
                {
                    System.out.println("Listar un directorio");
                    listarDirectorio("/ADAT/Practicas/DirectorioPrueba");
                }
                else 
                    if(opcion==3)
                    {
                        System.out.println("Copiar un archivo");
                        copiarArchivo("/Users/Hugo/Documents/S.docx","/ADAT/Practicas/");
                    }
                    else
                        if(opcion==4)
                        {
                            System.out.println("Mover un archivo");
                            moverArchivo("/Users/Hugo/Documents/S.docx","/ADAT/Practicas/DirectorioPrueba");
                        }
                        else 
                            if(opcion==5)
                            {
                                System.out.println("Eliminar un archivo/directorio");
                                eliminarArchivoDirectorio("/ADAT/Practicas/DirectorioPrueba/S.docx");
                            }
                            else 
                                if(opcion==6)
                                {
                                    System.out.println("Saliendo del programa");
                                }
        }
    }

    public void crearDirectorio(String ruta,String nomDir)
    {
        File nuevoDirectorio = new File(ruta + File.separator + nomDir);


        if (!nuevoDirectorio.exists()) {
            // Intentar crear el directorio
            boolean creado = nuevoDirectorio.mkdirs();
            if (creado) {
                System.out.println("Directorio creado exitosamente: " + nuevoDirectorio.getAbsolutePath());
            } else {
                System.out.println("No se pudo crear el directorio.");
            }
        } else {
            System.out.println("El directorio ya existe.");
        }
    }

    public void listarDirectorio(String ruta) {
        File directorio = new File(ruta);

        if (directorio.exists() && directorio.isDirectory()) {
            File[] archivos = directorio.listFiles();
            if (archivos != null && archivos.length > 0) {
                System.out.println("Contenido del directorio:");
                for (File archivo : archivos) {
                    if (archivo.isDirectory()) {
                        System.out.println("[DIRECTORIO] " + archivo.getName());
                    } else if (archivo.isFile()) {
                        System.out.println("[ARCHIVO] " + archivo.getName());
                    }
                }
            } else {
                System.out.println("El directorio está vacío.");
            }
        } else {
            System.out.println("El directorio no existe o no es un directorio válido.");
        }
    }

    public void copiarArchivo(String rutaOri,String rutaDes) 
    {
    File archivoOrigen = new File(rutaOri);
    if (!archivoOrigen.exists() || !archivoOrigen.isFile()) {
        System.out.println("El archivo de origen no existe o no es un archivo válido.");
        return;
    }

    // Creamos el nuevo archivo en el directorio de destino
    File archivoDestino = new File(rutaDes, archivoOrigen.getName());

    try {
        // Copiamos el archivo
        Files.copy(archivoOrigen.toPath(), archivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Archivo copiado exitosamente a: " + archivoDestino.getAbsolutePath());
    } catch (IOException e) {
        System.out.println("Error al copiar el archivo: " + e.getMessage());
    }
}

    public void moverArchivo(String rutaArMov,String rutaDir)
    {    
        File archivoOrigen = new File(rutaArMov);
        if (!archivoOrigen.exists() || !archivoOrigen.isFile()) {
            System.out.println("El archivo de origen no existe o no es un archivo válido.");
            return;
        }
        // Creamos el nuevo archivo en el directorio de destino
        File archivoDestino = new File(rutaDir, archivoOrigen.getName());
        try {
            // Movemos el archivo
            Files.move(archivoOrigen.toPath(), archivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo movido exitosamente a: " + archivoDestino.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al mover el archivo: " + e.getMessage());
        }
    }

    public void eliminarArchivoDirectorio(String ruta)
    {
        File file = new File(ruta);

        if (file.exists()) {
            if (file.isDirectory()) {
                // Si es un directorio, comprobamos si está vacío
                String[] archivos = file.list();
                if (archivos != null && archivos.length > 0) {
                    System.out.println("El directorio no está vacío. No se puede eliminar.");
                } else {
                    // Eliminar directorio vacío
                    if (file.delete()) {
                        System.out.println("Directorio eliminado: " + file.getAbsolutePath());
                    } else {
                        System.out.println("No se pudo eliminar el directorio.");
                    }
                }
            } else {
                // Si es un archivo, eliminarlo directamente
                if (file.delete()) {
                    System.out.println("Archivo eliminado: " + file.getAbsolutePath());
                } else {
                    System.out.println("No se pudo eliminar el archivo.");
                }
            }
        } else {
            System.out.println("El archivo o directorio no existe.");
        }
    }


    public static void main(String[] args) {
        Ejercicio1 ej1 = new Ejercicio1();
        ej1.mostrar_menu();
    }

}