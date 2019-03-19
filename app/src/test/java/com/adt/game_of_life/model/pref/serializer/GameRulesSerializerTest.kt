package com.adt.game_of_life.model.pref.serializer

import org.junit.Test
import kotlin.test.*
import org.junit.Assert.*

class GameRulesSerializerTest {
    private val serializer = GameRulesSerializer()

    @Test
    fun serialize() {
        val toSerialize = listOf(1,2,3)
        val expected = "1;2;3;"
        val result = serializer.serialize(toSerialize)
        assertEquals(expected, result)
    }

    @Test
    fun deserialize() {
        val toDeserialize = "4;5;6;"
        val expected = listOf(4,5,6)
        val result = serializer.deserialize(toDeserialize)
        assertEquals(expected, result)
    }

    @Test
    fun deserializeNotNumbers(){
        val toDeserialize = "Just some test string."
        assertFails {
            serializer.deserialize(toDeserialize)
        }
    }

    @Test
    fun deserializeIncorrectString(){
        val toDeserialize = "2 2 3 "
        assertFails {
            serializer.deserialize(toDeserialize)
        }
    }

    @Test
    fun serializeBiggerThanDigit(){
        val toSerialize = listOf(12,32,45)
        assertFails {
            serializer.serialize(toSerialize)
        }
    }

    @Test
    fun deserializeBiggerThanDigit(){
        val toDeserialize = "123;2312;42"
        assertFails {
            serializer.deserialize(toDeserialize)
        }
    }
}