'''
Modifica el programa anterior de forma que cada una de las funcionalidades del programa se
ejecute mediante una función.
'''
##Declaracion funciones
def sumatorio(numeros):
    return sum(numeros)

def media(numeros):
    return sum(numeros) / len(numeros)

def maximo(numeros):
    return max(numeros)

def minimo(numeros):
    return min(numeros)

def mostrar_menu():
    print("\n¿Qué desea hacer con la lista?")
    print("1. Sumatorio")
    print("2. Media")
    print("3. Máximo")
    print("4. Mínimo")
    print("0. Salir")
    return int(input("Seleccione una opción: "))

def main():
    numeros = []
    for i in range(10):
        numero = int(input(f"Ingrese el número impar {i+1}: "))
        if numero % 2 != 0:
            numeros.append(numero)
        else:
            print("El número no es impar. Introduce uno impar.")

    print("Lista de números impares:", numeros)
    
    while True:
        opcion = mostrar_menu()
        if opcion == 1:
            suma = sumatorio(numeros)
            print("El sumatorio es:", suma)
        elif opcion == 2:
            med = media(numeros)
            print("La media de la lista es:", med)
        elif opcion == 3:
            maximo_valor = maximo(numeros)
            print("El máximo es:", maximo_valor)
        elif opcion == 4:
            minimo_valor = minimo(numeros)
            print("El mínimo es:", minimo_valor)
        elif opcion == 0:
            print("Saliendo del programa.")
            break
        else:
            print("Introduce una opción válida (1-4) o 0 para terminar.")
main()