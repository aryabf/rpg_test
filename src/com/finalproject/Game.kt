package com.finalproject

open class Game : GameInt {

    private var mainChara = MainChara("Arya")
    private var silverkey = Inventory("Silver Key", 0, 0, 250, "puzzle", 0, 0, 0)
    private var steak = Inventory("Steak", 0, 15, 10, "food", 15, 0, 0)
    private var healthpotion = Inventory("Health Potion", 0, 50, 30, "drink", mainChara.maxHP, 0, 0)
    private var oldhalberd = Inventory("Old Halberd", 0, 65, 50, "weapon", 0, 5, 0)
    private var brokenarmor = Inventory("Broken Armor", 0, 65, 50, "armor", 0, 0, 2)
    private var invcount = 0
    private var battle: Boolean = false
    private var name: Boolean = false
    private var damage: Int = 0
    private var inventory: MutableList<Inventory> = mutableListOf()
    private var strayDog = EnemyChara("Stray Dog", 25, 25, 7, 1, 0)
    private var enemy1 = EnemyChara(null, null, null, null, null, null)
    private var sparemeter: Int = 0
    private var text = mutableMapOf(
        1 to "[There you are.]",
        2 to "[I've been waiting for you...]",
        3 to "[...for as long as I can remember.]",
        4 to "[The world is on it's edge.]",
        5 to "[Mother earth will soon blow her final breath.]",
        6 to "[We don't have much time.]",
        7 to "[Please enter the vessel's name.]",
        8 to "Your Vessel's Name is "
    )

    override var playing: Boolean = true
    override var choice: Int = 0

    // Main Menu
    override fun mainMenu() {

        // Main Menu Interface (Haven't Played)
        if (!name){
            println("""
            +-----------------------+
            |  WELCOME TO RPGTEST!  |
            +-----------------------+
            Input numbers to make choices!
            [1] Start Game
            [2] How to Play
            [3] Quit Game
        """.trimIndent())
        }

        // Main Menu Interface (Have Played)
        else {
            println("""
            +-----------------------+
            |  WELCOME TO RPGTEST!  |
            +-----------------------+
            Input numbers to make choices!
            [1] Continue
            [2] How to Play
            [3] Quit Game
        """.trimIndent())
        }

        // Input Main Menu Choices
        do {
            println("Your Choice : ")
            choice = readLine()!!.toInt()
        } while (choice<1||choice>3)

        // Choice Results
        when (choice){
            // [1] New Game / Continue
            1 -> {
                // Haven't  Played
                if (!name){
                    println("""
                        .
                        .
                        .
                    """.trimIndent())
                    cutscene(1,8)
                    // Insert Name
                    mainChara.name = readLine()
                    text.putAll(
                        mutableMapOf(
                            9 to "[Thank you, please make sure ${mainChara.name} is okay.]",
                            10 to "[I will see you when I see you soon.]\n.\n.\n.",
                            11 to "[${mainChara.name} woke up in a dark cave.]",
                            12 to "[Inside there's an old shop, a stray dog, and a door covered with vines.]",
                            13 to "[Light emerges from the Silver Key as ${mainChara.name} gets closer to the door.]",
                            14 to "[The Key shines brighter yet brighter until their vision became white.]",
                            15 to "[As they got back their vision, ${mainChara.name} saw the vines covering the door was removed.]",
                            16 to "[${mainChara.name} tried to open the door.]",
                            17 to "[Creaking sound is heard from the door's hinge.]",
                            18 to "[The door opened, a ray of light shines from the other side of the door.]",
                            19 to "[${mainChara.name} saw someone they knew waving her hand at them with a smile on her face.]",
                            20 to "[There are thorned vines covering the door.]",
                            21 to "[${mainChara.name} looks intimidated by those vines.]",
                            22 to "[Maybe there's something nearby to remove them?]",
                            23 to "[A certain rock attracts ${mainChara.name}'s attention]",
                            24 to "[${mainChara.name} picks the rock up...]",
                            25 to "[...and found a shining silver key underneath.]",
                            26 to "[But nothing lies beneath it.]",
                            27 to "[${mainChara.name} tried to poke the dog...]",
                            28 to "[...but it doesn't seem to be alive.]",
                            29 to "[A stray dog glares at ${mainChara.name}, looks like it wants a headpat.]",
                            30 to "[A stray dog glares at ${mainChara.name}, looks like it wants a hug.]",
                            31 to "[A stray dog glares at ${mainChara.name}, looks like it wants to play.]",
                            32 to "[A stray dog glares at ${mainChara.name}, it seems to love ${mainChara.name}.]",
                            33 to "[${mainChara.name} saw someone they knew crossing her arms with a frown on her face.]"
                        )
                    )

                    name = true
                    cutscene(9, 12)
                }
                // To Main Room
                mainRoom()
            }
            // [2] How to Play
            2 -> howToPlay()
            // [3] Exit
            3 -> exit()
        }
    }

