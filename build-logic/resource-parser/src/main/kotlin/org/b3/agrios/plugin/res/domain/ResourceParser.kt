package org.b3.agrios.plugin.res.domain

interface ResourceParser<in I, out O> {
    fun parse(input: I): O
}
