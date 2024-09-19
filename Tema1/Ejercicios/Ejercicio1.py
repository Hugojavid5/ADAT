
##Crea un programa en Python que pida al usuario 10 números y los guarde en una lista.
##Imprime la lista
numeros = []
for i in range(10):
    numero = int(input(f"Ingrese el número {i+1}: "))
    numeros.append(numero)
print(numeros)