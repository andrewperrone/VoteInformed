package com.example.voteinformed.database

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.voteinformed.database.Users
import com.example.voteinformed.database.UsersDAO
import com.example.voteinformed.database.VoteInformedDatabase
import org.junit.After
import org.junit.Assert.* // ktlint-disable no-wildcard-imports
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class UsersDAOTest {

    private lateinit var usersDao: UsersDAO
    private lateinit var db: VoteInformedDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, VoteInformedDatabase::class.java
        ).build()
        usersDao = db.usersDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetUser() {
        val user = Users("testuser", "testpassword", "test@test.com")
        usersDao.insert(user)
        val byName = usersDao.getUser("testuser", "testpassword")
        assertEquals(byName.username, user.username)
    }

    @Test
    fun insertDuplicateUsername() {
        val user1 = Users("testuser", "testpassword", "test1@test.com")
        usersDao.insert(user1)

        try {
            val user2 = Users("testuser", "testpassword", "test2@test.com")
            usersDao.insert(user2)
            fail("Should have thrown SQLiteConstraintException")
        } catch (e: SQLiteConstraintException) {
            // expected
        }
    }

    @Test
    fun insertDuplicateEmail() {
        val user1 = Users("testuser1", "testpassword", "test@test.com")
        usersDao.insert(user1)

        try {
            val user2 = Users("testuser2", "testpassword", "test@test.com")
            usersDao.insert(user2)
            fail("Should have thrown SQLiteConstraintException")
        } catch (e: SQLiteConstraintException) {
            // expected
        }
    }
}
