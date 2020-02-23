package com.tarek360.test

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import kotlin.reflect.KClass
import org.hamcrest.core.IsInstanceOf.instanceOf

infix fun Any?.shouldEqual(theOther: Any?) = assertEquals(theOther, this)

infix fun Any?.shouldNotEqual(theOther: Any?) = assertNotEquals(theOther, this)

infix fun <T> Collection<T>.hasItem(item: T) = assertThat(this, CoreMatchers.hasItem(item))

infix fun <T> Collection<T>.notHasItem(item: T) = assertThat(this, CoreMatchers.not(CoreMatchers.hasItem(item)))

infix fun Any.isInstanceOf(clazz: KClass<*>) = assertThat("",this, instanceOf(clazz.java))
