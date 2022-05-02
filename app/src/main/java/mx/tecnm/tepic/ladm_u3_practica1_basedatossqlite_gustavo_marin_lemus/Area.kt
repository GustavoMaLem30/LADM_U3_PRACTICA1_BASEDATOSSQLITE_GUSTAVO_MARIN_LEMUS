package mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteException
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus.CosasUtiles.arregloArea
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus.ui.home.HomeFragment

class Area(este: Context) {
    var idArea = 0
    var descripcion = ""
    var division = ""
    var cantidadEmpleados = ""
    private var este = este
    private var err = ""
    fun insertar(): Boolean {
        var baseDatos = BaseDatos(este, "MAPEO_EMPRESAS", null, 1)
        err = ""
        try {
            val tabla = baseDatos.writableDatabase
            var datos = ContentValues()
            datos.put("DESCRIPCION", descripcion)
            datos.put("DIVISION", division)
            datos.put("CANTIDAD_EMPLEADOS", cantidadEmpleados)
            var resultado = tabla.insert("AREA",null, datos)
            if (resultado == -1L) {
                return false
            }
        } catch (err: SQLiteException) {
            AlertDialog.Builder(este)
                .setMessage(err.message!!)
                .show()
            return false
        } finally {
            baseDatos.close()
        }
        return true
    }
    fun mostrarAreaDesc(descripcion : String) : ArrayList<String>{
        var baseDatos = BaseDatos(este,"MAPEO_EMPRESAS",null,1)
        var arreglo = ArrayList<String>()
        arregloArea.clear()
        err = ""
        try{
            var tabla = baseDatos.readableDatabase
            var SQL_SELECT = "SELECT * FROM AREA WHERE DESCRIPCION=?"
            var cursor = tabla.rawQuery(SQL_SELECT, arrayOf(descripcion))
            if(cursor.moveToFirst()){
                do{
                    val area = Area(este)
                    arreglo.add(cursor.getString(1))
                    area.idArea = cursor.getInt(0)
                    area.descripcion = cursor.getString(1)
                    area.division = cursor.getString(2)
                    area.cantidadEmpleados = cursor.getString(3)
                    arregloArea.add(area)
                }while (cursor.moveToNext())
            }
        }catch (err : SQLiteException){
            this.err = err.message!!
        }finally {
            baseDatos.close()
        }
        return arreglo
    }
    fun mostrarAreaDiv(div : String) :  ArrayList<String>{
        var baseDatos = BaseDatos(este,"MAPEO_EMPRESAS",null,1)
        var arreglo = ArrayList<String>()
        arregloArea.clear()
        err = ""
        try{
            var tabla = baseDatos.readableDatabase
            var SQL_SELECT = "SELECT * FROM AREA WHERE DIVISION=?"
            var cursor = tabla.rawQuery(SQL_SELECT, arrayOf(div))
            if(cursor.moveToFirst()){
                do{
                    val area = Area(este)
                    arreglo.add(cursor.getString(2))
                    area.idArea = cursor.getInt(0)
                    area.descripcion = cursor.getString(1)
                    area.division = cursor.getString(2)
                    area.cantidadEmpleados = cursor.getString(3)
                    arregloArea.add(area)
                }while (cursor.moveToNext())
            }
        }catch (err : SQLiteException){
            this.err = err.message!!
        }finally {
            baseDatos.close()
        }
        return arreglo
    }
    fun mostrarTodos() : ArrayList<String>{
        var baseDatos = BaseDatos(este,"MAPEO_EMPRESAS",null,1)
        var arreglo = ArrayList<String>()
        arregloArea.clear()
        err = ""
        try{
            var tabla = baseDatos.readableDatabase
            var SQL_SELECT = "SELECT * FROM AREA"
            var cursor = tabla.rawQuery(SQL_SELECT,null)
            if(cursor.moveToFirst()){
                do{
                    val area = Area(este)
                    area.idArea = cursor.getInt(0)
                    arreglo.add(cursor.getString(1))
                    area.descripcion = cursor.getString(1)
                    area.division = cursor.getString(2)
                    area.cantidadEmpleados = cursor.getString(3)
                    arregloArea.add(area)
                }while (cursor.moveToNext())
            }
        }catch (err : SQLiteException){
            this.err = err.message!!
        }finally {
            baseDatos.close()
        }
        return arreglo
    }
    fun mostrarTodosDiv() : ArrayList<String>{
        var baseDatos = BaseDatos(este,"MAPEO_EMPRESAS",null,1)
        var arreglo = ArrayList<String>()
        arregloArea.clear()
        err = ""
        try{
            var tabla = baseDatos.readableDatabase
            var SQL_SELECT = "SELECT * FROM AREA"
            var cursor = tabla.rawQuery(SQL_SELECT,null)
            if(cursor.moveToFirst()){
                do{
                    val area = Area(este)
                    area.idArea = cursor.getInt(0)
                    area.descripcion = cursor.getString(1)
                    area.division = cursor.getString(2)
                    arreglo.add(cursor.getString(2))
                    area.cantidadEmpleados = cursor.getString(3)
                    arregloArea.add(area)
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
            datosActualizados.put("DESCRIPCION",descripcion)
            datosActualizados.put("DIVISION",division)
            datosActualizados.put("CANTIDAD_EMPLEADOS",cantidadEmpleados)
            val resultado = tabla.update("AREA",datosActualizados,"IDAREA=?", arrayOf(idArea.toString()))
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
            val respuesta = tabla.delete("AREA","IDAREA=?",arrayOf(idArea.toString()))
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
    fun obtenerDatos(idArea1: String) : ArrayList<String> {
        var baseDatos = BaseDatos(este,"MAPEO_EMPRESAS",null,1)
        var arreglo = ArrayList<String>()
        err = ""
        try{
            var tabla = baseDatos.readableDatabase
            var SQL_SELECT = "SELECT * FROM AREA WHERE IDAREA=?"
            var cursor = tabla.rawQuery(SQL_SELECT, arrayOf(idArea1))
            if(cursor.moveToFirst()){
                    arreglo.add(cursor.getString(1))
                    arreglo.add(cursor.getString(2))
                    arreglo.add(cursor.getInt(3).toString())
            }
        }catch (err : SQLiteException){
            this.err = err.message!!
        }finally {
            baseDatos.close()
        }
        return arreglo
    }
}

