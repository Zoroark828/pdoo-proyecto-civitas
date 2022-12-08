
package civitas;

/** autora: @Zoroark828
 * 
 * Esta clase se puede borrar. Es una clase para probar que lo realizado en la Practica 4 funciona bien.
 * 
 */

public class TestP4 {
    public static void main(String[] args){
        // Creamos un nuevo jugador y una casilla calle
        Jugador jugador1 = new Jugador ("Pepe");
        CasillaCalle calle1 = new CasillaCalle("Calle1",10,10,10);
        
        // Hacemos que el jugador pueda comprar dicha casilla y la compramos
        jugador1.puedeComprarCasilla();
        jugador1.comprar(calle1);
        
        // Imprimimos la información actual
        System.out.println("INFORMACION PRE-CONVERSION");
        System.out.println(jugador1.toString());
        System.out.println(calle1.toString());

        // Convertimos al jugador en un especulador y comprobamos como la informacion se ha actualizado
        jugador1 = jugador1.convertir();
        System.out.println("\n\nINFORMACION UNA VEZ CONVERTIDO EL JUGADOR");
        System.out.println(((JugadorEspeculador) jugador1).toString());
        System.out.println(calle1.toString());
        
        // Vamos a hacer que construya 8 casas para que quede comprobado que puede hacer trampas
        jugador1.construirCasa(0);
        jugador1.construirCasa(0);
        jugador1.construirCasa(0);
        jugador1.construirCasa(0);
        jugador1.construirCasa(0);
        jugador1.construirCasa(0);
        jugador1.construirCasa(0);
        jugador1.construirCasa(0);
        jugador1.construirCasa(0);      // Novena casa, que no se construirá porque el limite es 8
        
        System.out.println("\n\nINFORMACION TRAS CONSTRUIR 8 CASAS");
        System.out.println(((JugadorEspeculador) jugador1).toString());
        System.out.println(calle1.toString());
        
        // Si te fijas en el saldo del jugador antes de construir las 8 casas y después, verás que
        // se le han descontado 40 creditos. Esto es porque paga la mitad, así que pagó
        // 8 * 5 (creditos) = 40 creditos, en vez de pagar 10 creditos por casa como un jugador normal
        
        // Si compra una casa, tambien pagará la mitad:
        CasillaCalle calle2 = new CasillaCalle("Calle2",400,70,60);
        jugador1.puedeComprarCasilla();
        jugador1.comprar(calle2);
        
        System.out.println("\n\nINFORMACION TRAS COMPRAR LA CALLE 2");
        System.out.println(((JugadorEspeculador) jugador1).toString());
        System.out.println(calle2.toString());
        // Efectivamente, se le han descontado 200 creditos y no 400 :)
        
        // Queda comprobado que el programa funciona perfectamente
        
    }
    
    
    
    
}
