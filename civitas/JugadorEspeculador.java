
package civitas;

/** autora: @Zoroark828
 *
 * Este es un nuevo tipo de jugador que podrá construir 2 veces más casas y hoteles (FactorEspeculador = 2)
 * Por ello he decidido sobreescribir el getter de CasasMax y HotelesMax. Si en un futuro el numero de CasasMax
 * cambia para los jugadores normales, el getter de CasasMax cambiará automaticamente el numero de casas que puede
 * construir un especulador.
 * 
 * Los jugadores especuladores también pagan la mitad cuando deban pagar algo. Por ello, he sobreescrito el metodo
 * pagar() en esta clase y he hecho casting en los metodos de Jugador y CasillaCalle que hacian uso de pagar()
 * 
 * Tambien he sobreescrito el metodo toString para que muestre que el jugador es especulador.
 *
 */

public class JugadorEspeculador extends Jugador {
    protected static int FactorEspeculador = 2;
    
    JugadorEspeculador (Jugador jugador){
        super(jugador);     // Llamamos al constructor por copia de jugador
        this.actualizaPropiedadesPorConversion();
    }
    
    void actualizaPropiedadesPorConversion(){
        if (this.tieneAlgoQueGestionar())  // (Si tiene alguna propiedad)
            this.getPropiedades().get(0).actualizaPropietarioPorConversion(this, this.getPropiedades());
    }
    
    protected static int getCasasMax(){
        return CasasMax * FactorEspeculador;
    }
    
    protected static int getHotelesMax(){
        return HotelesMax * FactorEspeculador;
    }
    
    @Override
    boolean paga (float cantidad){
        float pagoEspeculador = -1 * cantidad / FactorEspeculador;
        return this.modificarSaldo(pagoEspeculador);
    }
    
    @Override
    public String toString(){
        int numeroCasillaReal = this.getCasillaActual() + 1;
        String informacion = "El *jugador ESPECULADOR* " +this.getNombre() +" se encuentra en la casilla numero " +numeroCasillaReal 
                +".\nSu saldo es de " +this.getSaldo() +" euros.\n";
        return informacion;
    }
    
    
    
}
