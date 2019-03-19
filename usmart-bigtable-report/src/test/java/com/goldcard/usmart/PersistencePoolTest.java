package com.goldcard.usmart;

import java.util.Collection;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author 2299
 * @version 1.0 2019-02-22
 */
public class PersistencePoolTest {

  private Random random = new Random();

//  @Test
  public void testPool() throws InterruptedException {
    AbstractPersistencePool<String> pool = new StringPersistencePool();
    new StringSender("A", pool, 201).start();
    new StringSender("B", pool, 201).start();
    new StringSender("C", pool, 201).start();
    new StringSender("D", pool, 201).start();
    new StringSender("E", pool, 201).start();

    while (Thread.activeCount() > 3) {
      Thread.yield();
    }

    Thread.sleep(2000);
    Assert.assertEquals(201 * 5, pool.getCount());

  }

  class StringPersistencePool extends AbstractPersistencePool<String> {

    @Override
    public void drink(Collection<String> pool) {
      StringBuffer buffer = new StringBuffer();
      for (String s : pool) {
        buffer.append(s);
      }
      System.out.println(buffer);
    }
  }

  class StringSender extends Thread {

    private final String item;
    private final AbstractPersistencePool<String> pool;
    private final int size;

    StringSender(String item, AbstractPersistencePool<String> pool, int size) {
      this.item = item;
      this.pool = pool;
      this.size = size;
    }

    @Override
    public void run() {
      for (int i = 0; i < size; i++) {
        pool.put(item);
        try {
          Thread.sleep(random.nextInt(50));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

//  @Test
  public void testRandom() {
    for (int i = 0; i < 100; i++) {
      System.out.print(random.nextInt(10)+" ");
    }
  }
}