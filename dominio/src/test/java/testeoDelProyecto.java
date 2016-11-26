package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import character.*;
import items.*;

public class TesteoDelProyecto {

	@Test
	public void statsAsgardiano() {
		PC p = new Asgardiano("Prueba", Clase.AGENTE);
		assertEquals(true, p.calcularAtaque() == 13);
		assertEquals(true, p.calcularDefensa() == 12);
		assertEquals(true, p.calcularVelocidad() == 12);
		assertEquals(true, p.calcularEvasion() == 0);
		assertEquals(true, p.calcularDanoMagico() == 15);
		assertEquals(true, p.calcularEnergiaMax() == 26);
		assertEquals(true, p.calcularVidaMaxima() == 62);
	}

	@Test
	public void statsHulk() {
		PC p = new Hulk("Prueba2", Clase.HECHIZERO);
		assertTrue(p.calcularVidaMaxima() == 57);
		assertTrue(p.calcularEnergiaMax() == 13);
		assertTrue(p.calcularAtaque() == 11);
		assertTrue(p.calcularDanoMagico() == 9);
		assertTrue(p.calcularDefensa() == 14);
		assertTrue(p.calcularVelocidad() == 6);
		assertTrue(p.calcularEvasion() == 0);
	}

	@Test
	public void statsMutante() {
		PC p = new Mutante("Prueba2", Clase.HECHIZERO);
		assertTrue(p.calcularVidaMaxima() == 54);
		assertTrue(p.calcularEnergiaMax() == 20);
		assertTrue(p.calcularAtaque() == 11);
		assertTrue(p.calcularDanoMagico() == 11);
		assertTrue(p.calcularDefensa() == 10);
		assertTrue(p.calcularVelocidad() == 17);
		assertTrue(p.calcularEvasion() == 0);
	}

	@Test
	public void ganarExperienciaYSubirPuntos() {
		PC p = new Hulk("Prueba", Clase.TANQUE);
		assertTrue(p.getNivel() == 1);
		p.ganarExp(50);
		assertTrue(p.getNivel() == 2);

	}

	@Test
	public void equiparUnicoCasco() {
		PC p = new Mutante("Prueba", Clase.AGENTE);
		int anterior = p.calcularDefensa();
		p.equiparItemCabeza(new CascoDeMagneto());
		p.equiparItemCabeza(new CascoDeMagneto());
		assertTrue(anterior == p.calcularDefensa() - 20);

	}

	@Test
	public void mostrarItemCabeza() {
		PC p = new Mutante("Prueba", Clase.AGENTE);
		assertTrue(p.mostrarItemCabeza() == "libre");
		p.equiparItemCabeza(new CascoDeMagneto());
		assertTrue(p.mostrarItemCabeza() == "Caso de Magneto");
	}

	@Test
	public void desequiparUnicoCasco() {
		PC p = new Mutante("Prueba", Clase.AGENTE);
		p.desequiparItemCabeza();
		assertTrue(p.mostrarItemCabeza() == "libre");
		int anterior = p.calcularDefensa();
		p.equiparItemCabeza(new CascoDeMagneto());
		p.equiparItemCabeza(new CascoDeMagneto());
		p.desequiparItemCabeza();
		assertTrue(anterior == p.calcularDefensa());
	}

	@Test
	public void equiparUnicoPecho() {
		PC p = new Mutante("Prueba", Clase.AGENTE);
		int anterior = p.calcularVidaMaxima();
		p.equiparItemPecho(new Simbionte());
		p.equiparItemPecho(new Simbionte());
		assertTrue(anterior == p.calcularVidaMaxima() - 25);
	}

	@Test
	public void mostrarPecho() {
		PC p = new Mutante("Prueba", Clase.AGENTE);
		assertTrue(p.mostrarItemPecho() == "libre");
		p.equiparItemPecho(new Simbionte());
		assertTrue(p.mostrarItemPecho() == "Simbionte");
	}

	@Test
	public void desequiparItemPecho() {
		PC p = new Mutante("Prueba", Clase.AGENTE);
		int anterior = p.calcularVidaMaxima();
		p.equiparItemPecho(new Simbionte());
		p.desequiparItemPecho();
		assertEquals(true, anterior == p.calcularVidaMaxima());
	}

	@Test
	public void equiparItemManoIzq() {
		PC p = new Hulk("Prueba", Clase.AGENTE);
		int anterior = p.calcularVelocidad();
		p.equiparItemManoIzq(new GuanteDeThor());
		p.equiparItemManoIzq(new GuanteDeThor());
		assertTrue(anterior == p.calcularVelocidad() - 20);
	}

	@Test
	public void cascoDeNovaSpeed() {
		PC p = new Asgardiano("Prueba", Clase.TANQUE);
		int anterior = p.calcularVelocidad();
		p.equiparItemCabeza(new CascoDeNova());
		assertTrue(p.calcularVelocidad() == anterior + 10);
	}

	@Test
	public void mostrarItemManoIzq() {
		PC p = new Asgardiano("Prueba", Clase.AGENTE);
		assertTrue(p.mostrarItemManoIzq() == "libre");
		p.equiparItemManoIzq(new GuanteDeThor());
		assertTrue(p.mostrarItemManoIzq() == "Guante de Thor");

	}

