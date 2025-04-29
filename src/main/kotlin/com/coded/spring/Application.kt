package com.coded.spring


import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.ObjectInputFilter

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
	orderingConfig.getMapConfig("menu").setTimeToLiveSeconds(60)
}

val orderingConfig = Config("online-ordering-cache")
val serverCache: HazelcastInstance = Hazelcast.newHazelcastInstance(orderingConfig)

