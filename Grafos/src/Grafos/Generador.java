package Grafos;
/** @author LUIS-CIC **/
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
public class Generador {
      public static void main(String[] args) throws IOException {
        while (1==1){
        String cadena=JOptionPane.showInputDialog("Generador de grafos:\n (A)-Erdös y Rényi \r (B)-Gilbert \n (C) -Geográfico simple \r (D) -Barabási-Albert");
        int verificador1=0, verificador2=0, verificador3=0 ,  numeronodos=0, esdirigido=0 , hayauto=0, Nrealizar=0; 
        if (cadena == null){System.exit(0);}
        int contadoraristas = 0;
        int comp=0;
        String datosarista = "sin datos";
        String metodo = " ",ciclos =" ", dirigidos= " ";
        /*--------------------------*/
        
        verificador1 =0;
        while (verificador1 == 0){
        if ("A".equals(cadena) || "a".equals(cadena) || "B".equals(cadena) || "b".equals(cadena) || "C".equals(cadena) || "c".equals(cadena) || "D".equals(cadena) || "d".equals(cadena) ){
        verificador1 = 1;}
        else {
        cadena=JOptionPane.showInputDialog("Error vuelva a indicar el generador\nGenerador de grafos:\n (A)-Erdös y Rényi \r (B)-Gilbert \n (C) -Geográfico simple \r (D) -Barabási-Albert");
        } if (cadena == null){System.exit(0);}  }
        char tipo = cadena.charAt(0);
        
        if ("A".equals(cadena) || "a".equals(cadena))   {              tipo = 'A';        }
        if ("B".equals(cadena) || "b".equals(cadena))   {              tipo = 'B';        }
        if ("C".equals(cadena) || "c".equals(cadena))   {              tipo = 'C';        }
        if ("D".equals(cadena) || "d".equals(cadena))   {              tipo = 'D';        }    
        while (verificador2 == 0){
        String dirigido=JOptionPane.showInputDialog("¿El grafo es dirigido o no dirigido? s(Si) , n(No)");
        if ("s".equals(dirigido) || "S".equals(dirigido) ) {
        esdirigido =1; verificador2=1;}
        else if ("n".equals(dirigido)|| "N".equals(dirigido)){
        esdirigido =0; verificador2=1;}
        else {
        verificador2=0;}
        if (dirigido == null){System.exit(0);}    }
        while (verificador3 == 0){
        String autociclo=JOptionPane.showInputDialog("¿Se permiten autociclo? s(Si) , n(No)");
        if ("s".equals(autociclo) || "S".equals(autociclo) ) {
        hayauto =1; verificador3=1;}
        else if ("n".equals(autociclo)|| "N".equals(autociclo)){
        hayauto =0; verificador3=1;}
        else {
        verificador3=0;}
        if (autociclo == null){System.exit(0);} }
        String Nnodos=JOptionPane.showInputDialog("Número de nodos a crear:");
        numeronodos = Integer.parseInt(Nnodos);
        if (Nnodos == null){System.exit(0);}
        JFileChooser archi = new JFileChooser();
        archi.showSaveDialog(null);
        String archivo = archi.getCurrentDirectory().toString() + "\\" + archi.getSelectedFile().getName();
        /*--------------------------*/
        HashMap<Integer, Nodos> LNodos = new HashMap<>();
        HashMap<Integer, Aristas> LAristas = new HashMap<>();
        switch ( tipo ) {
        /*------------------------------------------------------------------------------------------------*/      
        case ('A'):
        System.out.println( "1" );
        int numeropareseroz = 0;
        contadoraristas = 0;
        String Npareseroz=JOptionPane.showInputDialog("Número de pares de nodos");
        numeropareseroz = Integer.parseInt(Npareseroz);
        comp=0;
        int contador = 0, x, y,i=0,Identificador=0;
        String Nombre,Datos;
        for (i = 0; i < numeronodos; i++) { 
        Identificador= i;
        Nombre = "Nodo" + i;
        Datos = "Dato" + i;
        Nodos nodo = new Nodos(Identificador,Nombre,Datos);
        LNodos.put(Identificador, nodo);}
        try (FileWriter fw = new FileWriter(archivo + ".gv");
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw)) {
        out.println("strict digraph{");
        out.flush();
        Random r = new Random();
        while(contadoraristas < numeropareseroz) {
        x = r.nextInt(numeronodos);
        y = r.nextInt(numeronodos);
        if (hayauto == 0) { 
        while (x == y) { 
        y = r.nextInt(numeronodos);}         }
        datosarista = "Nodo x" + x + "Nodo y" + y;
        Nodos a = LNodos.get(x); 
        Nodos b = LNodos.get(y); 
        if (comp == 0){
        Aristas arista = new Aristas(a,b,datosarista);
        LAristas.put(contadoraristas,arista);  
        if (esdirigido == 0) {
        out.println("\"" + a.Dameidentificador() + "\"--\"" + b.Dameidentificador() +  "\"");
        out.flush();  } 
        else { 
        out.println("\"" + a.Dameidentificador() + "\"->\"" + b.Dameidentificador() +  "\"");
        out.flush();}
        comp = 1;   } 
        else {
        for (i=0;i < LAristas.size();i++){
        Aristas comparaarista = LAristas.get(i);
        HashMap<Integer, Nodos> verificaaristas = comparaarista.Regresanodos();    
        Nodos va=verificaaristas.get(1);
        Nodos vb=verificaaristas.get(2);
        Nrealizar=1;
        if ((a == va && b == vb) || (a == vb && b == va && esdirigido == 0)) { 
        Nrealizar=0;
        break;}
        
        }         
        if(Nrealizar==1){
        Aristas arista = new Aristas(a,b,datosarista);
        LAristas.put(contadoraristas,arista);  
        if (esdirigido == 0) {
        out.println("\"" + a.Dameidentificador() + "\"--\"" + b.Dameidentificador() +  "\"");
        out.flush();} 
        else { 
        out.println("\"" + a.Dameidentificador() + "\"->\"" + b.Dameidentificador() +  "\"");
        out.flush();}
        contadoraristas++;}}}
        out.println("}");
        out.close(); } 
        break;
        
        
        
        /*------------------------------------------------------------------------------------------------*/
        
        
        case 'B':
        float numeroprobabilidad = 2;
        contadoraristas = 0;
        while (numeroprobabilidad < 0 || numeroprobabilidad > 1 ){     
        String probabilidad=JOptionPane.showInputDialog("Probabilidad de conexión (Min=0 , Max=1)");
        numeroprobabilidad = Float.parseFloat(probabilidad); }
        Random r = new Random();
        int cont=0;
        int j,k;
        comp=0;
        float comparaproba;
        for (i = 0; i < numeronodos; i++) { 
        Identificador= i;
        Nombre = "Nodo" + i;
        Datos = "Dato" + i;
        Nodos nodo = new Nodos(Identificador,Nombre,Datos);
        LNodos.put(Identificador, nodo);}   
        try (FileWriter fw = new FileWriter(archivo + ".gv");
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw)) {
        out.println("strict digraph{");
        out.flush();
        for(i = 0; i < numeronodos; i++) { for(j = 0; j < numeronodos; j++){
        cont++;    
        System.out.println( cont);
        if (hayauto == 0 && (i == j)) { continue;}
        comparaproba = r.nextFloat(); 
        datosarista = "Nodo x" + i + "Nodo y" + j; 
        if (comparaproba < numeroprobabilidad) {
        Nodos a = LNodos.get(i);
        Nodos b = LNodos.get(j);
        if (comp == 0){
        Aristas arista = new Aristas(a,b,datosarista);
        LAristas.put(contadoraristas,arista);  
        if (esdirigido == 0) {
        out.println("\"" + a.Dameidentificador() + "\"--\"" + b.Dameidentificador() +  "\"");
        out.flush();  } 
        else { 
        out.println("\"" + a.Dameidentificador() + "\"->\"" + b.Dameidentificador() +  "\"");
        out.flush();}
        comp=1;
        } 
        else {
        for (k=0; k < LAristas.size() ;k++){
        Aristas comparaarista = LAristas.get(k);
        HashMap<Integer, Nodos> verificaaristas = comparaarista.Regresanodos();    
        Nodos va=verificaaristas.get(1);
        Nodos vb=verificaaristas.get(2);
        
        Nrealizar=1;
        if ((a == va && b == vb) || (a == vb && b == va && esdirigido == 0)) { 
        Nrealizar=0;
        break;}
        
        }
        }
        if(Nrealizar==1){
        Aristas arista = new Aristas(a,b,datosarista);
        LAristas.put(contadoraristas,arista);  
        if (esdirigido == 0) {
        out.println("\"" + a.Dameidentificador() + "\"--\"" + b.Dameidentificador() +  "\"");
        out.flush();} 
        else { 
        out.println("\"" + a.Dameidentificador() + "\"->\"" + b.Dameidentificador() +  "\"");
        out.flush();}
        contadoraristas++;}
        }}}
        out.println("}");
        out.close(); }
        break;  
        
        
        /*------------------------------------------------------------------------------------------------*/
        
        
        case 'C':
        System.out.println( "3" );
       
        double dismin=-1;
        double distancia=0;
         int q;
        while (dismin <= 0 ){     
        String dis=JOptionPane.showInputDialog("Distancia minima para conectar los nodos (Min=0 , Max=1)");
        /*dismin = Integer.parseInt(dis);*/
        dismin = Float.parseFloat(dis);
        if(dismin > 1)
        {dismin = 1;}
        }
        for (i = 0; i < numeronodos; i++) { 
        Identificador= i;
        Nombre = "Nodo" + i;
        Datos = "Dato" + i;
        Nodos nodo = new Nodos(Identificador,Nombre,Datos);
        LNodos.put(Identificador, nodo);}    
        List l1 = new ArrayList();
        List l2 = new ArrayList();
        for (int w = 0; w < numeronodos; w++) {
        l1.add(w);
        l2.add(w);
        }
        Collections.shuffle(l1);
        Collections.shuffle(l2);
        try (FileWriter fw = new FileWriter(archivo + ".gv");
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw)) {
        out.println("strict digraph{");
        out.flush();
        for(i = 0; i < numeronodos; i++) { 
        int v1=(int)l1.get(i);
        for(j = 0; j < numeronodos; j++){
        int v2=(int)l2.get(j);
        if (hayauto == 0 && v1 == v2) { continue;}
        Nodos a = LNodos.get(v1);
        Nodos b = LNodos.get(v2);
        r = new Random();
        distancia = Math.sqrt(Math.pow( r.nextFloat() - r.nextFloat(),2) + Math.pow(r.nextFloat() - r.nextFloat(),2));
        if (distancia <= dismin ) {
        datosarista = "Nodo x" + v1 + "Nodo y" + v2;
        if (comp == 0){
        Aristas arista = new Aristas(a,b,datosarista);
        LAristas.put(contadoraristas,arista);  
        if (esdirigido == 0) {
        out.println("\"" + a.Dameidentificador() + "\"--\"" + b.Dameidentificador() +  "\"");
        out.flush();  } 
        else { 
        out.println("\"" + a.Dameidentificador() + "\"->\"" + b.Dameidentificador() +  "\"");
        out.flush();}
        comp = 1;   } 
        else {
       
        for (q=0;q < LAristas.size();q++){
        Aristas comparaarista = LAristas.get(q);
        HashMap<Integer, Nodos> verificaaristas = comparaarista.Regresanodos();    
        Nodos va=verificaaristas.get(1);
        Nodos vb=verificaaristas.get(2);
       
        
        Nrealizar=1;
        if ((a == va && b == vb) || (a == vb && b == va && esdirigido == 0)) { 
        Nrealizar=0;
        break;}
        
        
        }      
        if(Nrealizar==1){
        Aristas arista = new Aristas(a,b,datosarista);
        LAristas.put(contadoraristas,arista);  
        if (esdirigido == 0) {
        out.println("\"" + a.Dameidentificador() + "\"--\"" + b.Dameidentificador() +  "\"");
        out.flush();} 
        else { 
        out.println("\"" + a.Dameidentificador() + "\"->\"" + b.Dameidentificador() +  "\"");
        out.flush();}
        contadoraristas++;}}}}}
        out.println("}");
        out.close();}    
        break;
       
        
        /*------------------------------------------------------------------------------------------------*/
        
        
        case 'D':
        System.out.println( "bara" );
        int dat;
        String eldato;
        double proba;
        int pnodos=-1,contj,t,co,divisor;
        float comparador;
        while (pnodos < 0 ){     
        String primnodos=JOptionPane.showInputDialog("Primeros n Nodos");
        pnodos = Integer.parseInt(primnodos);
        }   
        try (FileWriter fw = new FileWriter(archivo + ".gv");
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw)) {
        out.println("strict digraph{");
        out.flush();
        r = new Random();
        Identificador= 0;
        Nombre = "Nodo" + 0;
        Datos = ""+ (pnodos + -1);
        contj=0;
        Nodos nodo = new Nodos(Identificador,Nombre,Datos); 
        LNodos.put(Identificador, nodo);
        for (t = 1; t < pnodos; t++) { 
        Identificador= t;
        Nombre = "Nodo" + t;
        co = pnodos -1;
        Datos = ""+ co ;
        nodo = new Nodos(Identificador,Nombre,Datos); 
        LNodos.put(Identificador, nodo);
    
        }
        
        for(i = 0; i < pnodos; i++) { for(j = 0; j < pnodos; j++){
        if (hayauto == 0 && (i == j)) {    continue;         }
        datosarista = "Nodo x" + i + "Nodo y" + j;
        Nodos a = LNodos.get(i);
        Nodos b = LNodos.get(j);
        Aristas arista = new Aristas(a,b,datosarista);
        LAristas.put(contadoraristas,arista);  
        if (esdirigido == 0) {
        out.println("\"" + a.Dameidentificador() + "\"--\"" + b.Dameidentificador() +  "\"");
        out.flush();  } 
        else { 
        out.println("\"" + a.Dameidentificador() + "\"->\"" + b.Dameidentificador() +  "\"");
        out.flush();}
        }}
        divisor = pnodos  ;
        for(i = pnodos; i < numeronodos; i++) { 
        Identificador= i;
        Nombre = "Nodo" + i;
        Datos = "" + 0;
        nodo = new Nodos(Identificador,Nombre,Datos); 
        LNodos.put(Identificador, nodo); 
        
        for(j = 0; j < LNodos.size(); j++){
        if (hayauto == 0 && (i == j)) {    continue;         }
        System.out.println( i + "-ij-" + j );
        comparador =r.nextFloat();
        Nodos a = LNodos.get(i);
        Nodos b = LNodos.get(j); 
        eldato =b.Damedatos();
        dat= Integer.parseInt(eldato);
        System.out.println( dat + "--max--" + pnodos );
        if (dat > pnodos){continue;}
        if (Integer.parseInt(a.Damedatos()) > pnodos){continue;}
        System.out.println( dat + "--div--" + divisor );
        proba= (float)dat/(pnodos*(i/(j+1)));
        System.out.println( proba+"--sihacer--"+comparador );
        if (proba>comparador){ System.out.println("si" );
        contj++;  
        a.Dadatos(""+ contj );
        dat=dat+1;
        b.Dadatos(""+dat);
        
        Aristas arista = new Aristas(a,b,datosarista);  
        LAristas.put(contadoraristas,arista);
        if (esdirigido == 0) { 
        out.println("\"" + a.Dameidentificador() + "\"--\"" + b.Dameidentificador() +  "\"");
        out.flush();  } 
        else { 
        out.println("\"" + a.Dameidentificador() + "\"->\"" + b.Dameidentificador() +  "\"");
        out.flush();}
        }
       
        }
       
        contj=0;
        }  out.println("}");
        out.close();}        
           break;
           
           
           
         default:
           System.out.println("error" );
           break;
      }
        
       
        /*--------------------------*/
        if (hayauto == 1){
        ciclos = "El grafo tiene autociclos";}
        if (hayauto == 0){
        ciclos = "El grafo no tiene autociclos";}
        /*--------------------------*/
        if (esdirigido == 1){
        dirigidos = "El grafo es dirigido";}
        if (esdirigido == 0){
        dirigidos = "El grafo no es dirigido";}
        /*--------------------------*/
        if ("A".equals(cadena) || "a".equals(cadena) ){
            metodo= "Erdös y Rény";
        }
        if ("B".equals(cadena) || "b".equals(cadena)){
            metodo= "Gilbert";
        }
        if ("C".equals(cadena) || "c".equals(cadena)){
            metodo = "Geográfico simple";
        }
        if ("D".equals(cadena) || "d".equals(cadena)){
            metodo= "Barabási-Albert";
        }
        
        /*--------------------------*/
        JOptionPane.showMessageDialog(null, "Metodo eligido:" + metodo + "\n Número de nodo: " + numeronodos + "\n"+dirigidos + "\n" + ciclos + "\n Nombre del grafo: " + archivo +".gv");
        
        
        System.out.println(numeronodos);
        
        
        
        
        }}}

  
      
      


    

