package com.goldcard.usmart.mq.rocketmq;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageConst;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import com.goldcard.usmart.service.CollectionService;

@Component
public class PersistenceMsgConsumer {

	private static final Logger logger = LoggerFactory.getLogger(PersistenceMsgConsumer.class);
	
	DefaultMQPushConsumer consumer = null;

	@Value("${rocketmq.instrucitonTopicName}")
	private String instrucitonTopicName;
	
	@Value("${rocketmq.instructionConsumerId}")
	private String instructionConsumerId;
	@Value("${rocketmq.rocketMqNameSrv}")
	private String mqNameSrv;
	@Autowired
	CollectionService collectionService;
	
	@PostConstruct
	public void startUp() {
		consumer = new DefaultMQPushConsumer(instructionConsumerId);
		consumer.setNamesrvAddr(mqNameSrv);
		consumer.setMessageModel(MessageModel.BROADCASTING);
		consumer.setPullBatchSize(100);
		consumer.setConsumeMessageBatchMaxSize(100);
		try {
			consumer.subscribe(instrucitonTopicName,"*");
			consumer.registerMessageListener(new MessageListenerConcurrently() {
				public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
	            	// 处理消息
					try {
						String maxOffset = msgs.get(0).getProperty(MessageConst.PROPERTY_MAX_OFFSET);
						long currentOffset = msgs.get(0).getQueueOffset();
						long result = Long.valueOf(maxOffset) - currentOffset;
						if (result > 100) {
							logger.warn("消息堆积：maxOffset:" + maxOffset + ",currrentOffset:" + currentOffset + ",消息堆积个数:"
									+ result + ",storeHost=" + msgs.get(0).getStoreHost());
						}
						List<String> listStr=new ArrayList<String>();

						for (Message msg : msgs) {
							String str = null;
							str = new String(msg.getBody(), "UTF-8");
							listStr.add(str);
						}
						logger.info("length:" + msgs.size());
						
						collectionService.executor(listStr);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
	                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	            }
	        });
			consumer.start();
		} catch (MQClientException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
