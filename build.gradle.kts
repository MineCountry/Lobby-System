import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    id("java")
    id ("com.github.johnrengelman.shadow") version "8.1.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0" // Generates the plugin.yml when building the project
    id("xyz.jpenilla.run-paper") version "2.2.3"
}

group = "eu.minecountry"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.aikar.co/content/groups/aikar/")
    maven("https://repo.minebench.de/")
    maven("https://repo.codemc.io/repository/maven-snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    // Dependencies that are available at runtime
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")

    // Dependencies that have to be shadowed
    implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.3.3")
    implementation("de.chojo.sadu:sadu:2.1.0")
    implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")
    implementation("de.themoep:inventorygui:1.6.1-SNAPSHOT")
    implementation("net.wesjd:anvilgui:1.9.2-SNAPSHOT")


}

tasks {
    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    }
    compileJava {
        options.encoding = "UTF-8"
    }

    shadowJar {
        archiveBaseName.set("shadow")
        mergeServiceFiles()
        manifest {
            attributes(mapOf("Main-Class" to "eu.minecountry.lobbysystem.LobbySystem"))
        }

        val base = "eu.minecountry.lobbysystem.libs."
        val mapping = mapOf(
                "org.mariadb.jdbc" to "jdbc",
                "de.chojo.sadu" to "sadu",
                "co.aikar" to "acf",
                "de.themoep" to "inventorygui",
                "net.wesjd" to "anvilgui"
        )
        for ((pattern, name) in mapping) relocate(pattern, "${base}${name}")
    }

    build {
        dependsOn(shadowJar)
    }

    runServer {
        minecraftVersion("1.20.4")
        downloadPlugins {
            url("https://ci.lucko.me/job/spark/400/artifact/spark-bukkit/build/libs/spark-1.10.59-bukkit.jar")
        }
    }

}

bukkit {
    main = "eu.minecountry.lobbysystem.LobbySystem"
    apiVersion = "1.20"
    foliaSupported = false
    load = BukkitPluginDescription.PluginLoadOrder.POSTWORLD
    author = "Maaxxs_"
    contributors = listOf("Maaxxs_")
    depend = listOf("spark")
}


