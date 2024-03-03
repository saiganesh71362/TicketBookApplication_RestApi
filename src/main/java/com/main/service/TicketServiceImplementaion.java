package com.main.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.main.entity.TicketDetails;
import com.main.repository.TicketRepository;

@Service
public class TicketServiceImplementaion implements TicketService
{
	@Autowired
    private TicketRepository repository;

	@Override
	public String addTicket(TicketDetails book_tickets)
	{
		repository.save(book_tickets);
		return "Ticket Booked Success Fully";
	}

	@Override
	public TicketDetails getTicketById(Integer ticket_id) 
	{
		Optional<TicketDetails>  ticket_ids = repository.findById(ticket_id);
		if(ticket_ids.isPresent())
		{
			return ticket_ids.get();
		}
		return null;
	}

	@Override
	public List<TicketDetails> getAllTickets(Integer integer)
	{
		List<TicketDetails> getAll = repository.findAll();
		return getAll;
	}

	@Override
	public String deleteTicketById(Integer ticket_id) 
	{
	    if (repository.existsById(ticket_id))
	    {
	        repository.deleteById(ticket_id);
	        
	        return "Record Deleted Successfully";
	    } 
	    else 
	    {
	        return "There is No Record";
	    }
	}

//	@Override
//	public TicketDetails updateTickets(TicketDetails employee, Integer id)
//	{
//		TicketDetails existingRecord = repository.findById(id).get();
//		existingRecord.setName(employee.getName());
//		existingRecord.setGender(employee.getGender());
//		existingRecord.setCategory(employee.getCategory());
//		existingRecord.setPrice(employee.getPrice());
//		return repository.save(existingRecord);
//	}
	
	
	@Override
	public TicketDetails updateTickets(TicketDetails ticketDetails, Integer id)
	{
	    TicketDetails existingRecord = repository.findById(id).orElse(null);

	    if (existingRecord != null) 
	    {
	        existingRecord.setName(ticketDetails.getName());
	        existingRecord.setGender(ticketDetails.getGender());
	        existingRecord.setCategory(ticketDetails.getCategory());
	        existingRecord.setPrice(ticketDetails.getPrice());

	        return repository.save(existingRecord);
	    } 
	    else 
	    {
	        // Handle the case where the ticket with the given id is not found.
	        // You might want to throw an exception or return a specific response.
	        return null;
	    }
	}

//	@Override
//	public TicketDetails updateTicketByFields(Map<String, Object> fields, Integer id)
//	{
//		Optional<TicketDetails> ExistTicketUpdateFields = repository.findById(id);
//		if(ExistTicketUpdateFields.isPresent())
//		{
//			fields.forEach((key,value)->
//			{
//				Field requiredField = ReflectionUtils.findRequiredField(TicketDetails.class,key);
//				requiredField.setAccessible(true);
//				ReflectionUtils.setField(requiredField, ExistTicketUpdateFields.get(), value);
//			});
//			return repository.save(ExistTicketUpdateFields.get());
//
//		}
//		return null;
//	}
	
	
	@Override
	public TicketDetails updateTicketByFields(Map<String, Object> fields, Integer id) {
	    Optional<TicketDetails> optionalTicket = repository.findById(id);

	    if (optionalTicket.isPresent()) {
	        TicketDetails existingTicket = optionalTicket.get();

	        fields.forEach((key, value) -> {
	            Field field = ReflectionUtils.findRequiredField(TicketDetails.class, key);

	            if (field != null) {
	                field.setAccessible(true);
	                ReflectionUtils.setField(field, existingTicket, value);
	            }
	        });

	        return repository.save(existingTicket);
	    }

	    return null;
	}
}
