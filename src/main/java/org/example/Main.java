package org.example;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

//DICA Para <b>Executar</b> o código, pressione <shortcut actionId="Run"/> ou
// clique no ícone <icon src="AllIcons.Actions.Execute"/> no gutter.
public class Main {
    private static final String CONNECT_STRING = "localhost:2181"; // Endereço do servidor ZooKeeper
    private static final int SESSION_TIMEOUT = 3000; // Tempo limite da sessão em milissegundos

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException  {
        ZooKeeper zooKeeper = new ZooKeeper(CONNECT_STRING, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {

            }
        });

        // Exemplo: Criar um znode
        String path = "/exemplo";
        byte[] data = "Olá, ZooKeeper!".getBytes();
        Stat stat = zooKeeper.exists(path, false);

        if (stat != null) {
            // Atualizar os dados do znode com os novos dados
            zooKeeper.setData(path, data, stat.getVersion());
            System.out.println("Dados atualizados com sucesso!");
        } else {
            System.out.println("Znode não existe, criando um...");
            zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        // Exemplo: Obter dados de um znode
        byte[] retrievedData = zooKeeper.getData(path, null, null);
        System.out.println("Dados: " + new String(retrievedData));

        // Fechar o cliente ZooKeeper
        zooKeeper.close();
    }
}