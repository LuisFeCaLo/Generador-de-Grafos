package Grafos;
/**@author LUIS-CIC*/
public class Nodos {
                                                                                int Identificador;
                                                                                String Nombre, Datos;
    public Nodos() {/*constructor vacio*/  }
    public Nodos(int Identificador, String Nombre, String Datos) 
                                                                                {  this.Identificador = Identificador;
                                                                                   this.Nombre = Nombre;
                                                                                   this.Datos = Datos;  }
    public int Dameidentificador() 
                                                                                {return Identificador;}
    public String Damenombre()
                                                                                {return Nombre;}
    public String Damedatos()
                                                                                {return Datos;}     
    public void Dadatos(String Dato) 
                                                                                {Datos = Dato;}
}
    
    
    
    

