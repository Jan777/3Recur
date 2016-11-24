package Window;


public class Conector {
	private Batalla bat1 =null;
	private Batalla bat2 =null;


	public void add(Batalla bata,boolean player1)
	{
		if(player1)
			this.bat1 = bata;
		else
			this.bat2 = bata;
	}
	public void deshabilitarBotones(boolean player1)
	{
		if(player1)
		{
		bat1.getBtnAtaqueFisico().setEnabled(false);
		bat1.getBtnAtaqueMagico().setEnabled(false);
		}
		else
		{
			bat2.getBtnAtaqueFisico().setEnabled(false);
			bat2.getBtnAtaqueMagico().setEnabled(false);
		}
	
	}
	public void habilitarBotones(boolean player1)
	{
		if(player1)
		{
		bat1.getBtnAtaqueFisico().setEnabled(true);
		bat1.getBtnAtaqueMagico().setEnabled(true);
		}
		else
		{
			bat2.getBtnAtaqueFisico().setEnabled(true);
			bat2.getBtnAtaqueMagico().setEnabled(true);
		}
		
	}
	public void mostrarVida(boolean player1,int cant)
	{

		if(player1)
		{

			bat1.getLblVidajugador_1().setText("Vida Actual "+cant);
			bat2.getLblVidajugador().setText("Vida Actual "+ cant);

		}
		else
		{
			bat1.getLblVidajugador().setText("Vida Actual "+cant);
			bat2.getLblVidajugador_1().setText("Vida Actual "+ cant);		
		}
	}

	public void mostrarMensajesDeServer(String arg)
	{
		bat1.getMensajesServer().setText(arg);
		bat2.getMensajesServer().setText(arg);
	}


}
