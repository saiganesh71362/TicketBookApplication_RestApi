package com.main.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.entity.TicketDetails;
import com.main.service.TicketService;

@RestController
@RequestMapping
public class TicketController
{
   @Autowired
   private TicketService  ticket_book_service;
   @PostMapping("/ticket")
   public ResponseEntity<String>  BookTicket(@RequestBody TicketDetails book_tickets)
   {
	   String ticket = ticket_book_service.addTicket(book_tickets);
	   return new ResponseEntity<> (ticket,HttpStatus.CREATED);
   }
   
   @GetMapping("/ticket/{ticket_id}")
   public ResponseEntity<TicketDetails> getTicketById(@PathVariable("ticket_id") Integer ticket_id) {
       TicketDetails ticketById = ticket_book_service.getTicketById(ticket_id);
       if (ticketById != null) {
           return new ResponseEntity<>(ticketById, HttpStatus.OK);
       } else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
   }
      
   
//    public ResponseEntity<List<TicketDetails>>  getAllTickets()
//    {
//    	List<TicketDetails> allTickets = ticket_book_service.getAllTickets();
//        
//    	return new ResponseEntity<>(allTickets,HttpStatus.OK);
//    }
   @GetMapping("/alltickets")
   public ResponseEntity<List<TicketDetails>> getAllTickets(@RequestParam(name= "length",required = false) Integer length) 
   { 
       List<TicketDetails> allTickets = ticket_book_service.getAllTickets(length);
         if(length == null)
          {
           return new ResponseEntity<> (allTickets .stream().limit(allTickets .size()).collect(Collectors.toList()),HttpStatus.OK);
          }
         else
          {
	        return new ResponseEntity<> (allTickets .stream().limit(length).collect(Collectors.toList()),HttpStatus.OK);
          }
  }
//   @PutMapping("/update/{ticket_id}")
//   public ResponseEntity<TicketDetails>  updateTickets( @RequestBody TicketDetails ticket_details, @PathVariable Integer id)
//   {
//	   TicketDetails updateTickets = ticket_book_service.updateTickets(ticket_details, id);
//	   return new ResponseEntity<>(updateTickets,HttpStatus.ACCEPTED);
//	   
//   }
   
   @PutMapping("/update/{ticket_id}")
   public ResponseEntity<TicketDetails> updateTicket(@RequestBody TicketDetails ticketDetails, @PathVariable Integer ticket_id) {
       TicketDetails updatedTicket = ticket_book_service.updateTickets(ticketDetails, ticket_id);

       if (updatedTicket != null) {
           return new ResponseEntity<>(updatedTicket, HttpStatus.ACCEPTED);
       } else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
   }
   
//   @PatchMapping("/update/{ticket_id}")
//   public ResponseEntity<TicketDetails> updateEmployeeByFields(@RequestBody Map<String, Object>fields,@PathVariable Integer id )
//   {
//	   TicketDetails updateTicketByFields = ticket_book_service.updateTicketByFields(fields, id);
//   	
//   	
//   	return new ResponseEntity<> (updateTicketByFields, HttpStatus.ACCEPTED);
//   	
//   }
//   
   
   @PatchMapping("/update/{ticket_id}")
   public ResponseEntity<TicketDetails> updateTicketByFields(@RequestBody Map<String, Object> fields, @PathVariable Integer ticket_id) {
       TicketDetails updatedTicket = ticket_book_service.updateTicketByFields(fields, ticket_id);

       if (updatedTicket != null) {
           return new ResponseEntity<>(updatedTicket, HttpStatus.ACCEPTED);
       } else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
   }
   @DeleteMapping("/delete/{ticket_id}")
   public ResponseEntity<String> deleteTicketById(@PathVariable Integer ticket_id) 
   {
	   String deleteTicketById = ticket_book_service.deleteTicketById(ticket_id);
	   
	   return new ResponseEntity<>(deleteTicketById,HttpStatus.OK);
   }
  
   
}
