public class Dados 
{
	private int caras;
	
	Dados(int carasDado)
	{
		caras=carasDado;
	}
	
	public int lanza() 
	{
		return (int)((Math.random()*caras)+1);
	}
}
