# Mensajes:
### 1- Crear un personaje:
	cliente: envia informacion del personaje a crear.
		{"Personaje" : {"nombre" : "Thor", "raza": "Asgardiano", "clase": "TANQUE"}}
        
	servidor: devuelve los stats del personaje creado al cliente.
		{"Stats" : {"ataqueBase": 13, "defensaBase": 12, "velocidadBase": 12,
         "danoMagicoBase": 15, "energiaMaximaBase": 26, "vidaMaximaBase": 62,
         "vidaMaximaPorNivel": 5}}
		 
###2- Mover personaje:
	cliente: indica su ubicacion actual y deseada al servidor.
		{"UbicacionPersonaje": {"nombrePersonaje": "Thor",
         "ubicacionActual": {"x": 15, "y": 20}}
         "ubicacionDeseada": {"x": 20, "y": 20}}
		
	servidor: mueve al personaje haciendo que la ubicacion deseada sea la actual.
		{"UbicacionPersonaje": {"nombrePersonaje": "Thor",
         "ubicacionActual": {"x": 20, "y": 20}}
         "ubicacionDeseada": {"x": 20, "y": 20}}

###3- Atacar a otro personaje:
	cliente: envia al servidor la solicitud de ataque.
		{"Atacar" : {"atacante": "Thor", "nombreAtaque": "Mazaso", "atacado": "Loki"}}
		
	servidor: envia al atacado el daño realizado por el atacante.
		{"Daño" : {atacante": "Thor", "dañoVida": 8, "dañoMagico": 0}}
        
###4- Resultado batalla:
	servidor: envia a cada luchador el ganador de la batalla y las recompensas.
    	{"ResultadoBatalla": {"nombreGanador": "Thor", "expObtenida": 15,
         "itemObtenido": "cascoDeLoki"}}
        
  	cliente: notifica al servidor el nuevo estado del personaje.
    	{"EstadoPersonaje": {"nombrePersonaje": "Thor", "expTotal": 115, "nivel": 4}}
        
###5- Equipar un item:
	cliente: envia al servidor el id del item a equipar.
    	{"EquiparItem": {"personaje": "Thor", "idItem": 1010}}
        
	servidor: devuelve al cliente la cantidad de puntos del stat a modificar.
    	{"Item": {"nombreItem": "Mjolnir", "statListener": "attackListener",
         "puntosBonus": 12}}
         
###6- Unirse a una alianza:
	cliente: solicita al servidor una alianza.
    	{"Aliarse": {"solicitante": "Thor", "solicitado": "Wolverine"}}
        
	servidor: devuelve la alianza a los aliados.
    	{"Alianza": {"liderAlianza": "Thor",
         "aliados": [{"nombre": "Thor", "nivel": 4}, {"nombre": "Wolverine", nivel: 3}]}}
###7-Loguearse:
	cliente: solicita conexion con el servidor.
    	{"Loguearse": {"solicitante": "Cliente", "solicitado": "Servidor"}}

	servidor: verifica usuario y contraseña.
    	{"Loguearse": {"Iniciar sesion": "usuario y contraeña"}}