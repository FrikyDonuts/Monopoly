public class Jugador 
{
	private String nombre;
	private double cartera;
	private int posicion;
	private int turnosCarcel;
	private boolean bancarrota;
	//Aqui guardamos el num de la carta de suert/com en caso de que el jugador la tenga
	//Los inicializamos a -1 porque hay una carta 0
	private int carcelSuerte=-1;	
	private int carcelComunidad=-1;
	
	Jugador(String nombreJugador)
	{
		nombre=nombreJugador;
		cartera=100000;
		posicion=0;
		turnosCarcel=0;
		bancarrota=false;
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
	public void regresaPosicion(int casillas)	//TODO: Que si le toca retroceder x casillas y al bajar de 0 pase a 39,38,37...
	{
		this.posicion-=casillas;
	}
	//Nombre
	public String getNombre() 
	{
		return nombre;
	}
	//Carcel
	public int getTurnosCarcel() 
	{
		return this.turnosCarcel;
	}
	public void setTurnosCarcel(int turnos) 
	{
		this.turnosCarcel = turnos;
	}
	public void sumaTurnosCarcel(int turnos) 
	{
		this.turnosCarcel+=turnos;
	}
	public void restaTurnosCarcel(int turnos) 
	{
		this.turnosCarcel-=turnos;
	}	
	//Bancarrota
	public void setBancarrota(boolean Bancarrota)
	{
		this.bancarrota=Bancarrota;
	}
	public boolean getBancarrota() 
	{
		return this.bancarrota;
	}
	//CartasCarcel
	public void agregaCartaSuerte(int numCarta) 
	{
		this.carcelSuerte=numCarta;
	}
	public void quitaCartaSuerte() 
	{
		this.carcelSuerte=-1;
	}
	public int getCartaSuerte() 
	{
		return carcelSuerte;
	}
	public void agregaCartaComunidad(int numCarta) 
	{
		this.carcelSuerte=numCarta;
	}
	public void quitaCartaComunidad() 
	{
		this.carcelSuerte=-1;
	}
	public int getCartaComunidad() 
	{
		return carcelComunidad;
	}
}
