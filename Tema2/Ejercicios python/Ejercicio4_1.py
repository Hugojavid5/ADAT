import os
class Libro:
    def __init__(self, nombre, autor, precio, precioDec, paginas):
        self.__nombre = nombre
        self.__autor = autor
        self.__precio = precio
        self.__precioDec = precioDec
        self.__paginas = paginas

    def __repr__(self):
        return f"Libro(nombre='{self.__nombre}', autor='{self.__autor}', precio={self.__precio}, precioDec={self.__precioDec}, paginas={self.__paginas})"
    def get_nombre(self):
        return self.__nombre
    def get_autor(self):
        return self.__autor
    def get_precio(self):
        return self.__precio
    def get_precioDec(self):
        return self.__precioDec
    def get_paginas(self):
        return self.__paginas
    def set_nombre(self, nombre):
        self.__nombre = nombre
    def set_autor(self, autor):
        self.__autor = autor
    def set_precio(self, precio):
        self.__precio = precio
    def set_precioDec(self, precioDec):
        self.__precioDec = precioDec
    def set_paginas(self, paginas):
        self.__paginas = paginas
def mostrarMenu():
    try:
        nombre = str(input("Nombre del libro: "))
        autor = str(input("Nombre del autor del libro: "))
        precio = float(input("¿Cual el precio del libro?: "))
        precioDes = float(input("¿Cual el precio con descuento del libro?: "))
        paginas = int(input("¿Cuantas paginas tiene el libro?: "))
        l =Libro(nombre,autor,precio,precioDes,paginas)
        return l
    except ValueError:
        print("Entrada inválida. Por favor, ingresa un valor correcto.")
        return -1

def guardarLibro(l):
    try:
        ruta_actual = os.path.dirname(os.path.abspath(__file__))
        with open(os.path.join(ruta_actual, "biblioteca.txt"), "a") as archivo:
            archivo.write(repr(l) + "\n")
    except IOError:
        print("Ocurrió un error al intentar guardar el libro.")

if __name__ == "__main__":
    respuesta='S'
    while(respuesta!='N'):
        l=mostrarMenu()
        if(l!=-1):
            guardarLibro(l)
            respuesta = str(input("Quiere añadir un nuevo Libro (S/N) ")).upper()
