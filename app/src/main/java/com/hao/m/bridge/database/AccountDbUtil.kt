package com.hao.m.bridge.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.hao.m.KotlinMApp
import com.hao.m.entity.data.AccountData
import com.hao.m.greedao.AccountDataDao
import com.hao.m.greedao.DaoMaster

/**
 * Created by HaoBoy on 2018/7/18.
 *
 * 数据库操作 --- 用户账户登录信息
 */

class AccountDbUtil {

    @JvmField
    val DB_NAME = "kotlin_db"

    @JvmField
    var openHelper: DaoMaster.DevOpenHelper? = null

    @JvmField
    var context: Context? = null

    init {
        context = KotlinMApp.context()
        openHelper = DaoMaster.DevOpenHelper(context, DB_NAME)
    }

    companion object {
        fun instance() = Inner.instance
    }

    private object Inner {
        val instance = AccountDbUtil()
    }


    /**
     * 获取可读数据库
     */
    private fun getReadableDatabase(): SQLiteDatabase {
        openHelper = if (openHelper == null) DaoMaster.DevOpenHelper(context, DB_NAME) else openHelper
        return openHelper!!.readableDatabase
    }

    /**
     * 获取可写数据库
     */
    private fun getWritableDatabase(): SQLiteDatabase {
        openHelper = if (openHelper == null) DaoMaster.DevOpenHelper(context, DB_NAME) else openHelper
        return openHelper!!.writableDatabase
    }


    fun saveAccount(account: AccountData) {
        val daoMaster = DaoMaster(getWritableDatabase())
        val daoSession = daoMaster.newSession()
        val accountDao = daoSession.accountDataDao

        val oldDao = queryAccountByUsername(account.username)
        deleteAccount(oldDao)
        accountDao.insert(account)
    }

    fun deleteAccount(account: AccountData?) {
        val daoMaster = DaoMaster(getWritableDatabase())
        val daoSession = daoMaster.newSession()
        val accountDao = daoSession.accountDataDao
        if (account != null) accountDao.delete(account)
    }

    fun updateAccount(account: AccountData) {
        val daoMaster = DaoMaster(getWritableDatabase())
        val daoSession = daoMaster.newSession()
        val accountDao = daoSession.accountDataDao
        accountDao.update(account)
    }

    fun queryAccountByUsername(username: String): AccountData? {
        val daoMaster = DaoMaster(getWritableDatabase())
        val daoSession = daoMaster.newSession()
        val accountDao = daoSession.accountDataDao
        var list = accountDao.queryBuilder().where(AccountDataDao.Properties.Username.eq(username)).list()
        return if (list == null || list.size == 0) null else list[0]
    }

    fun queryOrderByTime(): List<AccountData>? {
        val daoMaster = DaoMaster(getWritableDatabase())
        val daoSession = daoMaster.newSession()
        val accountDao = daoSession.accountDataDao
        val queryBuilder = accountDao.queryBuilder()
        return queryBuilder.orderDesc(AccountDataDao.Properties.Time).list()
    }

    fun queryAccountAll(): List<AccountData>? {
        val daoMaster = DaoMaster(getWritableDatabase())
        val daoSession = daoMaster.newSession()
        val accountDao = daoSession.accountDataDao
        return accountDao.queryBuilder().list()
    }
}
