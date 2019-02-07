package top.forethought.designpattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * @author  wangwei
 * @date     2019/1/24 12:18
 * @classDescription
 *
 */
/**
 * 设计模式:代理模式,此代理类来给目标类的方法增加执行时间
 * //阿里巴巴开发手册要求:运用设计模式的类名需要将运用的设计模式名作为类名后缀,
 * 方便理解
 */
public class TimeLogProxy implements InvocationHandler {
    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    private Object target; // 代理目标
    private Object proxy; // 代理对象
    private static HashMap<Class<?>, InvocationHandler> invokHandlers = new HashMap<>();


    /**
     *  代理方法
     * @param proxy  代理对象
     * @param method 方法
     * @param args 参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
          long start=System.currentTimeMillis();
         Object result = method.invoke(target, args); //执行代理对象的方法
         logger.info("method:"+method.getName()+", class:"+target.getClass()+" ,execute time MillionsSeconds:"+(System.currentTimeMillis()-start));
        return result;
    }
    /**
     * 获取动态代理对象:原对象上添加时间输出功能后的增强对象
     *
     */
    /**
     * 通过Class来生成动态代理对象Proxy
     *
     * @param clazz
     * @return
     */
    public synchronized static <T> T getProxyInstance(Class<T> clazz) {
        top.forethought.designpattern.TimeLogProxy invoHandler = (top.forethought.designpattern.TimeLogProxy) invokHandlers.get(clazz);

        if (null == invoHandler) {
            invoHandler = new top.forethought.designpattern.TimeLogProxy();
            try {
                T tar = clazz.newInstance();
                invoHandler.setTarget(tar);
                invoHandler.setProxy(Proxy.newProxyInstance(tar.getClass().getClassLoader(),
                        tar.getClass().getInterfaces(), invoHandler));
            } catch (Exception e) {
                e.printStackTrace();
            }
            invokHandlers.put(clazz, invoHandler);

        }

        return (T) invoHandler.getProxy();
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        return proxy;
    }

    public void setProxy(Object proxy) {
        this.proxy = proxy;
    }
}
