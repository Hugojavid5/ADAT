import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Ejercicio2 {
	
	private static void generarFicheroCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("athlete_events.csv"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("olimpiadas.csv"))) {
            String linea;
            String leido=br.readLine();
            if (leido!=null) {
            	int juegos=-1;
            	int anio=-1;
            	int temporadda=-1;
            	int city=-1;
            	String[] values=leido.split(",");
            	for(int i=0;i<values.length;i++) {
            		String valor=values[i];
            		if(valor.equals("\"Games\"")) {
            			juegos=i;
            		}
            		if(valor.equals("\"Year\"")) {
            			anio=i;
            		}
            		if(valor.equals("\"Season\"")) {
            			temporadda=i;
            		}
            		if(valor.equals("\"City\"")) {
            			city=i;
            		}
            	}
                bw.write("\"Games\",\"Year\",\"Season\",\"City\"\n");
                while ((linea = br.readLine()) != null) {
                	values=linea.split(",");
                    String games=values[juegos];
                    String year=values[anio];
                    String season=values[temporadda];
                    String citi=values[city];
                    bw.write(games+","+year+","+season+","+citi+"\n");
                }
                System.out.println("Fichero generado");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	private static void mostrarDeportistas(String cadenaABuscar) {
		try (BufferedReader br = new BufferedReader(new FileReader("athlete_events.csv"))) {
			String linea;
            String leido = br.readLine();
            if (leido!=null) {
            	int id=-1;
            	int nombre=-1;
            	int sexo=-1;
            	int edad=-1;
            	int altura=-1;
            	int ancho=-1;
            	int participacion=-1;
            	String[] values=leido.split(",");
            	for(int i=0;i<values.length;i++) {
            		String valor=values[i];
            		if(valor.equals("\"ID\"")) {
            			id=i;
            		}
            		if(valor.equals("\"Name\"")) {
            			nombre=i;
            		}
            		if(valor.equals("\"Sex\"")) {
            			sexo=i;
            		}
            		if(valor.equals("\"Age\"")) {
            			edad=i;
            		}
            		if(valor.equals("\"Height\"")) {
						altura=i;
            		}
            		if(valor.equals("\"Weight\"")) {
            			ancho=i;
            		}
            		if(valor.equals("\"Games\"")) {
            			participacion=i;
            		}
            	}
            	int i=0;
            	while ((linea=br.readLine()) != null) {
            		values=linea.split(",");
            		String nombres=values[nombre];
            		if(nombres.contains(cadenaABuscar)) {
	            		 i++;
	                     String ids=values[id];
	                     String sex=values[sexo];
	                     String eda=values[edad];
	                     String alt=values[altura];
	                     String peso=values[ancho];
	                     String particip=values[participacion];
	                     System.out.println("ID: "+ids+", Nombre: "+nombres+
	                    		 ", Sexo: "+sex+", Edad: "+eda+", Altura: "+
	                    		 alt+", Peso: "+peso+", Participacion: "+
	                    		 particip);
            		}
                 }
            	 if(i==0) {
            		 System.out.println("No se ha encontrado a ningun "
            		 		+ "deportista que contenga "+cadenaABuscar+" en "
            		 				+ "el nombre");
            	 }
            	
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void buscarPorDeporteYAnio(String deporte,String anio,
			int temporada) {
		String temp="Winter";
		if(temporada==1) {
			temp="Summer";
		}
		try (BufferedReader br=new BufferedReader(new FileReader(
				"Datos_Olimpiadas/athlete_events.csv"))) {
			String linea;
            String leido=br.readLine();
            if (leido!=null) {
            	int indiceSport=-1;
            	int indiceYear=-1;
            	int indiceSeason=-1;
            	int indiceName=-1;
            	int indiceEvent=-1;
            	int indiceMedal=-1;
            	int indiceGames=-1;
            	int indiceCity=-1;
            	String[] values = leido.split(",");
            	for(int i=0;i<values.length;i++) {
            		String valor=values[i];
            		if(valor.equals("\"Sport\"")) {
            			indiceSport=i;
            		}
            		if(valor.equals("\"Year\"")) {
            			indiceYear=i;
            		}
            		if(valor.equals("\"Season\"")) {
            			indiceSeason=i;
            		}
            		if(valor.equals("\"Name\"")) {
            			indiceName=i;
            		}
            		if(valor.equals("\"Event\"")) {
            			indiceEvent=i;
            		}
            		if(valor.equals("\"Medal\"")) {
            			indiceMedal=i;
            		}
            		if(valor.equals("\"Games\"")) {
            			indiceGames=i;
            		}
            		if(valor.equals("\"City\"")) {
            			indiceCity=i;
            		}
            	}
            	System.out.println(deporte);
            	HashMap<String,ArrayList<String>> mapa=new HashMap<String,
            			ArrayList<String>>();
            	while ((linea=br.readLine())!=null) {
            		values=linea.split(",");
            		if(values[indiceSport].equals("\""+deporte+"\"")&&
            				(values[indiceYear].equals(anio))&&
            				(values[indiceSeason].equals("\""+temp+"\""))) {
	                     String nombre=values[indiceName];
	                     String evento=values[indiceEvent];
	                     String medalla=values[indiceMedal];
	                     String juegos=values[indiceGames];
	                     String ciudad=values[indiceCity];
	                     if(!mapa.containsKey(juegos+" "+ciudad)) {
	                    	 mapa.put(juegos+" "+ciudad,
	                    			 new ArrayList<String>());
	                     }
	                     mapa.get(juegos+" "+ciudad).add("Nombre: "+nombre+
	                    		 ", Evento: "+evento+", Medalla: "+medalla);
            		}
            	}
            	if(mapa.isEmpty()) {
            		System.out.println("No hay nadie para el deporte que has "
            				+ "elegido en la fecha y temporada que has "
            				+ "elegido");
            	}
            	else {
	            	for(Entry<String,ArrayList<String>>entrada:
	            		mapa.entrySet()){
	            		System.out.println("\t"+entrada.getKey());
	            		for(String persona:entrada.getValue()) {
	            			System.out.println("\t\t"+persona);
	            		}
	            	}
            	}
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void AniadeDeportista(String nombre,int sexo,int edad,
			int altura,float peso, String equipo,String noc,String anio,
			int temporada,String ciudad,String deporte,
			String evento,int medalla) {
		String id="";
		try (BufferedReader br = new BufferedReader(new FileReader
        		("Datos_Olimpiadas/athlete_events.csv"))) {
			String linea;
			String leido=br.readLine();
            if (leido!=null) {
            	int indiceID=-1;
            	String[] values = leido.split(",");
            	for(int i=0;i<values.length;i++) {
            		String valor=values[i];
            		if(valor.equals("\"ID\"")) {
            			indiceID=i;
            		}
            	}
            	 while ((linea = br.readLine()) != null) {
            		 values=linea.split(",");
            		 id=values[indiceID];
            	 }
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		id="\""+(Integer.parseInt(id.substring(1,id.length()-1))+1)+"\"";
		String sex="M";
		if(sexo==2) {
			sex="F";
		}
		String temp="Summer";
		if(temporada==2) {
			temp="Winter";
		}
		String med="NA";
		if(medalla==1) {
			med="\"Gold\"";
		}else {
			if(medalla==2) {
				med="\"Silver\"";
			}
			else {
				if(medalla==3) {
					med="\"Bronze\"";
				}
			}
		}
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter
					("Datos_Olimpiadas/athlete_events.csv",true));
			bw.write(id+",\""+nombre+"\",\""+sex+"\","+edad+","+
			altura+","+peso+",\""+equipo+"\",\""+noc+"\",\""+anio+" "+temp+
			"\","+anio+",\""+temp+"\",\""+ciudad+"\",\""+deporte+"\",\""+
			evento+"\","+med);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		System.out.println("1. Generar fichero csv de olimpiadas");
		System.out.println("2. Buscar deportista");
		System.out.println("3. Buscar deportistas por deporte y olimpiada");
		System.out.println("4. Añadir deportista");
		int opcion=input.nextInt();
		input.nextLine();
		switch (opcion) {
		case 1:
			generarFicheroCSV();
			break;
		case 2:
			System.out.println("Dime a quien quieres buscar");
			String persona=input.nextLine();
			mostrarDeportistas(persona);
			break;
		case 3:
			System.out.println("Dime el deprote");
			String deporte=input.nextLine();
			System.out.println("Dime el año");
			String anio=input.nextLine();
			int temporada=-1;
			do {
				System.out.println("Dime la temporada\n1 Summer\n2 Winter)");
				temporada=input.nextInt();
			}while(temporada!=1&&temporada!=2);
			
			buscarPorDeporteYAnio(deporte, anio, temporada);
			break;
		case 4:
			System.out.println("Dime el nombre del atleta");
			String nombre=input.nextLine();
			int sexo=-1;
			do {
			System.out.println("Dime su sexo\n1 Hombre\n2 Mujer");
			sexo=input.nextInt();
			}while(sexo!=1&&sexo!=2);
			System.out.println("Dime su edad");
			int edad=input.nextInt();
			System.out.println("Dime su altura");
			int altura=input.nextInt();
			System.out.println("Dime su peso");
			float peso=input.nextFloat();
			input.nextLine();
			System.out.println("Dime su equipo");
			String equipo=input.nextLine();
			System.out.println("Dime su NOC");
			String noc=input.nextLine();
			System.out.println("Dime en que año fue");
			anio=input.nextLine();
			temporada=-1;
			do {
				System.out.println("Dime la temporada\n1 Summer\n2 Winter)");
				temporada=input.nextInt();
			}while(temporada!=1&&temporada!=2);
			input.nextLine();
			System.out.println("Dime en que ciudad fue");
			String ciudad=input.nextLine();
			System.out.println("Dime el deprote");
			deporte=input.nextLine();
			System.out.println("Dime el nombre del evento al que fue");
			String evento=input.nextLine();
			int medalla=-1;
			do {
				System.out.println("Dime que medalla consiguio");
				System.out.println("1 oro\n2 plata\n3 bronce\n4 ninguna");
				medalla=input.nextInt();
			}while(medalla!=1&&medalla!=2&&medalla!=3&&medalla!=4);
			AniadeDeportista(nombre,sexo,edad,altura,peso,equipo,noc,
					anio,temporada,ciudad,deporte,evento,medalla);
			break;

		default:
			System.out.println("Opcion no valida");
			break;
		}
	}
}