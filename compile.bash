chmod 777 src/es/ubu/proyecto/model/*
chmod 777 src/es/ubu/proyecto/management/*
chmod 777 src/es/ubu/proyecto/storage/*
chmod 777 src/es/ubu/proyecto/textui/*

mkdir -p bin
javac -classpath .:src/:bin/:. -d bin/ src/es/ubu/proyecto/model/Producto.java src/es/ubu/proyecto/model/Linea.java src/es/ubu/proyecto/model/ListaCompra.java src/es/ubu/proyecto/management/*.java src/es/ubu/proyecto/storage/*.java src/es/ubu/proyecto/textui/*.java

chmod 777 bin/es/ubu/proyecto/model/*
chmod 777 bin/es/ubu/proyecto/management/* 
chmod 777 bin/es/ubu/proyecto/storage/*
chmod 777 bin/es/ubu/proyecto/textui/*

java -cp bin/ es.ubu.proyecto.textui.TextMain