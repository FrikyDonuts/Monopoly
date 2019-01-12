public class JMonopoly 
{
	//Monopoly 1.0
	/*Lista de efectos:
 	* 0: Nada
 	* 1: Avanzas x pasos
 	* 2: Retrocedes x pasos
 	* 3: Pagar a banca
 	* 4: Pagar a todos los jugadores
 	* 5: La banca te paga x dineros
 	* 6: Cada jugador te paga x dinero
 	* 7: Ir a la carcel 
 	* 8: Salir de la carcel -> Esta carta te la puedes quedar 
 	* 9: Ve a la salida
 	*/
	//DATOS
	static Casillas arrayCasillas[]=new Casillas[40];	//Donde guardamos todas las casillas del juego con sus datos
	static Jugador arrayJugadores[];	//Donde guardaremos a todos los jugadores
	static Cartas arrayCartasComunidad[]=new Cartas[15];	//Donde guardaremos todas las cartas
	static Deck comunidad = new Deck(15);	//Creamos un objeto deck que reparte y baraja las cartas
	static Cartas arrayCartasSuerte[]=new Cartas[15];
	static Deck suerte = new Deck(15);
	static Dados d6 = new Dados(6);	//El dado que lanzamos
	static int []turnos;	//TODO Donde guardaremos el orden de los turnos de la partida
	/*TODO:
	*Que los turnos se ordenen por un lanzamiento de dados de mayor a menor al principio de la partida
	*El alquiler aumenta dependiendo el numero de casas y de hotel
	*modificar muestr tablero para que muestra la ficha de los jugadores en su respectiva posicion (despues de tirar)
	*Cambiar hoteles por un boolean ya que solo puede haber uno por propiedad
	*/

	public static void main(String[] args) 
	{
		creaJugadores();
		creaCasillas();
		creaCartas();
		juego();
	}
	
	//PRE-JUEGO
	private static void creaJugadores() 
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
		
		System.out.println("");
	}
	private static void creaCasillas() 
	{
		arrayCasillas[0]=new Casillas("Salida", 5 ,200);
		arrayCasillas[1]=new Casillas("Alcobendas", 600, "Marron");
		arrayCasillas[2]=new Casillas("Caja de comunidad", "Comunidad");
		arrayCasillas[3]=new Casillas("San Fernando", 800, "Marron");
		arrayCasillas[4]=new Casillas("Impuestos", 3, 200);
		arrayCasillas[5]=new Casillas("Estacion de tren", 1000, "Estacion");
		arrayCasillas[6]=new Casillas("Cartagena", 1100, "Azul");
		arrayCasillas[7]=new Casillas("Suerte", "Suerte");
		arrayCasillas[8]=new Casillas("Paterna", 1300, "Azul");
		arrayCasillas[9]=new Casillas("Mijas", 1400, "Azul");
		arrayCasillas[10]=new Casillas("Prision", 0, 0);
		arrayCasillas[11]=new Casillas("Guadalajara", 1600, "Rosa");
		arrayCasillas[12]=new Casillas("Compañia de electricidad", 1700, "Servicio");
		arrayCasillas[13]=new Casillas("Boadilla del Monte", 1800, "Rosa");
		arrayCasillas[14]=new Casillas("Ponferrada", 1900, "Rosa");
		arrayCasillas[15]=new Casillas("Estacion de tren2", 2000, "Estacion");
		arrayCasillas[16]=new Casillas("Salamanca", 2100, "Naranja");
		arrayCasillas[17]=new Casillas("Caja de comunidad2", "Comunidad");
		arrayCasillas[18]=new Casillas("Mérida", 2300, "Naranja");
		arrayCasillas[19]=new Casillas("Alicante", 2400, "Naranja");
		arrayCasillas[20]=new Casillas("Parking gratuito", 0, 0);
		arrayCasillas[21]=new Casillas("PropiedadPrueba1", 2600,"Rojo");
		arrayCasillas[22]=new Casillas("Suerte2", "Suerte");
		arrayCasillas[23]=new Casillas("PropiedadPrueba3", 2700, "Rojo");
		arrayCasillas[24]=new Casillas("PropiedadPrueba4", 2800, "Rojo");
		arrayCasillas[25]=new Casillas("Estacion de tren3", 2900, "Estacion");
		arrayCasillas[26]=new Casillas("PropiedadPrueba5", 3000, "Amarillo");
		arrayCasillas[27]=new Casillas("PropiedadPrueba6", 3100, "Amarillo");
		arrayCasillas[28]=new Casillas("Compañia de agua", 3200, "Servicio");
		arrayCasillas[29]=new Casillas("PropiedadPrueba8", 3300,"Amarillo");
		arrayCasillas[30]=new Casillas("Ve a la carcel", 7, 0);
		arrayCasillas[31]=new Casillas("PropiedadPrueba10", 3400, "Verde");
		arrayCasillas[32]=new Casillas("PropiedadPrueba11", 3500, "Verde");
		arrayCasillas[33]=new Casillas("Caja de comunidad3", "Comunidad");
		arrayCasillas[34]=new Casillas("PropiedadPrueba13", 3600, "Verde");
		arrayCasillas[35]=new Casillas("Estacion de tren4", 3700, "Estacion");
		arrayCasillas[36]=new Casillas("Suerte3", "Suerte");
		arrayCasillas[37]=new Casillas("PropiedadPrueba16", 3800, "Negro");
		arrayCasillas[38]=new Casillas("Impuesto de lujo", 3, 200);
		arrayCasillas[39]=new Casillas("PropiedadPrueba17", 3900, "Negro");
	}
	private static void creaCartas() 
	{
		arrayCartasComunidad[0]=new Cartas(1, 10, "Avanzas 10 casillas");
		arrayCartasComunidad[1]=new Cartas(2, 10, "Retrocedes 10 casillas");
		arrayCartasComunidad[2]=new Cartas(3, 1000, "Pagas 1000€ a la banca");
		arrayCartasComunidad[3]=new Cartas(4, 1000, "Pagas 1000€ a todos los jugadores");
		arrayCartasComunidad[4]=new Cartas(5, 1000, "La banca te paga 1000€");
		arrayCartasComunidad[5]=new Cartas(6, 1000, "Cada jugador te paga 1000€");
		arrayCartasComunidad[6]=new Cartas(7, "Te vieron hacer trampas, ve a la carcel");
		arrayCartasComunidad[7]=new Cartas(9, "Adelanta hasta la salida");
		arrayCartasComunidad[8]=new Cartas(1, 5, "Avanza 5 casillas");
		arrayCartasComunidad[9]=new Cartas(2, 5, "Retrocede 5 casillas");
		arrayCartasComunidad[10]=new Cartas(3, 500, "Pagas 500€ a la banca");
		arrayCartasComunidad[11]=new Cartas(4, 500, "Pagas 500€ a todos los jugadores");
		arrayCartasComunidad[12]=new Cartas(5, 2000, "La banca te paga 2000€");
		arrayCartasComunidad[13]=new Cartas(8, "Carta para salir de la carcel");
		arrayCartasComunidad[14]=new Cartas(9, "Adelanza hasta la salida");

		arrayCartasSuerte[0]=new Cartas(1, 10, "Avanzas 10 casillas");
		arrayCartasSuerte[1]=new Cartas(2, 10, "Retrocedes 10 casillas");
		arrayCartasSuerte[2]=new Cartas(3, 1000, "Pagas 1000€ a la banca");
		arrayCartasSuerte[3]=new Cartas(4, 1000, "Pagas 1000€ a todos los jugadores");
		arrayCartasSuerte[4]=new Cartas(5, 1000, "La banca te paga 1000€");
		arrayCartasSuerte[5]=new Cartas(6, 1000, "Cada jugador te paga 1000€");
		arrayCartasSuerte[6]=new Cartas(7, "Te vieron hacer trampas, ve a la carcel");
		arrayCartasSuerte[7]=new Cartas(9, "Adelanta hasta la salida");
		arrayCartasSuerte[8]=new Cartas(1, 5, "Avanza 5 casillas");
		arrayCartasSuerte[9]=new Cartas(2, 5, "Retrocede 5 casillas");
		arrayCartasSuerte[10]=new Cartas(3, 500, "Pagas 500€ a la banca");
		arrayCartasSuerte[11]=new Cartas(4, 500, "Pagas 500€ a todos los jugadores");
		arrayCartasSuerte[12]=new Cartas(5, 2000, "La banca te paga 2000€");
		arrayCartasSuerte[13]=new Cartas(8, "Carta para salir de la carcel");
		arrayCartasSuerte[14]=new Cartas(9, "Adelanza hasta la salida");
	}
	//JUEGO
	private static void juego()	
	{
		boolean victoria=false;
		int turnoJugador=0;	//El turno de cada jugador dependera del numero que tengan asignado en el array de jugadores
		
		while(!victoria) 
		{
			muestraTablero();
			
			String nombreJugador=arrayJugadores[turnoJugador].getNombre();
			System.out.println("");
			System.out.println("");
			System.out.println("Es el turno de: "+nombreJugador);
			
			boolean bancarrota=arrayJugadores[turnoJugador].getBancarrota();
			if(!bancarrota) 
			{
				int turnosCarcel=arrayJugadores[turnoJugador].getTurnosCarcel();
				if(turnosCarcel==0) 
				{
					int dados=lanzaDados(turnoJugador);
					arrayJugadores[turnoJugador].adelantaPosicion(dados);
					
					int casillaActual=arrayJugadores[turnoJugador].getPosicion();
					String tipoCasilla=arrayCasillas[casillaActual].getTipo();
					String grupoCasilla=arrayCasillas[casillaActual].getGrupo();
					
					if(tipoCasilla=="Efecto")
					{
						efecto(casillaActual, turnoJugador);						
					}
					else if(tipoCasilla=="Comunidad" || tipoCasilla=="Suerte")
						cogeCarta(casillaActual, turnoJugador);
					else if(tipoCasilla=="Propiedad" && grupoCasilla=="Estacion")
						pagaEstacion(casillaActual, turnoJugador);
					else if(tipoCasilla=="Propiedad" && grupoCasilla=="Servicio")
						pagaServicio(dados, casillaActual, turnoJugador);
					else
						pagaAlquiler(casillaActual, turnoJugador);
					
					/*TODO: Despues de una casilla de efecto/suerte/comunidad la casilla del jugador
					 * puede cambiar, modificar esto para que despues de moverse segun el efecto 
					 * tenga que interactuar con la nueva casilla actual y que muestre la informacion de esta
					 */
					
					muestraInfoJugador(turnoJugador);
					muestraInfoCasilla(casillaActual);
					pideAccion(casillaActual, turnoJugador);
					
					double dineroJugador=arrayJugadores[turnoJugador].getDineros();
					while(dineroJugador<0 && !bancarrota)
					{
						System.out.println("Tu saldo es negavito! X_X ");
						System.out.println("No puedes continuar a menos que consigas dinero o declares bancarrota");
						muestraInfoJugador(turnoJugador);
						pideAccion(casillaActual, turnoJugador);
						
						bancarrota=arrayJugadores[turnoJugador].getBancarrota();
						dineroJugador=arrayJugadores[turnoJugador].getDineros();
					}
					
					System.out.println("");				
				}
				else
				{
					System.out.println("Estas en la carcel");
					
					int cartaComunidad=arrayJugadores[turnoJugador].getCartaComunidad();
					int cartaSuerte=arrayJugadores[turnoJugador].getCartaSuerte();
					
					if(cartaComunidad!=-1 || cartaSuerte!=-1)
					{
						System.out.println("Tienes una carta para salir de la carcel deseas usarla? (s/n");
						char conf=Leer.datoChar();
						if(conf=='s')
						{
							if(cartaComunidad!=-1 && cartaSuerte!=-1)
							{
								System.out.println("Cual? [1]Carta Comunidad // [2]Carta Suerte");
								int op=Leer.datoInt();
								if(op==1)
								{
									int cartaCarcel=arrayJugadores[turnoJugador].getCartaComunidad();
									arrayCartasComunidad[cartaCarcel].setDuenho(false);
									arrayJugadores[turnoJugador].quitaCartaComunidad();
									arrayJugadores[turnoJugador].setTurnosCarcel(0);
								}
								else if(op==2)
								{
									int cartaCarcel=arrayJugadores[turnoJugador].getCartaSuerte();
									arrayCartasSuerte[cartaCarcel].setDuenho(false);
									arrayJugadores[turnoJugador].quitaCartaSuerte();
									arrayJugadores[turnoJugador].setTurnosCarcel(0);
								}
							}
							else if(cartaComunidad!=-1)
							{
								int cartaCarcel=arrayJugadores[turnoJugador].getCartaComunidad();
								arrayCartasComunidad[cartaCarcel].setDuenho(false);
								arrayJugadores[turnoJugador].quitaCartaComunidad();
								arrayJugadores[turnoJugador].setTurnosCarcel(0);
							}
							else
							{
								int cartaCarcel=arrayJugadores[turnoJugador].getCartaSuerte();
								arrayCartasSuerte[cartaCarcel].setDuenho(false);
								arrayJugadores[turnoJugador].quitaCartaSuerte();
								arrayJugadores[turnoJugador].setTurnosCarcel(0);
							}
						}
					}
					else 
					{
						
						System.out.println("Quieres pagar 50€ para salir en el proximo turno? (s/n)");
						char conf=Leer.datoChar();
						if(conf=='s')
						{
							arrayJugadores[turnoJugador].restaDineros(50);
							arrayJugadores[turnoJugador].setTurnosCarcel(0);
						}
						else 
						{
							System.out.println("Te quedan "+(turnosCarcel-1)+" turnos en la carcel");
							arrayJugadores[turnoJugador].restaTurnosCarcel(1);
						}
					}
				}	
				
			}
			else
				System.out.println(nombreJugador+" esta en bancarrota");
	
			victoria=compruebaVictoria();
			
			turnoJugador++;
			
			if(turnoJugador==arrayJugadores.length)
				turnoJugador=0;
		}
		
		int ganador=compruebaGanador();
		System.out.println("Enhorabuena "+arrayJugadores[ganador].getNombre()+" eres un cerdo capitalista!");

	}
	private static int lanzaDados(int jugador) 
	{
		//Lanzamos dos veces el d6 y sumamos los resultados, si hay dobles se repite y en caso de 3 veces dobles carcel
		int dado1=d6.lanza();
		int dado2=d6.lanza();
		int movimientos=dado1+dado2;
		System.out.println("Ha salido: "+dado1+" y un "+dado2);
		
		if(dado1==dado2)
		{
			System.out.println("DOBLES, lanza otra vez");
			dado1=d6.lanza();
			dado2=d6.lanza();
			System.out.println("Ha salido: "+dado1+" y un "+dado2);
			movimientos+=dado1+dado2;
			
			if(dado1==dado2)
			{
				System.out.println("DOBLES OTRA VEZ, lanza otra vez");
				dado1=d6.lanza();
				dado2=d6.lanza();
				System.out.println("Ha salido: "+dado1+" y un "+dado2);
				movimientos+=dado1+dado2;
				
				if(dado1==dado2)
				{
					System.out.println("Dobles 3 veces, vas a la carcel");
					arrayJugadores[jugador].setPosicion(11);
					return 0;
				}
			}
		}
		System.out.println("Avanzas "+movimientos+" casillas");
		System.out.println("");
		return movimientos;
	}
	private static void pideAccion(int posicion, int jugador) 
	{
		boolean finTurno=false;
		while(!finTurno) 
		{
			System.out.println("[1] Ver informacion de una casilla");
			System.out.println("[2] Comprar propiedad"); //TODO: Vender una propiedad a un jugador y subasta(?)
			System.out.println("[3] Mis propiedades");
			System.out.println("[4] Crear estructuras");	
			System.out.println("[5] Hipotecar estructuras");	
			System.out.println("[6] Hipotecar propiedad");
			System.out.println("[7] Comprar otra propiedad");	
			System.out.println("[8] Ver las propiedades de un jugador");
			System.out.println("[9] Pagar hipotecas");
			System.out.println("[10] Mostrar un grupo");
			System.out.println("[11] Vender una tarjeta de salir de la carcel");
			System.out.println("[0] Declararse en bancarrota");
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
				venderEstructura(casilla, jugador);
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
			case 10:
				System.out.println("Que grupo deseas ver?");
				String grupo=Leer.dato();
				muestraGrupo(grupo);
				break;
			case 11:	
				System.out.println("A que jugador quieres vender la tarjeta?");
				nombre=Leer.dato();
				System.out.println("Por cuantos dineros?");
				double coste=Leer.datoDouble();
				vendeCarta(jugador, nombre, coste);
				break;
			case 0:
				System.out.println("Estas seguro? (s/n)");
				conf=Leer.datoChar();
				if(conf=='s')
				{
					arrayJugadores[jugador].setBancarrota(true);
					finTurno=true;
				}
				break;
			default:
				finTurno=true;
			}	
		}
	}
	//COMPRA
	private static void compraCasilla(int posicion, int jugador)
	{
		//Variables para facilitar la lectura
		String propietario=arrayCasillas[posicion].getPropietario();
		String nombreJugador=arrayJugadores[jugador].getNombre();
		String tipoCasilla=arrayCasillas[posicion].getTipo();
		int casillaJugador=arrayJugadores[jugador].getPosicion();
		double dineroJugador=arrayJugadores[jugador].getDineros();
		double precioCasilla;

		if(tipoCasilla=="Propiedad")
		{
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
				
				if(conf=='s')
				{
					System.out.println("Cuanto quieres cobrar por esta propiedad ?");
					precioCasilla=Leer.datoDouble();
					System.out.println(nombreJugador+" aceptas el trato? (s/n)");
					conf=Leer.datoChar();
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
			}
			else if(propietario==nombreJugador)
				System.out.println("Esta propiedad ya te pertenece");
		}
		else
			System.out.println("No puedes comprar esa propiedad");
	}
	private static void compraEstructura(int posicion, int jugador) 
	{
		//Variables para facilitar lectura
		String propietario=arrayCasillas[posicion].getPropietario();
		String nombreJugador=arrayJugadores[jugador].getNombre();
		String grupo=arrayCasillas[posicion].getGrupo();
		double valorCasa=arrayCasillas[posicion].getValorCasa();
		double valorHotel=arrayCasillas[posicion].getValorHotel();
		double dineroJugador=arrayJugadores[jugador].getDineros();
		int numCasas=arrayCasillas[posicion].getCasas();
		int numHoteles=arrayCasillas[posicion].getHoteles();
		boolean hipotecada=arrayCasillas[posicion].getHipoteca();
		//DATOS
		double coste;	//Coste total de la construccion
		char conf;
		int opcion;
		int num;	//Numero de estructuras a construir
		
		if(grupo!="Estacion" && grupo!="Servicio")
		{
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
	}
	//MUESTRA
	private static void muestraInfoCasilla(int posicion) 
	{
		System.out.println("***InfoCasilla ["+posicion+"]***");
		System.out.println("Nombre: "+arrayCasillas[posicion].getNombre());
		System.out.println("Tipo: "+arrayCasillas[posicion].getTipo());
		if(arrayCasillas[posicion].getTipo()=="Propiedad") 
		{
			System.out.println("Grupo: "+arrayCasillas[posicion].getGrupo());
			System.out.println("Propietario: "+arrayCasillas[posicion].getPropietario());
			System.out.println("Valor: "+arrayCasillas[posicion].getValorCasilla());
			System.out.println("Alquiler: "+arrayCasillas[posicion].getPrecioAlquiler()); //TODO: Que muestre el alquiler base y el actual
			if(arrayCasillas[posicion].getGrupo()!="Estacion" && arrayCasillas[posicion].getGrupo()!="Servicio")
			{
				System.out.println("Coste casa: "+arrayCasillas[posicion].getValorCasa());
				System.out.println("Coste hotel: "+arrayCasillas[posicion].getValorHotel());
				if(arrayCasillas[posicion].getPropietario()!="Banca") 
				{
					if(arrayCasillas[posicion].getCasas()!=0)
						System.out.println("Casas: "+arrayCasillas[posicion].getCasas());
					if(arrayCasillas[posicion].getHoteles()!=0)
						System.out.println("Hoteles: "+arrayCasillas[posicion].getHoteles());	
					if(arrayCasillas[posicion].getHipoteca())
						System.out.println("Hipotecada");
				}
			}
		}
		System.out.println("");
	}
	private static void muestraInfoJugador(int jugador) 
	{
		System.out.println("***Info de "+arrayJugadores[jugador].getNombre()+"***");
		System.out.println("Dineros en cartera: "+arrayJugadores[jugador].getDineros()+"€");
		System.out.println("Posicion: "+arrayJugadores[jugador].getPosicion());
		int cartasCarcel=0;
		if(arrayJugadores[jugador].getCartaSuerte()!=-1)
			cartasCarcel++;
		if(arrayJugadores[jugador].getCartaComunidad()!=-1)
			cartasCarcel++;
		if(cartasCarcel!=0)
			System.out.println("Cartas salir de carcel: "+cartasCarcel);
		System.out.println("");
	}
	private static void muestraPropiedades(String nombreJugador) 
	{
		//Muestra las propiedades del jugador que le indicamos
		
		System.out.println("");
		System.out.println(nombreJugador+" es dueño de: ");
		
		for(int i=0; i<arrayCasillas.length; i++) 
		{
			String propietario=arrayCasillas[i].getPropietario();
			String color=arrayCasillas[i].getGrupo();
			
			if(propietario==nombreJugador)
				System.out.println("["+i+"] "+arrayCasillas[i].getNombre()+" ("+color+")");
		}
	}
	private static void muestraTablero() 
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
		for(int i=0; i<9; i++) 
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
	}	
	private static void muestraGrupo(String grupo) 
	{
		System.out.println("Las propiedades del grupo "+grupo+" son: ");
		for(int i=0; i<arrayCasillas.length; i++) 
			if(arrayCasillas[i].getTipo()=="Propiedad")
				if(arrayCasillas[i].getGrupo().equals(grupo))
					System.out.println("["+i+"] "+arrayCasillas[i].getNombre()+"-"+arrayCasillas[i].getPropietario());
	}
	//VENTA
	private static void venderEstructura(int posicion, int jugador) 
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
	private static void hipotecarPropiedad(int posicion, int jugador) 
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
	private static void vendeCarta(int jugador, String nombreComprador, double precio) 
	{
		int comprador=-1;
		
		for(int i=0; i<arrayJugadores.length; i++)	//Buscamos cual es el numero correspondiente al nombreComprador
		{
			if(arrayJugadores[i].getNombre()==nombreComprador)
				comprador=i;
		}
		if(comprador==-1)	//La unica forma de que no cambie es que el nombre no fuera correcto
		{
			System.out.println("Ese nombre no es correcto");
		}
		else
		{
			int cartaComunidad=arrayJugadores[jugador].getCartaComunidad();
			int cartaSuerte=arrayJugadores[jugador].getCartaSuerte();
			
			if(cartaComunidad!=-1 || cartaSuerte!=-1)	//Verificamos que el jugador tenga alguna carta
			{
				if(cartaComunidad!=-1 && cartaSuerte!=-1)	//Si tiene las 2 que decida cual vender
				{
					System.out.println("Que carta desea vender? [1]Comunidad / [2]Suerte");
					int op=Leer.datoInt();
					if(op==1)
					{
						System.out.println(nombreComprador+" deseas comprarla la tarjeta por "+precio+"? (s/n)");
						char conf=Leer.datoChar();
						if(conf=='s')
						{
							double dinerosComprador=arrayJugadores[comprador].getDineros();
							if(dinerosComprador>=precio)
							{
								arrayJugadores[comprador].restaDineros(precio);
								arrayJugadores[jugador].sumaDineros(precio);
								arrayJugadores[comprador].agregaCartaComunidad(cartaComunidad);
								arrayJugadores[jugador].quitaCartaComunidad();
							}
						}
					}
					if(op==2)
					{
						System.out.println(nombreComprador+" deseas comprarla la tarjeta por "+precio+"? (s/n)");
						char conf=Leer.datoChar();
						if(conf=='s')
						{
							double dinerosComprador=arrayJugadores[comprador].getDineros();
							if(dinerosComprador>=precio)
							{
								arrayJugadores[comprador].restaDineros(precio);
								arrayJugadores[jugador].sumaDineros(precio);
								arrayJugadores[comprador].agregaCartaSuerte(cartaSuerte);
								arrayJugadores[jugador].quitaCartaSuerte();
							}
						}
					}	
				}
			}		
		}
	}
	//PAGAR
	private static void pagaHipoteca(int posicion, int jugador) 
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
	private static void pagaAlquiler(int posicion, int jugador) 
	{
		//Variables para facilitar lectura
		int numCasas=arrayCasillas[posicion].getCasas();
		int numHotel=arrayCasillas[posicion].getHoteles();
		boolean hipotecada=arrayCasillas[posicion].getHipoteca();
		double alquiler=arrayCasillas[posicion].getPrecioAlquiler();
		String propietario=arrayCasillas[posicion].getPropietario();
		String nombreJugador=arrayJugadores[jugador].getNombre();
		String grupo=arrayCasillas[posicion].getGrupo();
		
		if(!hipotecada && propietario!=nombreJugador && propietario!="Banca") 
		{
			if(numHotel==1)
				alquiler*=55;
			if(numCasas==4)
				alquiler*=45;
			if(numCasas==3)
				alquiler*=35;
			if(numCasas==2)
				alquiler*=10;
			if(numCasas==1)
				alquiler*=5;
			if(compruebaGrupo(propietario, grupo))
				alquiler*=2;
			System.out.println("Has caido en la casilla "+propietario+" tendras que pagar: "+alquiler);
			arrayJugadores[jugador].restaDineros(alquiler);				
		}
	}
	private static void pagaEstacion(int posicion, int jugador) 
	{
		boolean hipotecada=arrayCasillas[posicion].getHipoteca();
		double alquiler=arrayCasillas[posicion].getPrecioAlquiler();
		String grupo=arrayCasillas[posicion].getGrupo();
		String propietario=arrayCasillas[posicion].getPropietario();
		String nombreJugador=arrayJugadores[jugador].getNombre();
		
		if(!hipotecada && propietario!=nombreJugador && propietario!="Banca") 
		{
			alquiler*=arrayCasillas[posicion].getTotalGrupo(grupo);
			System.out.println("Has caido en la casilla "+propietario+" tendras que pagar: "+alquiler);
			arrayJugadores[jugador].restaDineros(alquiler);
		}
	}
	private static void pagaServicio(int dados, int posicion, int jugador) 
	{
		boolean hipotecada=arrayCasillas[posicion].getHipoteca();
		String grupo=arrayCasillas[posicion].getGrupo();
		String propietario=arrayCasillas[posicion].getPropietario();
		String nombreJugador=arrayJugadores[jugador].getNombre();
		
		if(!hipotecada && propietario!=nombreJugador && propietario!="Banca") 
		{
			System.out.println("Caiste en el servicio de "+propietario);
			double alquiler;
			int numTotalServicios=arrayCasillas[posicion].getTotalGrupo(grupo);
			int numServiciosPropietario=cuentaGrupo(propietario, grupo);

			if(numServiciosPropietario==numTotalServicios)
				alquiler=dados*10;
			else
				alquiler=dados*4;
			
			System.out.println("Pierdes "+alquiler);
			arrayJugadores[jugador].restaDineros(alquiler);
		}
	}
	//CARTAS
	private static void cogeCarta(int posicion, int jugador) 
	{
		String tipoCasilla=arrayCasillas[posicion].getTipo();
		int carta=0;
		boolean tieneDuenho=true;
		
		if(tipoCasilla=="Comunidad")
		{
			while(tieneDuenho)
			{
				System.out.println("Sacas una carta de comunidad");
				System.out.println("Toca la carta de pila numero "+comunidad.getCartaPila());
				
				if(comunidad.compruebaDeck())
				{
					System.out.println("No quedaban cartas, barajando...");
					comunidad.barajaDeck();
				}
				
				carta=comunidad.dameCarta();
				tieneDuenho=arrayCartasComunidad[carta].getDuenho();
			}
		}
		else if(tipoCasilla=="Suerte") 
		{
			while(tieneDuenho)
			{
				System.out.println("Sacas una carta de comunidad");
				System.out.println("Toca la carta de pila numero "+suerte.getCartaPila());
				
				if(suerte.compruebaDeck())
				{
					System.out.println("No quedaban cartas, barajando...");
					suerte.barajaDeck();
				}
				
				carta=suerte.dameCarta();
				tieneDuenho=arrayCartasSuerte[carta].getDuenho();
			}
		}
		
		efecto(tipoCasilla, carta, jugador);
	}
	//GRUPO
	private static boolean compruebaGrupo(String nombreJugador, String grupo) 
	{
		//DATOS
		boolean tieneGrupo=false;
		int numTotalGrupo=arrayCasillas[1].getTotalGrupo(grupo); //Usamos esta casilla para pedir el total porque son variables staticas y da igual
		int numTotalJugador=cuentaGrupo(nombreJugador, grupo);
		
		if(numTotalGrupo==numTotalJugador)
			tieneGrupo=true;
		
		return tieneGrupo;
	}
	private static int cuentaGrupo(String nombreJugador, String grupo) 
	{
		int numTotalJugador=0;
		
		for(int i=0; i<arrayCasillas.length; i++) 
		{
			//Variables de lectura
			String colorGrupo=arrayCasillas[i].getGrupo();
			String propietario=arrayCasillas[i].getPropietario();
			
			if(colorGrupo==grupo && propietario==nombreJugador)
					numTotalJugador++;
		}
		
		return numTotalJugador;
	}
	//EFECTO
	private static void efecto(String tipoCarta, int carta, int jugador)	
	{	
		//DATOS
		int efecto=0;
		double num=0;
		
		if(tipoCarta=="Suerte")
		{
			efecto=arrayCartasSuerte[carta].getEfecto();
			num=arrayCartasSuerte[carta].getNum();
		}
		else 
		{
			efecto=arrayCartasComunidad[carta].getEfecto();
			num=arrayCartasComunidad[carta].getNum();
		}
		
		switch(efecto) 
		{
		case 0:
			
			System.out.println("Esta casilla no hace nada");
			break;
			
		case 1:
			
			System.out.println("Avanzas "+num+" pasos");
			arrayJugadores[jugador].adelantaPosicion((int)num);
			break;
			
		case 2:
			
			System.out.println("Retrocedes "+num+" pasos");
			arrayJugadores[jugador].regresaPosicion((int)num);
			break;
			
		case 3:
			
			System.out.println("Tienes que pagar a banca "+num+"€");
			arrayJugadores[jugador].restaDineros(num);
			break;
			
		case 4:
			
			System.out.println("Tienes que pagar "+num+" atodos los jugadores");
			for(int jugadores=0; jugadores<arrayJugadores.length; jugadores++)
			{
				if(jugador!=jugadores && !arrayJugadores[jugadores].getBancarrota())
				{
					System.out.println("Le pagas a "+arrayJugadores[jugadores].getNombre()+""+num+"€");
					arrayJugadores[jugador].restaDineros(num);
					arrayJugadores[jugadores].sumaDineros(num);
				}
			}
			break;
			
		case 5:
			
			System.out.println("La banca te paga "+num+"€");
			arrayJugadores[jugador].sumaDineros(num);
			break;
			
		case 6:
			
			System.out.println("Cada jugador te paga "+num+"€");
			for(int jugadores=0; jugadores<arrayJugadores.length; jugadores++)
			{
				if(jugador!=jugadores && !arrayJugadores[jugadores].getBancarrota())
				{
					System.out.println(arrayJugadores[jugadores].getNombre()+" te paga "+num+"€");
					arrayJugadores[jugador].sumaDineros(num);
					arrayJugadores[jugadores].restaDineros(num);
				}
			}
			break;
			
		case 7:
			
			System.out.println("Te han visto hacer trampas, vas a tener que ir a la carcel");
			arrayJugadores[jugador].setPosicion(10);
			arrayJugadores[jugador].setTurnosCarcel(3);
			break;
			
		case 8:
			
			System.out.println("Esta carta te permite salir de la carcel");
			if(tipoCarta=="Suerte")			
			{
				arrayCartasSuerte[carta].setDuenho(true);
				arrayJugadores[jugador].agregaCartaSuerte(carta);
			}
			else 	
			{
				arrayCartasComunidad[carta].setDuenho(true);
				arrayJugadores[jugador].agregaCartaComunidad(carta);
			}	
			break;
			
		case 9:
			
			System.out.println("Ve a la casilla de salida");
			arrayJugadores[jugador].setPosicion(0);
			break;
			
		default:
			System.out.println("Ese efecto no esta disponible");
		}
	} 
	private static void efecto(int posicion, int jugador)	
	{	
		//DATOS
		int efecto=arrayCasillas[posicion].getEfecto();
		double num=arrayCasillas[posicion].getNum();
				
		switch(efecto) 
		{
		case 0:
			
			System.out.println("Esta casilla no hace nada");
			break;
			
		case 1:
			
			System.out.println("Avanzas "+num+" pasos");
			arrayJugadores[jugador].adelantaPosicion((int)num);
			break;
			
		case 2:
			
			System.out.println("Retrocedes "+num+" pasos");
			arrayJugadores[jugador].regresaPosicion((int)num);
			break;
			
		case 3:
			
			System.out.println("Tienes que pagar a banca "+num+"€");
			arrayJugadores[jugador].restaDineros(num);
			break;
			
		case 4:
			
			System.out.println("Tienes que pagar "+num+" atodos los jugadores");
			for(int jugadores=0; jugadores<arrayJugadores.length; jugadores++)
			{
				if(jugador!=jugadores && !arrayJugadores[jugadores].getBancarrota())
				{
					System.out.println("Le pagas a "+arrayJugadores[jugadores].getNombre()+""+num+"€");
					arrayJugadores[jugador].restaDineros(num);
					arrayJugadores[jugadores].sumaDineros(num);
				}
			}
			break;
			
		case 5:
			
			System.out.println("La banca te paga "+num+"€");
			arrayJugadores[jugador].sumaDineros(num);
			break;
			
		case 6:
			
			System.out.println("Cada jugador te paga "+num+"€");
			for(int jugadores=0; jugadores<arrayJugadores.length; jugadores++)
			{
				if(jugador!=jugadores && !arrayJugadores[jugadores].getBancarrota())
				{
					System.out.println(arrayJugadores[jugadores].getNombre()+" te paga "+num+"€");
					arrayJugadores[jugador].sumaDineros(num);
					arrayJugadores[jugadores].restaDineros(num);
				}
			}
			break;
			
		case 7:
			
			System.out.println("Te han visto hacer trampas, vas a tener que ir a la carcel");
			arrayJugadores[jugador].setPosicion(10);
			arrayJugadores[jugador].setTurnosCarcel(3);
			break;
			
		case 8:
			
			System.out.println("Este efecto solo esta disponible en cartas");		
			break;
			
		case 9:
			
			System.out.println("Ve a la casilla de salida");
			arrayJugadores[jugador].setPosicion(0);
			break;
			
		default:
			System.out.println("Ese efecto no esta disponible");
		}
	}
	//VICTORIA
	private static boolean compruebaVictoria() 
	{
		boolean fin=false;
		int jugadoresBancarrota=0;
		
		for(int i=0; i<arrayJugadores.length; i++) 
		{
			if(arrayJugadores[i].getBancarrota())
				jugadoresBancarrota++;
		}
		if(jugadoresBancarrota==(arrayJugadores.length-1))
			fin=true;
		
		return fin;
	}
	private static int compruebaGanador() 
	{
		int ganador=0;
		
		for(int i=0; i<arrayJugadores.length; i++)
			if(!arrayJugadores[i].getBancarrota())
				ganador=i;
		
		return ganador;
	}
}