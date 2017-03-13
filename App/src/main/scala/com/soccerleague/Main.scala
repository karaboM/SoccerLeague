package com.soccerleague

import com.soccerleague.domain.Team

/**
  * This is the Main class which is the main entry point into this application.
  */
object Main {

  val welcomeMessage = "********************Welcome to the Soccer league application********************"

  def main(args: Array[String]): Unit = {
    val soccerApp = new SoccerApp
    println(welcomeMessage)
    if(args.size > 0)
      soccerApp.run(args(0))
    else
      soccerApp.run()
  }
}
