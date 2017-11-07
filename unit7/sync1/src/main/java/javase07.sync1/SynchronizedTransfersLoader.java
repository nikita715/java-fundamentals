package javase07.sync1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class SynchronizedTransfersLoader extends TransfersLoader {

    List<Transfer> transfers = new ArrayList<>();

    public SynchronizedTransfersLoader(Path path) throws IOException {
        super(path);
        List<Thread> threads = startTransferFormerThreads(path);
        waitForAllThreads(threads);
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    private void waitForAllThreads(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Thread> startTransferFormerThreads(Path path) throws IOException {
        List<Thread> threads = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Thread threadForThisLine = new Thread(new SynchronizedTransferFormer(transfers, line));
                threadForThisLine.start();
                threads.add(threadForThisLine);
            }
        }

        return threads;
    }

    private static class SynchronizedTransferFormer extends TransferFormer implements Runnable {
        private final List<Transfer> transfers;

        private SynchronizedTransferFormer(List<Transfer> transfers, String input) {
            super(input);
            this.transfers = transfers;
        }

        @Override
        public void run() {
            Matcher matcher = pattern.matcher(input);

            if (matcher.matches()) {
                Transfer transfer = getTransfer(matcher);
                synchronized (transfers) {
                    transfers.add(transfer);
                }
            }
        }
    }
}
