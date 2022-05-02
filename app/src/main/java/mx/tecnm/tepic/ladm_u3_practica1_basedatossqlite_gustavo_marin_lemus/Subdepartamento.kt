package mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteException
import androidx.core.content.contentValuesOf
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus.CosasUtiles.arregloSub

class Subdepartamento(este : Context) {
    var idSubDepto = 0
    var idEdificio = ""
    var piso = ""
    var idArea = 0
    private var este = este
    private var err = ""
    fun insertar () : Boolean {
        var baseDatos = BaseDatos(este,"MAPEO_EMPRESAS",null,1)
        err = ""
        try{
            val tabla = baseDatos.writableDatabase
            var datos = ContentValues()
            datos.put("IDEDIFICIO",idEdificio)
            datos.put("PISO",piso)
            datos.put("IDAREA",idArea)
            var resultado = tabla.insert("SUBDEPARTAMENTO","IDSUBDEPTO",datos)
            if (resultado == -1L){
                return false
            }
        }catch (err : SQLiteException){
            this.err = err.message!!
            return false
        }finally {
            baseDatos.close()
        }
        return true;
    }
    fun mostrarTodos() : ArrayList<String>{
        var baseDatos = BaseDatos(este,"MAPEO_EMPRESAS",null,1)
        var arreglo = ArrayList<String>()
        arregloSub.clear()
        err = ""
        try{
            var tabla = baseDatos.readableDatabase
            var SQL_SELECT = "SELECT * FROM SUBDEPARTAMENTO"
            var cursor = tabla.rawQuery(SQL_SELECT,null)
            if(cursor.moveToFirst()){
                do{
                    val Subdepartamento = Subdepartamento(este)
                    Subdepartamento.idSubDepto = cursor.getInt(0)
                    Subdepartamento.idEdificio = cursor.getString(1)
                    arreglo.add(cursor.getString(1))
                    Subdepartamento.piso = cursor.getString(2)
                    Subdepartamento.idArea = cursor.getInt(3)
                    arregloSub.add(Subdepartamento)
                }while (cursor.moveToNext())
            }
        }catch (err : SQLiteException){
            this.err = err.message!!
        }finally {
            baseDatos.close()
        }
        return arreglo
    }

    fun mostrarIdEdificio(idEdificioAbuscar : String) :  ArrayList<String>{
        var baseDatos = BaseDatos(este,"MAPEO_EMPRESAS",null,1)
        var arreglo = ArrayList<String>()
        arregloSub.clear()
        err = ""
        try{

            var tabla = baseDatos.readableDatabase
            var SQL_SELECT = "SELECT * FROM SUBDEPARTAMENTO WHERE IDEDIFICIO=?"
            var cursor = tabla.rawQuery(SQL_SELECT, arrayOf(idEdificioAbuscar))
            if(cursor.moveToFirst()){
                do{
                val Subdepartamento = Subdepartamento(este)
                arreglo.add(cursor.getString(1))
                    Subdepartamento.idSubDepto = cursor.getInt(0)
                Subdepartamento.idEdificio = cursor.getString(1)
                    Subdepartamento.piso = cursor.getString(2)
                    Subdepartamento.idArea = cursor.getInt(3)
                arregloSub.add(Subdepartamento)
                }while (cursor.moveToNext())
            }
        }catch (err : SQLiteException){
            this.err = err.message!!
        }finally {
            baseDatos.close()
        }
        return arreglo
    }

