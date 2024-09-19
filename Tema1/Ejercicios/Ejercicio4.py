numeros = []
for i in range(10):
    numero = int(input(f"Ingrese el numero impar {i+1}: "))
    if numero %2 !=0:
        numeros.append(numero)
    else:
        print("El numero no es impar.Introduce uno impar")
print("Lista de numeros impares:",numeros)
while True:
    print("¿Qué desea hacer con la lista?")
    print("1. Sumatorio")
    print("2. Media")
    print("3. Máximo")
    print("4. Mínimo")
    print("0. Salir")
    opcion = int(input("Selecciona una opcion:"))

    if opcion==1:
        suma=sum(numeros)   
        print("el sumatorio es:",suma)
    elif opcion ==2:
        med=suma/len(numeros)
        print("la media de la lista es:",med)
    elif opcion==3:
        maximo = max(numeros)
        print("el maximo es:", maximo)
    elif opcion ==4:
        minimo = min(numeros)
        print("el minimo es:", minimo)
    elif opcion ==0:
        print("saliendo del programa")
        break
    else:
        print("Introduce una opcion valida(1-4) o 0 para terminar")
