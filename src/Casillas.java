class Casillas 
{
	private static int numCasilla=0;	//No puede ser statica pero no statica tampoco me vale porque serian todos 1
	private String nombreCasilla;
	private int valorCasilla;	//Precio para comprar la propiedad o lo que sea
	private String tipoCasilla;	//Pueden ser casillas de carta, efecto, propiedades. De momento solo trabajamos con propiedades
	private String propietario;
	private int efectoCasilla;//Programaremos una lista de metodos efectos y un metodo que compruebe el num, depende del num llamara a uno u otro
	private double valorCasa;
	private double valorHotel;
	private int numCasas;
	private int numHoteles;
	private boolean hipotecada;
	private String colorGrupo;//Si el grupo es de un color u otro se ajusta el valor de la casa, el valor del hotel, el porcentaje alquiler y rentas por casa
	private double precioAlquiler;	//Porcentaje del valor que pagara
	static private int totalRojos=0;	
	static private int totalVerdes=0;
	static private int totalAzules=0;
	
	Casillas(String nombre, int valor, String color)
	{
		nombreCasilla=nombre;
		valorCasilla=valor;
		colorGrupo=color;
		tipoCasilla="Propiedad";
		propietario="Banca";
		hipotecada=false;
		numCasas=0;
		numHoteles=0;
		switch(colorGrupo)
		{
		case "Azul":
			totalAzules++;
			valorCasa=100;
			valorHotel=200;
			precioAlquiler=0.1*valorCasilla;
			break;
		case "Rojo":
			totalRojos++;
			valorCasa=200;
			valorHotel=300;
			precioAlquiler=0.15*valorCasilla;
			break;
		case "Verde":
			totalVerdes++;
			valorCasa=300;
			valorHotel=400;
			precioAlquiler=0.2*valorCasilla;
			break;
		default:
			System.out.println("COLOR NO ASIGNADO");
		}

	}
	Casillas(String nombre, int efecto)
	{
		nombreCasilla=nombre;
		tipoCasilla="Efecto";
		efectoCasilla=efecto;
	}
	
	//Propietario
	public String getPropietario() 
	{
		return this.propietario;
	}
	public void setPropietario(String nuevoPropietario) 
	{
		this.propietario=nuevoPropietario;
	}
	public void inicializaPropietario() 
	{
		this.propietario="banca";
	}
	//Nombre
	public String getNombre()
	{
		return this.nombreCasilla;
	}
	//Misc
	public int getNumCasilla() 
	{
		return numCasilla;
	}
	//Casilla
	public int getValorCasilla() 
	{
		return this.valorCasilla;
	}
	//Tipo
	public String getTipo() 
	{
		return this.tipoCasilla;
	}
	//Estructuras
	//Casas
	public int getCasas() 
	{
		return this.numCasas;
	}
	public double getValorCasa() 
	{
		return this.valorCasa;
	}
	public void agregaCasas(int nuevasCasas) 
	{
		this.numCasas+=nuevasCasas;
	}
	public void quitaCasas(int casas) 
	{
		this.numCasas-=casas;
	}
	//Hoteles
	public int getHoteles() 
	{
		return this.numHoteles;
	}
	public double getValorHotel() 
	{
		return this.valorHotel;
	}
	public void agregaHoteles(int nuevosHoteles) 
	{
		this.numHoteles+=nuevosHoteles;
	}
	public void quitaHoteles(int hoteles) 
	{
		this.numHoteles-=hoteles;
	}
	//Hipoteca
	public void setHipoteca(boolean valor) 
	{
		this.hipotecada=valor;
	}
	public boolean getHipoteca() 
	{
		return this.hipotecada;
	}
	//Alquiler
	public double getPrecioAlquiler() 
	{
		return this.precioAlquiler;
	}
	//Grupo
	public String getColorGrupo() 
	{
		return this.colorGrupo;
	}
	public int getTotalColor(String color) 
	{
		int total=0;
		
		if(color=="Azul")
			total=totalAzules;
		if(color=="Rojo")
			total=totalRojos;
		if(color=="Verde")
			total=totalVerdes;
		
		return total;
	}
	//Efectos
	/*Lista de efectos:
	 * 1: Avanzas x pasos
	 * 2: Retrocedes x pasos
	 * 3: Pagar a banca
	 * 4: Pagar a jugador/es
	 * 5: Recibir diner de jugador/es
	 * 6: Recibir dinero banca
	 * 7: Ir a la carcel ->Si retrocedes o vas a la carcel no cuentan los 200€ de salida
	 * 8: Salir de la carcel -> Esta carta te la puedes quedar 
	 */
}
