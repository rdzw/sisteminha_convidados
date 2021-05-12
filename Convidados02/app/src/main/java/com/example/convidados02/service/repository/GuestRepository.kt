package com.example.convidados02.service.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidados02.constants.DataBaseConstants
import com.example.convidados02.service.model.GuestModel

// aqui ninguem mexe na classe
//conceito singleton
class GuestRepository private constructor(context: Context) {
    private var mGuestDataBaseHelper: GuestDataBaseHelper = GuestDataBaseHelper(context)


    //metodo estatico
    companion object {
        private lateinit var repository: GuestRepository
        fun getInstance(context: Context): GuestRepository {
            //identifica se a variavel foi iniciada
            if (!::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    //metodo que pega dados
    //recebe uma lista do tipo GuestModel

    fun getAll(): List<GuestModel> {


        //começo da lista
        val list: MutableList<GuestModel> = ArrayList()

        return try {
            //ler o banco de dados
            val db = mGuestDataBaseHelper.readableDatabase

            //projecao dos valores
            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            //Execução da Query
            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )

            //preenchemos nossa lista, criando um convisdado com os valores do banco de dados
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    //AQUI PEGAMOS OS VALORES

                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }

                cursor.moveToFirst()
            }

            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
        return list
    }

    //metodo que pega dados
    fun getPresent(): List<GuestModel> {
        //começo da lista
        val list: MutableList<GuestModel> = ArrayList()

        return try {
            //ler o banco de dados
            val db = mGuestDataBaseHelper.readableDatabase



            //Execução da Query
            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 1",null)

            //preenchemos nossa lista, criando um convisdado com os valores do banco de dados
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    //AQUI PEGAMOS OS VALORES

                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }

                cursor.moveToFirst()
            }

            cursor?.close()

            list
        } catch (e: Exception) {
            list
        }
    }

    //metodo que pega dados
    fun getAbsent(): List<GuestModel> {
        //começo da lista
        val list: MutableList<GuestModel> = ArrayList()

        return try {
            //ler o banco de dados
            val db = mGuestDataBaseHelper.readableDatabase



            //Execução da Query
            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 0",null)

            //preenchemos nossa lista, criando um convisdado com os valores do banco de dados
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    //AQUI PEGAMOS OS VALORES

                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }

                cursor.moveToFirst()
            }

            cursor?.close()

            list
        } catch (e: Exception) {
            list
        }
    }


    //crud - ceate, read, update, delete


    fun get(id: Int): GuestModel? {
        var guest: GuestModel? = null
        return try {

            val db = mGuestDataBaseHelper.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )


            //CRITERIO DE ATUALIZAÇÃO
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())
            ///////////////////////////////////////////////////////


            //metodo
            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null
            )
            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()


                //AQUI PEGAMOS OS VALORES
                val name =
                    cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                val presence =
                    (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                guest = GuestModel(id, name, presence)


            }

            cursor?.close()



            guest
        } catch (e: Exception) {
            guest
        }

    }


    //peguei o parametro la do GuestModel
    fun save(guest: GuestModel): Boolean {

        return try {

            val db = mGuestDataBaseHelper.writableDatabase

            val contentvalues = ContentValues()
            contentvalues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentvalues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)
            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, contentvalues)
            true
        } catch (e: Exception) {
            false
        }
    }

    //peguei o parametro do GuestModel
    fun update(guest: GuestModel): Boolean {
        return try {

            val db = mGuestDataBaseHelper.writableDatabase

            val contentvalues = ContentValues()
            contentvalues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentvalues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)


            //CRITERIO DE ATUALIZAÇÃO
            val section = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString())
            ///////////////////////////////////////////////////////


            db.update(DataBaseConstants.GUEST.TABLE_NAME, contentvalues, section, args)
            true
        } catch (e: Exception) {
            false
        }

    }

    //peguei o parametro do GuestModel
    //remocao
    fun delete(id: Int): Boolean {

        return try {

            val db = mGuestDataBaseHelper.writableDatabase
            //CRITERIO DE ATUALIZAÇÃO
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())
            ///////////////////////////////////////////////////////

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)

            true
        } catch (e: Exception) {
            false
        }

    }
}