    // How to Play Page
    override fun howToPlay() {

        // How to Play Interface
        println("""
            +---------------+
            |  HOW TO PLAY  |
            +---------------+
            1. Welcome to RPGTEST by Arya Bagus Farrazan (2110511093)!
            2. This RPG console game was made for a study club final project.
            3. This game was heavily inspired from DELTARUNE.
            4. When given numbers inside square brackets ([]), input numbers available to make choices!
            5. Remember to only put the numbers available.
            6. I would really appreciate if you keep everyone in this world alive.
            7. This game does not support saving yet.
            8. I hope you enjoy the short adventure!
        """.trimIndent())

        // Back to Main Menu
        mainMenu()
    }

    // Exit
    override fun exit() {

        // Exit Confirmation Interface
        println("""
            Are you sure you want to quit?
            [0] No
            [1] Yes
        """.trimIndent())

        // Input Exit Confirmation
        do {
            println("Your Choice : ")
            choice = readLine()!!.toInt()
        } while (choice<0||choice>1)

        // Choice Results
        when (choice){

            // Quit Game
            1 -> {
                println("""
                    +----------------------------+
                    |   THANK YOU FOR PLAYING!   |
                    |  SEE YOU IN ANOTHER TIME!  |
                    +----------------------------+
                """.trimIndent())
                playing = false
            }

            // Back to Main Menu (Cancel Quitting)
            0 -> mainMenu()
        }
    }

    // Cutscene (1 Text)
    override fun cutscene(id: Int) {
        println(text[id])
    }

    // Cutscene (Multiple Texts)
    override fun cutscene(id: Int, id2: Int){
        for (i in id..id2){
            println(text[i])
        }
    }

    // Main Room
    override fun mainRoom(){

        // Main room interface
        println("""
            +-------------+
            |  DARK CAVE  |
            +-------------+
            What will ${mainChara.name} do?
            [0] Menu
            [1] Interact with door
            [2] Interact with shop
            [3] Interact with stray dog
            [4] Interact with a certain rock
        """.trimIndent())

        // Main room choice input
        do {
            println("Your Choice : ")
            choice = readLine()!!.toInt()
        } while (choice<0||choice>4)

        // Choices
        when (choice){

            // [0] Menu
            0 -> menu()

            // [1] Door
            1 -> {

                // If Unlocked
                if (silverkey.amount==1){
                    if (strayDog.status!=666) cutscene(13, 19)
                    else {
                        cutscene(13, 18)
                        cutscene(33)
                    }
                    println("""
                    +----------------------------+
                    |   THANK YOU FOR PLAYING!   |
                    |  SEE YOU IN ANOTHER TIME!  |
                    +----------------------------+
                    """.trimIndent())
                    playing = false
                }

                // If Locked
                else {
                    cutscene(20, 22)
                    mainRoom()
                }

            }

            // [2] Shop
            2 -> shop()

            // [3] Stray Dog
            3 -> {
                // If you killed the dog
                if (strayDog.status==666){
                    cutscene(27, 28)
                    mainRoom()
                }
                // If dog is alive
                else {
                    when (strayDog.status){
                        0 -> cutscene(29) // Not Patted
                        1 -> cutscene(30) // Patted
                        2 -> cutscene(31) // Hugged
                        3 -> cutscene(32) // Played with
                    }
                    // Dog Interface
                    println("""
                        [0] Leave It Alone
                        [1] Disturb It
                    """.trimIndent())
                    // Dog choice input
                    do {
                        println("Your Choice : ")
                        choice = readLine()!!.toInt()
                    } while (choice<0||choice>1)
                    // [1] Disturb -> to Battle
                    if (choice==1) {
                        battle = true
                        enemy1 = strayDog
                        enemy1.hp = 25
                        sparemeter = 0
                        while (battle){
                            battle()
                        }
                    }
                    // [0] Leave it Alone
                    else mainRoom()
                }
            }

            // [4] Certain Rock
            4 -> {
                // Has Key
                if (silverkey.amount==1){
                    cutscene(23)
                    cutscene(26)
                }
                // No Key
                else {
                    cutscene(23, 25)
                    println("- ${mainChara.name} Got A Silver Key! -")
                    silverkey.amount = 1
                    inventory.add(silverkey)
                }
                // Back to Main Room
                mainRoom()
            }
        }
    }

