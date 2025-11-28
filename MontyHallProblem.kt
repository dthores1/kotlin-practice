fun main() {
    val trials = 100_000
    val (switchWins, stayWins) = runSimulation(trials)

    println("================ RESULTS ================")
    println("Number of trials: $trials")
    println("Wins by switching doors: $switchWins")
    println("Wins by staying: $stayWins")
}

fun montyHallProblemTrial(shouldSwitchDoor: Boolean): Boolean {    
    val doors = arrayOf(0, 1, 2)
    val doorWithCar = doors.random()
    var doorPlayerSelected = doors.random()

    // Host opens a goat door that is not the player's choice and not the car
    val doorHostOpens = doors
        .filter { door -> door != doorPlayerSelected && door != doorWithCar }
        .random()
        
    // If switching, choose the remaining closed door
    if (shouldSwitchDoor) {
        doorPlayerSelected = doors.first { it != doorPlayerSelected && it != doorHostOpens }
    }
    
	return doorPlayerSelected == doorWithCar
}

fun runSimulation(trials: Int): Pair<Int, Int> {
    var switchWins = 0
    var stayWins = 0

    repeat(trials) {
        if (montyHallProblemTrial(true)) {
            switchWins++
        }
        
        if (montyHallProblemTrial(false)) {
            stayWins++
        }
    }

    return switchWins to stayWins
}

/*
================ RESULTS - RUN 1 ================
Number of trials: 100000
Wins by switching doors: 66826
Wins by NOT switching doors: 33689


================ RESULTS - RUN 2 ================
Number of trials: 100000
Wins by switching doors: 66705
Wins by NOT switching doors: 33249


================ RESULTS - RUN 3 ================
Number of trials: 100000
Wins by switching doors: 66354
Wins by NOT switching doors: 33346


================ RESULTS - RUN 4 ================
Number of trials: 100000
Wins by switching doors: 66606
Wins by staying: 33322
*/
