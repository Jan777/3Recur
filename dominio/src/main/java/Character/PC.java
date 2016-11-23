package character;

import java.util.ArrayList;

import items.*;
import statListeners.*;

public abstract class PC extends Personaje {

	public static int[] requisitoExp = { 50, 148, 316, 578, 954, 1468, 2140, 2992, 4044, 5318, 6834, 8611, 10668, 13026,
			15702, 18715, 22083, 25822, 29949 };
	protected int exp;
	protected Clase clase;
	private int puntosHabilidadDisponibles;
	private int fuerza;
	private int destreza;
	private int inteligencia;
	protected int ataqueBase;
	protected int defensaBase;
	protected int velocidadBase;
	protected int danoMagicoBase;
	protected int energiaMaximaBase;
	protected int vidaMaximaBase;
	protected int vidaMaximaPorNivel;

	private ArrayList<AttackListener> attackListeners = new ArrayList<AttackListener>();
	private ArrayList<DefenseListener> defenseListeners = new ArrayList<DefenseListener>();
	private ArrayList<SpeedListener> speedListeners = new ArrayList<SpeedListener>();
	private ArrayList<EvasionListener> evasionListeners = new ArrayList<EvasionListener>();
	private ArrayList<DanoMagicoListener> danoMagicoListeners = new ArrayList<DanoMagicoListener>();
	private ArrayList<VidaMaximaListener> vidaMaximaListeners = new ArrayList<VidaMaximaListener>();
	private ArrayList<EnergiaMaximaListener> energiaMaximaListeners = new ArrayList<EnergiaMaximaListener>();

	private ItemCabeza itemCabeza;
	private ItemPecho itemPecho;
	private ItemMano itemManoIzq;
	private ItemMano itemManoDer;

	public Item equiparItemCabeza(ItemCabeza item) {
		Item prev = itemCabeza;
		if (itemCabeza != null)
			itemCabeza.desequipar(this);
		itemCabeza = item;
		itemCabeza.equipar(this);
		return prev;
	}

	public Item desequiparItemCabeza() {
		Item prev = itemCabeza;
		if (itemCabeza != null)
			itemCabeza.desequipar(this);
		itemCabeza = null;
		return prev;
	}

	public Item equiparItemPecho(ItemPecho item) {
		Item prev = itemPecho;
		if (itemPecho != null)
			itemPecho.desequipar(this);
		itemPecho = item;
		itemPecho.equipar(this);
		return prev;
	}

	public Item desequiparItemPecho() {
		Item prev = itemPecho;
		if (itemPecho != null)
			itemPecho.desequipar(this);
		itemPecho = null;
		return prev;
	}

	public Item equiparItemManoDer(ItemMano item) {
		Item prev = itemManoDer;
		if (itemManoDer != null)
			itemManoDer.desequipar(this);
		itemManoDer = item;
		itemManoDer.equipar(this);
		return prev;
	}

	public Item equiparItemManoIzq(ItemMano item) {
		Item prev = itemManoIzq;
		if (itemManoIzq != null)
			itemManoIzq.desequipar(this);
		itemManoIzq = item;
		itemManoIzq.equipar(this);
		return prev;
	}

	public Item desequiparItemManoDer() {
		Item prev = itemManoDer;
		if (itemManoDer != null)
			itemManoDer.desequipar(this);
		itemManoDer = null;
		return prev;
	}

	public Item desequiparItemManoIzq() {
		Item prev = itemManoIzq;
		if (itemManoIzq != null)
			itemManoIzq.desequipar(this);
		itemManoIzq = null;
		return prev;
	}

	public void speedSubscribe(SpeedListener subscriber) {
		speedListeners.add(subscriber);
	}

	public void speedUnsubscribe(SpeedListener subscriber) {
		speedListeners.remove(subscriber);
	}

	private int getSubscribedSpeed() {
		int speed = 0;
		for (SpeedListener sl : speedListeners) {
			if (sl != null)
				speed += sl.getSpeedBonus();
		}
		return speed;
	}

