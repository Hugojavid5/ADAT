##Escribe un programa que imprima solo
##las palabras que tienen más de 10 letras
palabras = [
    "extraordinario", 
    "casa", 
    "impresionante", 
    "carro", 
    "carreterasonda", 
    "sencillo", 
    "espectaculares", 
    "computadoras", 
    "soledad", 
    "genialidad"
]
for palabra in palabras:
    if len(palabra) > 10:
        print(palabra)

##Escribe un programa que pase números decimales a binarios.
def decimal_a_binario(decimal):
    return bin(decimal)[2:]
decimal = int(input("Escribe un número decimal: "))
binario = decimal_a_binario(decimal)
print("El numero decimal",decimal ,"en binario es:" ,binario)

##Escribe un programa que añada espacios antes de la palabra, el número
##de espacios deberá ser el doble del tamaño de la palabra.
pal=str(input("escribe una palabra:"))
def poner_espacios(pal):
    espacios = len(pal) *2
    return ' ' * espacios + pal
print(poner_espacios(pal))

##Escribe un programa que devuelva True cuando una palabra no contenga ‘a’ y false en caso contrario.
def ver_no_tiene_a(pal):
    return 'a' not in pal
pal=str(input("Escribe una palabra:"))
result= ver_no_tiene_a(pal)
print(result)

##Escribe un programa que pida por pantalla un texto y calcule el porcentaje de palabras que no tienen a.
# Función que calcula el porcentaje de palabras sin la letra 'a'
def porcent(texto):
    contador=0
    for i in range(len(texto)):
        if texto[i] == 'a':
            contador=contador+1
    porcent=contador*100/len(texto)
    porcent=100-porcent
    return porcent
texto=str(input("Escribe un texto:"))
resul=porcent(texto)
print(resul)


##Escribe un programa que busque el índice de una letra en una palabra y si
##no contiene la letra devuelva -1. (sin utilizar la función find y utilizando la
##función find)

    ##sin utilizar find
def buscar_indice_sin_find(palabra, letra):
    for i in range(len(palabra)):
        if palabra[i] == letra:
            return i +1
    return -1
palabra = input("Ingrese una palabra:")
letra = input("Ingrese una letra a buscar:")  
indice = buscar_indice_sin_find(palabra, letra)
print("el indice es:",buscar_indice_sin_find)

    ##utilizando find
palabra = input("Ingrese una palabra:")
letra = input("Ingrese una letra a buscar:")  
buscInd=palabra.find(letra)
print("el indice es:",buscInd)

##Crea un diccionario que contenga los caracteres ascii y su correspondiente valor numérico
dict_ascii={}
for i in range(100):
    dict_ascii[i]=chr(i)        
    print(dict_ascii)