#!/bin/bash
echo "### Aplicando permisos de ejecucion ###"
chmod +x catalogo/gradlew
chmod +x configserver/gradlew
chmod +x servicediscovery/gradlew
echo "### Generando Servicio catalogo ###"
catalogo/gradlew -p catalogo clean
catalogo/gradlew -p catalogo build
echo "### Generando Servicio configserver ###"
configserver/gradlew -p configserver clean
configserver/gradlew -p configserver build
echo "### Generando Servicio servicediscovery ###"
servicediscovery/gradlew -p servicediscovery clean
servicediscovery/gradlew -p servicediscovery build
echo "### Ejecutando docker-compose ###"
docker-compose -f docker-compose.yml -p catalogo_cristian_pardo up -d