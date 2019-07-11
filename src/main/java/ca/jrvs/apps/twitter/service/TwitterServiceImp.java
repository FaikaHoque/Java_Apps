package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;

public class TwitterServiceImp implements TwitterService {

    private CrdDao dao;

    public TwitterServiceImp(CrdDao dao){
        this.dao = dao;
    }




}