    // Menu
    override fun menu() {

        // Menu Interface
        println("""
            +--------+
            |  MENU  |
            +--------+
            [0] Back
            [1] Stats
            [2] Inventory
            [3] Main Menu
        """.trimIndent())

        // Menu chocies input
        do {
            println("Your Choice : ")
            choice = readLine()!!.toInt()
        } while (choice<0||choice>3)

        // Choices
        when (choice){

            // [0] Back
            0 -> mainRoom()

            // [1] Stats
            1 -> {
                println("""
                    +---------+
                    |  STATS  |
                    +---------+
                    - ${mainChara.name} -
                    Level   : ${mainChara.level}
                    Exp     : ${mainChara.exp}/${mainChara.exp + mainChara.nextExp}
                    HP      : ${mainChara.hp}/${mainChara.maxHP}
                    Money   : $${mainChara.money}
                    Attack  : ${mainChara.att}
                    Defense : ${mainChara.def}
                    Weapon  : ${mainChara.weapon?: "Bare Hands"}
                    Armor   : ${mainChara.armor?: "Pajamas"}
                """.trimIndent())
                menu()
            }

            // [2] Inventory
            2 -> inventory()

            // [3] Main Menu
            3 -> mainMenu()
        }
    }

    // Inventory
    override fun inventory(){

        // How many items in inventory
        var invconfirm: Int
        invcount = 0
        var heal: Int

        // Interface
        println("""
            +-------------+
            |  INVENTORY  |
            +-------------+
            [0] Back
        """.trimIndent())
        // Printing Items
        try {
            invcount = 1
            for (i in inventory){
                invcount++
            }
            println("[$invcount] ${inventory[invcount].name} [${inventory[invcount].amount}]")
        }
        catch (e: IndexOutOfBoundsException){
            invcount = 0
        }
        finally {
            // Inventory choice input
            for (i in inventory){
                invcount++
                println("[$invcount] ${i.name} [${i.amount}]")
            }
        }
        // Inventory choice input
        do {
            println("Your Choice : ")
            choice = readLine()!!.toInt()
        } while (choice<0||choice>invcount)
        // Choices
        when (choice){
            // [0] Back to battle if accessed mid-battle, else to menu
            0 -> if (battle) battle() else menu()
            // [Else] Items
            else -> {
                choice--
                // Based on Item Type
                when (inventory[choice].type){
                    // Can't use a "puzzle" item
                    "puzzle" -> {
                        println("This item will be used automatically when necessary.")
                        inventory()
                    }
                    // Other than puzzle
                    else -> {
                        // Confirmation Interface
                        println("""
                            Use the ${inventory[choice].name}?
                            [0] No
                            [1] Yes
                        """.trimIndent())
                        // Input Confirmation
                        do {
                            println("Your Choice : ")
                            invconfirm = readLine()!!.toInt()
                        } while (invconfirm<0||invconfirm>1)
                        // Chocies
                        when (invconfirm){
                            // [0] No
                            0 -> inventory()
                            // [1] Yes
                            1 -> {
                                // Based on item type
                                when (inventory[choice].type){
                                    // Food -> "ate", heal
                                    "food" -> {
                                        if (mainChara.hp==mainChara.maxHP) println("But ${mainChara.name}'s HP is full!")
                                        else{
                                            heal = if (inventory[choice].heal + mainChara.hp > mainChara.maxHP) mainChara.maxHP - mainChara.hp else inventory[choice].heal
                                            println("""
                                                ${mainChara.name} ate the ${inventory[choice].name}!
                                                ${mainChara.name} gained $heal HP!
                                            """.trimIndent())
                                            mainChara.hp += heal
                                            inventory[choice].amount--
                                            if (inventory[choice].amount==0) inventory.removeAt(choice)
                                        }
                                    }
                                    // Drink -> "drank", heal
                                    "drink" -> {
                                        if (mainChara.hp==mainChara.maxHP) println("But ${mainChara.name}'s HP is full!")
                                        else {
                                            heal = if (inventory[choice].heal + mainChara.hp > mainChara.maxHP) mainChara.maxHP - mainChara.hp else inventory[choice].heal
                                            println("""
                                                ${mainChara.name} drank the ${inventory[choice].name}!
                                                ${mainChara.name} gained $heal HP!
                                            """.trimIndent())
                                            mainChara.hp += heal
                                            inventory[choice].amount--
                                            if (inventory[choice].amount==0) inventory.removeAt(choice)
                                        }
                                    }
                                    // Weapon -> "used", att+
                                    "weapon" -> {
                                        if (mainChara.weapon==inventory[choice].name) println("${mainChara.name} still uses the ${inventory[choice].name}.")
                                        else{
                                            mainChara.att = 10 + inventory[choice].att
                                            mainChara.weapon = inventory[choice].name
                                            println("""
                                            ${mainChara.name} used the ${inventory[choice].name}!
                                            ${mainChara.name} gained ${inventory[choice].att} Attack!
                                        """.trimIndent())
                                        }
                                    }
                                    // Armor -> "wears", def+
                                    "armor" -> {
                                        if (mainChara.armor==inventory[choice].name) println("${mainChara.name} still wears the ${inventory[choice].name}.")
                                        else {
                                            mainChara.def = 1 + inventory[choice].def
                                            mainChara.armor = inventory[choice].name
                                            println("""
                                            ${mainChara.name} wears the ${inventory[choice].name}!
                                            ${mainChara.name} gained ${inventory[choice].def} Defense!
                                        """.trimIndent())
                                        }
                                    }
                                }
                                // Back to battle if accessed mid-battle
                                if (battle) {
                                    enemyattack()
                                    battle()
                                }
                                // Back to Inventory
                                else inventory()
                            }
                        }
                    }
                }
            }
        }
    }

