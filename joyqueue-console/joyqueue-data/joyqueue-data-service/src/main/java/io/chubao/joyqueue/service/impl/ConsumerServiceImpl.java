/**
 * Copyright 2019 The JoyQueue Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
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
package io.chubao.joyqueue.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import io.chubao.joyqueue.convert.CodeConverter;
import io.chubao.joyqueue.model.domain.Consumer;
import io.chubao.joyqueue.model.domain.Identity;
import io.chubao.joyqueue.model.domain.Topic;
import io.chubao.joyqueue.nsr.ConsumerNameServerService;
import io.chubao.joyqueue.nsr.TopicNameServerService;
import io.chubao.joyqueue.service.ApplicationService;
import io.chubao.joyqueue.service.ConsumerService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("consumerService")
public class ConsumerServiceImpl  implements ConsumerService {
    private final Logger logger = LoggerFactory.getLogger(ConsumerServiceImpl.class);

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private TopicNameServerService topicNameServerService;

    @Autowired
    private ConsumerNameServerService consumerNameServerService;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public int add(Consumer consumer) {
        Preconditions.checkArgument(consumer!=null && consumer.getTopic()!=null, "invalid consumer arg");

        try {
            //Find topic
            Topic topic = topicNameServerService.findById(consumer.getTopic().getId());
            consumer.setTopic(topic);
            consumer.setNamespace(topic.getNamespace());

            return consumerNameServerService.add(consumer);
        }catch (Exception e){
            String errorMsg = String.format("add consumer with nameServer failed, consumer is %s.", JSON.toJSONString(consumer));
            logger.error(errorMsg, e);
            throw new RuntimeException(errorMsg, e);//回滚
        }
    }

    @Override
    public Consumer findById(String s) throws Exception {
        return consumerNameServerService.findById(s);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public int delete(Consumer consumer) {
        //Validate
        checkArgument(consumer);
        try {
            consumerNameServerService.delete(consumer);
        }catch (Exception e){
            String errorMsg = String.format("remove consumer status by nameServer failed, consumer is %s.", JSON.toJSONString(consumer));
            logger.error(errorMsg, e);
            throw new RuntimeException(errorMsg, e);//回滚
        }
        return 1;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public int update(Consumer consumer) {
        //Validate
        checkArgument(consumer);
		try {
            consumerNameServerService.update(consumer);
        }catch (Exception e){
            String errorMsg = String.format("update consumer by nameServer failed, consumer is %s.", JSON.toJSONString(consumer));
            logger.error(errorMsg, e);
            throw new RuntimeException(errorMsg, e);//回滚
        }
        return 1;
    }

    @Override
    public Consumer findByTopicAppGroup(String namespace, String topic, String app, String group) {
        try {
            if (StringUtils.isNotBlank(group)) {
                return consumerNameServerService.findByTopicAndApp(topic, namespace, CodeConverter.convertApp(new Identity(app), group));
            } else {
                return consumerNameServerService.findByTopicAndApp(topic, namespace, app);
            }
        } catch (Exception e) {
            logger.error("findByTopicAppGroup error",e);
            throw new RuntimeException("findByTopicAppGroup error",e);
        }
    }

    @Override
    public List<Consumer> findByTopic(String topic, String namespace) throws Exception {
        return consumerNameServerService.findByTopic(topic, namespace);
    }

    @Override
    public List<Consumer> findByApp(String app) throws Exception {
        return consumerNameServerService.findByApp(app);
    }

    @Override
    public List<String> findAllSubscribeGroups() {
        try {
            return consumerNameServerService.findAllSubscribeGroups();
        } catch (Exception e) {
            logger.error("findAllSubscribeGroups error",e);
            throw new RuntimeException("findAllSubscribeGroups error",e);
        }
    }


    private void checkArgument(Consumer consumer) {
        Preconditions.checkArgument(consumer != null && consumer.getId() != null, "invalidate consumer arg.");
    }

}