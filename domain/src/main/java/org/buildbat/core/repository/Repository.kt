package org.buildbat.core.repository

interface Repository<ENTITY, in ID> {
    fun find(name: ID): ENTITY
    fun save(t: ENTITY) : ENTITY
    fun list(): List<ENTITY>
    fun remove(name: ID)
}