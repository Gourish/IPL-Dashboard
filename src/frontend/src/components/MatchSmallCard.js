import { React } from 'react';
import {Link} from 'react-router-dom';
import './MatchSmallCard.scss'

export  const  MatchSmallCard  = ({teamName,match}) => {
  const otherTeam = match.team1===teamName?match.team2:match.team1;
  const otherTeamRoute = `/teams/${otherTeam}`;
  const isMatchWin = teamName === match.matchWinner;
  return (
    <div className={isMatchWin ? 'MatchSmallCard won-match' : 'MatchSmallCard lost-match'}>
      <h5><Link to={otherTeamRoute}>vs {otherTeam}</Link></h5>
      <h3>{match.matchWinner} won by {match.resultMargin} {match.result}</h3>
    </div>
  );
};