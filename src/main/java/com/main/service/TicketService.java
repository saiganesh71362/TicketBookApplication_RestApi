package com.main.service;

import java.util.List;
import java.util.Map;

import com.main.entity.TicketDetails;



public interface TicketService
{
   public String addTicket(TicketDetails ticket_book);
   
   public TicketDetails getTicketById(Integer ticket_id);
   
   public List<TicketDetails> getAllTickets(Integer length);
   
   TicketDetails updateTickets(TicketDetails employee, Integer id);
   
   TicketDetails  updateTicketByFields(Map<String , Object > fields, Integer id);
   
   public String deleteTicketById(Integer ticket_id);
   
   
}
