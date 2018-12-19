public class JMonopoly 
{
	//Monopoly 0.3
	//DATOS
	static Casillas arrayCasillas[]=new Casillas[40];	//Donde guardamos todas las casillas del jugeo con sus respectivos datos
	static Jugador arrayJugadores[];	//Donde guardaremos a todos los jugadores, el tamaño lo definiremos en el metodo mejor
	static Dados d6 = new Dados(6);	//El dado que lanzamos
	/*TODO:
	*Que los turnos se ordenen por un lanzamiento de dados de mayor a menor al principio de la partida
	*Si un jugador saca dobles vuelve a tirar, a si saca dobles 3 veces va directo a la carcel
	*Si caes en carcel puedes: Pagar 50€ de multa y salir de la carcel el proximo turno
	*Comprar una carta de queda libre de la carcel
	*usar tu carta de queda libre
	*Esperar 3 turnos
	*Metodo que muestre todas las propiedades de un grupo
	*Si caes en un alquiler o tienes que pagar y no tienes dinero suficiente que te pregunte si deseas hipotecar/vender
	*Para levantar la hipoteca tienes que pagar el valo de la hipoteca + 10%
	*Si un jugador tiene saldo negativo que se le obligue a quedarse en su turno hasta que pueda pagar la deuda o se declare en bancarrota
	*/
	public static void main(String[] args) 
	{
		creaJugadores();
		creaCasillas();
		juego();
	}
	
	//PRE-JUEGO
	static public void creaJugadores() 
	{
		System.out.println("Cuantos jugadores vamos a tener ?");
		int numJugadores=Leer.datoInt();
		
		arrayJugadores=new Jugador[numJugadores];
		
		for(int i=0; i<arrayJugadores.length; i++)
		{
			System.out.println("Nombre del jugador "+i);
			String nombreJugador=Leer.dato();
			arrayJugadores[i]=new Jugador(nombreJugador);
		}
	}
	static public void creaCasillas() 
	{
		//Creamos las casillas del array con su respectivo nobre, valir, tipo, coste de casas y hotel
		arrayCasillas[0]=new Casillas("Salida", 500, "Azul");
		arrayCasillas[1]=new Casillas("Alcobendas", 600, "Azul");
		arrayCasillas[2]=new Casillas("Alcorcón", 700, "Azul");
		arrayCasillas[3]=new Casillas("San Fernando", 800, "Azul");
		arrayCasillas[4]=new Casillas("Elda", 900, "Azul");
		arrayCasillas[5]=new Casillas("Utrera", 1000, "Azul");
		arrayCasillas[6]=new Casillas("Cartagena", 1100, "Azul");
		arrayCasillas[7]=new Casillas("Torrejón de Ardoz", 1200, "Azul");
		arrayCasillas[8]=new Casillas("Paterna", 1300, "Azul");
		arrayCasillas[9]=new Casillas("Mijas", 1400, "Azul");
		arrayCasillas[10]=new Casillas("Logroño", 1500, "Rojo");
		arrayCasillas[11]=new Casillas("Guadalajara", 1600, "Rojo");
		arrayCasillas[12]=new Casillas("Linares", 1700, "Rojo");
		arrayCasillas[13]=new Casillas("Boadilla del Monte", 1800, "Rojo");
		arrayCasillas[14]=new Casillas("Ponferrada", 1900, "Rojo");
		arrayCasillas[15]=new Casillas("Zaragoza", 2000, "Rojo");
		arrayCasillas[16]=new Casillas("Salamanca", 2100, "Rojo");
		arrayCasillas[17]=new Casillas("Elche", 2200, "Rojo");
		arrayCasillas[18]=new Casillas("Mérida", 2300, "Rojo");
		arrayCasillas[19]=new Casillas("Alicante", 2400, "Rojo");
		arrayCasillas[20]=new Casillas("Pedroso", 2500, "Rojo");
		arrayCasillas[21]=new Casillas("Arevalillo de Cega", 2600, "Rojo");
		arrayCasillas[22]=new Casillas("Estepona", 500, "Verde");
		arrayCasillas[23]=new Casillas("PropiedadPrueba1", 500, "Verde");
		arrayCasillas[24]=new Casillas("PropiedadPrueba2", 500, "Verde");
		arrayCasillas[25]=new Casillas("PropiedadPrueba3", 500, "Verde");
		arrayCasillas[26]=new Casillas("PropiedadPrueba4", 500, "Verde");
		arrayCasillas[27]=new Casillas("PropiedadPrueba5", 500, "Verde");
		arrayCasillas[28]=new Casillas("PropiedadPrueba5", 500, "Verde");
		arrayCasillas[29]=new Casillas("PropiedadPrueba6", 500, "Verde");
		arrayCasillas[30]=new Casillas("PropiedadPrueba7", 500, "Verde");
		arrayCasillas[31]=new Casillas("PropiedadPrueba8", 500, "Verde");
		arrayCasillas[32]=new Casillas("PropiedadPrueba9", 500, "Verde");
		arrayCasillas[33]=new Casillas("PropiedadPrueba10", 500, "Verde");
		arrayCasillas[34]=new Casillas("PropiedadPrueba11", 500, "Verde");
		arrayCasillas[35]=new Casillas("PropiedadPrueba12", 500, "Verde");
		arrayCasillas[36]=new Casillas("PropiedadPrueba13", 500, "Verde");
		arrayCasillas[37]=new Casillas("PropiedadPrueba14", 500, "Verde");
		arrayCasillas[38]=new Casillas("PropiedadPrueba15", 500, "Verde");
		arrayCasillas[39]=new Casillas("PropiedadPrueba16", 500, "Verde");
	}
	//JUEGO
	static public void juego() 
	{
		boolean victoria=false;
		int turnoJugador=0;	//El turno de cada jugador dependera del numero que tengan asignado en el array de jugadores
		
		while(!victoria) 
		{
			muestraTablero();
			
			System.out.println("Es el turno de: "+arrayJugadores[turnoJugador].getNombre());
			
			arrayJugadores[turnoJugador].adelantaPosicion(lanzaDados());
			int casillaActual=arrayJugadores[turnoJugador].getPosicion();	//Realmente este paso sobra pero lo pongo para facilitar lectura de cod
			
			pagaAlquiler(casillaActual, turnoJugador);
			muestraInfoJugador(turnoJugador);
			muestraInfoCasilla(casillaActual);
			
			pideAccion(casillaActual, turnoJugador);
			
			turnoJugador++;
			
			if(turnoJugador==arrayJugadores.length)
				turnoJugador=0;
			
			System.out.println("");
		}

	}
	public static int lanzaDados() 
	{
		//Lanzamos dos veces el d6 y sumamos los resultados
		int dado1=d6.lanza();
		int dado2=d6.lanza();
		System.out.println("");
		System.out.println("Ha salido un: "+dado1+" y un "+dado2);
		System.out.println("Avanzas "+(dado1+dado2)+" casillas");
		return dado1+dado2;
	}
	public static void pideAccion(int posicion, int jugador) 
	{
		boolean finTurno=false;
		while(!finTurno) 
		{
			System.out.println("");
			System.out.println("[1] Ver informacion de una casilla");
			System.out.println("[2] Comprar propiedad"); //Faltaria venderla, hoteles y casas
			System.out.println("[3] Ver propiedades");	//Otra opcion de ver las de otro jugador
			System.out.println("[4] Crear estructuras");	
			System.out.println("[5] Hipotecar estructuras");	
			System.out.println("[6] Hipotecar propiedad");
			System.out.println("[7] Comprar otra propiedad");	
			System.out.println("[8] Ver las propiedades de un jugador");
			System.out.println("[9] Pagar hipotecas");
			int accion=Leer.datoInt();
			
			switch(accion) 
			{
			case 1:
				System.out.println("Que casilla quieres ver?");
				int casilla=Leer.datoInt();
				muestraInfoCasilla(casilla);
				break;
			case 2:
				System.out.println("Deseas comprar esta propiedad? (s/n)");
				char conf=Leer.datoChar();
				if(conf=='s')
					compraCasilla(posicion, jugador);
				break;
			case 3:
				String nombreJugador=arrayJugadores[jugador].getNombre();
				muestraPropiedades(nombreJugador);
				break;
			case 4:
				System.out.println("Donde deseas crear la estructura?");
				casilla=Leer.datoInt();
				compraEstructura(casilla, jugador);
				break;
			case 5:
				System.out.println("Donde deseas hipotecar la estructura?");
				casilla=Leer.datoInt();
				hipotecarEstructura(casilla, jugador);
				break;
			case 6:
				System.out.println("Que propiedad deseas hipotecar?");
				casilla=Leer.datoInt();
				hipotecarPropiedad(casilla, jugador);
				break;
			case 7:
				System.out.println("Que propiedad deseas comprar?");
				casilla=Leer.datoInt();
				compraCasilla(casilla, jugador);
				break;
			case 8:
				System.out.println("Inserta el nombre del jugador");
				String nombre=Leer.dato();
				muestraPropiedades(nombre);
				break;
			case 9:
				System.out.println("Que propiedad deseas pagar?");
				casilla=Leer.datoInt();
				pagaHipoteca(casilla, jugador);
				break;
			default:
				finTurno=true;
			}	
		}
	}
	//COMPRA
	public static void compraCasilla(int posicion, int jugador)	//Generico
	{
		//Variables para facilitar la lectura
		String propietario=arrayCasillas[posicion].getPropietario();
		String nombreJugador=arrayJugadores[jugador].getNombre();
		int casillaJugador=arrayJugadores[jugador].getPosicion();
		double dineroJugador=arrayJugadores[jugador].getDineros();
		double precioCasilla;

		if(propietario=="Banca" && casillaJugador==posicion) 
		{
			precioCasilla=arrayCasillas[posicion].getValorCasilla();
			
			if(dineroJugador>=precioCasilla) 
			{
				arrayJugadores[jugador].restaDineros(precioCasilla);
				arrayCasillas[posicion].setPropietario(nombreJugador);
			}
			else
				System.out.println("No tienes los suficientes dineros en tu cartera");
		}
		else if(propietario!=nombreJugador && propietario!="Banca")
		{
			System.out.println("El jugador "+nombreJugador+" quiere comprar esta propiedad");
			System.out.println(propietario+" quieres vender esta propiedad a "+nombreJugador+" ? (s/n)");
			char conf=Leer.datoChar();
			
			System.out.println("Cuanto quieres cobrar por esta propiedad ?");
			precioCasilla=Leer.datoDouble();
			
			if(conf=='s')
			{
				if(dineroJugador>=precioCasilla) 
				{
					arrayJugadores[jugador].restaDineros(precioCasilla);
					arrayCasillas[posicion].setPropietario(nombreJugador);
				}
				else
					System.out.println("No tienes los suficientes dineros en tu cartera");
			}
		}
		else if(propietario==nombreJugador)
			System.out.println("Esta propiedad ya te pertenece");
		else
			System.out.println("No puedes comprar esa propiedad");
	}
	public static void compraEstructura(int posicion, int jugador) 
	{
		//Si eres propietario de la estructura y tienes el dinero puedes poner una estructura
		//Cambiar la condicion de construccion de los hoteles
		//DATOS
		int opcion;
		int num;	//Numero de estructuras a construir
		double coste;	//Coste total de la construccion
		char conf;
		//Variables para facilitar lectura
		String propietario=arrayCasillas[posicion].getPropietario();
		String nombreJugador=arrayJugadores[jugador].getNombre();
		double valorCasa=arrayCasillas[posicion].getValorCasa();
		double valorHotel=arrayCasillas[posicion].getValorHotel();
		double dineroJugador=arrayJugadores[jugador].getDineros();
		int numCasas=arrayCasillas[posicion].getCasas();
		int numHoteles=arrayCasillas[posicion].getHoteles();
		boolean hipotecada=arrayCasillas[posicion].getHipoteca();
		
		if(propietario!=nombreJugador)
			System.out.println("No puedes crear estructuras en una propiedad de "+propietario);
		else if(!hipotecada)
		{
			System.out.println("[1] Comprar una casa ("+valorCasa+")");
			System.out.println("[2] Comprar un hotel ("+valorHotel+")");
			System.out.println("[3] Cancelar");
			opcion=Leer.datoInt();
			switch(opcion) 
			{
			case 1:
				if(numHoteles==0)
				{
					if(numCasas<4) 
					{
						System.out.println("Cuantas casas quieres comprar?");
						num=Leer.datoInt();
						
						if((numCasas+num)<=4)
						{
							coste=valorCasa*num;
							System.out.println("El coste total seria: "+coste+" estas seguro? (s/n)");
							conf=Leer.datoChar();
							
							if(conf=='s')
							{
								if(dineroJugador>=coste) 
								{
									arrayJugadores[jugador].restaDineros(coste);
									arrayCasillas[posicion].agregaCasas(num);
								}	
								else
									System.out.println("No tienes los dineros suficientes para hacer eso");
							}					
						}
						else
							System.out.println("No puedes tener mas de 4 casas por propiedad");
					}
					else
						System.out.println("Ya tienes demasiadas casas en esa propiedad");
				}
				else
					System.out.println("No puedes construir casas donde tienes un hotel");

				break;
				
			case 2:
				if(numCasas==4)
				{
					if(numHoteles==0)
					{
						System.out.println("Cuantos hoteles quieres comprar?");
						System.out.println("El coste total seria: "+valorHotel+" estas seguro? (s/n)");
						conf=Leer.datoChar();
						if(conf=='s')
						{
							if(dineroJugador>=valorHotel) 
							{
								arrayJugadores[jugador].restaDineros(valorHotel);
								arrayCasillas[posicion].agregaHoteles(1);
								arrayCasillas[posicion].quitaCasas(4);
							}
							else
								System.out.println("No tienes los dineros suficientes para hacer eso");
						}						
					}
					else
						System.out.println("Ya tienes un hotel en esta posicion");
				}
				else
					System.out.println("Necesitas tener 4 casas para comprar un hotel");
				break;
				
			default:
				System.out.println("");
			}
		}
		else
			System.out.println("No puedes construir estructuras en una casa hipotecada");
	}
	//MUESTRA
	public static void muestraInfoCasilla(int posicion) 
	{
		//Muestra la informacion de la casilla, si es una propiedad muestra datos de propietario y valo de las estructuras
		//Y si tiene casas u hoteles muestra cuantos hay
		System.out.println("");
		System.out.println("***InfoCasilla ["+posicion+"]***");
		System.out.println("Nombre: "+arrayCasillas[posicion].getNombre());
		System.out.println("Tipo: "+arrayCasillas[posicion].getTipo());
		System.out.println("Grupo: "+arrayCasillas[posicion].getColorGrupo());
		if(arrayCasillas[posicion].getTipo()=="Propiedad") 
		{
			System.out.println("Propietario: "+arrayCasillas[posicion].getPropietario());
			System.out.println("Valor: "+arrayCasillas[posicion].getValorCasilla());
			System.out.println("Alquiler: "+arrayCasillas[posicion].getPrecioAlquiler());
			System.out.println("Coste casa: "+arrayCasillas[posicion].getValorCasa());
			System.out.println("Coste hotel: "+arrayCasillas[posicion].getValorHotel());
			
			if(arrayCasillas[posicion].getPropietario()!="Banca") 
			{
				if(arrayCasillas[posicion].getCasas()!=0)
					System.out.println("Casas: "+arrayCasillas[posicion].getCasas());
				if(arrayCasillas[posicion].getHoteles()!=0)
					System.out.println("Hoteles: "+arrayCasillas[posicion].getHoteles());							
			}
		}
	}
	public static void muestraInfoJugador(int jugador) 
	{
		System.out.println("");
		System.out.println("***Info de "+arrayJugadores[jugador].getNombre()+"***");
		System.out.println("Dineros en cartera: "+arrayJugadores[jugador].getDineros());
		System.out.println("Posicion: "+arrayJugadores[jugador].getPosicion());
	}
	public static void muestraPropiedades(String nombreJugador) 
	{
		//Muestra las propiedades del jugador que le indicamos
		
		System.out.println("");
		System.out.println(nombreJugador+" es dueño de: ");
		
		for(int i=0; i<arrayCasillas.length; i++) 
		{
			String propietario=arrayCasillas[i].getPropietario();
			String color=arrayCasillas[i].getColorGrupo();
			
			if(propietario==nombreJugador)
				System.out.println("["+i+"] "+arrayCasillas[i].getNombre()+" ("+color+")");
		}
	}
	static public void muestraTablero() 
	{
		int numCasilla=0;
		
		//Imprime la primera fila de casillas
		for(int i=0; i<11; i++)	
		{
			System.out.print("["+numCasilla+"] \t");
			numCasilla++;
		}
		System.out.println("");
		
		//Imprime las columnas
		int numCasillaParalelo=numCasilla+28;
		for(int i=0; i<11; i++) 
		{
			
			System.out.println("["+numCasillaParalelo+"] \t \t \t \t \t \t \t \t \t \t["+numCasilla+"]");	
			numCasilla++;
			numCasillaParalelo--;
		}
		
		//Imprime la fila de abajo
		numCasilla+=10;
		for(int i=0; i<11; i++)
		{
			System.out.print("["+numCasilla+"] \t");
			numCasilla--;
		}
		
		System.out.println("");
	}	
	//HIPOTECAR
	public static void hipotecarEstructura(int posicion, int jugador) 
	{
		//DATOS
		int opcion;
		int num;	//Numero de estructuras a construir
		double ganancia;	//Coste total de la construccion
		char conf;
		//Variables para facilitar lectura
		String propietario=arrayCasillas[posicion].getPropietario();
		String nombreJugador=arrayJugadores[jugador].getNombre();
		double valorHipotecarCasa=(arrayCasillas[posicion].getValorCasa()/2);
		double valorHipotecarHotel=(arrayCasillas[posicion].getValorHotel()/2);
		int numCasas=arrayCasillas[posicion].getCasas();
		int numHoteles=arrayCasillas[posicion].getHoteles();
		boolean hipotecada=arrayCasillas[posicion].getHipoteca();
		
		if(propietario!=nombreJugador)
			System.out.println("No puedes hipotecar estructuras de "+propietario);
		else if(!hipotecada)
		{
			System.out.println("[1] Hipotecar una casa ("+valorHipotecarCasa+")");
			System.out.println("[2] Hipotecar un hotel ("+valorHipotecarHotel+")");
			System.out.println("[3] Cancelar");
			opcion=Leer.datoInt();
			switch(opcion) 
			{
			case 1:
				System.out.println("Cuantas casas quieres hipotecar?");
				num=Leer.datoInt();
				if(num<=numCasas)
				{
					ganancia=valorHipotecarCasa*num;
					System.out.println("La ganancia total seria: "+ganancia+" estas seguro? (s/n)");
					conf=Leer.datoChar();
					if(conf=='s')
					{
						arrayJugadores[jugador].sumaDineros(ganancia);
						arrayCasillas[posicion].quitaCasas(num);
					}					
				}
				else
					System.out.println("Solo tienes "+numCasas);
				break;
				
			case 2:
				if(numHoteles==1)
				{
					System.out.println("La ganancia total seria: "+valorHipotecarHotel+" estas seguro? (s/n)");
					conf=Leer.datoChar();
					if(conf=='s')
					{
						arrayJugadores[jugador].sumaDineros(valorHipotecarHotel);
						arrayCasillas[posicion].quitaHoteles(1);
						arrayCasillas[posicion].agregaCasas(4);
					}		
				}
				else
					System.out.println("No tienes ningun hotel para hipotecar");
				break;
				
			default:
				System.out.println("");
			}
		}
		else
			System.out.println("No puedes hipotecar una casa que ya esta hipotecada");
	}
	public static void hipotecarPropiedad(int posicion, int jugador) 
	{
		//DATOS
		double ganancia;
		char conf;
		//Variables inutiles para facilitar lectura
		String propietario=arrayCasillas[posicion].getPropietario();
		String nombreJugador=arrayJugadores[jugador].getNombre();
		String nombreCasilla=arrayCasillas[posicion].getNombre();
		int numCasas=arrayCasillas[posicion].getCasas();
		int numHoteles=arrayCasillas[posicion].getHoteles();
		
		if(propietario!=nombreJugador)
			System.out.println(nombreCasilla +"  no te pertecene");
		else 
		{
			if(numCasas==0 && numHoteles==0)
			{
				ganancia=arrayCasillas[posicion].getValorCasilla()/2;
				
				System.out.println("Estas seguro que quieres hipotecar "+nombreCasilla+"por "+ganancia+" ? (s/n)");
				conf=Leer.datoChar();
				if(conf=='s')
				{
					arrayJugadores[jugador].sumaDineros(ganancia);
					arrayCasillas[posicion].setHipoteca(true);
				}
			}
			else
				System.out.println("No puedes hipotecar una propiedad con estructuras");
		}
	}
	public static void pagaHipoteca(int posicion, int jugador) 
	{
		boolean hipotecada=arrayCasillas[posicion].getHipoteca();
		double costeHipoteca=arrayCasillas[posicion].getValorCasilla()/2;
		double pagoTotal=costeHipoteca+(costeHipoteca*0.1);
		double dineroJugador=arrayJugadores[jugador].getDineros();
		
		if(hipotecada)
		{
			if(dineroJugador>=pagoTotal)
			{
				arrayJugadores[jugador].restaDineros(pagoTotal);
				arrayCasillas[posicion].setHipoteca(false);
			}
			else
				System.out.println("No tienes los dineros suficientes");
		}
		else
			System.out.println("Esta propiedad no esta hipotecada");
	}
	//Alquiler
	public static void pagaAlquiler(int posicion, int jugador) 
	{
		//Variables para facilitar lectura
		boolean Hipotecada=arrayCasillas[posicion].getHipoteca();
		String propietario=arrayCasillas[posicion].getPropietario();
		String nombreJugador=arrayJugadores[jugador].getNombre();
		double alquiler=arrayCasillas[posicion].getPrecioAlquiler();
		String grupo=arrayCasillas[posicion].getColorGrupo();
		
		if(!Hipotecada && propietario!=nombreJugador && propietario!="Banca") 
		{
			if(compruebaGrupo(propietario, grupo))
				alquiler*=2;
			System.out.println("Has caido en la casilla "+propietario+" tendras que pagar: "+alquiler);
			arrayJugadores[jugador].restaDineros(alquiler);
		}
	}
	//Grupos
	public static boolean compruebaGrupo(String nombreJugador, String grupo) 
	{
		//DATOS
		boolean tieneGrupo=false;
		int numTotalGrupo=arrayCasillas[0].getTotalColor(grupo); //Usamos esta casilla para pedir el total porque son variables staticas y da igual
		int numTotalJugador=0;
		
		for(int i=0; i<arrayCasillas.length; i++) 
		{
			//Variables de lectura
			String colorGrupo=arrayCasillas[i].getColorGrupo();
			String propietario=arrayCasillas[i].getPropietario();
			
			if(colorGrupo==grupo && propietario==nombreJugador)
					numTotalJugador++;
		}
		
		System.out.println("GRUPO"+tieneGrupo);

		if(numTotalGrupo==numTotalJugador)
			tieneGrupo=true;
		
		return tieneGrupo;
	}
}