numeros = []
for i in range(10):
    numero = int(input(f"Ingrese el numero {i+1}: "))
    numeros.append(numero)
print(numeros)

suma=sum(numeros)
print("el sumatorio es:",suma)
med=suma/len(numeros)
print("la media de la lista es:",med)