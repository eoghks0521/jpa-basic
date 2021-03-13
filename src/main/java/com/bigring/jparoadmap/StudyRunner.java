package com.bigring.jparoadmap;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bigring.jparoadmap.relation.RelationMember;
import com.bigring.jparoadmap.relation.Team;
import com.bigring.jparoadmap.shop.domain.Book;
import com.bigring.jparoadmap.shop.domain.Member;
import com.bigring.jparoadmap.shop.domain.Order;
import com.bigring.jparoadmap.shop.domain.OrderItem;

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
        // findByJ PQL();
        //managePersistenceContext();
        // unidirectional();
        // bidirectional();
        // sample();
        // joinStrategy();
        // tablePerClassStrategy();
        // mappedSuperClass();
        // sample2();
        // proxy();
        cacadeAndOrphan();
    }

    private void create() {
        BasicMember basicMember = new BasicMember();
        basicMember.setId(1L);
        basicMember.setName("hello");
        em.persist(basicMember);
    }

    private void update() {
        BasicMember findBasicMember = em.find(BasicMember.class, 1L);
        log.info("Member id: {}, name: {}", findBasicMember.getId(), findBasicMember.getName());
        findBasicMember.setName("updateHello");
    }

    private void findByJPQL() {
        List<BasicMember> basicMemberList = em.createQuery("select m from Member as m", BasicMember.class)
            .setFirstResult(0)
            .setMaxResults(10)
            .getResultList();
        for (BasicMember basicMember : basicMemberList) {
            log.info("member name: {}", basicMember.getName());
        }
    }

    private void managePersistenceContext() {
        //비영속
        BasicMember basicMember = new BasicMember();
        basicMember.setId(3L);
        basicMember.setName("hi");

        //영속
        log.info("before");
        em.persist(basicMember);
        log.info("after");
        // before after 와 상관없이 뒤에 쿼리가 날아간다. -> 트랜잭션 커밋을 하는 순간 날아감

        // 1차 캐시에서 값을 가져오기 때문에 쿼리가 날아가지 않는다.
        BasicMember findBasicMember = em.find(BasicMember.class, "3L");
        log.info("member name: {}", findBasicMember.getName());
    }

    private void unidirectional() {
        Team team = new Team();
        team.name("TeamA");
        em.persist(team);

        RelationMember member = new RelationMember();
        member.username("MemberA");
        // 양방향 테스트를 위해 주석처리
        // member.team(team);
        em.persist(member);

        // 영속성 컨텍스트의 1차캐시를 타지 않게 하고 디비에서 가져오는 쿼리를 보고 싶다하는 경우
        // em.flush();
        // em.clear();

        RelationMember findMember = em.find(RelationMember.class, member.id());
        Team findTeam = findMember.team();
        log.info("findTeam = {}", findTeam.name());

        // 양방향 테스트를 위해 주석처리
        // 맴버 외래키 변경
        // Team newTeam = em.find(Team.class, 100L);
        // findMember.team(newTeam);
    }

    private void bidirectional() {
        Team team = new Team();
        team.name("TeamA");
        em.persist(team);

        RelationMember member = new RelationMember();
        member.username("MemberA");
        member.changeTeam(team);
        em.persist(member);

        em.flush();
        em.clear();

        Team findTeam = em.find(Team.class, team.id());
        List<RelationMember> members = findTeam.members();

        for (RelationMember relationMember : members) {
            log.info("username: {}", relationMember.username());
        }
    }

    private void sample() {

        Member member = new Member();
        member.setName("memberA");
        em.persist(member);

        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setMember(member);
        em.persist(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setCount(3);
        orderItem.addOrder(order);
        em.persist(orderItem);

        em.flush();
        em.clear();

        Order findOrder = em.find(Order.class, order.getId());
        List<OrderItem> orderItems = findOrder.getOrderItems();

        for (OrderItem findOrderItem : orderItems) {
            log.info("item count: {}", findOrderItem.getCount());
        }
    }

    private void joinStrategy() {
        BasicMovie movie = new BasicMovie();
        movie.setDirector("aaaa");
        movie.setActor("bbbb");
        movie.setName("바람과함께사라지다");
        movie.setPrice(10000);

        em.persist(movie);

        em.flush();
        em.clear();

        BasicMovie findMovie = em.find(BasicMovie.class, movie.getId());
        log.info("findMovie: {}", findMovie);
    }

    private void tablePerClassStrategy() {
        // union 으로 모든 테이블을 뒤적거리므로 매우 비효율
        BasicMovie movie = new BasicMovie();
        movie.setDirector("aaaa");
        movie.setActor("bbbb");
        movie.setName("바람과함께사라지다");
        movie.setPrice(10000);

        em.persist(movie);

        em.flush();
        em.clear();

        BasicItem findItem = em.find(BasicItem.class, movie.getId());
        log.info("findItem: {}", findItem);
    }

    private void mappedSuperClass() {
        RelationMember member = new RelationMember();
        member.setCreatedBy("kwon");
        member.setCreateDate(LocalDateTime.now());

        em.persist(member);
    }

    private void sample2() {
        Book book = new Book();
        book.setName("kwon's house");
        book.setAuthor("bigring");

        em.persist(book);
    }

    private void proxy() {
        BasicMember member = new BasicMember();
        member.setName("kwon");
        member.setId(1L);

        em.persist(member);

        em.flush();
        em.clear();

        // JPA 의 기본 메커니즘은 영속성 컨텍스트를 이용해서 같은 호출에 대해서 == 비교가 true 가 되어야한다.
        BasicMember m1 = em.find(BasicMember.class, member.getId());
        BasicMember reference = em.getReference(BasicMember.class, member.getId());

        log.info("m1 == reference: {}", m1 == reference);
        em.clear();

        reference = em.getReference(BasicMember.class, member.getId());
        m1 = em.find(BasicMember.class, member.getId());
        log.info("m1 == reference: {}", m1 == reference);

    }

    private void cacadeAndOrphan() {
        Child child1 = new Child();
        Child child2 = new Child();

        Parent parent = new Parent();
        parent.addChild(child1);
        parent.addChild(child2);

        em.persist(parent);

        em.flush();
        em.clear();

        Parent parent1 = em.find(Parent.class, parent.getId());
        parent1.getChildren().remove(0);
    }

}
