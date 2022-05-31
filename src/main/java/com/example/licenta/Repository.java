package com.example.licenta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@org.springframework.stereotype.Repository
public class Repository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertTeam(TeamsEntity team) {
        entityManager.persist(team);
    }

    @Transactional
    public void updateTeam(TeamsEntity team) {
        entityManager.createQuery("UPDATE TeamsEntity t SET t.points = :points WHERE t.name = :name")
                .setParameter("points", team.getPoints())
                .setParameter("name", team.getName())
                .executeUpdate();
    }

    @Transactional
    public void deletePlayer(String position, Long age) {
        entityManager.createQuery("DELETE FROM PlayersEntity p WHERE p.position = :position AND p.age > :age")
                .setParameter("position", position)
                .setParameter("age", age)
                .executeUpdate();
    }

    public List getPlayerAndTeamByPosition(String position) {
        return entityManager.createQuery("SELECT p, t.country, t.city, t.points FROM PlayersEntity p INNER JOIN TeamsEntity t ON p.team.name = t.name " +
                        "WHERE p.position = :position AND p.team.name LIKE '%United'")
                .setParameter("position", position)
                .getResultList();
    }

    public List<PlayersEntity> getAllPlayers() {
        return entityManager.createQuery("SELECT p FROM PlayersEntity p ORDER BY p.id ASC", PlayersEntity.class)
                .getResultList();
    }

    public List<TeamsEntity> getTeamsByPoints(Long points) {
        return entityManager.createQuery("SELECT t FROM TeamsEntity t WHERE t.points > :points", TeamsEntity.class)
                .setParameter("points", points)
                .getResultList();
    }


}
