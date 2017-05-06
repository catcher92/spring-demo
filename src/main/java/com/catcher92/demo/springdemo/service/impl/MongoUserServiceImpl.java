package com.catcher92.demo.springdemo.service.impl;

import com.catcher92.demo.springdemo.entity.User;
import com.catcher92.demo.springdemo.service.MongoUserService;
import com.mongodb.WriteResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by catcher92 on 2017/5/6.
 */
@Service
public class MongoUserServiceImpl implements MongoUserService{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public long add(User user) {
        mongoTemplate.insert(user);
        return 1L;
    }

    @Override
    public int del(Long userId) {
        Query query = new Query();
        CriteriaDefinition criteria = Criteria.where("userId").is(userId);
        query.addCriteria(criteria);
        WriteResult result = mongoTemplate.remove(query, User.class);
        return result.getN();
    }

    @Override
    public User find(Long userId) {
        Query query = new Query();
        CriteriaDefinition criteria = Criteria.where("userId").is(userId);
        query.addCriteria(criteria);
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public int update(User user) {
        // mongoTemplate.save(user);
        Query query = new Query(new Criteria("userId").is(user.getId()));
        Update update = updateSelective(user);
        WriteResult writeResult = mongoTemplate.updateFirst(query, update, User.class);
        return writeResult.getN();
    }

    @Override
    public int updateAge(Long userId) {
        Query query = new Query();
        CriteriaDefinition baseCriteria = Criteria.where("userId").is(userId);
        query.addCriteria(baseCriteria);
        Update update = new Update();
        update.inc("age", 1);
        update.set("updateBy", SecurityUtils.getSubject().getPrincipal());
        update.set("updateDate", new Date());
        WriteResult writeResult = mongoTemplate.updateFirst(query, update, User.class);
        return writeResult.getN();
    }

    private Update updateSelective(User user) {
        Update update = new Update();
        if (0 != user.getAge()) {
            update.set("age", user.getAge());
        }
        if (StringUtils.isNotBlank(user.getName())) {
            update.set("name", user.getName());
        }
        if (null != user.getCreateDate()) {
            update.set("createDate", user.getCreateDate());
        }
        if (null != user.getUpdateDate()) {
            update.set("updateDate", user.getUpdateDate());
        }
        return update;
    }
 }
