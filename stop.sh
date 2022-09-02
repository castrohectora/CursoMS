#!/bin/bash
echo "### Deteniendo y limpiando ###"
docker-compose -f docker-compose.yml -p catalogo_hector-castro down --remove-orphans