	public void danoMagicoSubscribe(DanoMagicoListener subscriber) {
		danoMagicoListeners.add(subscriber);
	}

	public void danoMagicoUnsubscribe(DanoMagicoListener subscriber) {
		danoMagicoListeners.remove(subscriber);
	}

	private int getSubscribedDanoMagico() {
		int danoMagico = 0;
		for (DanoMagicoListener sl : danoMagicoListeners) {
			if (sl != null)
				danoMagico += sl.getDanoMagicoBonus();
		}
		return danoMagico;
	}

	public void attackSubscribe(AttackListener subscriber) {
		attackListeners.add(subscriber);
	}

	public void attackUnsubscribe(AttackListener subscriber) {
		attackListeners.remove(subscriber);
	}

	private int getSubscribedAttack() {
		int attack = 0;
		for (AttackListener sl : attackListeners) {
			if (sl != null)
				attack += sl.getAttackBonus();
		}
		return attack;
	}

	public void defenseSubscribe(DefenseListener subscriber) {
		defenseListeners.add(subscriber);
	}

	public void defenseUnsubscribe(DefenseListener subscriber) {
		defenseListeners.remove(subscriber);
	}

	private int getSubscribedDefense() {
		int defense = 0;
		for (DefenseListener sl : defenseListeners) {
			if (sl != null)
				defense += sl.getDefenseBonus();
		}
		return defense;
	}

	public void evasionSubscribe(EvasionListener subscriber) {
		evasionListeners.add(subscriber);
	}

	public void evasionUnsubscribe(EvasionListener subscriber) {
		evasionListeners.remove(subscriber);
	}

	private int getSubscribedEvasion() {
		int evasion = 0;
		for (EvasionListener sl : evasionListeners) {
			if (sl != null)
				evasion += sl.getEvasionBonus();
		}
		return evasion;
	}

	public void energiaMaximaSubscribe(EnergiaMaximaListener subscriber) {
		energiaMaximaListeners.add(subscriber);
	}

	public void energiaMaximaUnsubscribe(EnergiaMaximaListener subscriber) {
		energiaMaximaListeners.remove(subscriber);
	}

	private int getSubscribedEnergiaMaxima() {
		int energiaMaxima = 0;
		for (EnergiaMaximaListener sl : energiaMaximaListeners) {
			if (sl != null)
				energiaMaxima += sl.getEnergiaMaximaBonus();
		}
		return energiaMaxima;
	}

	public void vidaMaximaSubscribe(VidaMaximaListener subscriber) {
		vidaMaximaListeners.add(subscriber);
	}

	public void vidaMaximaUnsubscribe(VidaMaximaListener subscriber) {
		vidaMaximaListeners.remove(subscriber);
	}

	private int getSubscribedVidaMaxima() {
		int vidaMaxima = 0;
		for (VidaMaximaListener sl : vidaMaximaListeners) {
			if (sl != null)
				vidaMaxima += sl.getVidaMaximaBonus();
		}
		return vidaMaxima;
	}

	public int getNivel() {
		int lv = 1;
		while (lv <= requisitoExp.length && exp >= requisitoExp[lv - 1])
			lv++;
		return lv;
	}

	private void subirNivel() {
		puntosHabilidadDisponibles += 3;
	}

	public void subirFuerza() {
		if (puntosHabilidadDisponibles <= 0) {
			System.out.println("No tienes suficientes puntos de habilidad");
			return;
		}
		puntosHabilidadDisponibles--;
		fuerza++;
	}

	public void subirDestreza() {
		if (puntosHabilidadDisponibles <= 0) {
			System.out.println("No tienes suficientes puntos de habilidad");
			return;
		}
		puntosHabilidadDisponibles--;
		destreza++;
	}

	public void subirInteligencia() {
		if (puntosHabilidadDisponibles <= 0) {
			System.out.println("No tienes suficientes puntos de habilidad");
			return;
		}
		puntosHabilidadDisponibles--;
		inteligencia++;
	}

