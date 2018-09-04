package com.hao.m.bridge.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.hao.m.KotlinMApp
import com.hao.m.entity.data.CookieResult
import com.hao.m.greedao.CookieResultDao
import com.hao.m.greedao.DaoMaster

/**
 * Created by HaoBoy
 *
 * 数据库操作 -- 网络数据缓存
 */
class CookieDbUtil private constructor() {


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

    //单例模式
    companion object {
        val instance: CookieDbUtil by lazy { CookieDbUtil() }
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

    fun saveCookie(info: CookieResult) {
        val daoMaster = DaoMaster(getWritableDatabase())
        val daoSession = daoMaster.newSession()
        val downInfoDao = daoSession.cookieResultDao
        downInfoDao.insert(info)
    }

    fun updateCookie(info: CookieResult) {
        val daoMaster = DaoMaster(getWritableDatabase())
        val daoSession = daoMaster.newSession()
        val downInfoDao = daoSession.cookieResultDao
        downInfoDao.update(info)
    }


    fun deleteCookie(info: CookieResult) {
        val daoMaster = DaoMaster(getWritableDatabase())
        val daoSession = daoMaster.newSession()
        val downInfoDao = daoSession.cookieResultDao
        downInfoDao.delete(info)
    }

    fun queryCookieBy(url: String): CookieResult? {
        val daoMaster = DaoMaster(getReadableDatabase())
        val daoSession = daoMaster.newSession()
        val downInfoDao = daoSession.cookieResultDao
        val qb = downInfoDao.queryBuilder()
        qb.where(CookieResultDao.Properties.Url.eq(url))
        val list = qb.list()
        return if (list.isEmpty()) {
            null
        } else {
            list[0]
        }
    }

    fun queryCookieAll(): List<CookieResult> {
        val daoMaster = DaoMaster(getReadableDatabase())
        val daoSession = daoMaster.newSession()
        val downInfoDao = daoSession.cookieResultDao
        val qb = downInfoDao.queryBuilder()
        return qb.list()
    }


}