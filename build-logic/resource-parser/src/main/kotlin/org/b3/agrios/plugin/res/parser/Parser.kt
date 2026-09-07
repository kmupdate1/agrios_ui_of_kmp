package org.b3.agrios.plugin.res.parser

internal interface Parser<in I, out O> {
    fun parse(input: I): O
}
