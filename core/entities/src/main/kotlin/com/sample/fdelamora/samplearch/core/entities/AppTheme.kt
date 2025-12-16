package com.sample.fdelamora.samplearch.core.entities

import co.touchlab.kermit.Logger

enum class AppTheme(val id: Int) {
    SYSTEM(0),
    LIGHT(1),
    RED(2),
    DARK(3);

    companion object {
        fun getFromId(themeId: Int): AppTheme {
            values().forEach { theme ->
                if (theme.id == themeId) {
                    return theme
                }
            }
            Logger.d { "Invalid theme id received, fallback to SYSTEM" }
            return SYSTEM
        }
    }
}
