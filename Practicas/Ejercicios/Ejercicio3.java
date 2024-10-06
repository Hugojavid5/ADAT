import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.Objects;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
public class Ejercicio3 {
	
	private static void crearXmlOlimpiadas() {
		try(BufferedReader br=new BufferedReader(new FileReader("olimpiadas.csv"))){
			DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
	        Document doc=docBuilder.newDocument();
	        Element rootElement=doc.createElement("Olimpiadas");
            doc.appendChild(rootElement);
            String line;
            String[] headers=null;
            int lineNumber=0;
            while ((line=br.readLine())!=null) {
                String[] values=line.split(",");
                if (lineNumber==0) {
                    headers=values;
                    for(int i=0;i<headers.length;i++) {
                    	if(headers[i].equals("\"Games\"")) {
                    		headers[i]="juegos";
                    	}else {
                    		if(headers[i].equals("\"Season\"")) {
                    			headers[i]="temporada";
                    		}else {
                    			if(headers[i].equals("\"City\"")) {
                    				headers[i]="ciudad";
                    			}
                    		}
                    	}
                    }
                } else {
                	Element rowElement=doc.createElement("Olimpiada");
                	for (int i=0;i<values.length;i++) {
                		Element columnElement=null;
                		if(headers[i].equals("\"Year\"")) {
                			 rowElement.setAttribute("year", values[i]);
                		}else {
	                		if(headers[i].charAt(0)=='\"') {
	                			 columnElement=doc.createElement(headers[i].
	                					 substring(1,headers[i].length()-1));
	                		}else {
	                			 columnElement=doc.createElement(headers[i]);
	                		}
	                		columnElement.appendChild(doc.createTextNode(
	                				values[i]));
               			 	rowElement.appendChild(columnElement);
                		}
                        
                    }
                	rootElement.appendChild(rowElement);
                }
                lineNumber++;
            }
        TransformerFactory transformerFactory=TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new 
        		File("olimpiadas.xml"));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	private static void crearXmlDeportistas() {
		ArrayList<Deportista> lstDeportistas=new ArrayList<Deportista>();
		try (BufferedReader br = new BufferedReader(new FileReader(
				"athlete_events.csv"))) {
			String linea;
            String leido = br.readLine();
            String[] values=null;
            if (leido!=null) {
            	int indiceID=-1;
            	int indiceName=-1;
            	int indiceSex=-1;
            	int indiceHeight=-1;
            	int indiceWeight=-1;
            	int indiceSport=-1;
            	int indiceAge=-1;
            	int indiceNOC=-1;
            	int indiceTeam=-1;
            	int indiceGames=-1;
            	int indiceCity=-1;
            	int indiceEvent=-1;
            	int indiceMedal=-1;
            	values=leido.split(",");
            	for(int i=0;i<values.length;i++) {
            		String valor=values[i];
            		if(valor.equals("\"ID\"")) {
            			indiceID=i;
            		}
            		if(valor.equals("\"Name\"")) {
            			indiceName=i;
            		}
            		if(valor.equals("\"Sex\"")) {
            			indiceSex=i;
            		}
            		if(valor.equals("\"Height\"")) {
            			indiceHeight=i;
            		}
            		if(valor.equals("\"Weight\"")) {
            			indiceWeight=i;
            		}
            		if(valor.equals("\"Sport\"")) {
            			indiceSport=i;
            		}
            		if(valor.equals("\"Age\"")) {
            			indiceAge=i;
            		}
            		if(valor.equals("\"NOC\"")) {
            			indiceNOC=i;
            		}
            		if(valor.equals("\"Team\"")) {
            			indiceTeam=i;
            		}
            		if(valor.equals("\"Games\"")) {
            			indiceGames=i;
            		}
            		if(valor.equals("\"City\"")) {
            			indiceCity=i;
            		}
            		if(valor.equals("\"Event\"")) {
            			indiceEvent=i;
            		}
            		if(valor.equals("\"Medal\"")) {
            			indiceMedal=i;
            		}
            	}
            	while ((linea=br.readLine()) != null) {
            		String[] values2=linea.split(",");
            		for(int i=0;i<values2.length;i++) {
            			if(values2[i].contains("\"")) {
            				values2[i]=values2[i].substring(1,values2[i].length()-1);
            			}
            		}
            		int id=Integer.parseInt(values2[indiceID]);
            		String nombre=values2[indiceName];
            		char sexo=values2[indiceSex].charAt(0);
            		if(values2[indiceHeight].equals("NA")) {
            			values2[indiceHeight]="-1";
            		}
            		int altura=Integer.parseInt(values2[indiceHeight]);
            		if(values2[indiceWeight].equals("NA")) {
            			values2[indiceWeight]="-1";
            		}
            		float peso=Float.parseFloat(values2[indiceWeight]);
            		String deporte=values2[indiceSport];
            		if(values2[indiceAge].equals("NA")) {
            			values2[indiceAge]="-1";
            		}
            		int edad=Integer.parseInt(values2[indiceAge]);
            		String noc=values2[indiceNOC];
            		String equipo=values2[indiceTeam];
            		String juegos=values2[indiceGames];
            		String ciudad=values2[indiceCity];
            		String evento=values2[indiceEvent];
            		String medalla=values2[indiceMedal];
            		Deportista dep=new Deportista(id, nombre, sexo, altura, peso);
            		if(!lstDeportistas.contains(dep)) {
            			lstDeportistas.add(dep);
            		}else {
            			dep=lstDeportistas.get(lstDeportistas.indexOf(dep));
            		}
        			if(!dep.mapaParticipacion.containsKey(deporte)) {
        				dep.creaMapa(deporte);
        			}
        			dep.aniadirElemento(deporte, new Participacion(edad, noc, equipo, juegos, ciudad, evento, medalla));
            	}
            }
            for(int i=0;i<values.length;i++) {
            	if(values[i].charAt(0)=='\"') {
            		values[i]=values[i].substring(1,values[i].length()-1);
            	}
            }
            DocumentBuilderFactory docFactory=DocumentBuilderFactory.
            		newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();
			Document doc=docBuilder.newDocument();
			Element deportistas=doc.createElement("deportistas");
			doc.appendChild(deportistas);
			Iterator<Deportista> it=lstDeportistas.iterator();
			while (it.hasNext()){
				Deportista dep=it.next();
				Element deportista=doc.createElement("deportista");
				deportista.setAttribute("id", dep.id+"");
				aniadirElemento(doc, deportista, "nombre", dep.nombre);
				aniadirElemento(doc, deportista, "sexo", dep.sexo+"");
				aniadirElemento(doc, deportista, "altura", dep.altura+"");
				aniadirElemento(doc, deportista, "peso", dep.peso+"");
				
				for(Entry<String, ArrayList<Participacion>> partic:dep.mapaParticipacion.entrySet()) {
					Element deporte=doc.createElement("deporte");
					deporte.setAttribute("nombre", partic.getKey());
					aniadirParticipacion(dep, doc, deporte,partic.getKey());
					deportista.appendChild(deporte);
				}
  			 	deportistas.appendChild(deportista);
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(new File("Datos_Olimpiadas/deportisas.xml"));
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.transform(source, result);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	private static void aniadirParticipacion(Deportista dep, Document doc, Element deporte, String particKey) {
		 Iterator<Participacion> iterator = dep.mapaParticipacion.get(particKey).iterator();
		while(iterator.hasNext()) {
			Participacion par=iterator.next();
			Element participacion=doc.createElement("participacion");
			participacion.setAttribute("age",par.edad+"");
			Element equipo=doc.createElement("equipo");
			equipo.setAttribute("abbr", par.noc);
			equipo.setTextContent(par.equipo);
			participacion.appendChild(equipo);
			aniadirElemento(doc, participacion, "juegos", par.juegos+"-"+par.ciudad);
			aniadirElemento(doc, participacion, "evento", par.evento);
			aniadirElemento(doc, participacion, "medalla",par.medalla);
			deporte.appendChild(participacion);
		}
	}
	
	private static void aniadirElemento(Document doc, Element rowElement, String header, String texto) {
		Element elemento=doc.createElement(header);
		elemento.appendChild(doc.createTextNode(texto));
		rowElement.appendChild(elemento);
	}
	
	private static void mostrarListado() {
		 try {
			 XMLReader procesadorXML=XMLReaderFactory.createXMLReader();
			 contenido gestor=new contenido();
			 procesadorXML.setContentHandler(gestor);
			 InputSource fileXML=new InputSource("Datos_Olimpiadas/olimpiadas.xml");
			 procesadorXML.parse(fileXML);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		System.out.println("1. Crear fichero XML de olimpiadas");
		System.out.println("2. Crear un fichero XML de deportistas");
		System.out.println("3. Listado de olimpiadas");
		int opcion=input.nextInt();
		input.nextLine();
		switch (opcion) {
		case 1:
			crearXmlOlimpiadas();
			break;
		case 2:
			crearXmlDeportistas();
			break;
		case 3:
			mostrarListado();
			break;
		}
		input.close();
	}
}
class Participacion{
	int edad;
	String noc;
	String equipo;
	String juegos;
	String ciudad;
	String evento;
	String medalla;
	public Participacion(int edad,String noc,String equipo,String juegos,String ciudad,String evento,String medalla) {
		this.edad=edad;
		this.noc=noc;
		this.equipo=equipo;
		this.juegos=juegos;
		this.ciudad=ciudad;
		this.evento=evento;
		this.medalla=medalla;
	}
}

class Deportista {
	int id;
	String nombre;
	char sexo;
	int altura;
	float peso;
	HashMap<String, ArrayList<Participacion>> mapaParticipacion;
	
	public Deportista(int id,String nombre,char sexo,int altura,float peso) {
		this.id=id;
		this.nombre=nombre;
		this.sexo=sexo;
		this.altura=altura;
		this.peso=peso;
		this.mapaParticipacion=new HashMap<String, ArrayList<Participacion>>();
	}
	void creaMapa(String deporte) {
		this.mapaParticipacion.put(deporte,new ArrayList<Participacion>());
	}
	
	void aniadirElemento(String deporte,Participacion participacion) {
		this.mapaParticipacion.get(deporte).add(participacion);
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deportista other = (Deportista) obj;
		return id == other.id;
	}
}

class contenido extends DefaultHandler{
	String anio="";
	String juego;
	boolean esJuego=false;
	

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		 if (qName.equals("Olimpiada")) {
             anio = attributes.getValue("year");
         }
		 if (qName.equalsIgnoreCase("juegos")) {
             esJuego = true;
         }
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (esJuego) {
            juego = new String(ch, start, length).trim();
            esJuego = false;
        }
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equals("Olimpiada")) {
			System.out.println("Games: " + juego + ", Year: " + anio);
		}
	}
}