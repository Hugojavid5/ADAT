import random
import os
def rnd_word(archivo1, archivo2):
    palabrasSeleccionadas=[]
    ar1=open(archivo1,'r')
    lineas = ar1.readlines()

    for linea in lineas:
        palabras = linea.split()
        if palabras:
            palabra_aleatoria = random.choice(palabras)
            palabrasSeleccionadas.append(palabra_aleatoria)
    ar1.close()

    ar2=open(archivo2,'w')
    for palabra in palabrasSeleccionadas:
        ar2.write(palabra+"\n")
    ar2.close()
ruta_actual = os.path.dirname(os.path.abspath(__file__))
rnd_word(os.path.join(ruta_actual, "archivo1.txt"), os.path.join(ruta_actual, "archivo2.txt"))