package com.soccerleague

import com.soccerleague.domain.Team

import scala.collection.mutable.ArrayBuffer

/**
  * This class stores and calculates the league table dynamically, and provides a method
  */
object LeagueTable {

  val soccerTeams: ArrayBuffer[Seq[Team]] = new ArrayBuffer[Seq[Team]]()


  def processTeamResults(teamResultStrToProcess: IndexedSeq[String]): Unit= {
    teamResultStrToProcess.foreach(f => {
      if(validateTeamResultStr(f)){
        val extractedTeams = f.split(SoccerLeagueConstants.TeamSeparator)
          .map(f => {
            val teamArr = f.trim.split(SoccerLeagueConstants.EmptySpace)
            new Team(teamArr(0), teamArr(1).toInt)
          }).toSeq
        soccerTeams += extractedTeams
      }
    })

  }

  def teamPointsValid(rawTeamArr: Array[String]): Boolean = {
      rawTeamArr(1).toInt match {
        case SoccerLeagueConstants.WIN | SoccerLeagueConstants.DRAW  | SoccerLeagueConstants.LOSE =>
          true
        case _ =>
          false
      }

  }

  def validateTeamResultStr(teamResultStr: String): Boolean = {
    teamResultStr.split(SoccerLeagueConstants.TeamSeparator)
      .map(f => {
        val rawTeamArr = f.trim.split(SoccerLeagueConstants.EmptySpace)
        rawTeamArr.length == 2 && teamPointsValid(rawTeamArr)
      }).forall(_ == true)
  }

  def determinePointTxt(result: Int): String = {
    result match {
      case 1 =>
        SoccerLeagueConstants.PtStr
      case _ =>
        SoccerLeagueConstants.PtsStr
    }
  }

  def printLeagueResults(): String = {
    processSoccerTeams(soccerTeams)
      .zipWithIndex
      .map{
        case(element, index)=> s"${index + 1}. ${element.name}, ${element.result} " + determinePointTxt(element.result) + "\n"
      }
      .mkString
  }

  def processSoccerTeams(soccerTeams: Seq[Seq[Team]]): Seq[Team] = {
    soccerTeams
      .flatten
      .groupBy(_.name)
      .map{case(name: String, teams: Seq[Team]) => new Team(name, teams.map(_.result).sum)}
      .toSeq
      .sortWith((leftE, rightE) => {
        if(leftE.result == rightE.result)
          leftE.name < rightE.name
        else
          leftE.result > rightE.result
      })

  }
}
