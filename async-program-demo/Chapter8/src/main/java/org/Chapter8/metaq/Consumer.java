package org.Chapter8.metaq;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class Consumer {

	public static void main(String[] args) throws InterruptedException, MQClientException {

		// 1. 创建消费实例 和 配置ns地址
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("my-consumer-group");
		consumer.setNamesrvAddr("127.0.0.1:9876");

		// 2. 消费属性配置
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

		// 3. 订阅TopicTest topic下所有tag
		consumer.subscribe("TopicTest", "*");
		// 4. 注册回调
		consumer.registerMessageListener(new MessageListenerConcurrently() {

			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				for (MessageExt msg : msgs) {
					String body = "";
					try {
						body = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					System.out.printf("%s Receive New Messages: %s %s %n", Thread.currentThread().getName(),
							msg.getMsgId(), body);

				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});

		// 5.启动消费实例
		consumer.start();

		System.out.printf("Consumer Started.%n");
	}
}