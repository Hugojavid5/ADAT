
import os
from Ejercicio4_1 import Libro

def leerLibros():
    libros = []
    try:
        ruta_actual = os.path.dirname(os.path.abspath(__file__))
        with open(os.path.join(ruta_actual, "libros.txt"), "r") as archivo:
            for linea in archivo:
                linea = linea.strip()
                if not linea:
                    continue
                try:
                    libro = eval(linea)
                    libros.append(libro)
                except Exception as e:
                    print(f"Error al procesar la línea: {linea}. Detalles del error: {e}")
    except IOError:
        print("Ocurrió un error al intentar leer el archivo.")
    return libros
def libroMayorDescuento(libros):
    if not libros:
        print("No hay libros en la base de datos.")
        return
    
    libro_max_descuento = None
    porMayor = 0

    for libro in libros:
        precio = libro.get_precio()
        precio_desc = libro.get_precioDec()
        if precio > 0:
            descuento = (precio - precio_desc) / precio * 100
            if descuento > porMayor:
                porMayor = descuento
                libro_max_descuento = libro

    if libro_max_descuento:
        print(f"El libro con el mayor descuento es: {libro_max_descuento.get_nombre()} con un {porMayor:.2f}% de descuento.")
    else:
        print("No se encontró un libro con descuento.")

libros=leerLibros()
libroMayorDescuento(libros)