    // Shop
    override fun shop() {

        // Shop interface
        println("""
            +--------+
            |  SHOP  |
            +--------+
            Seam : Hello! I'm old man Seam!
            Seam : Pronounced Shawm!
            Seam : Welcome to my little seap!
            [0] Back
            [1] Buy
            [2] Sell
        """.trimIndent())

        // Shop choice input
        do {
            println("Your Choice : ")
            choice = readLine()!!.toInt()
        } while (choice<0||choice>2)

        // Choices
        when (choice){

            // [0] Back
            0 -> {
                println("Seam : See ya later... or not!")
                mainRoom()
            }

            // [1] Buy
            1 -> buy()

            // [2] Sell
            2 -> sell()
        }
    }

    // Buying
    override fun buy() {

        var buyconfirm: Int
        // Buying Interface
        println("""
            +------------------+
            |  BUY SOMETHING!  |
            +------------------+
            ${mainChara.name}'s money : $${mainChara.money}
            [0] Back
            [1] Steak
            [2] Health Potion
            [3] Old Halberd
            [4] Broken Armor
        """.trimIndent())

        // Buying choices
        do {
            println("Your Choice : ")
            choice = readLine()!!.toInt()
        } while (choice<0||choice>4)

        // Choices
        when (choice){
            // [0] Back to Shop Choices
            0 -> shop()
            // [1] Steak
            1 -> {
                println("""
                    Buy a Steak for $${steak.buy}?
                    [0] No
                    [1] Yes
                """.trimIndent())

                do {
                    println("Your Choice : ")
                    buyconfirm = readLine()!!.toInt()
                } while (buyconfirm<0||buyconfirm>1)

                when (buyconfirm){
                    // [0] No
                    0 -> buy()
                    // [1] Yes
                    1 -> {
                        if (mainChara.money < steak.buy){
                            println("${mainChara.name} doesn't have enough money!")
                            buy()
                        }
                        else {
                            println("""
                            ${mainChara.name} bought a Steak!
                            -$${steak.buy}
                            Seam : Enjoy your meal!
                            """.trimIndent())
                            if (steak.amount==0) inventory.add(steak)
                            steak.amount += 1
                            mainChara.money -= steak.buy
                            buy()
                        }
                    }
                }
            }
            // [2] Health Potion
            2 -> {
                println("""
                    Buy a Health Potion for $${healthpotion.buy}?
                    [0] No
                    [1] Yes
                """.trimIndent())

                do {
                    println("Your Choice : ")
                    buyconfirm = readLine()!!.toInt()
                } while (buyconfirm<0||buyconfirm>1)

                when (buyconfirm){
                    // [0] No
                    0 -> buy()
                    // [1] Yes
                    1 -> {
                        if (mainChara.money < healthpotion.buy){
                            println("${mainChara.name} doesn't have enough money!")
                            buy()
                        }
                        else {
                            println("""
                            ${mainChara.name} bought a Health Potion!
                            -$${healthpotion.buy}
                            Seam : May not taste good, but it's good for your health!
                            """.trimIndent())
                            if (healthpotion.amount==0) inventory.add(healthpotion)
                            healthpotion.amount += 1
                            mainChara.money -= healthpotion.buy
                            buy()
                        }
                    }
                }
            }
            // [3] Old Halberd
            3 -> {
                // If you have one
                if (oldhalberd.amount == 1){
                    println("Seam : But you already have one!")
                    buy()
                }
                // If you don't have one
                else {
                    println("""
                    Buy an Old Halberd for $${oldhalberd.buy}?
                    [0] No
                    [1] Yes
                """.trimIndent())

                    do {
                        println("Your Choice : ")
                        buyconfirm = readLine()!!.toInt()
                    } while (buyconfirm<0||buyconfirm>1)

                    when (buyconfirm){
                        // [0] No
                        0 -> buy()
                        // [1] Yes
                        1 -> {
                            if (mainChara.money < oldhalberd.buy){
                                println("${mainChara.name} doesn't have enough money!")
                                buy()
                            }
                            else {
                                println("""
                            ${mainChara.name} bought an Old Halberd!
                            -$${oldhalberd.buy}
                            Seam : I hope you don't use it to kill anyone!
                            """.trimIndent())
                                inventory.add(oldhalberd)
                                oldhalberd.amount += 1
                                mainChara.money -= oldhalberd.buy
                                buy()
                            }
                        }
                    }
                }
            }
            // [4] Broken Armor
            4 -> {
                // If you already have one
                if (brokenarmor.amount == 1){
                    println("Seam : But you already have one!")
                    buy()
                }
                // If you don't have one
                else {
                    println("""
                    Buy a Broken Armor for $${brokenarmor.buy}?
                    [0] No
                    [1] Yes
                """.trimIndent())

                    do {
                        println("Your Choice : ")
                        buyconfirm = readLine()!!.toInt()
                    } while (buyconfirm<0||buyconfirm>1)

                    when (buyconfirm){
                        // [0] No
                        0 -> buy()
                        // [1] Yes
                        1 -> {
                            if (mainChara.money < brokenarmor.buy){
                                println("${mainChara.name} doesn't have enough money!")
                                buy()
                            }
                            else {
                                println("""
                            ${mainChara.name} bought a Broken Armor!
                            -$${brokenarmor.buy}
                            Seam : You'll look good in that!
                            """.trimIndent())
                                inventory.add(brokenarmor)
                                brokenarmor.amount += 1
                                mainChara.money -= brokenarmor.buy
                                buy()
                            }
                        }
                    }
                }
            }
        }
    }

