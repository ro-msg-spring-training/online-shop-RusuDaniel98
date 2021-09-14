package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.service.DummyDatabaseRecordsService;


@RestController
public class DummyDatabaseRecordsController {

    @Autowired
    DummyDatabaseRecordsService dummyService;

    @GetMapping("/dummy")
    private void addDummyRecords() {
        dummyService.addProducts();
        dummyService.addCustomers();
    }

}
