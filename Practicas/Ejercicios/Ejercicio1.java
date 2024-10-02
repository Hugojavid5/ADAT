package Practicas.Ejercicios;

import java.util.Scanner;

import javax.tools.OptionChecker;

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
                crearDirectorio(null, null);
            }
            else 
                if(opcion ==2)
                {
                    System.out.println("Listar un directorio");
                    listarDirectorio();
                }
                else 
                    if(opcion==3)
                    {
                        System.out.println("Copiar un archivo");
                        copiarArchivo();
                    }
                    else
                        if(opcion==4)
                        {
                            System.out.println("Mover un archivo");
                            moverArchivo();
                        }
                        else 
                            if(opcion==5)
                            {
                                System.out.println("Eliminar un archivo/directorio");
                                eliminarArchivoDirectorio();
                            }
                            else 
                                if(opcion==6)
                                {
                                    System.out.println("Saliendo del programa");
                                }
        }
    }

    public void crearDirectorio(String ruta,String nombreFichero)
    {

    }

    public void listarDirectorio()
    {
        
    }

    public void copiarArchivo()
    {
        
    }

    public void moverArchivo()
    {
        
    }

    public void eliminarArchivoDirectorio()
    {

    }


    public static void main(String[] args) {
        Ejercicio1 ej1 = new Ejercicio1();
        ej1.mostrar_menu();
    }

}