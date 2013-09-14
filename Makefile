#var
plib_path=../PUtils
kaserver_path=./

#module
putils_package:
	"PUtils mvn package"
	cd ${plib_path} && mvn package

putils_install:
	echo "PUtils mvn install"
	cd ${plib_path} && mvn install

kaserver_package:putils_install
	echo "kaserver mvn install"
	cd ${kaserver_path} && mvn package

	
