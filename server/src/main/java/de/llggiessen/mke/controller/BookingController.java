package de.llggiessen.mke.controller;

import de.llggiessen.mke.repository.BookingRepository;
import de.llggiessen.mke.schema.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(path = "/booking")
public class BookingController {

    @Autowired
    BookingRepository repository;

    @GetMapping("")
    public Iterable<Booking> getBookings(){
        return repository.findAll();
    }

    @GetMapping(value = "", params = {"id"})
    public Optional<Booking> getBookingByID(@RequestParam long id){
        return repository.findByID(id);
    }

    @GetMapping(value = "", params = {"year"})
    public Iterable<Booking> getBookingsByYear(@RequestParam(value = "year", required = false, defaultValue = "") String year) {
        return repository.findAllByYear(year);
    }

    @GetMapping(value = "", params = {"retrievalDate"})
    public Iterable<Booking> getBookingsByRetrievalDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date retrievalDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return repository.findAllByRetrievalBoatDate(simpleDateFormat.format(retrievalDate));
    }

    @GetMapping(value = "", params = {"returnDate"})
    public Iterable<Booking> getBookingsByReturnDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date returnDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return repository.findAllByReturnBoatDate(simpleDateFormat.format(returnDate));
    }

    @GetMapping(value = "", params = {"retrievalDate", "returnDate"})
    public Iterable<Booking> getBookingsInRange(@RequestParam("retrievalDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date retrievalDate, @RequestParam("returnDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date returnDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return  repository.findAllInRange(simpleDateFormat.format(retrievalDate), simpleDateFormat.format(returnDate));
    }

    @GetMapping(value = "", params = {"status"})
    public Iterable<Booking> getBookingsByStatus(@RequestParam char status){
        return repository.findAllByStatus(status);
    }
}
