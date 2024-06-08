package com.finalproject

class MainChara(
    override var name: String?, var level: Int = 1,
    var exp: Int = 0, var nextExp: Int = 100,
    var hp: Int = 50, var maxHP: Int = 50,
    var att: Int = 10, var def: Int = 1,
    var money: Int = 50,
    var weapon: String? = null, var armor: String? = null): Chara(name)
