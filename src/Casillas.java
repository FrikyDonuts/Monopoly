class Casillas 
{
	//Variables que dependen de los argumentos del constructor
	private String nombreCasilla;
	private int valorCasilla;	//Precio de comprar
	private String tipoGrupo;	//Grupo de propiedades: Estacion, Servicio o color
	private String tipoCasilla; //Pueden ser casillas de comunidad/suerte, efecto o propiedades
	private int efectoCasilla;	//Un numero que tienen asignado un efectos, la lista de efectos esta en JMonopoly.java
	private double cantidadEfecto;	//Guarda la cantidad de pasos o dinero que se usa en los efectos
	//Si el grupo es de un color u otro se ajusta el valor de la casa, hotel y alquiler	
	private double valorCasa;
	private double valorHotel;
	private double precioAlquiler;	
	//Variables que se inicializan en el constructor
	private String propietario;
	private int numCasas;
	private int numHoteles;
	private boolean hipotecada;
	static private int totalAzules=0;
	static private int totalRojos=0;	
	static private int totalVerdes=0;
	static private int totalMarrones=0;
	static private int totalRosas=0;
	static private int totalNaranjas=0;
	static private int totalAmarillos=0;
	static private int totalNegros=0;
	static private int totalEstaciones=0;
	
	Casillas(String nombre, int valor, String grupo)
	{
		nombreCasilla=nombre;
		valorCasilla=valor;
		tipoGrupo=grupo;
		tipoCasilla="Propiedad";
		propietario="Banca";
		hipotecada=false;
		numCasas=0;
		numHoteles=0;
		switch(tipoGrupo)
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
		case "Marron":
			totalMarrones++;
			valorCasa=350;
			valorHotel=700;
			precioAlquiler=0.25*valorCasilla;
			break;
		case "Rosa":
			totalRosas++;
			valorCasa=400;
			valorHotel=800;
			precioAlquiler=0.3*valorCasilla;
			break;
		case "Naranja":
			totalNaranjas++;
			valorCasa=450;
			valorHotel=900;
			precioAlquiler=0.3*valorCasilla;
			break;
		case "Amarillo":
			totalAmarillos++;
			valorCasa=500;
			valorHotel=1000;
			precioAlquiler=0.35*valorCasilla;
			break;
		case "Negro":
			totalNegros++;
			valorCasa=600;
			valorHotel=1200;
			precioAlquiler=0.4*valorCasilla;
			break;
		case "Estacion":
			totalEstaciones++;
			precioAlquiler=0.125*valorCasilla;
			break;
		case "Servicio":
			totalEstaciones++;
			precioAlquiler=0.125*valorCasilla;
			break;
		default:
			System.out.println("GRUPO NO ASIGNADO");
		}

	}
	Casillas(String nombre, String tipo)
	{
		nombreCasilla=nombre;
		tipoCasilla=tipo;
	}
	Casillas(String nombre, int efecto, double cantidad)
	{
		tipoCasilla="Efecto";
		nombreCasilla=nombre;
		efectoCasilla=efecto;
		cantidadEfecto=cantidad;
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
	public String getGrupo() 
	{
		return this.tipoGrupo;
	}
	public int getTotalGrupo(String grupo) 
	{
		int total=0;
		
		if(grupo=="Azul")
			total=totalAzules;
		if(grupo=="Rojo")
			total=totalRojos;
		if(grupo=="Verde")
			total=totalVerdes;
		if(grupo=="Rosa")
			total=totalRosas;
		if(grupo=="Marron")
			total=totalMarrones;
		if(grupo=="Naranja")
			total=totalNaranjas;
		if(grupo=="Amarillo")
			total=totalAmarillos;
		if(grupo=="Negro")
			total=totalNegros;
		if(grupo=="Estacion")
			total=totalEstaciones;
		
		return total;
	}
	//Efecto
	public int getEfecto() 
	{
		return efectoCasilla;
	}
	public double getNum() 
	{
		return cantidadEfecto;
	}
}
