package com.finalproject

class EnemyChara(
    override var name: String?,
    var hp: Int?, var maxHP: Int?,
    var att: Int?, var def: Int?,
    var status: Int?
): Chara(name)
