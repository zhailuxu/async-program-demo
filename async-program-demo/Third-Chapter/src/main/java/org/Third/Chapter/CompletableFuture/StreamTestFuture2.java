package org.Third.Chapter.CompletableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamTestFuture2 {

	public static String rpcCall(String ip, String param) {

		System.out.println(ip + " rpcCall:" + param);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return param;

	}

	public static void main(String[] args) {

		// 1.生成ip列表
		List<String> ipList = new ArrayList<String>();
		for (int i = 1; i <= 10; ++i) {
			ipList.add("192.168.0." + i);
		}

		// 2.并发调用
		long start = System.currentTimeMillis();
		List<CompletableFuture<String>> futureList = ipList.stream()
				.map(ip -> CompletableFuture.supplyAsync(() -> rpcCall(ip, ip))).collect(Collectors.toList());

		List<String> resultList = futureList.stream().map(future -> future.join()).collect(Collectors.toList());

		// 3.输出
		resultList.stream().forEach(r -> System.out.println(r));

		System.out.println("cost:" + (System.currentTimeMillis() - start));

	}
}
