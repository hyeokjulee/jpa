package hellojpa.controller;

import hellojpa.entity.Member;
import hellojpa.entity.Team;
import jakarta.persistence.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberComtroller {
    @PersistenceUnit
    EntityManagerFactory emf;

    @GetMapping("/create")
    public void createMember() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setName("hello");

            team.getMembers().add(member);
            member.setTeam(team);

            em.persist(member);

            //검색
            String jpql = "select m from Member m join fetch m.team where m.name like '%hello%'";
            List<Member> resultList = em.createQuery(jpql, Member.class)
                    .setFirstResult(10)
                    .setMaxResults(20)
                    .getResultList();

            //조회
            Member findMember = em.find(Member.class, member.getId());

            //수정
            findMember.setName("t아카데미");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
