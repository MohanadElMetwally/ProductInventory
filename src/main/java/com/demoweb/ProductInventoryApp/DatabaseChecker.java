package com.demoweb.ProductInventoryApp;
// package com.demoweb.BookingApp;

// import java.sql.Connection;

// import javax.sql.DataSource;

// import org.springframework.stereotype.Component;

// import jakarta.annotation.PostConstruct;

// @Component
// public class DatabaseChecker {
//     private final DataSource dataSource;

//     public DatabaseChecker(DataSource dataSource) {
//         this.dataSource = dataSource;
//     }

//     @PostConstruct
//     public void verifyConnection() throws Exception {
//         try (Connection conn = dataSource.getConnection()) {
//             System.out.println("✅ Connected to: " + conn.getMetaData().getURL());
//             System.out.println("🧩 Driver: " + conn.getMetaData().getDriverName());
//             System.out.println("🧩 DB Product: " + conn.getMetaData().getDatabaseProductName());
//         }
//     }
// }
