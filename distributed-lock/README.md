### 分布式锁

### 一、使用Spring Boot Redis 实现分布式锁：

#### Version 1

```java
/**
* v1:非线程安全的,存在以下问题：
* 1.获取锁后，如果业务操作异常，导致无法执行解锁代码，造成死锁
*/
private int reduceV1(){
    String lockName = "Product001"; //分布式锁名称，假定为:产品001
    int currentStock = 0;
    //1.获取锁: 如果键不存在则新增,存在则不改变已经有的值
    boolean locked = redisTemplate.opsForValue().setIfAbsent(lockName,lockName);
    // 为解决死锁问题给锁加上失效期：与上一步操作分两步走，不能保证原子性
    // 1).这里存在宕机风险，导致设置有效期失败，key永久存在，导致死锁
    // 2).如果在失效期过后，后面业务操作没有做完（还没有减库存），另一个线程会重新获取锁，会导致少减库存的问题
    redisTemplate.expire(lockName,10,TimeUnit.SECONDS);

    //2.业务操作，减库存
    if(locked) {
        currentStock =  reduceStock();//减库存操作
        //3.解锁，这里存在宕机或上面语句异常风险，导致删除锁失败，从而造成死锁
        redisTemplate.delete(lockName);
    }
    return currentStock;
}
```

#### Version 2

```java
 /**
  * v2:非线程安全的。
  * 解决问题：死锁问题
  * 存在问题：
  * 1.业务操作未执行完而锁过期失效，进而导致少减库存
  */
private int reduceV2(){
    String lockName = "Product001"; //分布式锁名称，假定为:产品001
    int currentStock = 0;
    boolean locked = false;
    try{
        //1.获取锁: 如果键不存在则新增,存在则不改变已经有的值，并且设置锁过期时间，防止死锁的产生
        // 1).如果在失效期过后，后面业务操作没有做完（还没有减库存），另一个线程会重新获取锁，会导致少减库存的问题
        locked = redisTemplate.opsForValue().setIfAbsent(lockName,lockName,10, TimeUnit.SECONDS);
        //2.业务操作，减库存
        if(locked) {
            currentStock =  reduceStock();//减库存操作
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if(locked) {
            //3.解锁。将解锁放到finally 中，防止死锁的情况。
            redisTemplate.delete(lockName);
        }
    }
    return currentStock;
}
```

#### Version 3

```java
/**
 * v3:非线程安全的。
 * 解决问题：
 *  1.死锁问题
 *  2.多线程并发情况下少减库存问题
 * 存在问题：
 * 1.Redis主从模式：如果某台Redis服务器宕机，数据复制不及时，切换主从服务器是后存在数据丢失的情况。 从而导致多个线程可能共同持有锁，进而少减库存
 */
private int reduceV3(){
    String lockName = "Product001"; //分布式锁名称，假定为:产品001
    String value = UUID.randomUUID().toString(); //设置一不重复的随机值
    int currentStock = 0;
    boolean locked = false;
    try{
        //1.获取锁: 如果键不存在则新增,存在则不改变已经有的值，并且设置锁过期时间，防止死锁的产生
        // 解决少减库存问题，每个线程只能删除自己加的锁
        locked = redisTemplate.opsForValue().setIfAbsent(lockName,value,10, TimeUnit.SECONDS);
        //2.业务操作，减库存
        if(locked) {
            currentStock =  reduceStock();//减库存操作
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if(locked) {
            //3.解锁。将解锁放到finally 中，防止死锁的情况。
            // 每个线程只能删除自己加的锁
            if(value.equals(String.valueOf(redisTemplate.opsForValue().get(lockName))))
            {
                redisTemplate.delete(lockName);
            }
        }
    }
    return currentStock;
}
```

### 二、使用Redisson框架实现分布式锁

#### RedissonLock

```java
/**
 * v4: 使用Redisson架构来实现分布式锁
 * Redisson 架构实现了分布式锁，解决了上面所述的问题
*/
private int reduceRedssion(){
    String lockName = "Product001"; //分布式锁名称，假定为:产品001
    String value = UUID.randomUUID().toString(); //设置一不重复的随机值
    int currentStock = 0;
    boolean locked = false;
    //1.初始化锁
    RLock rLock = redissonManager.getRedisson().getLock(lockName);
    try {
        //2.加锁，并且设置锁过期时间，防止死锁的产生
        locked = rLock.tryLock(10,TimeUnit.MILLISECONDS);
        if(locked){
            currentStock =  reduceStock();//3.业务操作，减库存操作
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    } finally {
        if(rLock != null){
            rLock.unlock(); //4.解锁
        }
    }

    return currentStock;
}
```

#### RedissonManager.java

```java
@Component
public class RedissonManager {
    private static final Logger logger = LoggerFactory.getLogger(RedissonManager.class);
    private static Config config = new Config();
    private static Redisson redisson;

    static {
        try {
            config = Config.fromYAML(RedissonManager.class.getClassLoader().getResource("redisson-config.yml"));
            //创建Redisson实例
            redisson = (Redisson)Redisson.create(config);
            logger.error("初始化Redisson成功");
        } catch (IOException e) {
            logger.error("加载Redisson配置文件redisson-config.yml失败",e);
        }
    }

    public Redisson getRedisson(){
        return redisson;
    }
}
```