    // Selling
    override fun sell() {

        invcount = 0
        // Interface
        println("""
            +-------------------+
            |  SELL SOMETHING!  |
            +-------------------+
            [0] Back
        """.trimIndent())

        try {
            invcount = 1
            for (i in inventory){
                invcount++
            }
            println("[$invcount] ${inventory[invcount].name} [${inventory[invcount].amount}]")
        }
        catch (e: IndexOutOfBoundsException){
            invcount = 0
        }
        finally {
            // Inventory choice input
            for (i in inventory){
                invcount++
                //println("[$invcount] ${inventory[invcount-1].name} [${inventory[invcount-1].amount}]")
                println("[$invcount] ${i.name} [${i.amount}]")
            }
        }

        // Inventory choice input
        do {
            println("Your Choice : ")
            choice = readLine()!!.toInt()
        } while (choice<0||choice>invcount)

        when (choice){
            0 -> shop()
            else -> {
                choice--
                when (inventory[choice]){
                    silverkey -> {
                        println("""
                            Seam : That's a beautiful key!
                            Seam : But I don't want it though, since I don't know what it does!
                        """.trimIndent())
                        sell()
                    }
                    else -> {
                        println("""
                            Seam : You bought that from me, right?
                            Seam : I already have tons of those!
                            Seam : So no, thanks!
                        """.trimIndent())
                        sell()
                    }
                }
            }
        }

    }

