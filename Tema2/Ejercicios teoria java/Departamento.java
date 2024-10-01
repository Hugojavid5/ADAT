import java.io.Serializable;
public class Departamento implements Serializable {

    int numDepartamento;
    String nombreDepartamento;
    String localidad;
    
    public Departamento(int numDepartamento,String nombreDepartamento,String localidad)
    {
        this.numDepartamento=numDepartamento;
        this.nombreDepartamento=nombreDepartamento;
        this.localidad=localidad;
    }
     @Override
    public String toString() 
    {
        String str="El departamento "+numDepartamento+" con nombre "+nombreDepartamento+" y su localidad es "+localidad;
        return str;
    }
}