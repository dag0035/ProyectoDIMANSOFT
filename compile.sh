chmod 777 src/es/ubu/proyecto/model/*
chmod 777 src/es/ubu/proyecto/management/*
chmod 777 src/es/ubu/proyecto/storage/*
chmod 777 src/es/ubu/proyecto/UI/*
chmod 777 src/es/ubu/proyecto/UI/GUI/*
chmod 777 src/es/ubu/proyecto/UI/textUI/*

mkdir -p bin
javac -classpath .:src/:bin/:. -d bin/ src/es/ubu/proyecto/model/*.java src/es/ubu/proyecto/management/*.java src/es/ubu/proyecto/storage/*.java src/es/ubu/proyecto/UI/*.java src/es/ubu/proyecto/UI/GUI/*.java src/es/ubu/proyecto/UI/textUI/*.java