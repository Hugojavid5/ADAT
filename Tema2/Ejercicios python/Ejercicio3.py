import random
import pickle

def generar_lista_aleatoria(tamaño, rango_min, rango_max):
    return [random.uniform(rango_min, rango_max) for _ in range(tamaño)]

def guardar_lista_en_archivo(lista, nombre_archivo):
    with open(nombre_archivo, 'wb') as archivo:
        pickle.dump(lista, archivo)

if __name__ == "__main__":
    tamaño_lista = 1000
    rango_min = -100
    rango_max = 100

    lista1 = generar_lista_aleatoria(tamaño_lista, rango_min, rango_max)
    lista2 = generar_lista_aleatoria(tamaño_lista, rango_min, rango_max)
    guardar_lista_en_archivo(lista1, 'lista1.pkl')
    guardar_lista_en_archivo(lista2, 'lista2.pkl')

    print("Las listas han sido guardadas en los archivos 'lista1.pkl' y 'lista2.pkl'.")
