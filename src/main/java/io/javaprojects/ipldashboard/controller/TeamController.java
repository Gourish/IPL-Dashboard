package io.javaprojects.ipldashboard.controller;

import io.javaprojects.ipldashboard.model.Match;
import io.javaprojects.ipldashboard.model.Team;
import io.javaprojects.ipldashboard.repos.MatchRepository;
import io.javaprojects.ipldashboard.repos.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MatchRepository matchRepository;

    @GetMapping("/team")
    public Iterable<Team> getTeams()
    {
        return teamRepository.findAll();
    }

    @GetMapping("/team/{teamName}")
    public Team getTeamName(@PathVariable("teamName") String teamName)
    {
        Team team = teamRepository.findByTeamName(teamName).get(0);
        team.setMatches(matchRepository.getLatestMatchesByTeam(team.getTeamName(),4));
        return team;

    }
    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable("teamName") String teamName, @RequestParam("year") int year)
    {
        LocalDate dateStart =  LocalDate.of(year,1,1);
        LocalDate dateEnd = LocalDate.of(year+1,1,1);
       return matchRepository.getMatchesByTeamBetweenDates(teamName,dateStart,dateEnd);
    }


}
