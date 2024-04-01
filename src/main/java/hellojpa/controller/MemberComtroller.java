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

            em.flush();
            em.clear();

//            Member findMember = em.find(Member.class, member.getId());
//            Team findTeam = findMember.getTeam();
//
//            findTeam.getName();
//
//            List<Member> members = findTeam.getMembers();
//            for (Member member1 : members) {
//                System.out.println("member1 = " + member1);
//            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
