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
    def get_nombre(self):
        return self.__nombre

def main():
    persona1 = Persona(nombre='Carlos', edad=28, sexo='H', peso=85, altura=1.80)
    persona2 = Persona(nombre='María', edad=17, sexo='M', peso=55, altura=1.60)
    persona3 = Persona(nombre='Luis', edad=40, sexo='H', peso=95, altura=1.75)

    personas = [persona1, persona2, persona3]

    for persona in personas:
        imc_result = persona.calcularIMC()
        if imc_result == Persona.PESO_BAJO:
            print(f"{persona.get_nombre()} está por debajo de su peso ideal.")
        elif imc_result == Persona.PESO_IDEAL:
            print(f"{persona.get_nombre()} está en su peso ideal.")
        elif imc_result == Persona.SOBREPESO:
            print(f"{persona.get_nombre()} tiene sobrepeso.")
        if persona.esMayorDeEdad():
            print(f"{persona.get_nombre()} es mayor de edad.")
        else:
            print(f"{persona.get_nombre()} es menor de edad.")

        print(persona.toString())
        print('-' * 40)
if __name__ == "__main__":
    main()
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
    def get_nombre(self):
        return self.__nombre

def main():
    persona1 = Persona(nombre='Carlos', edad=28, sexo='H', peso=85, altura=1.80)
    persona2 = Persona(nombre='María', edad=17, sexo='M', peso=55, altura=1.60)
    persona3 = Persona(nombre='Luis', edad=40, sexo='H', peso=95, altura=1.75)

    personas = [persona1, persona2, persona3]

    for persona in personas:
        imc_result = persona.calcularIMC()
        if imc_result == Persona.PESO_BAJO:
            print(f"{persona.get_nombre()} está por debajo de su peso ideal.")
        elif imc_result == Persona.PESO_IDEAL:
            print(f"{persona.get_nombre()} está en su peso ideal.")
        elif imc_result == Persona.SOBREPESO:
            print(f"{persona.get_nombre()} tiene sobrepeso.")
        if persona.esMayorDeEdad():
            print(f"{persona.get_nombre()} es mayor de edad.")
        else:
            print(f"{persona.get_nombre()} es menor de edad.")

        print(persona.toString())
        print('-' * 40)
if __name__ == "__main__":
    main()
