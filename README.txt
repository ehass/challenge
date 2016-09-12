=======================================================
INFORMAÇÕES GERAIS
=======================================================
O desenvolvimento foi realizado numa máquina com Windows 8, portanto todas as orientações podem ser realizadas nesta plataforma.
Caso seja necessário executar o sistema numa máquina com Linux, será necessário adaptar alguns passos.

### Pré-requisitos
Para a execução do projeto é preciso ter instalado as seguintes dependências na máquina executará o programa.

1 - Java 1.8 (jdk 1.8.0_102) - http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html
2 - Apache Maven 3.3.9 (apache-maven-3.3.9-bin.zip)- https://maven.apache.org/download.cgi

### Configuração do ambiente
1 - Java
	- Baixar a jdk
	
	- Instalar na máquina
	
	- Adicionar a variável de ambiente do JAVA_HOME com o valor correspondente ao caminho de onde foi instalado a jdk na máquina
		Exemplo: no caso do Windows seria JAVA_HOME = C:\Program Files\Java\jdk1.8.0_102

2 - Maven
	- Baixar o binário do maven
	
	- Descompactar a pasta em algum local do computador
	
	- Adicionar a variável de ambiente do MAVEN_HOME com o valor correspondente ao caminho de onde foi descompactado.
		Exemplo: no caso do Windows seria MAVEN_HOME = C:\opt\apache-maven-3.3.9
	
	- Adicionar a variável de ambiente M2_HOME com o valor correspondente a pasta do binário do maven.
		Exemplo: no caso do Windows seria MAVEN_HOME = C:\opt\apache-maven-3.3.9\bin

	- Adicionar as varíaveis de ambiente MAVEN_HOME e M2_HOME na variável de caminhos executáveis, na variável Path.

	- Copiar o settings.xml para a pasta [USER_HOME/.m2]

3 - Validando configurações realizadas

	- Validando a configuração do Java
		java -version

		Exemplo de saída:
		C:\opt\projeto\spotippos>java -version
		java version "1.8.0_102"
		Java(TM) SE Runtime Environment (build 1.8.0_102-b14)
		Java HotSpot(TM) 64-Bit Server VM (build 25.102-b14, mixed mode)

	- Validando a configuração do Maven
		mvn --version

		Exemplo de saída:
		C:\opt\projeto\spotippos>mvn --version
		Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T14:41:47-02:00)
		Maven home: C:\opt\apache-maven-3.3.9\bin\..
		Java version: 1.8.0_102, vendor: Oracle Corporation
		Java home: C:\Program Files\Java\jdk1.8.0_102\jre
		Default locale: en_US, platform encoding: Cp1252
		OS name: "windows 8.1", version: "6.3", arch: "amd64", family: "dos"


### Execução do programa

1 - Executar o comando abaixo na raiz do projeto onde tem o pom.xml
	mvn clean install

2 - Subir o sistema a partir da pasta "frontend" existente dentro da pasta raiz
	mvn jetty:run-war -Djetty.port=80
