package at.sigmoid.util

typealias PluralFunction<K, R> = (Set<K>) -> Map<K, R>
typealias SingularFunction<K, R> = (K) -> R

fun <K, R> PluralFunction<K, R>.singular(defaultValue: (() -> R)? = null): SingularFunction<K, R> {
    return if (defaultValue != null) {
        { k -> this(setOf(k)).getOrElse(k, defaultValue) }
    } else {
        { k -> this(setOf(k)).getValue(k) }
    }
}

val <K, R> PluralFunction<K, R>.singular: SingularFunction<K, R>
    get() = this.singular(null)
