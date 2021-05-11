package com.yaof.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @Author yaof
 * @Date 2021-04-13
 */
public class ZkClient {

    public static ZooKeeper getZookeeper(){
        ZooKeeper zooKeeper=null;
        try {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            //连接成功后，会回调watcher监听，此连接操作是异步的，执行完new语句后，直接调用后续代码
            //  可指定多台服务地址 127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183
            zooKeeper = new ZooKeeper("127.0.0.1:2181", 3000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if(Event.KeeperState.SyncConnected==event.getState()){
                        //如果收到了服务端的响应事件,连接成功
                        countDownLatch.countDown();
                    }
                }
            });
            countDownLatch.await();
            System.out.println("【初始化ZooKeeper连接状态....】=" + zooKeeper.getState());

        }catch (Exception e){
            System.out.println("初始化ZooKeeper连接异常....】="+e.getMessage());
        }
        return  zooKeeper;
    }

    public static void main(String[] args) {
        try {
            Stat stat = getZookeeper().exists("/dubbo", true);
            System.out.println(new Date(stat.getCtime())); //获取创建的时间

//            Stat stat1 = getZookeeper().exists("/dubbo/abcd/yaoffff", true);
//            System.out.println(stat1); //获取创建的时间
            String nood1 = getZookeeper().create("/dubbo/abcd/test", "姚放放11".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println(nood1);

            byte[] data = getZookeeper().getData("/dubbo/abcd/test", true, null);
            System.out.println(new String(data));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
