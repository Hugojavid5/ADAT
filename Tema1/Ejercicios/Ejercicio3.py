##Modifica el programa anterior de forma que la lista de 10 números sean números impares.
##(hay que asegurarse de que lo que se introduce en la lista son números).
numeros = []
for i in range(10):
    numero = int(input(f"Ingrese el numero impar {i+1}: "))
    if numero %2 !=0:
        numeros.append(numero)
    else:
        print("El numero no es impar.Introduce uno impar")
print("Lista de numeros impares:",numeros)
suma=sum(numeros)
print("el sumatorio es:",suma)
med=suma/len(numeros)
print("la media de la lista es:",med)