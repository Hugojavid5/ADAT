'''
Crea una clase Criptográfo que contenga dos métodos: encriptar y desencriptar. Los dos
métodos recibirán un texto y devolverán otro texto. El funcionamiento de los métodos es el
siguiente:
◦ encriptar(txt): El texto recibido se encriptará sustituyendo cada uno de los
caracteres por el siguiente caracter según su valor ASCII.
◦ desencriptar(txt): Realizará la acción inversa al metodo anterior, es decir sustituirá
cada carácter por el anterior según su valor ASCII.
Nota: Para realizar este ejercicio son muy útiles las funciones ord() y chr().
'''
class Criptografo:
    def encriptar(self, txt):
        return ''.join(chr(ord(char) + 1) for char in txt)
    
    def desencriptar(self, txt):
        return ''.join(chr(ord(char) - 1) for char in txt)
criptografo = Criptografo()

texto_original = "este es el ejercicio 6 de python"
texto_encriptado = criptografo.encriptar(texto_original)
texto_desencriptado = criptografo.desencriptar(texto_encriptado)

print("El texto original es:", texto_original)
print("El texto ecriptado es:", texto_encriptado)
print("El texto desencriptado es:", texto_desencriptado)