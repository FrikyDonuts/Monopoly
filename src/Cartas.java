public class Cartas 
{	
	//Los efectos funcionan igual que los de Casillas.java
	private boolean duenho;
	private String tituloCarta;
	private int efectoCarta;
	private double num;
	
	Cartas(int efecto, int cantidad, String titulo)	
	{
		tituloCarta=titulo;
		efectoCarta=efecto;
		num=cantidad;
		duenho=false;
	}
	Cartas(int efecto, String titulo) //Para las cartas que tienen un efecto "fijo" por ejemplo: ve a la carcel
	{
		tituloCarta=titulo;
		efectoCarta=efecto;
		duenho=false;
	}
	
	public double getNum() 
	{
		return this.num;
	}
	public String getTituloCarta() 
	{
		return this.tituloCarta;
	}
	public int getEfecto()
	{
		return this.efectoCarta;
	}
	public boolean getDuenho() 
	{
		return duenho;
	}
	public void setDuenho(boolean duenho) 
	{
		this.duenho = duenho;
	}

}