    fun mostrarDesc(desc : String) :  ArrayList<String>{
        var baseDatos = BaseDatos(este,"MAPEO_EMPRESAS",null,1)
        var arreglo = ArrayList<String>()
        arregloSub.clear()
        err = ""
        try{
            var tabla = baseDatos.readableDatabase
            var SQL_SELECT = "SELECT * FROM SUBDEPARTAMENTO S JOIN AREA A ON(S.IDAREA = A.IDAREA) WHERE A.DESCRIPCION=?"
            var cursor = tabla.rawQuery(SQL_SELECT, arrayOf(desc))
            if(cursor.moveToFirst()){
                do{
                    val Subdepartamento = Subdepartamento(este)
                    arreglo.add(cursor.getString(1))
                    Subdepartamento.idSubDepto = cursor.getInt(0)
                    Subdepartamento.idEdificio = cursor.getString(1)
                    Subdepartamento.piso = cursor.getString(2)
                    Subdepartamento.idArea = cursor.getInt(3)
                    arregloSub.add(Subdepartamento)
                }while (cursor.moveToNext())
            }
        }catch (err : SQLiteException){
            this.err = err.message!!
        }finally {
            baseDatos.close()
        }
        return arreglo
    }
    fun mostrarDivi(desc : String) :  ArrayList<String>{
        var baseDatos = BaseDatos(este,"MAPEO_EMPRESAS",null,1)
        var arreglo = ArrayList<String>()
        arregloSub.clear()
        err = ""
        try{
            var tabla = baseDatos.readableDatabase
            var SQL_SELECT = "SELECT * FROM SUBDEPARTAMENTO S JOIN AREA A ON(S.IDAREA = A.IDAREA) WHERE A.DIVISION=?"
            var cursor = tabla.rawQuery(SQL_SELECT, arrayOf(desc))
            if(cursor.moveToFirst()){
                do{
                    val Subdepartamento = Subdepartamento(este)
                    arreglo.add(cursor.getString(1))
                    Subdepartamento.idSubDepto = cursor.getInt(0)
                    Subdepartamento.idEdificio = cursor.getString(1)
                    Subdepartamento.piso = cursor.getString(2)
                    Subdepartamento.idArea = cursor.getInt(3)
                    arregloSub.add(Subdepartamento)
                }while (cursor.moveToNext())
            }
        }catch (err : SQLiteException){
            this.err = err.message!!
        }finally {
            baseDatos.close()
        }
        return arreglo
    }

    fun actualizar () : Boolean{
        var baseDatos = BaseDatos(este,"MAPEO_EMPRESAS",null,1)
        err = ""
        try{
            var tabla = baseDatos.writableDatabase
            val datosActualizados = ContentValues()
            datosActualizados.put("IDEDIFICIO",idEdificio)
            datosActualizados.put("PISO",piso)
            datosActualizados.put("IDAREA",idArea)
            val resultado = tabla.update("SUBDEPARTAMENTO",datosActualizados,"IDSUBDEPTO=?", arrayOf(idSubDepto.toString()))
            if (resultado == 0){
                return false
            }
        }catch (err : SQLiteException){
            this.err = err.message!!
            return false
        }finally {
            baseDatos.close()
        }
        return true
    }
    fun eliminar() : Boolean{
        var baseDatos = BaseDatos(este,"MAPEO_EMPRESAS",null,1)
        err = ""

        try{
            var tabla = baseDatos.writableDatabase
            val respuesta = tabla.delete("SUBDEPARTAMENTO","IDSUBDEPTO=?",arrayOf(idSubDepto.toString()))
            if(respuesta ==0){
                return false
            }

        }catch (err : SQLiteException){
            this.err = err.message!!
            return false
        }finally {
            baseDatos.close()
        }
        return true
    }
    fun eliminarPorArea(idAeliminar : String){
        var baseDatos = BaseDatos(este,"MAPEO_EMPRESAS",null,1)
        err = ""
        try{
            var tabla = baseDatos.writableDatabase
            val respuesta = tabla.delete("SUBDEPARTAMENTO","IDAREA=?",arrayOf(idAeliminar.toString()))
        }catch (err : SQLiteException){
            this.err = err.message!!
        }finally {
            baseDatos.close()
        }
    }
}

/*
        var baseDatos = BaseDatos(este,"MAPEO_EMPRESAS",null,1)
                err = ""

    try{

    }catch (err : SQLiteException){
        this.err = err.message!!

    }finally {
        baseDatos.close()
    }
 */