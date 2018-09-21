package com.hao.m.entity.response

import java.io.Serializable
import java.sql.Date

class ZhUserEntity : Serializable {
    var id: Int = 0
    var userId: String? = null
    var userName: String? = null
    var armType: Int = 0
    var rank: String? = null
    var bornTime: Date? = null
    var deadTime: Date? = null
    var enlistTime: Date? = null
    var description: String? = null

}