    // Battle
    override fun battle() {

        // idk
        choice = 7
        damage = 0

        // Battle interface
        println("""
            +----------+
            |  BATTLE  |
            +----------+
            A stray dog came nearby!
            ${mainChara.name}'s HP : ${mainChara.hp}/${mainChara.maxHP}
            [0] Run
            [1] Check
            [2] Attack
            [3] Act
            [4] Item
            [5] Defend
            [6] Spare
        """.trimIndent())

        // Battle choice input
        do {
            println("Your Choice : ")
            choice = readLine()!!.toInt()
        } while (choice<0||choice>6)

        // Choices
        when (choice){

            // [0] Run
            0 -> {
                println("${mainChara.name} ran away from ${enemy1.name}")
                battle = false
                mainRoom() // Back to main room
            }

            // [1] Check
            1 -> {
                println("""
                    +---------+
                    |  CHECK  |
                    +---------+
                    ${enemy1.name}
                    Attack : ${enemy1.att}
                    Defense : ${enemy1.def}
                    HP : ${enemy1.hp}/${enemy1.maxHP}
                    Sparemeter : $sparemeter%
                """.trimIndent())
                when (enemy1.status){
                    0 -> cutscene(29) // Not Patted
                    1 -> cutscene(30) // Patted
                    2 -> cutscene(31) // Hugged
                    3 -> cutscene(32) // Played with
                }
            }

            // [2] Attack
            2 -> {
                damage = mainChara.att - enemy1.def!!
                if (damage<0) damage = 0
                if (enemy1.status!! > 0) {
                    enemy1.status = enemy1.status!! - 1
                    enemy1.att = enemy1.att!! + 1
                }
                if (damage > enemy1.hp!!) damage = enemy1.hp!!
                println("""
                    ${mainChara.name} attacked ${enemy1.name}!
                    ${mainChara.name} dealt $damage damage!
                """.trimIndent())

                // Enemy HP-
                enemy1.hp = enemy1.hp?.minus(damage)
                sparemeter -= 30
                if (sparemeter<0) sparemeter = 0

                // If enemy's HP is below 1
                if (enemy1.hp!! < 1){
                    println("""
                       ${mainChara.name} killed ${enemy1.name}.
                       ${mainChara.name} got 10 EXP and found $50!
                    """.trimIndent())
                    enemy1.status = 666
                    battle = false
                    mainChara.exp += 10
                    mainChara.nextExp -= 10
                    mainChara.money += 50
                    mainRoom() // Back to mainroom
                }
                else enemyattack()
            }

//            // [3] Act
            3 -> act()

            // [4] Item
            4 -> inventory()

            // [5] Defend
            5 -> {
                println("""
                    ${mainChara.name} defended!
                """.trimIndent())

                mainChara.def += 2

                enemyattack()
                mainChara.def -= 2
            }

            // [6] Spare
            6 -> {

                // If sparemeter >= 100
                if (sparemeter >= 100){
                    println("""
                        ${mainChara.name} spared ${enemy1.name}!
                        ${mainChara.name} got $30!
                    """.trimIndent())
                    mainChara.money += 30
                    battle=false
                    mainRoom()
                }

                // If sparemeter < 100
                else {
                    println("""
                        You cannot spare ${enemy1.name} YET!
                    """.trimIndent())
                }
            }
        }
    }

