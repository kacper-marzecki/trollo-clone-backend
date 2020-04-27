package com.kmarzecki.trollo.util

import com.kmarzecki.trollo.model.Language

object InternationalizationUtil {
    private val language = Language.EN
    fun userExists(user: String): String {
        return if (language == Language.PL) "Użytkownik $user już istnieje" else "User $user exists"
    }

    fun cannotFindRequestedUsers(language: Language): String {
        return if (language == Language.PL) "Nie udało się naleźć żądanych użytkowników" else "Cannot find all requested users"
    }

    fun cannotBeYourOwnFriend(language: Language): String {
        return if (language == Language.PL) "Nie możesz być sam sobie przyjacielem :)" else "You cannot be your own friend :)"
    }

    fun alreadyAFriendOrInProgress(language: Language): String {
        return if (language == Language.PL) "Już go znasz / nie odpowiedział na zapytanie" else "Already a friend or in progress of becoming one"
    }

    fun conversationNameIsNotUnique(language: Language): String {
        return if (language == Language.PL) "Nazwa konwersacji nie jest unikalna" else "Conversation name is not unique"
    }

    fun cannotRespondToSomeoneElsesRequest(language: Language): String {
        return if (language == Language.PL) "Nie można odpowiedzieć na czyjeś zapytanie" else "Cannot respond to someone else's request"
    }

    fun cannotFindRequestedRequest(language: Language): String {
        return if (language == Language.PL) "Nie udało się naleźć żądanej operacji" else "Such request does not exist"
    }
}