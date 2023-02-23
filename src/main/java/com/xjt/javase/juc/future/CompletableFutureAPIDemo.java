package com.xjt.javase.juc.future;

import java.util.concurrent.*;

public class CompletableFutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //m_thenrun();

//        m_thenapply();

//        m_handle();

//        m_applyToEither();
//        m_thenCombine();

//        m_anyof();


//        m_thenRunAsync();

//        m_complete();


    }

    /**
     * //ForkJoinPool.commonPool-worker-5	---come in
     * //ForkJoinPool.commonPool-worker-3	---come in
     * //main	play2 is winner
     */
    public static void m_applyToEither() {
        CompletableFuture<String> play1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in ");
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "play1 ";
        });

        CompletableFuture<String> play2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in ");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "play2";
        });

        CompletableFuture<String> applyToEither = play1.applyToEither(play2, f -> {     //对计算速度进行选用
            return f + " is winner";
        });

        System.out.println(Thread.currentThread().getName() + "\t" + applyToEither.join());
    }

    public static void m_complete() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        CompletableFuture<String> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("=============>"+ Thread.currentThread().getName() +" thread");
            try {
                TimeUnit.SECONDS.sleep(5);      //执行需要2秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });

        System.out.println("=============>main thread");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(uCompletableFuture.complete("xxoo"));
        System.out.println(uCompletableFuture.get());

        long end = System.currentTimeMillis();
        System.out.println("计算耗时==" + (end - start));
    }

    public static void m_thenRunAsync() {
        //自定义线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        try {
            CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
//                try {
//                    TimeUnit.MILLISECONDS.sleep(20);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println("111--------->" + Thread.currentThread().getName());
                return "111";
            },threadPool).thenRunAsync(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("222--------->" + Thread.currentThread().getName());
            }).thenRunAsync(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("333--------->" + Thread.currentThread().getName());
            }).thenRunAsync(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("444--------->" + Thread.currentThread().getName());
            });
            completableFuture.get(2L,TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    public static void m_anyof() throws InterruptedException, ExecutionException {
        CompletableFuture<String> futureImg = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 查询商品的图片信息");
            return "hello.jpg";
        });

        CompletableFuture<String> futureAttr = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 查询商品的属性");
            return "黑色+256G";
        });

        CompletableFuture<String> futureDesc = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 查询商品介绍");
            return "华为";
        });
        //需要全部完成
        CompletableFuture<Void> all = CompletableFuture.allOf(futureImg, futureAttr, futureDesc);
        System.out.println(all.get());      //null
        //想要取得返回结果 使用 get()
//        futureImg.get();
//        futureAttr.get();
//        futureDesc.get();

//        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(futureImg, futureAttr, futureDesc);
//        System.out.println(anyOf.get());
//        System.out.println("main over.....");
    }

    public static void m_thenCombine() {
        //public <U,V> CompletableFuture<V> thenCombine
        //(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn)
        //两个CompletionStage任务都完成后,最终把两个任务的结果一起交给thenCombine来处理
        //先完成的先等着,等待其他分支任务
        System.out.println(CompletableFuture.supplyAsync(() -> {
            return 10;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            return 20;
        }), (r1, r2) -> {
            return r1 + r2;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            return 30;
        }), (r3, r4) -> {
            return r3 + r4;
        }).join());         //60

        System.out.println(CompletableFuture.supplyAsync(() -> {
            return 10;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            return 20;
        }), (r1, r2) -> {
            return r1 + r2;
        }).join());         //30
    }

    public static void m_handle() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,
                20,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(50),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        Integer aaa = 1;

        Integer ret = CompletableFuture.supplyAsync(() -> {
            return aaa + 1;
        }).handle((f, e) -> {
            System.out.println("-----1");
            return f + 2;
        }).handle((f, e) -> {
            //如果这里异常了,handle方法依旧可以继续执行下去
            int error = 1 / 0;
            System.out.println("-----2");
            return f + 3;
        }).handle((f, e) -> {
            System.out.println("-----3");
            return f + 4;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("----result: " + v);
            } else {
                System.out.println("========ret=" + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        }).join();
        System.out.println(ret);

        threadPoolExecutor.shutdown();
    }

    /**
     * main	over....
     * -----1
     * java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
     * at java.base/java.util.concurrent.CompletableFuture.encodeThrowable(CompletableFuture.java:314)
     * at java.base/java.util.concurrent.CompletableFuture.completeThrowable(CompletableFuture.java:319)
     * at java.base/java.util.concurrent.CompletableFuture$UniApply.tryFire(CompletableFuture.java:645)
     */
    public static void m_thenapply() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }).thenApply(s -> {
            System.out.println("-----1");
            //如果加上int error=1/0; 由于存在依赖关系(当前步错,不走下一步),当前步骤有异常的话就叫停
            int error = 1 / 0;
            return s + 1;
        }).thenApply(s -> {
            System.out.println("-----2");
            return s + 2;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("result-----" + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
        System.out.println(Thread.currentThread().getName() + "\t" + "over....");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * //1号任务	pool-1-thread-1
     * //2号任务	pool-1-thread-1
     * //3号任务	pool-1-thread-1
     * //4号任务	pool-1-thread-1
     */
    public static void m_thenrun() {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1号任务" + "\t" + Thread.currentThread().getName());
            return "abcd";
        }, threadPool).thenRun(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2号任务" + "\t" + Thread.currentThread().getName());
        }).thenRun(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("3号任务" + "\t" + Thread.currentThread().getName());
        }).thenRun(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("4号任务" + "\t" + Thread.currentThread().getName());
        });
    }
}



