Instrucciones para su consumo:

1) Con el fin de centralizar las configuraciones de los microservicios se debe descargar el microservicio https://github.com/FcondeDev/ml-config-server,
ya que de este se toman las configuraciones con las que ml-coupon-service levanta.

2) Al descargar los repositorios se debe ejecutar primero el servidor de configuracion y a continuacion el microservicio, si se usa la consola se puede ejecutar el siguiente comando sobre el path del microservicio mvn spring-boot:run, si estas usando algun IDE con la consola de spring boot se puede ejecutar cada uno.

3) El servicio posee excepciones customizadas con el fin de contestar dependiendo de la situacion en la que ocurra un error : 

NOT_FOUND_EXCEPTION -> "Ninguno de los items se puede adquirir con el cupon suministrado".
	FEIGN_EXCEPTION -> "No se pudo contactar el api de items de mercado libre".
	ITEM_NOT_FOUND_ML_API -> "No fue posible encontrar informacion en el API de mercado libre para alguno de los items recibidos".
  
  cada uno de los mensajes anteriores contestara con un body describiendo el error.
  
4) para desplegar los servicios en AWS fargate se uso Docker, por lo cual si desea crear una imagen de docker de alguno de los microservicios basta con ejecutar mvn clean install sobre el path del microservicio y el plugin de maven-docker y el dockerfile haran el resto.


