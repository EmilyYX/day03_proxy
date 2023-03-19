package com.yanxia.study.proxy;

/**
 * 一个生产者，出售产品
 */
public class Producer implements IProducer {
    /**
     * 销售
     *
     * @param money
     */
    public void saleProduct(float money) {
        System.out.println("销售了产品并拿到货款" + money);
    }

    /**
     * 服务
     *
     * @param money
     */
    public void afterService(float money) {
        System.out.println("提供售后服务并拿到服务费" + money);
    }
}
