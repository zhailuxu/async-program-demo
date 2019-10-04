package org.Chapter8.akka;

import java.io.Serializable;

public class Messages {
	// 加法域对象
	public static class Sum implements Serializable {
		private int first;
		private int second;

		public Sum(int first, int second) {
			this.first = first;
			this.second = second;
		}

		public int getFirst() {
			return first;
		}

		public int getSecond() {
			return second;
		}
	}

	// 存放计算结果的域对象
	public static class Result implements Serializable {
		private int result;

		public Result(int result) {
			this.result = result;
		}

		public int getResult() {
			return result;
		}
	}
}
