# Hystrix 配置项说明

---

### 1.运行（Execution）

- execution.isolation.strategy：运行时隔离策略
 - THREAD：在独立的线程上运行，程序的并发数量受线程池中的线程数限制
 - SEMAPHORE：在调用线程上执行，程序的并发数量受信号量的计数限制
> HystrixCommand 方式默认策略是THREAD，HystrixObservableCommand方式默认策略是SEMAPHORE。线程方式的隔离策略能够在调用超过网络延迟限制时提供额外的保护。一般SEMAPHORE只有应用请求量很大（每个实例每秒钟上百的请求），每个请求单独线程执行开销太大，一般只适用于非网络调用。

- execution.isolation.thread.timeoutInMilliseconds：  线程隔离级别请求的超时时间（毫秒）
- execution.timeout.enabled：当以HystrixCommand.run()方式运行时，是否有超时时间
> 默认值为true
> 默认属性为hystrix.command.default.execution.timeout.enabled

- execution.isolation.thread.interruptOnTimeout：当以HystrixCommand.run()方式运行时，调用超时时中断调用
> 默认值true
> 默认属性hystrix.command.default.execution.isolation.thread.interruptOnTimeout

- execution.isolation.thread.interruptOnCancel：当以HystrixCommand.run()方式运行时，调用取消是中断调用
> 默认值fasle
> 默认属性hystrix.command.default.execution.isolation.thread.interruptOnCancel

- execution.isolation.semaphore.maxConcurrentRequests：当采用SEMAPHORE隔离策略时，允许的最大请求数量
> 默认值10
> 如果请求数量达到最大限制，会拒绝后续的请求
> 一个容器中大部分的请求应该采用THREAD隔离策略，因为熔断器不能为SEMAPHORE提供任何保护

### 2.回退（Fallback）
    回退相关配置的属性，对THREAD和SEMAPHORE策略都适用

- fallback.isolation.semaphore.maxConcurrentRequests：程序允许通过HystrixCommand.getFallback()方法获取结果的最大请求数量
> 默认值10
> 默认属性 hystrix.command.default.fallback.isolation.semaphore.maxConcurrentRequests
> 如果达到这个限制，后续的请求将得不到Fallback的结果，并且程序将抛出异常

- fallback.enabled：程序请求失败或者被拒绝是，是否尝试调用HystrixCommand.getFallback()
> 默认值true
> 默认属性 hystrix.command.default.fallback.enabled

### 3.断路器（Circuit Breaker）

- circuitBreaker.enabled：Hystrix是否跟踪根据服务的健康状况，并且在服务不工作时对其进行断路处理
> 默认值true
> 默认属性 hystrix.command.default.circuitBreaker.enabled

- circuitBreaker.requestVolumeThreshold: 断路器的请求量阈值，在每个滚动的请求窗口能够让服务断路的最小请求数量
> 默认值20
> 当属性值为20时，在每个请求滚动窗口（10秒钟），只有请求失败的数量达到20时（19个失败也不行）才会使程序断路

- circuitBreaker.sleepWindowInMilliseconds：断路器关闭请求窗口时间（毫秒）。当服务发生断路之后，在此属性设置的时间之后，断路器才会允许请求再次尝试调用服务，来确定服务是否关闭
> 默认值 5000
> 默认属性 	hystrix.command.default.circuitBreaker.sleepWindowInMilliseconds

- circuitBreaker.errorThresholdPercentage： 启动断路器错误阈值（百分比）。当请求失败的比例达到该阈值，断路器将会对程序进行短路，并且执行服务的回退逻辑
> 默认值50
> 默认属性 hystrix.command.default.circuitBreaker.errorThresholdPercentage

- circuitBreaker.forceOpen：断路器是否强制开启。当属性为true时，强制开启断路器，所有请求都被拒绝。
> 默认值false
> 默认属性 hystrix.command.default.circuitBreaker.forceOpen
> 优先级高于 circuitBreaker.forceClosed

- circuitBreaker.forceClosed：断路器是否强制关闭。当属性为true时，强制关闭断路器，所有请求都会通过，并且忽略circuitBreaker.errorThresholdPercentage设置。
> 默认值false
> 默认属性 hystrix.command.default.circuitBreaker.forceClosed
> 当circuitBreaker.forceOpen为ture时，该配置不生效

### 4. 指标（Metrics）

- metrics.rollingStats.timeInMilliseconds：执行状态的统计滚动窗口的持续时间（毫秒），是Hystrix断路器使用和发布指标的统计持续的时间。
> 默认值10000
> 默认属性 hystrix.command.default.metrics.rollingStats.timeInMilliseconds
> 窗口被分割成numBuckets（默认10）个buckets，并且以此为增量进行滚动。默认值10秒，窗口被分割成10个buckets，以buckets进行新增统计，并且将最早的过时buckets，保持有效统计的buckets是10个。

- metrics.rollingStats.numBuckets：执行状态的滚动统计窗口被分割的成buckets数量
> 默认值10
> 默认属性 hystrix.command.default.metrics.rollingStats.numBuckets
> metrics.rollingStats.timeInMilliseconds必须能够被metrics.rollingStats.numBuckets整除，否则会抛出异常

