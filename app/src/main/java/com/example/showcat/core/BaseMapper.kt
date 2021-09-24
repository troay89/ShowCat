package com.example.showcat.core

interface BaseMapper<in A, out B> {

    fun map(type: A?): B
}