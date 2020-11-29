package tech.clayclaw.argoncore.i18n

import dev.reactant.reactant.core.component.Component
import dev.reactant.reactant.core.component.lifecycle.LifeCycleControlAction
import dev.reactant.reactant.core.component.lifecycle.LifeCycleInspector
import dev.reactant.reactant.core.dependency.injection.Inject
import dev.reactant.reactant.core.dependency.injection.producer.ComponentProvider
import dev.reactant.reactant.extra.config.type.MultiConfigs
import dev.reactant.reactant.extra.config.type.SharedConfig
import tech.clayclaw.argoncore.ArgonCore
import tech.clayclaw.argoncore.config.ConfigI18nLocale
import tech.clayclaw.argoncore.config.CoreConfig
import java.lang.IllegalStateException
import java.util.Locale
import kotlin.collections.HashMap

@Component
class I18nService(
        @Inject("plugins/ArgonCore/i18n")
        private val localeConfigs: MultiConfigs<ConfigI18nLocale>,
        @Inject("plugins/ArgonCore/config.json")
        private val coreConfig: SharedConfig<CoreConfig>
): LifeCycleInspector {

    private val defaultLocaleTableMap: HashMap<Locale, ArrayList<LocaleTable>> = hashMapOf()
    private val loadedLocaleConfigs: HashMap<String, ConfigI18nLocale> = hashMapOf()

    fun registerLocaleTable(localeTable: LocaleTable) {
        defaultLocaleTableMap.getOrPut(localeTable.locale) { arrayListOf() }.add(localeTable)
    }

    operator fun invoke(registering: () -> LocaleTable) {
        registerLocaleTable(registering())
    }

    override fun afterBulkActionComplete(action: LifeCycleControlAction) {
        ArgonCore.log.info("Found registered default locale: ${defaultLocaleTableMap.keys}")

        // Load locale configs
        localeConfigs.getAll(false).blockingForEach { config ->
            val localeTag = config.path.split(".").asReversed()[1]
            loadedLocaleConfigs[localeTag] = config.content
        }

        // Check locale configs
        defaultLocaleTableMap.mapKeys { it.key.toLanguageTag() }
                .filter { loadedLocaleConfigs.containsKey(it.key) }
                .forEach {

                    it.value.flatMap { tables -> tables.getLocaleMap().entries }
                            .filterNot { entry -> loadedLocaleConfigs[it.key]!!.localePairs.containsKey(entry.key) }
                            .forEach { entry ->
                                loadedLocaleConfigs[it.key]!!.localePairs[entry.key] = entry.value
                                ArgonCore.log.info("Locale config ${it.key} key ${entry.key} not found, adding default item")
                            }

                }

        // Save to locale configs
        localeConfigs.getAll(false)
                .map { config ->
                    val localeTag = config.path.split(".").asReversed()[1]
                    Pair(loadedLocaleConfigs[localeTag], config)
                }
                .filter { configPair ->
                    configPair.first != null &&
                            configPair.first?.localePairs?.size != configPair.second.content.localePairs.size
                }
                .flatMapCompletable { configPair ->
                    configPair.second.content = configPair.first!!
                    configPair.second.save()
                }
                .blockingAwait()

        defaultLocaleTableMap.clear()
    }

    fun parse(tag: String) = parseByLocale(tag, coreConfig.content.locale)

    fun parseByLocale(tag: String, identifier: String) = loadedLocaleConfigs[identifier]?.localePairs
        ?.getOrDefault(tag, "Locale not found: $tag")
        ?: throw IllegalStateException("Locale ${coreConfig.content.locale} not found in loaded configs.")

}
