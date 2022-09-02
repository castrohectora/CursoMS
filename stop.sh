#!/bin/bash
echo "### Deteniendo y limpiando ###"
docker-compose -f docker-compose.yml -p catalogo_cristian_pardo down --remove-orphans