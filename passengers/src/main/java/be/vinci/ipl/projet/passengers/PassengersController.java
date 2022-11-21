package be.vinci.ipl.projet.passengers;

import be.vinci.ipl.projet.passengers.models.Passenger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PassengersController {

  private final PassengersService service;

  public PassengersController(PassengersService service) {
    this.service = service;
  }

  @PostMapping("/trips/{tripId}/users/{userId}")
  public ResponseEntity<Passenger> createOne(@PathVariable long tripId, @PathVariable long userId) {
    if (tripId == 0 || userId == 0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    Passenger createdPassenger = service.createOne(tripId, userId);
    if (createdPassenger == null) {
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(createdPassenger, HttpStatus.CREATED);
  }

  @GetMapping("/passenger/{id}")
  public Passenger readOne(@PathVariable long id) {
    Passenger review = service.readOne(id);
    if (review == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return review;
  }


}
