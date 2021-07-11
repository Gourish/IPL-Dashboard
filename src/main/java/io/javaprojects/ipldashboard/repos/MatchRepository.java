package io.javaprojects.ipldashboard.repos;

import io.javaprojects.ipldashboard.model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {
    public List<Match> getByTeam1OrTeam2OrderByDate(String team1, String team2, Pageable pageable);
    @Query("select m from Match m where (m.team1=:teamName or m.team2=:teamName) and m.date between :dateStart and :dateEnd Order by m.date desc")
    public List<Match> getMatchesByTeamBetweenDates(@Param("teamName") String teamName,
                                                    @Param("dateStart") LocalDate dateStart,
                                                    @Param("dateEnd") LocalDate dateEnd);
    default  List<Match> getLatestMatchesByTeam(String teamName,int count)
    {
        return getByTeam1OrTeam2OrderByDate(teamName,teamName, PageRequest.of(0,count));
    }
}
