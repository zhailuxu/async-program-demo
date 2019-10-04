package org.Chapter8.disruptor;

import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.util.DaemonThreadFactory;

public class LongEventMain {

	public static void main(String[] args) throws Exception {
		// 1.创建Ring Buffer中事件元素的工厂对象
		LongEventFactory factory = new LongEventFactory();

		// 2.指定Ring Buffer的大小,必须为2的幂次方.
		int bufferSize = 1024;

		// 3.构造Disruptor
		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, DaemonThreadFactory.INSTANCE, ProducerType.SINGLE,new BlockingWaitStrategy());

		// 4.注册消费者
		disruptor.handleEventsWith(new JournalConsumer(), new ReplicationConsumer()).then(new ApplicationConsumer());

		// 5.启动Disruptor, 启动线程运行
		disruptor.start();

		// 6.从Disruptor中获取Ring Buffer。
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

		// 7. 创建生产者
		LongEventProducer producer = new LongEventProducer(ringBuffer);

		// 8. 生产元素，并放入RingBuffer
		for (long l = 0; l < 100; l++) {
			producer.onData(l);

			Thread.sleep(1000);
		}

		// 9.挂起当前线程
		Thread.currentThread().join();

	}
}