import random

class Persona:
    #Declaracion de constantes
    PESO_BAJO = -1
    PESO_IDEAL = 0
    SOBREPESO = 1
    HOMBRE = 'H'
    MUJER = 'M'
    
    def __init__(self, nombre='', edad=0, sexo=HOMBRE, peso=0.0, altura=0.0):
        self.__nombre = nombre
        self.__edad = edad
        self.__dni = self.__genera_dni()  # El DNI se genera al construir el objeto
        self.__sexo = sexo
        self.__peso = peso
        self.__altura = altura

    # Método privado para generar el DNI
    def __genera_dni(self):
        numero = random.randint(10000000, 99999999)
        letra = self.__calcula_letra_dni(numero)
        return f"{numero}{letra}"

    # Método privado para calcular la letra del DNI basado en un número
    def __calcula_letra_dni(self, numero):
        letras = "TRWAGMYFPDXBNJZSQVHLCKE"
        return letras[numero % 23]

    # Método para calcular el IMC
    def calcularIMC(self):
        if self.__altura > 0:
            imc = self.__peso / (self.__altura ** 2)
            if imc < 20:
                return self.PESO_BAJO
            elif 20 <= imc <= 25:
                return self.PESO_IDEAL
            else:
                return self.SOBREPESO
        else:
            return None  # Altura no válida

    # Método para verificar si es mayor de edad
    def esMayorDeEdad(self):
        return self.__edad >= 18

    # Método para devolver la información completa del objeto
    def toString(self):
        imc_estado = self.calcularIMC()
        if imc_estado == self.PESO_BAJO:
            estado_peso = "Bajo peso"
        elif imc_estado == self.PESO_IDEAL:
            estado_peso = "Peso ideal"
        elif imc_estado == self.SOBREPESO:
            estado_peso = "Sobrepeso"
        else:
            estado_peso = "Desconocido"

        return (f"Nombre: {self.__nombre}, Edad: {self.__edad}, DNI: {self.__dni}, "
                f"Sexo: {self.__sexo}, Peso: {self.__peso}kg, Altura: {self.__altura}m, "
                f"Estado del peso: {estado_peso}, Mayor de edad: {'Sí' if self.esMayorDeEdad() else 'No'}")

    # Métodos set de cada atributo excepto el DNI
    def set_nombre(self, nombre):
        self.__nombre = nombre

    def set_edad(self, edad):
        self.__edad = edad

    def set_sexo(self, sexo):
        if sexo in (self.HOMBRE, self.MUJER):
            self.__sexo = sexo
        else:
            raise ValueError("Sexo inválido")

    def set_peso(self, peso):
        self.__peso = peso

    def set_altura(self, altura):
        self.__altura = altura
#Creamos un objeto y lo probamos
persona = Persona(nombre='Ana', edad=22, sexo='M', peso=60, altura=1.65)
print(persona.toString())