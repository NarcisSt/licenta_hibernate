package com.example.licenta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    private final Repository repository;

    @Autowired
    public Service(Repository repository) {
        this.repository = repository;
    }

    public void insertTeam(TeamsData team) {
        TeamsEntity teamsEntity = new TeamsEntity();

        teamsEntity.setCity(team.getCity());
        teamsEntity.setCountry(team.getCountry());
        teamsEntity.setName(team.getName());
        teamsEntity.setPoints(team.getPoints());

        repository.insertTeam(teamsEntity);
    }

    public void updateTeam(TeamsData team) {
        TeamsEntity teamsEntity = new TeamsEntity();

        teamsEntity.setCity(team.getCity());
        teamsEntity.setCountry(team.getCountry());
        teamsEntity.setName(team.getName());
        teamsEntity.setPoints(team.getPoints());

        repository.updateTeam(teamsEntity);
    }

    public void deletePlayer(String position, Long age) {

        repository.deletePlayer(position, age);
    }

    public List getPlayerAndTeamByPosition(String position) {
        return repository.getPlayerAndTeamByPosition(position);
    }

    public List<PlayersEntity> getAllPlayers(){
        return repository.getAllPlayers();
    }

    public List<TeamsEntity> getTeamsByPoints(Long points) {
        return repository.getTeamsByPoints(points);
    }
}
