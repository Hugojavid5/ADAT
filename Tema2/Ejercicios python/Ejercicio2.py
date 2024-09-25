import random

def palabra_aleatoria(frase):
    palabras=frase.split()
    if len(palabras) < 2:
        return "La frase no tiene suficientes palabras."
    palabrasRandom= random.sample(palabras, 2)

    palabra1=palabrasRandom[0]
    palabra2=palabrasRandom[1]    
    
    tamPal=len(palabra1)+len(palabra2)

    contPalabra1=0
    contPalabra2=0
    palNueva=""
    for i in range(tamPal):
        numRandom = random.randint(1, 2)
        if((numRandom==1 and contPalabra1<len(palabra1)) 
        or (numRandom==2 and contPalabra2>=len(palabra2) 
        and contPalabra1<=len(palabra1))):
            palNueva+=palabra1[contPalabra1]
            contPalabra1+=1

        elif(
            (numRandom==2 and contPalabra2<len(palabra2)) 
            or (numRandom==1 and contPalabra1>=len(palabra1) 
            and contPalabra2<=len(palabra2))
            ):
            palNueva+=palabra2[contPalabra2]
            contPalabra2+=1

    return palNueva

frase= str(input("Introduce un texto \n"))
palabra=palabra_aleatoria(frase)
print(palabra)