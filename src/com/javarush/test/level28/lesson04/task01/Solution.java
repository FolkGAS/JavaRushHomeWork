package com.javarush.test.level28.lesson04.task01;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* Пишем свою ThreadFactory
В классе Solution создайте публичный статический класс AmigoThreadFactory, реализующий интерфейс ThreadFactory
1.Реализация интерфейсного метода - создайте и верните трэд, который должен:
1.1. не быть демоном
1.2. иметь нормальный приоритет
1.3. имя трэда должно иметь шаблон "GN-pool-A-thread-B", где
GN - это имя группы,
A - это номер фабрики инкрементируется в пределах класса начиная с 1, используйте AtomicInteger
B - номер треда инкрементируется в пределах конкретной фабрики начиная с 1, используйте AtomicInteger
2.Каждая фабрика должна иметь ту группу тредов (ThreadGroup), в которой она была создана
3. Методы main и emulateThreadFactory не участвуют в тестировании
Пример вывода:
secondGroup-pool-2-thread-1
firstGroup-pool-1-thread-1
firstGroup-pool-1-thread-3
secondGroup-pool-2-thread-3
firstGroup-pool-1-thread-2
secondGroup-pool-2-thread-2
*/
public class Solution {

    public static void main(String[] args) {
        class EmulateThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulateThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulateThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }

    static class AmigoThreadFactory implements ThreadFactory{
        String format = "%s-pool-%d-thread-%d";
        String group = Thread.currentThread().getThreadGroup().getName();
        static AtomicInteger factories = new AtomicInteger(0);
        AtomicInteger threadNumber = new AtomicInteger(0);
        int factoryNumber;

        public AmigoThreadFactory()
        {
            factoryNumber = factories.incrementAndGet();
        }

        @Override
        public Thread newThread(Runnable r)
        {
            Thread thread = new Thread(r);
            thread.setName(String.format(format, group, factoryNumber, threadNumber.incrementAndGet()));
            thread.setDaemon(false);
            thread.setPriority(Thread.NORM_PRIORITY);
            return thread;
        }
    }
}
