package org.anfega.pokemon.adapter

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.database.Database

actual class DatabaseDriverFactory (private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = Database.Schema,
            context = context,
            name = "Database.db"
        )
    }
}