- metrics.rollingPercentile.enabled：统计指标是否能够被追踪并且计算成百分比，如果为false，所有的统计指标都会返回-1
> 默认为true
> 默认属性hystrix.command.default.metrics.rollingPercentile.enabled

- metrics.rollingPercentile.timeInMilliseconds：百分比统计指标滚动窗口的持续时间（毫秒），执行次数能够以百分比计算
> 默认值 60000‘’
> 默认属性 hystrix.command.default.metrics.rollingPercentile.timeInMilliseconds

- metrics.rollingPercentile.numBuckets：百分比统计指标滚动窗口被分割的成buckets数量
> 默认值6
> 默认属性 hystrix.command.default.metrics.rollingPercentile.numBuckets
> metrics.rollingPercentile.timeInMilliseconds必须能够被metrics.rollingPercentile.numBuckets整除，否则会抛出异常

- metrics.rollingPercentile.bucketSize：百分比统计指标bucket的容量，每个bucket中储存的最大执行的次数
> 同个周期的执行如果超过这个值，会从bucket的开始进行覆盖
> 增加此属性的值，会增加程序消耗的内存和计算指标的百分比消耗的时间

- metrics.healthSnapshot.intervalInMilliseconds：健康快照的统计周期（单位毫秒），在两个周期之间，允许通过健康快照计算成功和错误百分比，并影响断路器的状态

### 5.请求上下文(Request Context)

- requestCache.enabled：是否开启请求缓存。控制HystrixCommand.getCacheKey() 是否能够与HystrixRequestCache一起使用，来通过请求作用域的缓存删除重复数据。
> 默认值 true
> 默认属性 hystrix.command.default.requestCache.enabled

- requestLog.enabled：时候开启请求日志。熔断器相关的执行和事件能够通过HystrixRequestLog记录。
> 默认值 true
> 默认属性 hystrix.command.default.requestLog.enabled

### 6.雪崩属性（Collapser Properties）

- maxRequestsInBatch：触发批处理执行之前，设置批处理允许的最大请求数量
> 默认值 Integer.MAX_VALUE
> 默认属性 hystrix.collapser.default.maxRequestsInBatch

- timerDelayInMilliseconds：创建批处理之后间隔多久（毫秒数）被触发
> 默认值 10
> 默认属性 hystrix.collapser.default.timerDelayInMilliseconds

- requestCache.enabled：是否为HystrixCollapser.execute()和HystrixCollapser.queue() 调用开启缓存
> 默认值 true
> 默认属性 hystrix.collapser.default.requestCache.enabled

### 7.线程池属性（ThreadPool Properties）
> 1. 大部分情况10个或者更少的线程已经能够给满足需求
> 2. 如果确实要增加线程池容量，可以根据下面公司计算线程池大小：
    $$ RPS峰值 * ss + 额外的数量 $$

- coreSize：核心线程数
> 默认值 10
> 默认属性 hystrix.threadpool.default.coreSize

- maximumSize：线程池可以扩展的最大线程数
> 默认和coreSize值相同，若要设置大于coreSize的值，该属性只有allowMaximumSizeToDivergeFromCoreSize设置为true的时候有效（1.5.9以后版本）
> 默认值 10
> 默认属性 hystrix.threadpool.default.maximumSize

- maxQueueSize：线程池阻塞队列的最大容量。
 - 该值为-1的时，线程池使用同步队列 SynchronousQueue；
 - 该值为正整数时，线程池使用阻塞队列 LinkedBlockingQueue
> 改变线程池队列，需要重启程序才能生效
> 此属性在线程池初始化的时候生效，线程池不重新初始化的时候，队列的实现不能重置或调整大小
> 默认值 -1
> 默认属性 hystrix.threadpool.default.maxQueueSize

- queueSizeRejectionThreshold：队列最大容量阈值，超过阈值的请求会被拒绝
> maxQueueSize为-1时，该属性无效。
> 该属性优先级高于maxQueueSize，没达到maxQueueSize但是已经达到该属性限制的请求会被拒绝。该属性可以动态调整。
> 默认值 5
> 默认属性 hystrix.threadpool.default.queueSizeRejectionThreshold

- keepAliveTimeMinutes：超过核心线程数的线程的存活时间（分钟）<1.5.9版本之后>
> 默认值1
> 默认属性 hystrix.threadpool.default.keepAliveTimeMinutes

- allowMaximumSizeToDivergeFromCoreSize：线程池最大容量是否能够扩展到maximumSize
> 默认值 false
> 默认属性 hystrix.threadpool.default.allowMaximumSizeToDivergeFromCoreSize

- metrics.rollingStats.timeInMilliseconds：统计信息滚动窗口为线程池保存多久的指标（单位毫秒）
> 默认值10000
> 默认属性 hystrix.threadpool.default.metrics.rollingStats.timeInMilliseconds

- metrics.rollingStats.numBuckets：线程池统计指标滚动窗口被分割成的buckets数量
> 默认值10
> 默认属性 hystrix.threadpool.default.metrics.rollingStats.numBuckets