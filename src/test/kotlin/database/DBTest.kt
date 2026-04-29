package database

import org.junit.jupiter.api.Test

class DBTest {

  @Test
  fun deleteOrderTest() {
    JDBCHelper().deleteOrderById(91)
  }


}