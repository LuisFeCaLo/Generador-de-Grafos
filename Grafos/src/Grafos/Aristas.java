package Grafos;
import java.util.HashMap;
import java.util.*;
/**@author LUIS-CIC*/
public class Aristas {
                                                        Nodos x;
                                                        Nodos y;
                                                        String Datos;
    public Aristas()                                  
                                                      {/*constructor vacio*/}
    public Aristas(Nodos x, Nodos y, String Datos) 
                                                      { this.x = x;
                                                        this.y = y;
                                                        this.Datos = Datos; }
    public void Damenodos(Nodos X, Nodos Y) 
                                                      { this.x = X;
                                                        this.y = Y;    }
    public HashMap<Integer, Nodos> Regresanodos() 
                                                      { HashMap<Integer, Nodos> NHM = new HashMap<>();
                                                        NHM.put(1, x);
                                                        NHM.put(2, y);
                                                        return NHM;     }
    public void Damedatos(String Datos)
                                                      {Datos = Datos;}
    public String Regresadatos()
                                                      {return Datos;}
}



    