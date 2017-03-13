package com.soccerleague

import com.soccerleague.domain.Team
import org.scalatest.FunSuite
/**
  * This is the unit test for the LeagueTable object
  */
class LeagueTableUnitTest extends FunSuite {

  test("the processTeam method should return a list of teams ordered by points then by name"){
    val soccerTeam: Seq[Seq[Team]] = Seq(
      Seq(new Team("Lion", SoccerLeagueConstants.WIN), new Team("Juda", SoccerLeagueConstants.DRAW)),
    Seq(new Team("Barca", SoccerLeagueConstants.WIN), new Team("Lion", SoccerLeagueConstants.DRAW)),
    Seq(new Team("Juda", SoccerLeagueConstants.LOSE)))

    val soccerTeam2 = Seq(new Team("Lion", 4), new Team("Barca", 3), new Team("Juda", 1))

    val results = LeagueTable.processSoccerTeams(soccerTeam)

    assertResult(soccerTeam2)(results)
  }

  test("the processTeam method should return a list of teams ordered by points then by name with some teams with the same points"){
    val soccerTeam: Seq[Seq[Team]] = Seq(
      Seq(new Team("Lion", SoccerLeagueConstants.WIN), new Team("Juda", SoccerLeagueConstants.DRAW)),
      Seq(new Team("Barca", SoccerLeagueConstants.WIN), new Team("Lion", SoccerLeagueConstants.DRAW)),
      Seq(new Team("Juda", SoccerLeagueConstants.LOSE), new Team("Real", SoccerLeagueConstants.WIN)))

    val soccerTeam2 = Seq(new Team("Lion", 4), new Team("Barca", 3), new Team("Real", 3), new Team("Juda", 1))

    val results = LeagueTable.processSoccerTeams(soccerTeam)

    assertResult(soccerTeam2)(results)
  }

  test("test valid team points"){
    val teamPoints = Array("Barca", "1")
    val result = LeagueTable.teamPointsValid(teamPoints)
    assertResult(true)(result)
  }

  test("test invalid team points") {
    val teamPoints = Array("Barca", "5")
    val result = LeagueTable.teamPointsValid(teamPoints)
    assertResult(false)(result)
  }

  test("test valid team result string"){
    val teamStr = "Lion 3, Barca 1"
    val result = LeagueTable.validateTeamResultStr(teamStr)
    assertResult(true)(result)
  }

  test("test invalid team result string"){
    val teamStr = "Lion 3, Barca 6"
    val result = LeagueTable.validateTeamResultStr(teamStr)
    assertResult(false)(result)
  }


}