    // Act
    override fun act() {
        // Act Interface
        println("""
            +-------+
            |  ACT  |
            +-------+
            [0] Back
            [1] Head Pat
            [2] Hug
            [3] Play
        """.trimIndent())

        // Act Choices Input
        do {
            println("Your Choice : ")
            choice = readLine()!!.toInt()
        } while (choice<0||choice>4)

        // Choices
        when (choice){

            // [0] Back
            0 -> battle()

            // [1] Pat
            1 -> {

                // If haven't pat before
                if (enemy1.status==0) {
                    strayDog.att = strayDog.att?.minus(1)
                    strayDog.status = strayDog.status?.plus(1)
                }

                println("${mainChara.name} pats ${enemy1.name} in the head!")
                sparemeter += 20
                if (sparemeter>100) sparemeter = 100

                enemyattack()
            }

            // [2] Hug
            2 -> {

                when (strayDog.status) {
                    // If haven't hug before
                    1 -> {
                        strayDog.att = strayDog.att?.minus(1)
                        strayDog.status = strayDog.status?.plus(1)
                        println("${mainChara.name} hugs ${enemy1.name} tightly!")
                        sparemeter += 40
                        if (sparemeter>100) sparemeter = 100
                        enemyattack()
                    }
                    // If haven't pat
                    0 -> {
                        damage = strayDog.att!! - mainChara.def
                        println("${enemy1.name} strikes ${mainChara.name} as they got closer!")
                        println("${enemy1.name} dealt $damage damage to ${mainChara.name}!")
                        mainChara.hp -= damage
                        if (damage > mainChara.hp) damage = mainChara.hp
                        // If player's HP is below 1, battle's over
                        if (mainChara.hp<1) {
                            println("${mainChara.name} got killed by ${enemy1.name}!")
                            println("""
                                +-------------+
                                |  GAME OVER  |
                                +-------------+
                            """.trimIndent())
                            battle = false
                            playing = false
                        }
                        else battle()
                    }
                    else -> {
                        println("${mainChara.name} hugs ${enemy1.name} tightly!")
                        sparemeter += 40
                        if (sparemeter>100) sparemeter = 100
                        enemyattack()
                    }
                }
            }

            // [3] Play
            3 -> {

                when (strayDog.status) {
                    // If haven't play
                    2 -> {
                        strayDog.att = strayDog.att?.minus(1)
                        strayDog.status = strayDog.status?.plus(1)
                        println("${mainChara.name} plays stick fetching with ${enemy1.name}!")
                        sparemeter += 40
                        if (sparemeter>100) sparemeter = 100
                        enemyattack()
                    }

                    // If haven't pat or hug
                    0, 1 -> {
                        damage = strayDog.att!! - mainChara.def
                        println("${enemy1.name} strikes ${mainChara.name} as they got closer!")
                        println("${enemy1.name} dealt $damage damage to ${mainChara.name}!")
                        mainChara.hp -= damage
                        if (damage > mainChara.hp) damage = mainChara.hp
                        // If player's HP is below 1, battle's over
                        if (mainChara.hp<1) {
                            println("${mainChara.name} got killed by ${enemy1.name}!")
                            println("""
                                +-------------+
                                |  GAME OVER  |
                                +-------------+
                            """.trimIndent())
                            battle = false
                            playing = false
                        }
                        else battle()
                    }

                    else -> {
                        println("${mainChara.name} plays stick fetching with ${enemy1.name}!")
                        sparemeter += 40
                        if (sparemeter>100) sparemeter = 100
                        enemyattack()
                    }
                }

            }
        }

    }

    // Enemy Attacking
    override fun enemyattack(){

        damage = enemy1.att!! - mainChara.def
        if (damage<0) damage = 0
        println("${enemy1.name} attacks!")
        println("${enemy1.name} dealt $damage damage!")
        mainChara.hp = mainChara.hp - damage

        // If player's HP is below 1, battle's over
        if (mainChara.hp<1) {
            println("${mainChara.name} got killed by ${enemy1.name}!")
            println("""
                +-------------+
                |  GAME OVER  |
                +-------------+
            """.trimIndent())
            battle = false
            playing = false
        }

    }

}

