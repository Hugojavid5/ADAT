import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class Ejercicio4{
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		System.out.println("1. Crear fichero serializable de olimpiadas");
		System.out.println("2. Añadir edición olímpica");
		System.out.println("3. Buscar olimpiadas por sede");
		System.out.println("4. Eliminar edición olímpica");
		int opcion=input.nextInt();
		input.nextLine();
		switch(opcion) {
			case 1:
				crearFichero();
				break;
			case 2:
				System.out.println("Dime el año");
				int anio=input.nextInt();
				input.nextLine();
				System.out.println("Dime la temporada");
				String temp=input.nextLine();
				System.out.println("Dime la ciudad");
				String ciudad=input.nextLine();
				aniadirEdicionOlimpica(anio,temp,ciudad);
				break;
			case 3:
				System.out.println("Dime la sede de las olimpiadas");
				String sede=input.nextLine();
				buscarOlimpiadas(sede);
				break;
			case 4:
				System.out.println("Dime el año de la edicion");
				anio=input.nextInt();
				input.nextLine();
				do {
					System.out.println("Dime la temporada:\n1. Summer\n2."
							+ " Winter");
					temp=input.nextLine();
				}while(!temp.equals("1")&&!temp.equals("2"));
				if(temp.equals("1")) {
					temp="\"Summer\"";
				}
				else{
					temp="\"Winter\"";
				}
				eliminarEdicionOlimpica(anio,temp);
				break;
			default:
				break;
		}
	}
	private static void eliminarEdicionOlimpica(int anio, String temp) {
		boolean borrado=false;
		ArrayList<Olimpiada> lstOlimpiadas=crearLstOlimpiadas();
		Iterator<Olimpiada> it=lstOlimpiadas.iterator();
		while(it.hasNext()){
			Olimpiada ol=it.next();
			if(ol.anio==anio&&ol.temporada.equals(temp)) {
				it.remove();
				borrado=true;
			}
		}
		if(!borrado) {
			System.out.println("No se ha encontrado ninguna edicion de ese año y "
					+ "esa temporada");
		}else {
			try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(
					"olimpiadas.bin"))){
				Iterator<Olimpiada> it2=lstOlimpiadas.iterator();
				while(it2.hasNext()) {
					oos.writeObject(it2.next());
				}
				oos.writeObject(null);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private static void buscarOlimpiadas(String sede) {
		ArrayList<Olimpiada> lstOlimpiadas=crearLstOlimpiadas();
		for(Olimpiada ol:lstOlimpiadas) {
			if(ol.ciudad.contains(sede)) {
				System.out.println(ol);
			}
		}
	}
	private static void aniadirEdicionOlimpica(int anio, String temp, String ciudad) {
		ArrayList<Olimpiada> lstOlimpiadas = crearLstOlimpiadas();
		lstOlimpiadas.add(new Olimpiada(anio, anio+" "+temp, temp, ciudad));
		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("olimpiadas.bin"))){
			Iterator<Olimpiada> it=lstOlimpiadas.iterator();
			while(it.hasNext()) {
				oos.writeObject(it.next());
			}
			oos.writeObject(null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static ArrayList<Olimpiada> crearLstOlimpiadas() {
		ArrayList<Olimpiada> lstOlimpiadas=new ArrayList<Olimpiada>();
		try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream("olimpiadas.bin"))){
			Olimpiada leida=(Olimpiada) ois.readObject();
			while(leida!=null) {
				lstOlimpiadas.add(leida);
				leida=(Olimpiada) ois.readObject();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return lstOlimpiadas;
	}

	private static void crearFichero() {
		 
		 try {
			 XMLReader procesadorXML=XMLReaderFactory.createXMLReader();
			 GestorContenido gestor=new GestorContenido();
			 procesadorXML.setContentHandler(gestor);
			 InputSource fileXML=new InputSource("olimpiadas.xml");
			procesadorXML.parse(fileXML);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

}
class Olimpiada implements Serializable{
	int anio;
	String juegos;
	String temporada;
	String ciudad;
	Olimpiada(int anio,String juegos,String temporada,String ciudad){
		this.anio=anio;
		this.juegos=juegos;
		this.temporada=temporada;
		this.ciudad=ciudad;
	}
	@Override
	public String toString() {
		return "Año: "+anio+", Juegos: "+juegos+", Temporada: "+temporada+", Ciudad: "+ciudad;
	}
}
class GestorContenido extends DefaultHandler {
	ArrayList<Olimpiada> lstOlimpiadas=new ArrayList<Olimpiada>();
	int anio;
	String juegos;
	String temporada;
	String ciudad;
	boolean esJuegos=false;
	boolean esTemporada=false;
	boolean esCiudad=false;
	int cont=0;
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(qName.equals("Olimpiada")) {
			cont++;
			try {
				anio=Integer.parseInt(attributes.getValue("year"));
			}catch(NumberFormatException e) {
				System.out.println(cont);
			}
			
		}
		if(qName.equals("juegos")) {
			esJuegos=true;
		}
		if(qName.equals("temporada")) {
			esTemporada=true;
		}
		if(qName.equals("ciudad")) {
			esCiudad=true;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(esJuegos) {
			juegos=new String(ch, start, length).trim();
			esJuegos=false;
		}
		if(esTemporada) {
			temporada=new String(ch, start, length).trim();
			esTemporada=false;
		}
		if(esCiudad) {
			ciudad=new String(ch, start, length).trim();
			esCiudad=false;
		}
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equals("Olimpiada")) {
			lstOlimpiadas.add(new Olimpiada(anio, juegos, temporada, ciudad));
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("olimpiadas.bin"))){
			Iterator<Olimpiada> it=lstOlimpiadas.iterator();
			while(it.hasNext()) {
				oos.writeObject(it.next());
			}
			oos.writeObject(null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}