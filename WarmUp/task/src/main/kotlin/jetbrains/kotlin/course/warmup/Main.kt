package jetbrains.kotlin.course.warmup

// You will use this function later
fun getGameRules(wordLength: Int, maxAttemptsCount: Int, secretExample: String) =
    "Welcome to the game! $newLineSymbol" +
            newLineSymbol +
            "Two people play this game: one chooses a word (a sequence of letters), " +
            "the other guesses it. In this version, the computer chooses the word: " +
            "a sequence of $wordLength letters (for example, $secretExample). " +
            "The user has several attempts to guess it (the max number is $maxAttemptsCount). " +
            "For each attempt, the number of complete matches (letter and position) " +
            "and partial matches (letter only) is reported. $newLineSymbol" +
            newLineSymbol +
            "For example, with $secretExample as the hidden word, the BCDF guess will " +
            "give 1 full match (C) and 1 partial match (B)."

fun main() {
    // Write your solution here
    val wordLength: Int = 4
    val maxAttemptsCount: Int = 3
    val secretExample: String = "ACEB"
    println(getGameRules(wordLength, maxAttemptsCount, secretExample))

    playGame(generateSecret(), wordLength, maxAttemptsCount)
    countExactMatches(generateSecret(), "AFCD")
}

fun myName(intVariable: Int = 10, strVariable: String): Int = TODO("Not yet implemented")

fun generateSecret(): String = "ABCD"

fun countPartialMatches(secret: String, guess: String): Int = countAllMatches(secret, guess) - countExactMatches(secret, guess)

fun countExactMatches(secret: String, guess: String): Int = secret.filterIndexed { index, symbol -> guess[index] == symbol}.length

fun countAllMatches(secret: String, guess: String): Int = minOf(secret.filter { it in guess }.length, guess.filter { it in secret }.length)

fun isComplete(secret: String, guess: String): Boolean = guess.equals(secret)

fun printRoundResults(secret: String, guess: String) {
    println("Your guess has ${countExactMatches(secret, guess)} full matches and ${countPartialMatches(secret, guess)} partial matches.")
}

fun isWon(complete: Boolean, attempts: Int, maxAttemptsCount: Int): Boolean {
    if (attempts > maxAttemptsCount) { return false }
    if (complete) { return true }
    return false
}

fun isLost(complete: Boolean, attempts: Int, maxAttemptsCount: Int): Boolean = attempts > maxAttemptsCount && !complete

fun playGame(secret: String, wordLength: Int, maxAttemptsCount: Int) {
    var attempts = 0
    var guess = ""

    while (attempts <= maxAttemptsCount) {
        println("Please input your guess. It should be of length $wordLength.")
        guess = safeReadLine()
        val isComplete = isComplete(secret, guess)

        printRoundResults(secret, guess)

        if (isComplete) {
            println("Congratulations! You guessed it!")
            break
        }

        if (attempts == maxAttemptsCount) {
            println("Sorry, you lost! :( My word is $secret")
            break
        }

        attempts++
    }
}