package mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE AREA(IDAREA INTEGER PRIMARY KEY AUTOINCREMENT,DESCRIPCION VARCHAR(200),DIVISION VARCHAR(50),CANTIDAD_EMPLEADOS INTEGER)")
        db.execSQL("CREATE TABLE SUBDEPARTAMENTO(IDSUBDEPTO INTEGER PRIMARY KEY AUTOINCREMENT,IDEDIFICIO VARCHAR(20),PISO VARCHAR(50),IDAREA INTEGER, FOREIGN KEY(IDAREA) REFERENCES MAPEO_EMPRESAS(IDAREA))")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}