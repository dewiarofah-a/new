package com.example.anew

class data_pengguna {
    var hari: String? = null
    var tanggal: String? = null
    var suhu: String? = null
    var keluhan: String? = null
    var kontak: String? = null
    var key: String? = null

    constructor()

    constructor(hari: String?, tanggal:String?, suhu: String?, keluhan: String?, kontak: String?) {
        this.hari = hari
        this.tanggal = tanggal
        this.suhu = suhu
        this.keluhan = keluhan
        this.kontak = kontak

    }
}