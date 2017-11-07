package javase07.sync1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.*;
import java.util.regex.Matcher;

public class ConcurrentTransferLoader extends TransfersLoader {

    private List<Transfer> transfers = new ArrayList<>();

    public ConcurrentTransferLoader(Path path) throws FileNotFoundException {
        super(path);
        List<Future<Transfer>> futuresList = getTransferFutures(path);
        while (!futuresList.isEmpty()) {
            transfers.addAll(getFinishedTransfers(futuresList));
        }
    }


    @Override
    public List<Transfer> getTransfers() {
        return transfers;
    }

    private List<Transfer> getFinishedTransfers(List<Future<Transfer>> futureList) {
        List<Transfer> transfers = new ArrayList<>();

        for (ListIterator<Future<Transfer>> iterator = futureList.listIterator(); iterator.hasNext();) {
            Future<Transfer> transferFuture = iterator.next();
            if (transferFuture.isDone()) {
                try {
                    transfers.add(transferFuture.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                } finally {
                    iterator.remove();
                }
            }
        }

        return transfers;
    }

    private List<Future<Transfer>> getTransferFutures(Path path) {
        List<Future<Transfer>> futureList = new ArrayList<>();

        ExecutorService executorService = Executors.newCachedThreadPool();

        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Future<Transfer> transferFuture = executorService.submit(new ConcurrentTransferFormer(line));
                futureList.add(transferFuture);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return futureList;
    }

    private class ConcurrentTransferFormer extends TransferFormer implements Callable<Transfer> {

        public ConcurrentTransferFormer(String input) {
            super(input);
        }

        @Override
        public Transfer call() throws Exception {
            Matcher matcher = pattern.matcher(input);

            if (matcher.matches()) {
                return getTransfer(matcher);
            } else {
                throw new RuntimeException("Wrong format");
            }
        }
    }
}
