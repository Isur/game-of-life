package com.adt.game_of_life.model.pref

import android.app.Application
import android.content.Context
import com.adt.game_of_life.model.pref.serializer.IGameRulesSerializer
import com.adt.game_of_life.util.edit

class SharedPrefAccess(
    private val context: Application,
    private val serializer: IGameRulesSerializer
) : IGameRulesPref {

    private fun getPrefs() = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

    override fun setNeighboursToDie(neighboursToDie: List<Int>) {
        val serialized = serializer.serialize(neighboursToDie)
        getPrefs().edit { it.putString(NEIGHBOURS_TO_DIE, serialized) }
    }

    override fun getNeighboursToDie(): MutableList<Int> {
        val serialized = getPrefs().getString(NEIGHBOURS_TO_DIE, DEFAULT_NEIGHBOURS_TO_DIE)!!
        return serializer.deserialize(serialized)
    }

    override fun setNeighboursToBorn(neighboursToBorn: List<Int>) {
        val serialized = serializer.serialize(neighboursToBorn)
        getPrefs().edit { it.putString(NEIGHBOURS_TO_BORN, serialized) }
    }

    override fun getNeighboursToBorn(): MutableList<Int> {
        val serialized = getPrefs().getString(NEIGHBOURS_TO_BORN, DEFAULT_NEIGHBOURS_TO_BORN)!!
        return serializer.deserialize(serialized)
    }

    private companion object {
        const val SHARED_PREFS = "GAME_OF_LIFE_SHARED_PREFERENCES"
        const val NEIGHBOURS_TO_DIE = "NEIGHBOURS_TO_DIE"
        const val NEIGHBOURS_TO_BORN = "NEIGHBOURS_TO_BORN"

        const val DEFAULT_NEIGHBOURS_TO_DIE = "0;1;4;5;6;7;8;"
        const val DEFAULT_NEIGHBOURS_TO_BORN = "3;"
    }
}