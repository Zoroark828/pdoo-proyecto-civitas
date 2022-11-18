package vistaTextualCivitas;

//import civitas.Casilla;      //(No usado)
import civitas.CivitasJuego;
import civitas.Diario;
import civitas.OperacionJuego;
import controladorCivitas.Respuesta;
import civitas.OperacionInmobiliaria;
// import civitas.Jugador;      //(No usado)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class VistaTextual implements Vista {
  private static String separador = "=====================";
  private Scanner in;
  CivitasJuego juegoModel;
  
  public VistaTextual (CivitasJuego juegoModel) {
    in = new Scanner (System.in);
    this.juegoModel=juegoModel;
  }  
  
  @Override
  public  void pausa() {
    System.out.print ("\nPulsa una tecla");
    in.nextLine();
    System.out.println(" ");
  }

  int leeEntero (int max, String msg1, String msg2) {
    Boolean ok;
    String cadena;
    int numero = -1;
    do {
      System.out.print (msg1);
      cadena = in.nextLine();
      try {  
        numero = Integer.parseInt(cadena);
        ok = true;
      } catch (NumberFormatException e) { // No se ha introducido un entero
        System.out.println (msg2);
        ok = false;  
      }
      if (ok && (numero < 0 || numero >= max)) {
        System.out.println (msg2);
        ok = false;
      }
    } while (!ok);

    return numero;
  }

  int menu (String titulo, ArrayList<String> lista) {
    String tab = "  ";
    int opcion;
    System.out.println (titulo);
    for (int i = 0; i < lista.size(); i++) {
      System.out.println (tab+i+"-"+lista.get(i));
    }

    opcion = leeEntero(lista.size(),
                          "\n"+tab+"Elige una opcion: ",
                          tab+"Valor erroneo");
    return opcion;
  }
  
  @Override
  public void actualiza(){
      if (!this.juegoModel.finalDelJuego())
          System.out.println(juegoModel.getJugadorActual().toString() +separador 
                  +"\nCARACTERISTICAS CASILLA ACTUAL:\n" 
                  +juegoModel.getTablero().getCasilla(juegoModel.getJugadorActual().getCasillaActual()).toString());
                  // En esta ultima linea conseguimos el tablero, de él la casilla actual (su indice lo conseguimos desde
                  // el jugador actual) y le aplicamos su propio metodo toString()      
      else{
          // En este metodo NO aplicamos el metodo ranking(), de eso se encarga el controlador
          System.out.println("El ganador es el jugador " +juegoModel.getJugadores().get(0).getNombre() +". RANKING FINAL:");
          for (int i = 0; i < juegoModel.getJugadores().size(); i++){
              int numClasificado = i + 1;
              System.out.println(numClasificado + "o --> " +juegoModel.getJugadores().get(i).getNombre());
          }
      }
  }
  
  @Override
  public Respuesta comprar(){
      ArrayList<String> lista = new ArrayList<>(Arrays.asList("NO","SI"));
      int opcion = menu ("Quiere comprar la casilla actual?", lista);
      
      return Respuesta.values()[opcion];
  }
  
  @Override
  public OperacionInmobiliaria elegirOperacion(){
      ArrayList<String> lista = new ArrayList<>(Arrays.asList("CONSTRUIR CASA","CONSTRUIR HOTEL","TERMINAR"));
      int opcion = menu ("Elija que gestion inmobiliaria de las siguientes desea hacer:",lista);
      
      return OperacionInmobiliaria.values()[opcion];
  }
  
  @Override
  public int elegirPropiedad(){
      ArrayList<String> nombres = new ArrayList(); // Almacena los nombres de las calles en propiedad del jugador actual
      for (int i = 0; i < this.juegoModel.getJugadorActual().getPropiedades().size(); i++){
          nombres.add(this.juegoModel.getJugadorActual().getPropiedades().get(i).getNombre());
      }
      int indice = menu ("Elija sobre que propiedad realizara la gestion:",nombres);
      
      return indice;
  }
  
  @Override
  public void mostrarSiguienteOperacion (OperacionJuego operacion){
      System.out.println("==> SIGUIENTE GESTION: " +operacion +"\n");
  }
  
  @Override
  public void mostrarEventos(){
      while (Diario.getInstance().eventosPendientes())
          System.out.println(Diario.getInstance().leerEvento());
  }
  
  
  
  
  
  
  
  
}
