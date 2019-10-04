package org.Third.Chapter.CompletableFuture;

import java.util.ArrayList;
import java.util.List;

public class StreamTestFuture {

	public static String rpcCall(String ip, String param) {

		System.out.println(ip + " rpcCall:" + param);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
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

		// 2.发起广播调用
		long start = System.currentTimeMillis();
		List<String> result = new ArrayList<>();
		for (String ip : ipList) {
			result.add(rpcCall(ip, ip));
		}

		// 3.输出
		result.stream().forEach(r -> System.out.println(r));
		System.out.println("cost:" + (System.currentTimeMillis() - start));
	}
}
