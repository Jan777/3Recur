package tests;


import org.junit.Assert;
import org.junit.Test;

import main.*;
import main.Items.*;

public class CriteriosTests {

	//Criterio de aceptación 1.1
	@Test
	public void queAsgardianoTieneStatsBasePropios()
	{
		PC pj = new Asgardiano("pj1", Clase.AGENTE);
		Assert.assertFalse(pj.getAtaqueBase() == 0);
		Assert.assertFalse(pj.getDanoMagicoBase() == 0);
		Assert.assertFalse(pj.getDefensaBase() == 0);
		Assert.assertFalse(pj.getEnergiaMaximaBase() == 0);
		Assert.assertFalse(pj.getVelocidadBase() == 0);
		Assert.assertFalse(pj.getVidaMaximaBase() == 0);
		Assert.assertFalse(pj.getVidaMaximaPorNivel() == 0);
	}
	@Test
	public void queHulkTieneStatsBasePropios()
	{
		PC pj = new Hulk("pj1", Clase.AGENTE);
		Assert.assertFalse(pj.getAtaqueBase() == 0);
		Assert.assertFalse(pj.getDanoMagicoBase() == 0);
		Assert.assertFalse(pj.getDefensaBase() == 0);
		Assert.assertFalse(pj.getEnergiaMaximaBase() == 0);
		Assert.assertFalse(pj.getVelocidadBase() == 0);
		Assert.assertFalse(pj.getVidaMaximaBase() == 0);
		Assert.assertFalse(pj.getVidaMaximaPorNivel() == 0);
	}
	@Test
	public void queMutanteTieneStatsBasePropios()
	{
		PC pj = new Mutante("pj1", Clase.AGENTE);
		Assert.assertFalse(pj.getAtaqueBase() == 0);
		Assert.assertFalse(pj.getDanoMagicoBase() == 0);
		Assert.assertFalse(pj.getDefensaBase() == 0);
		Assert.assertFalse(pj.getEnergiaMaximaBase() == 0);
		Assert.assertFalse(pj.getVelocidadBase() == 0);
		Assert.assertFalse(pj.getVidaMaximaBase() == 0);
		Assert.assertFalse(pj.getVidaMaximaPorNivel() == 0);
		
	}
	//Fin criterio aceptación 1.1
	

	
	//Crieterio de aceptación 4.1
	@Test
	public void quePuedoGanarExp()
	{
		PC pj = new Hulk("pj1", Clase.AGENTE);
		int expInicial = pj.getExp();
		pj.ganarExp(10);
		Assert.assertTrue(expInicial + 10 == pj.getExp());
	}
	//Fin Criterio 4.1
	
	//Criterio 4.2
	
	@Test
	public void queAlGanarExpSuboDeNivel()
	{
		
		PC pj = new Asgardiano("pj1", Clase.AGENTE);
		int nivelInicial = pj.getNivel();
		pj.ganarExp(60);
		Assert.assertTrue(nivelInicial + 1 == pj.getNivel());	
	}
	
	//Fin criterio 4.2
	
	//Criterio 5.1
	@Test
	public void queAlTomarUnCascoDeMagnetoSeModificanStats()
	{
		PC pj = new Asgardiano("pj1", Clase.AGENTE);
		pj.equiparItemCabeza(new CascoDeMagneto());
		Assert.assertTrue(pj.calcularDefensa() != pj.getDefensaBase());
		
	}
	@Test
	public void queAlTomarUnCascoDeNovaSeModificanStats()
	{
		PC pj = new Asgardiano("pj1", Clase.AGENTE);
		pj.equiparItemCabeza(new CascoDeNova());
		Assert.assertTrue(pj.calcularDanoMagico() != pj.getDanoMagicoBase());
		Assert.assertTrue(pj.calcularVelocidad() != pj.getVelocidadBase());
		
	}
	@Test
	public void queAlTomarUnReactorARCSeModificanStats()
	{
		PC pj = new Asgardiano("pj1", Clase.AGENTE);
		pj.equiparItemPecho(new ReactorARC());
		Assert.assertTrue(pj.calcularEnergiaMax() != pj.getEnergiaMaximaBase());
		
	}
	@Test
	public void queAlTomarUnSimbionteSeModificanStats()
	{
		PC pj = new Asgardiano("pj1", Clase.AGENTE);
		pj.equiparItemPecho(new Simbionte());
		Assert.assertTrue(pj.calcularVidaMaxima() != pj.getVidaMaximaBase());
		
	}
	
	//Fin criterio 5.1
	
	//Criterio de aceptación 8.1
	@Test
	public void queAlPerderCombateHulkPierdeMejorItem()
	{
		PC pj = new Hulk("pj1", Clase.AGENTE);
		pj.equiparItemCabeza(new CascoDeMagneto());
		pj.morir();
		Assert.assertTrue(pj.getDefensaBase() == pj.calcularDefensa());
	}
	@Test
	public void queAlPerderCombateAsgardianoPierdeMejorItem()
	{
		PC pj = new Asgardiano("pj1", Clase.AGENTE);
		pj.equiparItemCabeza(new CascoDeMagneto());
		pj.morir();
		Assert.assertTrue(pj.getDefensaBase() == pj.calcularDefensa());
	}
	@Test
	public void queAlPerderCombateMutantePierdeMejorItem()
	{
		PC pj = new Mutante("pj1", Clase.AGENTE);
		pj.equiparItemCabeza(new CascoDeMagneto());
		pj.morir();
		Assert.assertTrue(pj.getDefensaBase() == pj.calcularDefensa());
	}
	
	//Fin criterio de aceptación 8.1
	
	
	

}
