package at.sigmoid.util

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class PluralFunctionsTest {
    @Test
    fun `singular should call plural method with single argument and extract result`() {
        val f = this::pluralSquare.singular
        assertEquals(9, f(3))
    }

    @Test
    fun `singular without default value and missing entry should fail`() {
        val f = this::pluralSquareIfPositive.singular

        assertEquals(9, f(3))
        assertFailsWith(NoSuchElementException::class) {
            f(-1)
        }
    }

    @Test
    fun `singular with default value and missing entry should return default value`() {
        val f = this::pluralSquareIfPositive.singular { null }

        assertEquals(9, f(3))
        assertEquals(null, f(-1))
    }

    private fun pluralSquare(values: Set<Int>): Map<Int, Int> {
        return values.associateBy({ it }, { it * it })
    }

    private fun pluralSquareIfPositive(values: Set<Int>): Map<Int, Int> {
        return values.filter { it >= 0 }.associateBy({ it }, { it * it })
    }
}
