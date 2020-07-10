package com.dorukkangal.geoalert.data.store.base.mapper

interface BaseMapper<in Data, out Domain> {

    fun mapToItem(data: Data, vararg extra: Any?): Domain

    fun mapToItem(data: Collection<Data>?, vararg extra: Any?): Collection<Domain> {
        return data?.map {
            mapToItem(it, *extra)
        } ?: emptyList()
    }
}
