package org.example;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

public class ZookeeperSample {
    private static final Logger LOG = LoggerFactory.getLogger(ZookeeperSample.class);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        LOG.info("Iniciando a conexão com o ZooKeeper...");
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 5000, null);

        LOG.info("Criando o nó '/node' se não existir...");
        createIfNotExists(zooKeeper, "/node", "data".getBytes(), CreateMode.PERSISTENT);

        LOG.info("Criando o nó '/node/child' se não existir...");
        createIfNotExists(zooKeeper, "/node/child", "child".getBytes(), CreateMode.PERSISTENT);

        // Adiciona um pequeno atraso para simular uma operação assíncrona
        Thread.sleep(6000);

        LOG.info("Lendo dados do nó '/node'...");
        Stat stat = new Stat();
        byte[] data = zooKeeper.getData("/node", false, stat);
        LOG.info("Dados do nó '/node': {}", new String(data));
        LOG.info("Versão do nó '/node': {}", stat.getVersion());

        LOG.info("Listando filhos do nó '/node'...");
        List<String> children = zooKeeper.getChildren("/node", false);
        children.forEach(child -> LOG.info("Filho encontrado: {}", child));

        Thread.sleep(6000);

        LOG.info("Atualizando dados do nó '/node'...");
        zooKeeper.setData("/node", "Estes são novos dados".getBytes(), -1);
        LOG.info("Dados do nó '/node' atualizados com sucesso!");

        Thread.sleep(6000);

        LOG.info("Excluindo o nó '/node/child'...");
        zooKeeper.delete("/node/child", -1);
        LOG.info("Nó '/node/child' excluído com sucesso!");

        LOG.info("Excluindo o nó '/node'...");
        zooKeeper.delete("/node", -1);
        LOG.info("Nó '/node' excluído com sucesso!");

        // Fechar a conexão com o ZooKeeper
        zooKeeper.close();
    }

    private static void createIfNotExists(ZooKeeper zooKeeper, String path, byte[] data, CreateMode createMode) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(path, false);
        if (stat == null) {
            zooKeeper.create(path, data, OPEN_ACL_UNSAFE, createMode);
            LOG.info("Nó '{}' criado com sucesso!", path);
        } else {
            LOG.warn("O nó '{}' já existe.", path);
        }
    }
}
