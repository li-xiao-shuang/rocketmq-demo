/*
 * Copyright 2021 Gypsophila open source organization.
 *
 * Licensed under the Apache License,Version2.0(the"License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.rocketmq.demo;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
 * 单项发送
 *
 * @author lixiaoshuang
 */
public class OnewayProducer {
    
    private static final String DEFAULT_TOPIC = "hello-topic";
    
    
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("hello-rocketmq-group");
        producer.setNamesrvAddr("101.42.108.126:9876");
        producer.setSendMsgTimeout(10000);
        producer.start();
        
        for (int i = 0; i < 10; i++) {
            Message message = new Message(DEFAULT_TOPIC, "TAGC",
                    ("hello rocketmq: " + i).getBytes(StandardCharsets.UTF_8));
            producer.sendOneway(message);
        }
        
        Thread.sleep(5000);
        producer.shutdown();
    }
    
}
