package games.game2048

/*
 * This function moves all the non-null elements to the beginning of the list
 * (by removing nulls) and merges equal elements.
 * The parameter 'merge' specifies the way how to merge equal elements:
 * it returns a new element that should be present in the resulting list
 * instead of two merged elements.
 *
 * If the function 'merge("a")' returns "aa",
 * then the function 'moveAndMergeEqual' transforms the input in the following way:
 *   a, a, b -> aa, b
 *   a, null -> a
 *   b, null, a, a -> b, aa
 *   a, a, null, a -> aa, a
 *   a, null, a, a -> aa, a
 *
 * You can find more examples in 'TestGame2048Helper'.
*/
fun <T : Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T> {
    val tmp = filterNotNull()
    val result = emptyList<T>().toMutableList()
    var i = 0
    while (i < tmp.size) {
        if ((i + 1) in tmp.indices && tmp[i] == tmp[i + 1]) {
            result.add(merge(tmp[i]))
            i++
        } else {
            result.add(tmp[i])
        }
        i++
    }

//    return with(filterNotNull()) {
//        if (size < 2) this
//        else zipWithNext { first, second ->
//            if (first == second) {
//                println("if $first || $second")
//                listOf(merge(first))
//            }
//            else {
//                println("else $first || $second")
//                listOf(first, second)
//            }
//        }.flatten()
//    }

    return result
}


