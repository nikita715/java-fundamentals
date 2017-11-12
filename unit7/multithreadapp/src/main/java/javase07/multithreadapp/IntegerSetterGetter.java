package javase07.multithreadapp;

import java.io.PrintStream;
import java.util.Random;

class IntegerSetterGetter extends Thread {
    private static PrintStream printStream = new PrintStream(System.out);
    private final SharedResource resource;
    private boolean run;
    private Random rand = new Random();
    private static volatile Integer setterCount = 0;
    private static volatile Integer setterGetterCount = 0;
    private static final Object setterCountLock = new Object();
    private static final Object setterGetterCountLock = new Object();

    public IntegerSetterGetter(String name, SharedResource resource) {
        super(name);
        this.resource = resource;
        run = true;
    }

    public void stopThread() {
        run = false;
    }

    @Override
    public synchronized void start() {
        synchronized (setterGetterCountLock) {
            setterGetterCount++;
        }
        super.start();
    }

    @Override
    public void run() {
        int action;

        try {
            while (run && setterGetterCount > 1) {
                action = rand.nextInt(1000);
                if (action % 2 == 0 && setterCount > 0) {
                    getIntegersFromResource();
                } else {
                    synchronized (setterCountLock) {
                        setterCount++;
                    }

                    setIntegersIntoResource();

                    synchronized (setterCountLock) {
                        setterCount--;
                    }
                }
            }

            printStream.println("Поток " + getName() + " завершил работу.");

            synchronized (setterGetterCountLock) {
                setterGetterCount--;
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

    }

    private void getIntegersFromResource() throws InterruptedException {
        Integer number;

        synchronized (resource) {
            printStream.printf("Поток %s хочет извлечь число.%n", getName());
            number = resource.getElement();
            while (number == null) {
                printStream.printf("Поток %s ждет пока очередь заполнится.%n", getName());

                resource.wait();
                printStream.printf("Поток %s возобновил работу.%n", getName());

                number = resource.getElement();
            }
            printStream.printf("Поток %s извлек число %d%n", getName(), number);
        }

    }

    private void setIntegersIntoResource() {
        Integer number = rand.nextInt(500);

        synchronized (resource) {
            resource.setElement(number);
            printStream.printf("Поток %s записал число %d%n", getName(), number);
            resource.notifyAll();
        }

    }
}