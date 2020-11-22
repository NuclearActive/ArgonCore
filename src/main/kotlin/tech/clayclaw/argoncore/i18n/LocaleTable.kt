package tech.clayclaw.argoncore.i18n

import java.util.Locale

open class LocaleTable(val locale: Locale) {

    private val localeMap: MutableMap<String, String> = hashMapOf()

    fun locale(tag: String, content: String): LocaleTable {
        localeMap[tag] = content
        return this
    }

    fun register(i18nService: I18nService) {
        i18nService.registerLocaleTable(this)
    }

    internal fun getLocaleMap() = localeMap.toMap()

    companion object {
        @JvmStatic
        fun of(locale: Locale) = LocaleTable(locale)
    }

}
