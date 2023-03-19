package com.yanxia.study.proxy;

/**
 * 对生产厂家要求的接口
 */
public interface IProducer {
    /**
     * 销售
     * @param money
     */
    public  void saleProduct(float money);

    /**
     * 服务
     * @param money
     */
    public void afterService(float money);
}
