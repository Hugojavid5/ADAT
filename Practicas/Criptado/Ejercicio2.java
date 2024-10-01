package Practicas.Criptado;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;

public class Ejercicio2 {

    // Método para generar una clave secreta AES
    public static SecretKey generateSecretKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256); // Longitud de clave AES (128, 192 o 256 bits)
        return keyGenerator.generateKey();
    }

    // Método para generar un IV (Initialization Vector)
    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16]; // Tamaño de bloque de AES = 16 bytes
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv); // Generar valores aleatorios
        return new IvParameterSpec(iv);
    }

    // Método para cifrar un fichero
    public static void encryptFile(String algorithm, SecretKey key, IvParameterSpec iv, File inputFile, File outputFile) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        FileInputStream inputStream = new FileInputStream(inputFile);
        byte[] inputBytes = new byte[(int) inputFile.length()];
        inputStream.read(inputBytes);

        byte[] outputBytes = cipher.doFinal(inputBytes);

        FileOutputStream outputStream = new FileOutputStream(outputFile);
        outputStream.write(outputBytes);

        inputStream.close();
        outputStream.close();
    }

    // Método para descifrar un fichero
    public static void decryptFile(String algorithm, SecretKey key, IvParameterSpec iv, File encryptedFile, File decryptedFile) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);

        FileInputStream inputStream = new FileInputStream(encryptedFile);
        byte[] inputBytes = new byte[(int) encryptedFile.length()];
        inputStream.read(inputBytes);

        byte[] outputBytes = cipher.doFinal(inputBytes);

        FileOutputStream outputStream = new FileOutputStream(decryptedFile);
        outputStream.write(outputBytes);

        inputStream.close();
        outputStream.close();
    }

    public static void main(String[] args) {
        try {
            // Generar clave secreta y IV
            SecretKey key = generateSecretKey();
            IvParameterSpec iv = generateIv();

            // Ficheros de entrada y salida
            File inputFile = new File("prueba.bin"); // Fichero binario original
            File encryptedFile = new File("prueba_encrypted.bin"); // Fichero cifrado
            File decryptedFile = new File("prueba_decrypted.bin"); // Fichero descifrado

            // Cifrar el fichero
            encryptFile("AES/CBC/PKCS5Padding", key, iv, inputFile, encryptedFile);
            System.out.println("Fichero cifrado correctamente.");

            // Descifrar el fichero
            decryptFile("AES/CBC/PKCS5Padding", key, iv, encryptedFile, decryptedFile);
            System.out.println("Fichero descifrado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