	@Test
	public void desequiparItemManoIzq() {
		PC p = new Mutante("Prueba", Clase.TANQUE);
		int anterior = p.calcularVelocidad();
		p.desequiparItemManoIzq();
		assertTrue(p.mostrarItemManoIzq() == "libre");
		p.equiparItemManoIzq(new GuanteDeThor());
		p.desequiparItemManoIzq();
		assertEquals(true, anterior == p.calcularVelocidad());
	}

	@Test
	public void equiparItemManoDer() {
		PC p = new Hulk("Prueba", Clase.AGENTE);
		int anterior = p.calcularVelocidad();
		p.equiparItemManoDer(new GuanteDeThor());
		p.equiparItemManoDer(new GuanteDeThor());
		assertTrue(anterior == p.calcularVelocidad() - 20);
	}

	@Test
	public void mostrarItemManoDer() {
		PC p = new Asgardiano("Prueba", Clase.AGENTE);
		assertTrue(p.mostrarItemManoDer() == "libre");
		p.equiparItemManoDer(new GuanteDeThor());
		assertTrue(p.mostrarItemManoDer() == "Guante de Thor");

	}

	@Test
	public void desequiparItemManoDer() {
		PC p = new Mutante("Prueba", Clase.TANQUE);
		int anterior = p.calcularVelocidad();
		p.desequiparItemManoDer();
		assertTrue(p.mostrarItemManoDer() == "libre");
		p.equiparItemManoDer(new GuanteDeThor());
		p.desequiparItemManoDer();
		assertEquals(true, anterior == p.calcularVelocidad());
	}

	@Test
	public void calcularDañoMagico() {
		PC p = new Mutante("Prueba", Clase.AGENTE);
		int anterior = p.calcularDanoMagico();
		p.equiparItemCabeza(new CascoDeNova());
		assertTrue(anterior == p.calcularDanoMagico() - 15);
		p.desequiparItemCabeza();
	}

	@Test
	public void calcularEnergia() {
		PC p = new Mutante("Prueba", Clase.AGENTE);
		int anterior = p.calcularEnergiaMax();
		p.equiparItemPecho(new ReactorARC());
		assertTrue(anterior == p.calcularEnergiaMax() - 50);
		p.desequiparItemPecho();
		assertTrue(anterior == p.calcularEnergiaMax());
	}

	@Test
	public void calcularEvasion() {
		PC p = new Mutante("Prueba", Clase.AGENTE);
		int anterior = p.calcularEvasion();
		p.equiparItemManoDer(new GuanteDeThor());
		assertTrue(anterior == p.calcularEvasion() - 20);
		p.desequiparItemManoDer();
	}

	@Test
	public void calcularAtaque() {
		PC p = new Mutante("Prueba", Clase.AGENTE);
		int anterior = p.calcularAtaque();
		p.equiparItemManoDer(new GuanteDeThor());
		assertTrue(anterior == p.calcularAtaque() - 5);
		p.desequiparItemManoDer();
	}

	@Test
	public void ganarPuntos() {
		PC p = new Hulk("Prueba", Clase.TANQUE);
		p.ganarExp(29949); // nivel 20
		assertTrue(p.getExp() == 29949);
	}

	@Test
	public void subirPuntos() {
		PC p = new Hulk("Prueba", Clase.TANQUE);
		p.ganarExp(29949); // nivel 20
		assertTrue(p.getPuntosDisponibles() == 57);
		
		assertTrue(p.getPuntosFuerza() == 0);
		assertTrue(p.getPuntosDestreza() == 0);
		assertTrue(p.getPuntosInteligencia() == 0);
		p.subirFuerza();
		p.subirDestreza();
		p.subirInteligencia();
		for (int i = 0; i < 100; i++) 
			p.subirInteligencia();
		p.subirDestreza();
		p.subirFuerza();
		assertTrue(p.getPuntosFuerza() == 1);
		assertTrue(p.getPuntosDestreza() == 1);

		assertTrue(p.getPuntosInteligencia() == 55);

	}

	@Test

	public void expRestante() {
		PC p = new Hulk("Prueba", Clase.TANQUE);
		assertTrue(p.expRestante() == 50);
		p.ganarExp(29949 - 50);// nivel maximo
		assertTrue(p.expRestante() == 0);
	}

	@Test
	public void mostrarNombre() {
		PC p = new Hulk("Prueba", Clase.TANQUE);
		assertTrue(p.getName() == "Prueba");

	}
	@Test
	public void getRazaHulk()
	{
		PC p = new Hulk("Prueba", Clase.TANQUE);		
		assertTrue(p.getRaza() == "Hulk");
}
	@Test
	public void getRazaMutante()
	{
		PC p = new Mutante("Prueba", Clase.TANQUE);		
		assertTrue(p.getRaza().equals("Mutante"));
		
	}
	@Test
	public void getRazaAsgardiano()
	{
		PC p = new Asgardiano("Prueba", Clase.TANQUE);	
		assertTrue(p.getRaza().equals("Asgardiano"));

	}
	@Test
	public void getClase() {
		PC p = new Asgardiano("Prueba", Clase.TANQUE);
		assertTrue(p.getClase().equals("TANQUE"));
	}
	
	@Test
	public void getAndSetXeY()
	{
		PC p = new Asgardiano("Prueba", Clase.TANQUE);
		p.setX(1);
		p.setY(2);
		assertTrue(p.getX() == 1);
		assertTrue(p.getY() == 2);
	}
	
}

