package com.finalproject

interface GameInt {

    var playing: Boolean
    var choice: Int

    fun mainMenu()
    fun howToPlay()
    fun exit()
    fun cutscene(id: Int)
    fun cutscene(id: Int, id2: Int)
    fun mainRoom()
    fun menu()
    fun inventory()
    fun shop()
    fun buy()
    fun sell()
    fun battle()
    fun act()
    fun enemyattack()

}