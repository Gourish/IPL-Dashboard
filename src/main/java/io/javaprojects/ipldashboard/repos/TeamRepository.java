package io.javaprojects.ipldashboard.repos;

import io.javaprojects.ipldashboard.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<Team,Long> {
    public List<Team> findByTeamName(String teamName);

}
