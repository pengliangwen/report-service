package com.goldcard.usmart;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 2299
 * @version 1.0 2019-02-22
 */
public abstract class AbstractPersistencePool<T> {

  private final int INTERVAL_SECONDS;
  private final int MAX_POOL_SIZE;

  private volatile long lastDrinkTime = System.nanoTime();
  private final ArrayBlockingQueue<T> pool;
  private final ScheduledExecutorService checkTask;

  private final AtomicInteger count;

  public AbstractPersistencePool() {
    this(1, 100);
  }

  public AbstractPersistencePool(int intervalSeconds, int maxPoolSize) {
    INTERVAL_SECONDS = intervalSeconds;
    MAX_POOL_SIZE = maxPoolSize;
    count = new AtomicInteger();
    pool = new ArrayBlockingQueue<>(MAX_POOL_SIZE);
    checkTask = Executors.newSingleThreadScheduledExecutor();
    initCheckTask();
  }

  private void initCheckTask() {
//	  pool.p
    checkTask.scheduleWithFixedDelay(() -> {
      if (pool.size() != 0 && System.nanoTime() - lastDrinkTime > 1000000000 * INTERVAL_SECONDS) {
        synchronized (pool) {
          if (pool.size() != MAX_POOL_SIZE ) {
            System.out.println(Thread.currentThread().getName() +": checkTask drink...");
            drink(pool);
            count.addAndGet(pool.size());
            pool.clear();
            lastDrinkTime = System.nanoTime();
          }
        }
      }
    }, 0, INTERVAL_SECONDS, TimeUnit.SECONDS);
  }

  public final void put(T s) {
    if (pool.size() < MAX_POOL_SIZE) {
      try {
        pool.put(s);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } else {
      synchronized (pool) {
        if (pool.size() == MAX_POOL_SIZE) {
          System.out.println(Thread.currentThread().getName() + ": full drink...");
          drink(pool);
          count.addAndGet(pool.size());
          pool.clear();
          lastDrinkTime = System.nanoTime();
        }
      }
      put(s);
    }
  }

  public final int getCount() {
    return count.get();
  }

  public abstract void drink(Collection<T> pool);

}
