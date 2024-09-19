##Modifica el programa anterior, de manera que al terminar de guardar los números en la lista
##se impriman la lista, el sumatorio y la media de todos los número de dicha lista.
numeros = []
for i in range(10):
    numero = int(input(f"Ingrese el numero {i+1}: "))
    numeros.append(numero)
print(numeros)

suma=sum(numeros)
print("el sumatorio es:",suma)
med=suma/len(numeros)
print("la media de la lista es:",med)