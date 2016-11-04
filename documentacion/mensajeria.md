# Mensajes:
### 1- Crear un personaje:
	cliente: 
		{"Personaje" : {"nombre" : "Thor", "raza": "Asgardiano", "clase": "TANQUE"}}
        
	servidor:
		{"Stats" : {"ataqueBase": 13, "defensaBase": 12, "velocidadBase": 12,
         "danoMagicoBase": 15, "energiaMaximaBase": 26, "vidaMaximaBase": 62,
         "vidaMaximaPorNivel": 5}}
		 
###2- Mover personaje:
	
    cliente:
		{"DesplazamietoDeseado": {"nombrePersonaje": "Thor",
         "ubicacionDeseada": {"x": 10, "y": 20}}
		
	servidor:
		{"DesplazamientoRealizado": {"nombrePersonaje": "Thor", "seDesplazó": "SI",
         "ubicacionActual": {"x": 10, "y": 20}}}

###3- Atacar a otro personaje:
	cliente:
		{"Ataque" : {"atacante": "Thor", "nombreAtaque": "Mazaso", "atacado": "Loki"}}
		
	servidor:
		{"Daño" : {atacante": "Thor", "dañoVida": 8, "dañoMagico": 0}}
        
###4- Resultado batalla:
	servidor:
    	{"ResultadoBatalla": {"nombreGanador": "Thor", "expObtenida": 15}}
        
  	cliente:
    	{"EstadoPersonaje": {"nombrePersonaje": "Thor", "expTotal": 115, "nivel": 4}}