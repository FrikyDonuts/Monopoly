public class Jugador 
{
	private String nombre;
	private double cartera;
	private int posicion;
	
	Jugador(String nombreJugador, double dineroInicial)
	{
		nombre=nombreJugador;
		cartera=dineroInicial;
		posicion=0;
	}
	Jugador(String nombreJugador)
	{
		nombre=nombreJugador;
		cartera=100000;
		posicion=0;
	}
	
	//Dineros
	public double getDineros() 
	{
		return cartera;
	}
	public void sumaDineros(double dineros) 
	{
		this.cartera+=dineros;
	}
	public void restaDineros(double dineros) 
	{
		this.cartera-=dineros;
	}
	//Movimientos
	public int getPosicion() 
	{
		return posicion;
	}
	public void setPosicion(int casilla) //Luego lo sobrecargaremos con uno de string al que le puedas decir: salida, prision, etc
	{
		this.posicion=casilla;
	}
	public void adelantaPosicion(int casillas) 
	{
		if((casillas+this.posicion)>=40)	//Si el jugador se pasa del 39 le dara una vuelta al tablero 
		{	
			int posicionJugador=this.posicion;
			int casillasHastaSalida=0;
			
			while(posicionJugador<40)	//Contamos las casillas que hay hasta la salida 
			{
				posicionJugador++;
				casillasHastaSalida++;
			}
			this.posicion=0;
			casillas-=casillasHastaSalida;	//Lo adelentamos hasta la salida y le restamos las posiciones que le adelantamos
		}
		this.posicion+=casillas;
	}
	public void regresaPosicion(int casillas) 
	{
		this.posicion-=casillas;
	}
	//Nombre
	public String getNombre() 
	{
		return nombre;
	}
	//Otro metodo que dependiendo del string que pase: Inicio/Carcel/etc lo mande a una posicion especifica
	
}
