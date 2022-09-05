#!/bin/bash
echo "### Aplicando permisos de ejecucion ###"
chmod +x user-service/gradlew
chmod +x gateway-service/gradlew
chmod +x eureka-service/gradlew
chmod +x config-service/gradlew
echo "### Generando Servicio user-service ###"
user-service/gradlew -p user-service clean
user-service/gradlew -p user-service build
echo "### Generando Servicio gateway-service ###"
gateway-service/gradlew -p gateway-service clean
gateway-service/gradlew -p gateway-service build
echo "### Generando Servicio eureka-service ###"
eureka-service/gradlew -p eureka-service clean
eureka-service/gradlew -p eureka-service build
echo "### Generando Servicio config-service ###"
config-service/gradlew -p config-service clean
config-service/gradlew -p config-service build
echo "### Ejecutando docker-compose ###"
docker-compose -f docker-compose.yml -p catalogo_hector-castro up -d