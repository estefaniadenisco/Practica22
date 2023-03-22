import dominio.Tablero;

import java.util.concurrent.TimeUnit;

public class Principal {
     public static void main(String[] args) {
         Tablero tablero = new Tablero();
        
         // Simulación con tablero leída del archivo
         System.out.println("SIMULACION CON LECTURA DEL TABLERO");
         tablero.leerEstadoActual("matriz.txt");
         System.out.println(tablero);
         for(int i = 0; i < 5; i++) {
             try{
                 TimeUnit.SECONDS.sleep(1);
             } catch (InterruptedException e) {
                 System.out.println(e);
             }
             
}
}
}
