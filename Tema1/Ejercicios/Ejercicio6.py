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