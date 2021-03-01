package com.bigring.jparoadmap;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StudyRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager em;

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // create();
        // update();
        findByJPQL();
    }
    private void create(){
        Member member = new Member();
        member.setName("hello");
        em.persist(member);
    }

    private void update(){
        Member findMember = em.find(Member.class, 1L);
        log.info("Member id: {}, name: {}",findMember.getId(),findMember.getName());
        findMember.setName("updateHello");
    }

    private void findByJPQL(){
        List<Member> memberList = em.createQuery("select m from Member as m", Member.class)
            .setFirstResult(0)
            .setMaxResults(10)
            .getResultList();
        for (Member member : memberList) {
            log.info("member name: {}", member.getName());
        }
    }

}
