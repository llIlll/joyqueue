package com.jd.joyqueue.nsr.composition.service;

import com.jd.joyqueue.nsr.composition.config.CompositionConfig;
import com.jd.joyqueue.domain.Consumer;
import com.jd.joyqueue.domain.TopicName;
import com.jd.joyqueue.model.PageResult;
import com.jd.joyqueue.model.QPageQuery;
import com.jd.joyqueue.nsr.model.ConsumerQuery;
import com.jd.joyqueue.nsr.service.ConsumerService;

import java.util.List;

/**
 * CompositionConsumerService
 * author: gaohaoxiang
 * date: 2019/8/12
 */
public class CompositionConsumerService implements ConsumerService {

    private CompositionConfig config;
    private ConsumerService igniteConsumerService;
    private ConsumerService journalkeeperConsumerService;

    public CompositionConsumerService(CompositionConfig config, ConsumerService igniteConsumerService,
                                      ConsumerService journalkeeperConsumerService) {
        this.config = config;
        this.igniteConsumerService = igniteConsumerService;
        this.journalkeeperConsumerService = journalkeeperConsumerService;
    }

    @Override
    public void deleteByTopicAndApp(TopicName topic, String app) {

    }

    @Override
    public Consumer getByTopicAndApp(TopicName topic, String app) {
        return null;
    }

    @Override
    public List<Consumer> getByTopic(TopicName topic, boolean withConfig) {
        return null;
    }

    @Override
    public List<Consumer> getByApp(String app, boolean withConfig) {
        return null;
    }

    @Override
    public List<Consumer> getConsumerByClientType(byte clientType) {
        return null;
    }

    @Override
    public void add(Consumer consumer) {

    }

    @Override
    public void update(Consumer consumer) {

    }

    @Override
    public void remove(Consumer consumer) {

    }

    @Override
    public Consumer getById(String id) {
        return null;
    }

    @Override
    public Consumer get(Consumer model) {
        return null;
    }

    @Override
    public void addOrUpdate(Consumer consumer) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public void delete(Consumer model) {

    }

    @Override
    public List<Consumer> list() {
        return null;
    }

    @Override
    public List<Consumer> list(ConsumerQuery query) {
        return null;
    }

    @Override
    public PageResult<Consumer> pageQuery(QPageQuery<ConsumerQuery> pageQuery) {
        return null;
    }
}