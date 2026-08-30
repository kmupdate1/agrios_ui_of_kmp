package org.b3.agrios.plugin.res.domain

import org.b3.agrios.plugin.res.model.Resource

interface ResourceParser<in I, R : Resource> {
    fun parse(input: I): R
}
