package com.yanxia.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        final Producer producer = new Producer();
        /**
         * 动态代理：
         * 特点：字节码随用随创建，随用随加载
         * 作用：在不修改源码的基础上对方法增强
         * 分类：
         *     基于接口的动态代理
         *     基于子类的动态代理
         * 基于接口的动态代理：
         *     涉及的类：Proxy
         *     提供者JDK官方
         * 如果创建代理对象
         *     使用Proxy类中的newProxyInstance方法
         * 创建代理对象的要求：
         *     被代理类至少实现一个接口，没有接口的类不能使用
         * newProxyInstance方法的参数
         *     ClassLoader:类加载器，用于加载代理对象字节码，写的是被代理对象的类加载器
         *     class[]：用于让代理对象和被代理对象有相同方法
         *     InvocationHandler：用于提供增强的代码。
         *              一般写一个该接口的实现类，通常情况下都是匿名内部类，但不是必须的，
         *              此接口的实现类都是随用随写
         */
        IProducer proxyProducer = (IProducer) Proxy.newProxyInstance(producer.getClass().getClassLoader(), producer.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 作用：执行被代理对象的任何接口都会经过该方法
                     * @param proxy the proxy instance that the method was invoked on代理对象的应用
                     *
                     * @param method the {@code Method} instance corresponding to
                     * the interface method invoked on the proxy instance.  The declaring
                     * class of the {@code Method} object will be the interface that
                     * the method was declared in, which may be a superinterface of the
                     * proxy interface that the proxy class inherits the method through.当前执行的方法
                     *
                     * @param args an array of objects containing the values of the
                     * arguments passed in the method invocation on the proxy instance,
                     * or {@code null} if interface method takes no arguments.
                     * Arguments of primitive types are wrapped in instances of the
                     * appropriate primitive wrapper class, such as
                     * {@code java.lang.Integer} or {@code java.lang.Boolean}.当前执行方法所需的参数
                     *
                     * @return 和被代理方法有相同的返回值
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //提供增强的代码
                        Object returnValue = null;
                        //1.获取方法执行的参数
                        Float money = (Float) args[0];
                        //2.判断当前方法是不是销售
                        if ("saleProduct".equals(method.getName())) {
                            returnValue = method.invoke(producer, money * 0.8f);
                        }
                        return returnValue;
                    }
                });
        proxyProducer.saleProduct(10000f);
    }
}