	public void ganarExp(int exp) {
		int lvAnterior = getNivel();
		this.exp += exp;
		int aux = getNivel() - lvAnterior;

		while (aux > 0) {
			subirNivel();
			aux--;
		}
	}

	public int expRestante() {
		int lv = getNivel();
		if (lv == requisitoExp.length)
			return 0;
		return requisitoExp[lv - 1] - exp;
	}

	public int calcularAtaque() {
		return ataqueBase + clase.getAtaqueBonus(fuerza) + getSubscribedAttack();
	}

	public int calcularDefensa() {
		return defensaBase + clase.getDefensaBonus(fuerza) + getSubscribedDefense();
	}

	public int calcularVelocidad() {
		return velocidadBase + clase.getVelocidadBonus(destreza) + getSubscribedSpeed();
	}

	public int calcularEvasion() {
		return clase.getEvacionBonus(destreza) + getSubscribedEvasion();
	}

	public int calcularDanoMagico() {
		return danoMagicoBase + clase.getDanoMagicoBonus(inteligencia) + getSubscribedDanoMagico();
	}

	public int calcularEnergiaMax() {
		return energiaMaximaBase + clase.getEnergiaMaxBonus(inteligencia) + getSubscribedEnergiaMaxima();
	}

	public int calcularVidaMaxima() {
		return vidaMaximaBase + vidaMaximaPorNivel * (getNivel() - 1) + getSubscribedVidaMaxima();
	}

	public String mostrarItemCabeza() {
		if (itemCabeza == null)
			return "libre";
		return itemCabeza.getNombre();
	}

	public String mostrarItemPecho() {
		if (itemPecho == null)
			return "libre";
		return itemPecho.getNombre();
	}

	public String mostrarItemManoDer() {
		if (itemManoDer == null)
			return "libre";
		return itemManoDer.getNombre();
	}

	public String mostrarItemManoIzq() {
		if (itemManoIzq == null)
			return "libre";
		return itemManoIzq.getNombre();
	}

	public int getPuntosDisponibles() {
		return puntosHabilidadDisponibles;
	}

	public int getPuntosFuerza() {
		return fuerza;
	}

	public int getPuntosDestreza() {
		return destreza;
	}

	public int getPuntosInteligencia() {
		return inteligencia;
	}

	public void mostrarPersonaje() {
		System.out.println("***************************");
		System.out.println(nombre + ":nivel " + getNivel() + " Exp:" + exp + "/" + requisitoExp[getNivel() - 1]);
		System.out.println("(" + expRestante() + " puntos para el siguiente nivel)");
		System.out.println("---------------------------");
		System.out.println("Vida Maxima:" + calcularVidaMaxima());
		System.out.println("Energia Maxima:" + calcularEnergiaMax());
		System.out.println("Ataque:" + calcularAtaque());
		System.out.println("Daño Magico:" + calcularDanoMagico());
		System.out.println("Defensa:" + calcularDefensa());
		System.out.println("Velocidad:" + calcularVelocidad());
		System.out.println("Evasion:" + calcularEvasion());
		System.out.println("---------------------------");
		System.out.println(
				"Puntos de habilidad:\nFuerza:" + fuerza + "\nDestreza:" + destreza + "\nInteligencia:" + inteligencia);
		System.out.println("Tienes " + puntosHabilidadDisponibles + " puntos de habilidad disponibles");
		System.out.println("---------------------------");
		System.out.println("Items:\nCabeza:" + mostrarItemCabeza());
		System.out.println("Pecho:" + mostrarItemPecho());
		System.out.println("Mano Derecha:" + mostrarItemManoDer());
		System.out.println("Mano Izquierda:" + mostrarItemManoIzq());
		System.out.println("***************************");
	}

	public String getName() {
		return nombre;
	}

	public int getExp() {
		return exp;
	}

	public String getClase() {
		return clase.toString();
	}

	public abstract String getRaza();
}
