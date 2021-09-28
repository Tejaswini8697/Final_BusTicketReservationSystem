package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Service.IBusService;
import com.app.Service.IRouteService;
import com.app.pojos.Bus;
import com.app.pojos.Routes;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	IRouteService routeService;
	@Autowired
	IBusService busService;

	public AdminController() {
		System.out.println("In Admin Controller..");
	}

	// ..........Routes Services...............

	@GetMapping("/routes")
	public ResponseEntity<?> getAllRoutes() {
		try {
			return new ResponseEntity<List<Routes>>(routeService.getAllRoutes(), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Routes cannot be fetched", HttpStatus.OK);
		}
	}

	@GetMapping("/routes/{id}")
	public ResponseEntity<?> getRoutesById(@PathVariable int id) {
		System.out.println("in getRoutesBy id");
		try {
			return new ResponseEntity<Routes>(routeService.getRouteById(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Routes not found", HttpStatus.OK);
		}
	}

	@PostMapping("/routes")
	public ResponseEntity<?> addRoutes(@RequestBody Routes r) {
		System.out.println("in add routes" + r);
		try {
			return new ResponseEntity<>(routeService.addRoute(r), HttpStatus.CREATED);

		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	 @PutMapping("/routes") 
	 public ResponseEntity<?> updateRoutes(@RequestBody Routes r)
	 {
		 System.out.println("In update routes");
		 try {
			 return new ResponseEntity<String>(routeService.updateRoutes(r),HttpStatus.OK); 
		 }catch(RuntimeException e)
		 {
			 e.printStackTrace();
			 return new ResponseEntity<String>("Routes cannot be updated", HttpStatus.OK);
		 }
	 
	 }
	
	 @DeleteMapping("/routes/{id}")
	public ResponseEntity<?> removeRoutes(@PathVariable int id) {
		try {
			return new ResponseEntity<String>(routeService.removeRoutes(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Routes deletion failed", HttpStatus.OK);
		}
	}

	// ..........Bus Services........

	@GetMapping("/bus")
	public ResponseEntity<?> getAllBuses() {
		try {
			return new ResponseEntity<List<Bus>>(busService.getAllBus(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Bus cannot be fetched.", HttpStatus.OK);
		}
	}

	@GetMapping("/bus/{id}")
	public ResponseEntity<?> getBusById(@PathVariable int id) {
		try {
			return new ResponseEntity<Bus>(busService.getBusById(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Bus not found..", HttpStatus.OK);
		}
	}

	@PostMapping("/bus/{routeId}")
	public ResponseEntity<?> addBus(@RequestBody Bus b, @PathVariable int routeId) {
		System.out.println("in add bus" + b);
		try {
			return new ResponseEntity<>(busService.addBus(b, routeId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			//return new ResponseEntity<String>("Bus cannot be added successfully", HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/bus")
	public ResponseEntity<?> updateBus(@RequestBody Bus b) {
		try {
			System.out.println("in update bus");
			return new ResponseEntity<>(busService.updateBus(b),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Bus updated successfully", HttpStatus.OK);
		}
	}

	@DeleteMapping("/bus/{id}")
	public ResponseEntity<?> removeBus(@PathVariable int id) {
		try {
			return new ResponseEntity<String>(busService.removeBus(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Bus removed successfully", HttpStatus.OK);
		}
	}

}
