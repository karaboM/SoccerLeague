package com.soccerleague

import scala.collection.mutable.ArrayBuffer
import scala.io.{Source, StdIn}

/**
  * This class contains the core of the soccer application logic required to run the soccer league app
  */
class SoccerApp {
  val menu = "Please choose from the following menu items:\n\t1.Enter match results\n\t2.View match results\n\t" +
    "3.Exit\n\nPlease choose an option:"

  val incorrectMenuOption = "*********Please select the correct menu option***************"
  val inputResultMessage = "Please enter team results( first enter the team name followed by space then either 3 for win, " +
    "1 for draw and 0 for lose. You can enter more than one team's results by separating them by a ','):"

  val viewResultMessage = "*********Soccer League standings*********"
  val exitMessage = "Exiting Soccer League application"
  val processFileMessage = "File is being processed"

  private def printMenu() = {
    println(menu)
  }


  def inputResults(): Unit = {
    println(inputResultMessage)
    var input = StdIn.readLine()
    var teamResultStrArr = ArrayBuffer[String]()
    while (input.nonEmpty || input == SoccerLeagueConstants.StopOperationKey) {
      teamResultStrArr += input

      input = StdIn.readLine()
    }
    LeagueTable.processTeamResults(teamResultStrArr)
    run()
  }

  def viewResults(): Unit = {
    println(viewResultMessage)
    println(LeagueTable.printLeagueResults())
    run()
  }


  def executeMenuOption(menuOption: Int): Unit = {
    menuOption match {
      case SoccerLeagueConstants.MatchResults =>
        inputResults()
      case SoccerLeagueConstants.ViewMatchResults =>
        viewResults()
      case SoccerLeagueConstants.Exit =>
        println(exitMessage)
      case _ =>
        println(incorrectMenuOption)
        StdIn.readLine()
        run()
    }
  }

  def processFile(inputFileName: String): Unit = {
    println(processFileMessage)
    var teamResultStrArr = ArrayBuffer[String]()
    for(line <- Source.fromFile(inputFileName).getLines()){
      teamResultStrArr += line
    }
    LeagueTable.processTeamResults(teamResultStrArr)
  }

  def run(inputFileName: String = ""): Unit = {
    if(inputFileName != SoccerLeagueConstants.EmptyString){
      processFile(inputFileName)
    }
    printMenu()
    val menuOption = StdIn.readLine()
    executeMenuOption(menuOption.toInt)
  }

}
