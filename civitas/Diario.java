package civitas;

import java.util.ArrayList;

/* Codigo de la clase aportado por los profesores
*
*
*/

public class Diario {
  static final private Diario instance = new Diario();
  
  private final ArrayList<String> eventos;
  
  static public Diario getInstance() {
    return instance;
  }

  /* Según el DC, hay que quitar este método
  public ArrayList<String> getEventos() {
    return eventos;
  }
  */
  
  
  private Diario () {                       // Constructor privado
    eventos = new ArrayList();
  }
  
  // CAMBIO (!!). Lo cambié de package a public para poder usarlo en VistaDiario
  public void ocurreEvento (String evento) {
    eventos.add (evento);
  }
  
  public boolean eventosPendientes () {
    return !eventos.isEmpty();
  }
  
  public String leerEvento () {
    String salida = "";
    if (!eventos.isEmpty()) {
      salida = eventos.remove(0);
    }
    return salida;
  }
}
