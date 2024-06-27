package com.abs.wfs.workman.function.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
public class RunnableTaskExample {

    public static void main(String[] args) {

        RunnableTaskExample runnableTaskExample = new RunnableTaskExample();
        runnableTaskExample.executeAsyncTaskWithReturn();
    }

    private void executeAsyncTaskWithoutReturn() {

        log.info("All task is ready to start.");

        // work item list
        List<Runnable> tasks =  Arrays.asList(
                () -> doWork("Task01"),
                () -> doWork("Task02"),
                () -> doWork("Task03"),
                () -> doWork("Task04"),
                () -> doWork("Task05")
        );

        // create thread pool
        ExecutorService service = Executors.newFixedThreadPool(tasks.size());

        // create future to execute tasks async
        List<CompletableFuture<Void>> futures = tasks.stream()
                .map(task -> CompletableFuture.runAsync(task, service))
                .collect(Collectors.toList());

        // join all future to wait for all tasks to complete.
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // shortcut to create future and join
//        CompletableFuture.allOf(tasks.stream()
//                .map(task -> CompletableFuture.runAsync(task, service)).toArray(CompletableFuture[]::new)).join();

        // shutdown thread pool
        service.shutdown();

        log.info("All task is done.");

    }

    private void executeAsyncTaskWithReturn() {

        log.info("All task is ready to start.");

        // work item list
        List<Supplier<String>> tasks =  Arrays.asList(
                () -> doWork("Task01"),
                () -> doWork("Task02"),
                () -> doWork("Task03"),
                () -> doWork("Task04"),
                () -> doWork("Task05")
        );

        // create thread pool
        ExecutorService service = Executors.newFixedThreadPool(tasks.size());

        // create future to execute tasks async
        List<CompletableFuture<String>> futures = tasks.stream()
                .map(task -> CompletableFuture.supplyAsync(task, service))
                .collect(Collectors.toList());

        // join all future to wait for all tasks to complete.
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // Get the results of each future
        List<String> results = futures.stream().map(CompletableFuture::join).collect(Collectors.toList());

        // shutdown thread pool
        service.shutdown();

        results.forEach(log::info);
        log.info("All task is done.");

    }


    private String doWork(String taskName) {

        String threadName = Thread.currentThread().getName();
        log.info("Task {} started by thread: {}.", taskName, threadName);
        long startTime = System.currentTimeMillis();

        try {
            // Simulating work by sleeping for a random duration
            Thread.sleep((long) (Math.random() * 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return String.format("%s is complete it's work %s in %s", threadName, taskName, String.valueOf(System.currentTimeMillis() - startTime));

    }
}