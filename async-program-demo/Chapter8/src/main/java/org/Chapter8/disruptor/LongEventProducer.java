package org.Chapter8.disruptor;

import com.lmax.disruptor.RingBuffer;

public class LongEventProducer {
	private final RingBuffer<LongEvent> ringBuffer;

	public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	public void onData(long bb) {
		long sequence = ringBuffer.next(); // 8.1 第一阶段，获取序列号
		try {
			LongEvent event = ringBuffer.get(sequence); // 8.2获取序列号对应的实体元素
			event.set(bb); // 8.3修改元素的值
		} finally {
			ringBuffer.publish(sequence);// 8.4发布元素。
		}
	}
}