public class Deck 
{
	private static int[]deck;
	private int cartaPila;
	
	Deck(int numCartas)
	{
		deck=new int[numCartas];
		barajaDeck();
	}
	
	public void barajaDeck() 
	{
		//Inicializamos el deck de cartas a -1 para que cuando verifiquemos que las cartas no esten repetidas el 0 no de problemas
		for(int i=0; i<deck.length; i++)
			deck[i]=-1;
		//Metemos en el mazo de forma al azar un num entre 0-14 que representara la posicion de las cartas.
		int cartasValidas=0;
		while(cartasValidas<deck.length)
		{
			int numRepetidos=0;
			int cartaAzar=(int) (Math.random()*deck.length);	//Generamos una carta al azar 
			
			for(int j=0; j<deck.length; j++)	//La comparamos con las que hay en el deck
			{
				if(cartaAzar==deck[j])
					numRepetidos++;
			}
			
			if(numRepetidos==0)	//Si no esta repetida la guardamos
			{
				deck[cartasValidas]=cartaAzar;
				cartasValidas++;
			}
		}
		//Inicializamos la primera carta de la pila
		cartaPila=0;
	}
	public int dameCarta() 
	{
		int carta=deck[this.cartaPila];
		this.cartaPila++;
		return carta;
	}
	public int getCartaPila() 
	{
		return this.cartaPila;
	}
	public boolean compruebaDeck() 
	{
		//Metodo que devuelve true en el caso de que no queden cartas en el deck
		if(this.cartaPila==deck.length) 
			return true;
		
		return false;
	}
}