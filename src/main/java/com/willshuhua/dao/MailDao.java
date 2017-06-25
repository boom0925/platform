package com.willshuhua.dao;

import com.willshuhua.entity.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by will on 2017-06-22.
 */
@Repository
public interface MailDao {

    List<UserInfo> queryAllUserInfo();
}
