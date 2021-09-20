package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.service.DummyDatabaseRecordsService;


@RestController
@Profile("test")
public class DummyDatabaseRecordsController {

    @Autowired
    DummyDatabaseRecordsService dummyService;

    // adds dummy records into the database
    @PostMapping("/dummy")
    private void addDummyRecords() {
        dummyService.addProducts();
        dummyService.addCustomers();
    }

    // clears the database.
    @DeleteMapping("/dummy")
    private void deleteDummyRecords() {
        dummyService.deleteAllRecords();
    }

}
