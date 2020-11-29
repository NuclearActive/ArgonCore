package tech.clayclaw.argoncore.persistent

import com.dieselpoint.norm.Database
import dev.reactant.reactant.core.component.Component
import dev.reactant.reactant.core.component.lifecycle.LifeCycleHook
import dev.reactant.reactant.core.dependency.injection.Inject
import dev.reactant.reactant.service.spec.config.Config
import io.reactivex.rxjava3.core.Completable
import tech.clayclaw.argoncore.ArgonCore
import tech.clayclaw.argoncore.config.ConfigDatabase

@Component
class DatabaseService(
        @Inject("plugins/ArgonCore/database.json")
        private val configDatabase: Config<ConfigDatabase>
): LifeCycleHook {

    val database: Database = Database()

    var databaseTested = false
        private set

    override fun onEnable() {

        if(!configDatabase.content.activate) {
            ArgonCore.log.info("Database service is disabled in config!")
            return
        }

        ArgonCore.log.info("Attempting to connect SQL database")

        database.setJdbcUrl(configDatabase.content.jdbcUrl)
        database.setUser(configDatabase.content.user)
        database.setPassword(configDatabase.content.password)
        database.setDatabaseName(configDatabase.content.database)

        ArgonCore.log.info("Testing database connection")

        Completable.fromRunnable {
            database.sql("drop table if exists test").execute()
        }.doOnError {
            ArgonCore.log.warn("Database could not be connected")
        }.doOnComplete {
            databaseTested = true
            ArgonCore.log.info("Database test completed")
        }.blockingAwait()

    }

}
