package com.willshuhua.service.impl;

import com.willshuhua.dao.MailDao;
import com.willshuhua.entity.UserInfo;
import com.willshuhua.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by will on 2017-06-22.
 */
@Service
public class MailServiceImpl implements MailService{

    @Autowired
    MailDao mailDao;

    public List<UserInfo> queryAllUserInfo() {
        return mailDao.queryAllUserInfo();
    }
}
