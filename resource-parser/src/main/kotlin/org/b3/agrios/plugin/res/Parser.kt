package org.b3.agrios.plugin.res

interface Parser<in I, out O> {
    fun parse(input: I): O
}
