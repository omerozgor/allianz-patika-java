package com.allianz.erp.controller;

import com.allianz.erp.model.dto.ResponseBillDTO;
import com.allianz.erp.model.dto.ResponseCustomerDTO;
import com.allianz.erp.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/bills")
public class BillController {

    @Autowired
    BillService billService;

    @GetMapping("getall")
    public ResponseEntity<List<ResponseBillDTO>> getAll(){

        return new ResponseEntity<>(billService.getAll(), HttpStatus.OK);
    }

    @GetMapping("get-by-uuid/{uuid}")
    public ResponseEntity<ResponseBillDTO> getByUuid(@PathVariable UUID uuid){
        if (billService.getByUuid(uuid) != null){
            return new ResponseEntity<>(billService.getByUuid(uuid),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

}
