package com.xueguoxue.aoplog.dao;

import com.xueguoxue.aoplog.domain.Action;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActionDao extends MongoRepository<Action, String> {
}
