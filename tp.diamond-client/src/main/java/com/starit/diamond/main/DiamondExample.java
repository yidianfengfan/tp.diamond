package com.starit.diamond.main;

import com.starit.diamond.client.DiamondConfigure;
import com.starit.diamond.manager.DiamondManager;
import com.starit.diamond.manager.ManagerListener;
import com.starit.diamond.manager.ManagerListenerAdapter;
import com.starit.diamond.manager.impl.DefaultDiamondManager;

/**
 * Created by leishouguo on 2014/5/23.
 */
public class DiamondExample {
    public static void main(String[] args) {
        String groupId = "tag";
        String dataId = "1000";
        DiamondConfigure diamondConfigure = new DiamondConfigure("diamond");
        diamondConfigure.setConnectionTimeout(1000);
        //diamondConfigure.setLocalFirst(true);
        //diamondConfigure.setUseFlowControl(true);

        ManagerListener managerListener =    new ManagerListenerAdapter() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("#########################" + configInfo);
            }
        };

        DefaultDiamondManager.Builder builder = new DefaultDiamondManager.Builder(dataId, managerListener)
                .setGroup(groupId)
                .setDiamondConfigure(diamondConfigure);

        DiamondManager diamondManager =  builder.build();

        System.out.println("===>" + diamondManager.getAvailableConfigureInfomation(1000));

       for(int i=0; i<10000; i++) {
           //System.out.println("===>" + diamondManager.getAvailableConfigureInfomation(1000));

           try {
               Thread.sleep(2000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }
}
