# Configurações do Projeto ZookeeperSample

Este é um projeto Java que demonstra como interagir com o serviço ZooKeeper para criar, ler, atualizar e excluir nós (nodes) e seus dados.

## Pré-requisitos

Certifique-se de ter instalado o seguinte:

- Java Development Kit (JDK) 8 ou superior
- Apache ZooKeeper
- Maven (opcional, se desejar construir o projeto a partir do código-fonte)

## Configuração do Apache ZooKeeper

Certifique-se de que o Apache ZooKeeper esteja instalado e configurado corretamente em sua máquina. Você pode usar o comando `zkserver` para iniciar o servidor ZooKeeper localmente.

Exemplo de como iniciar o servidor ZooKeeper localmente:

bash

Copy code

`zkserver`

## Executando o Projeto

1. Clone este repositório para o seu ambiente local:

bash

Copy code

`git clone https://github.com/seu_usuario/ZookeeperSample.git`

2. Navegue até o diretório do projeto:

bash

Copy code

`cd ZookeeperSample`

3. Compile o projeto (se necessário):

bash

Copy code

`mvn compile`

4. Execute o projeto:

bash

Copy code

`mvn exec:java -Dexec.mainClass="org.example.ZookeeperSample"`

Isso executará o código `ZookeeperSample.java`, que se conecta ao servidor ZooKeeper local e realiza operações como criar, ler, atualizar e excluir nós.