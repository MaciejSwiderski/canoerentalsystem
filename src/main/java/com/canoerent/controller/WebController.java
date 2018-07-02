package com.canoerent.controller;

import com.canoerent.CanoeRentApplication;
import com.canoerent.model.*;
import com.canoerent.repository.RentOfTripRepository;
import com.canoerent.repository.RentRepository;
import com.canoerent.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import static com.canoerent.service.utils.EmailContent.emailContentToSend;
import static com.canoerent.service.utils.EmailContent.emailContentToSendRentOfTrips;

@Controller
public class WebController {

    @Autowired
    private CanoeService canoeService;

    @Autowired
    private RentService rentService;

    @Autowired
    private TripService tripService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private RentOfTripRepository rentOfTripRepository;

    @Autowired
    private RentOfTripsService rentOfTripsService;

    @Autowired
    private UserService userService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CanoeForTripService canoeForTripService;

    private static Logger log = LoggerFactory.getLogger(CanoeRentApplication.class);


//    @GetMapping(value = "/index")
//    public ModelAndView index() {
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("index");
//        return modelAndView;
//    }

    @PostMapping(value = "/web/addRole")
    public String addRole(@ModelAttribute("role") Role role,
                          RedirectAttributes redirectAttributes) {
        roleService.save(role);
        redirectAttributes.addFlashAttribute("successMesssage", "added successfully");

        return "redirect:/web/roleForm";
    }

    @GetMapping(value = "/web/roleForm")
    public ModelAndView roleForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("roleForm");
        modelAndView.addObject("role", new Role());

        return modelAndView;
    }

    @PostMapping(value = "/web/remove")
    public ModelAndView removeRentByEmail(@ModelAttribute("rent") Rent rent,
                                          BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Rent rentExist = rentService.findRentByEmail(rent.getUser().getEmail());
        if (rentExist == null) {
            bindingResult.rejectValue("user.email", "error.rent", "Rent not found - wrong id");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("removeForm");
        } else {
            rentService.deleteRentByUserEmail(rent.getUser().getEmail());
            modelAndView.addObject("successMesssage", "Rent has been deleted");
        }
        modelAndView.setViewName("removeForm");
        return modelAndView;
    }

    @GetMapping(value = "/web/removeForm")
    public ModelAndView removeForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("removeForm");
        modelAndView.addObject("rent", new Rent());
        return modelAndView;
    }

    @PostMapping(value = "/web/removeId")
    public ModelAndView removeUserById(@ModelAttribute("rent") Rent rent
            , BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Rent rentExist = rentService.findRentById(rent.getRentId());
        if (rentExist == null) {
            bindingResult.rejectValue("rentId", "error.rent", "rent not found - wrong id");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("removeForm");
        } else {

            rentService.deleteRentById(rent.getRentId());
            modelAndView.addObject("successMesssage", "Rent has been deleted");
        }
        modelAndView.setViewName("removeForm");
        return modelAndView;
    }


    @PostMapping(value = "/web/remove2")
    public ModelAndView removeRentByEmailForTrips(@ModelAttribute("rentOfTrips") RentOfTrips rentOfTrips,
                                                  BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        RentOfTrips rentOfTripsExist = rentOfTripsService.findRentOfTripsByEmail(rentOfTrips.getUser().getEmail());
        if (rentOfTripsExist == null) {
            bindingResult.rejectValue("user.email", "error.rentOfTrips", "Rent not found - wrong id");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("removeRentTripForm");
        } else {
            rentOfTripsService.deleteRentOfTripsByUserEmail(rentOfTrips.getUser().getEmail());
            modelAndView.addObject("successMesssage", "Rent has been deleted");
        }
        modelAndView.setViewName("removeRentTripForm");
        return modelAndView;
    }

    @GetMapping(value = "/web/removeRentTripForm")
    public ModelAndView removeTripForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("removeRentTripForm");
        modelAndView.addObject("rentOfTrips", new RentOfTrips());
        return modelAndView;
    }

    @PostMapping(value = "/web/removeId2")
    public ModelAndView removeRentById(@ModelAttribute("rentOfTrips") RentOfTrips rentOfTrips
            , BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        RentOfTrips rentOfTripsExist = rentOfTripsService.findRentOfTripsById(rentOfTrips.getRentOfTripId());
        if (rentOfTripsExist == null) {
            bindingResult.rejectValue("rentOfTripId", "error.rentOfTrips", "rent not found - wrong id");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("removeRentTripForm");
        } else {


            rentOfTripsService.deleteRentOfTripsById(rentOfTrips.getRentOfTripId());
            //rentService.deleteRentById(rentOfTrips.getRentOfTripId());
            modelAndView.addObject("successMesssage5", "Rent has been deleted");
        }
        modelAndView.setViewName("removeRentTripForm");
        return modelAndView;
    }


    @PostMapping(value = "/web/removeU")
    public ModelAndView removeUserByEmail(@ModelAttribute("user") User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExist = userService.findByEmail(user.getEmail());
        if (userExist == null) {
            bindingResult.rejectValue("email", "error.user", "User not found - wrong email");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("removeUserForm");
        } else {
            rentService.deleteRentByUserEmail(user.getEmail());
            rentOfTripsService.deleteRentOfTripsByUserEmail(user.getEmail());
            userService.deleteUserByUserEmail(user.getEmail());
            modelAndView.addObject("successMesssage", "User and related Rents have been deleted");

        }
        modelAndView.setViewName("removeUserForm");
        return modelAndView;
    }


    @GetMapping(value = "/web/removeUserForm")
    public ModelAndView removeUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("removeUserForm");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }


    @GetMapping(value = "/web/removeTripForm")
    public ModelAndView removeTrip() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("removeTripForm");
        modelAndView.addObject("trip", new Trip());
        return modelAndView;
    }


    @PostMapping(value = "/web/removeTripId")
    public ModelAndView testowo(Trip trip, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Trip tripExist = tripService.findTripById(trip.getId());

        if (tripExist == null) {
            bindingResult.rejectValue("id", "error.trip", "Trip not found - wrong id");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("removeTripForm");
        } else {
            rentOfTripsService.deleteRentOfTripsByTripId(trip.getId());
            tripService.deleteTripById(trip.getId());
            modelAndView.addObject("successMesssage", "Trip and related Rents have been deleted");
        }
        modelAndView.setViewName("removeTripForm");
        return modelAndView;
    }


    @GetMapping(value = "/web/removeCanoeForm")
    public ModelAndView removeCanoe() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("removeCanoeForm");
        modelAndView.addObject("canoe", new Canoe());
        return modelAndView;
    }


    @PostMapping(value = "/web/removeCanoeId")
    public ModelAndView removeCanoe(Canoe canoe, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Canoe canoeExist = canoeService.findCanoeById(canoe.getId());

        if (canoeExist == null) {
            bindingResult.rejectValue("id", "error.canoe", "Canoe not found - wrong id");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("removeCanoeForm");
        } else {
            rentService.deleteRentByCanoeId(canoe.getId());
            canoeService.deleteCanoeById(canoe.getId());
            modelAndView.addObject("successMesssage", "Canoe and related Rents have been deleted");
        }
        modelAndView.setViewName("removeCanoeForm");
        return modelAndView;
    }


    @GetMapping(value = "/web/removeCanoeTripForm")
    public ModelAndView removeCanoeTrip() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("removeCanoeTripForm");
        modelAndView.addObject("canoeTrip", new CanoeTrip());
        return modelAndView;
    }

    @PostMapping(value = "/web/removeCanoeTripId")
    public ModelAndView removeCanoeTrip(CanoeTrip canoeTrip, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        CanoeTrip canoeTripExist = canoeForTripService.findCanoeTripById(canoeTrip.getCanoeTripId());

        if (canoeTripExist == null) {
            bindingResult.rejectValue("canoeTripId", "error.canoeTrip", "Canoe not found - wrong id");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("removeCanoeTripForm");
        } else {
            rentOfTripsService.deleteRentOfTripByCanoeTripId(canoeTrip.getCanoeTripId());
            canoeForTripService.deleteCanoeTripById(canoeTrip.getCanoeTripId());
            modelAndView.addObject("successMesssage", "Canoe and related Rents have been deleted");
        }
        modelAndView.setViewName("removeCanoeTripForm");
        return modelAndView;
    }


    @GetMapping(value = "/web/Rents")
    public ModelAndView rents() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(
                "rentsList",
                rentService.getRent()
        );
        modelAndView.addObject(
                "rentsListOfTrips",
                rentOfTripsService.getRentOfTrips());

        modelAndView.setViewName("rents");
        return modelAndView;
    }

    @GetMapping(value = "/web/Rent")
    public ModelAndView reservation() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(
                "rentsList",
                rentService.getbyEmail()
        );
        modelAndView.addObject(
                "rentsListOfTrips",
                rentOfTripsService.getbyEmail());
        modelAndView.setViewName("reservation");
        return modelAndView;
    }

    @GetMapping(value = "/web/Canoe")
    public ModelAndView canoeStock() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(
                "canoeList",
                canoeService.getCanoe()
        );
        modelAndView.setViewName("canoes");
        return modelAndView;
    }

    @GetMapping(value = "/web/CanoeTrip")
    public ModelAndView canoeTripStock() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(
                "canoeList",
                canoeForTripService.getCanoe()
        );
        modelAndView.setViewName("canoesForTrip");
        return modelAndView;
    }


    @GetMapping(value = "/web/RentLast")
    public ModelAndView getLastRent() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(
                "rentsList",
                rentService.getbyEmailLast());
        modelAndView.addObject(
                "rentsListOfTrips",
                rentOfTripsService.getbyEmailLast());
        modelAndView.setViewName("lastRent");
        return modelAndView;
    }


    @GetMapping(value = "/web/Trip")
    public ModelAndView tripStock() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(
                "tripList",
                tripService.getTrip()
        );
        modelAndView.setViewName("trips");
        return modelAndView;
    }

    @GetMapping(value = "/web/Users")
    public ModelAndView allUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(
                "userList",
                userService.getUser()
        );
        modelAndView.setViewName("users");
        return modelAndView;
    }


    @PostMapping(value = "/web/addCanoe")
    public String addCanoe(@ModelAttribute("canoe") Canoe canoe,
                           RedirectAttributes redirectAttributes) {
        canoeService.save(canoe);
        redirectAttributes.addFlashAttribute("successMesssage", "added successfully");

        return "redirect:/web/canoeForm";
    }

    @GetMapping(value = "/web/canoeForm")
    public ModelAndView canoeForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("canoeForm");
        modelAndView.addObject("canoe", new Canoe());
        return modelAndView;
    }


    @PostMapping(value = "/web/addCanoeTrip")
    public String addCanoeTrip(@ModelAttribute("canoeTrip") CanoeTrip canoeTrip,
                               RedirectAttributes redirectAttributes) {
        canoeForTripService.save(canoeTrip);
        redirectAttributes.addFlashAttribute("successMesssage", "added successfully");

        return "redirect:/web/canoeTripForm";
    }

    @GetMapping(value = "/web/canoeTripForm")
    public ModelAndView canoeTripForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("canoeTripForm");
        modelAndView.addObject("canoeTrip", new CanoeTrip());
        return modelAndView;
    }

    @PostMapping(value = "/web/updateTrip")
    public ModelAndView updateTrip(@ModelAttribute("trip") Trip trip, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Trip tripWithIdExist = tripService.findTripById(trip.getId());

        if (tripWithIdExist == null) {
            bindingResult.rejectValue("id", "error.trip", "Trip not found - wrong id");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("tripForm");
        } else {

            tripWithIdExist.setLocation(trip.getLocation());
            tripWithIdExist.setTripPrice(trip.getTripPrice());
            tripService.save(tripWithIdExist);
            modelAndView.addObject("successMesssage1", "Trip has been updated");
        }
        modelAndView.setViewName("tripForm");
        return modelAndView;
    }


    @PostMapping(value = "/web/updateCanoe")
    public ModelAndView updateCanoe(@ModelAttribute("canoe") Canoe canoe, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Canoe canoeWithIdExist = canoeService.findCanoeById(canoe.getId());

        if (canoeWithIdExist == null) {
            bindingResult.rejectValue("id", "error.canoe", "Canoe not found - wrong id");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("canoeForm");
        } else {

            canoeWithIdExist.setCanoeType(canoe.getCanoeType());
            canoeWithIdExist.setPrice(canoe.getPrice());
            canoeService.save(canoeWithIdExist);
            modelAndView.addObject("successMesssage2", "Canoe has been updated");
        }
        modelAndView.setViewName("canoeForm");
        return modelAndView;
    }


    @PostMapping(value = "/web/updateCanoeTrip")
    public ModelAndView updateCanoeTrip(@ModelAttribute("canoeTrip") CanoeTrip canoeTrip, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        CanoeTrip canoeTripWithIdExist = canoeForTripService.findCanoeTripById(canoeTrip.getCanoeTripId());

        if (canoeTripWithIdExist == null) {
            bindingResult.rejectValue("canoeTripId", "error.canoeTrip", "Canoe not found - wrong id");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("canoeTripForm");
        } else {

            canoeTripWithIdExist.setCanoeTripType(canoeTrip.getCanoeTripType());
            canoeTripWithIdExist.setCanoeForTripPrice(canoeTrip.getCanoeForTripPrice());
            canoeForTripService.save(canoeTripWithIdExist);
            modelAndView.addObject("successMesssage3", "Canoe has been updated");
        }
        modelAndView.setViewName("canoeTripForm");
        return modelAndView;
    }

    @PostMapping(value = "/web/addTrip")
    public String addTrip(@ModelAttribute("trip") Trip trip,
                          RedirectAttributes redirectAttributes) {
        tripService.save(trip);
        redirectAttributes.addFlashAttribute("successMesssage", "added successfully");

        return "redirect:/web/tripForm";
    }

    @GetMapping(value = "/web/tripForm")
    public ModelAndView tripeForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tripForm");
        modelAndView.addObject("trip", new Trip());
        return modelAndView;
    }


    @PostMapping(value = "/web/addRent")
    public String addRent(@ModelAttribute("rent") Rent rent, BindingResult bindingResult
    ) throws Exception {
        // ModelAndView modelAndView=new ModelAndView();


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.parse(rent.getRentPerHourDate());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        String dateNow = dateFormat.format(date);
        simpleDateFormat.parse(dateNow);


        if (simpleDateFormat.parse(rent.getRentPerHourDate()).before(simpleDateFormat.parse(dateNow)))
        // ||(simpleDateFormat.parse(rentOfTrips.getStartDate()).before(simpleDateFormat.parse(dateNow)))){
        {

            bindingResult.rejectValue("rentPerHourDate", "error.rent", "choose date before " + dateNow);
        }
        if (bindingResult.hasErrors()) {
            return "redirect:/web/rentForm";
        } else {
            rentService.save(rent);
            runEmail(rent);
        }
        return "redirect:/web/RentLast";
    }


    @PostMapping(value = "/web/addRentTrip")
    public String addRentOfTrip(@ModelAttribute("rentOfTrips") RentOfTrips rentOfTrips, BindingResult bindingResult
    ) throws Exception {
        // ModelAndView modelAndView=new ModelAndView();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.parse(rentOfTrips.getStartDate());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        String dateNow = dateFormat.format(date);
        simpleDateFormat.parse(dateNow);


        if (simpleDateFormat.parse(rentOfTrips.getStartDate()).before(simpleDateFormat.parse(dateNow)))
        // ||(simpleDateFormat.parse(rentOfTrips.getStartDate()).before(simpleDateFormat.parse(dateNow)))){
        {

            bindingResult.rejectValue("startDate", "error.rentOfTrips", "choose date before " + dateNow);
        }
        if (bindingResult.hasErrors()) {
            return "redirect:/web/rentForm";
        } else {

            rentOfTripsService.save(rentOfTrips);
            runEmailForTrips(rentOfTrips);
        }
        return "redirect:/web/RentLast";
    }


    @GetMapping(value = "/web/rentForm")
    public ModelAndView rentForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("rentForm");
        modelAndView.addObject(
                "canoe", new Canoe()
                //canoeService.getCanoe()
        );
        modelAndView.addObject(
                "canoeTrip", new CanoeTrip()
                //canoeService.getCanoe()
        );

        modelAndView.addObject(
                "canoeList",
                canoeService.getCanoe()
        );
        modelAndView.addObject("rent", new Rent());
        modelAndView.addObject("rentOfTrips", new RentOfTrips());
        modelAndView.addObject(
                "trip", new Trip()
                //canoeService.getCanoe()
        );
        modelAndView.addObject(
                "tripList",
                tripService.getTrip()
        );

        modelAndView.addObject(
                "canoeTripList",
                canoeForTripService.getCanoe()
        );

        return modelAndView;
    }


    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/admin")
    public String admin() {
        return "admin";
    }

    @RequestMapping(value = "/403")
    public String Error403() {
        return "403";
    }

    @RequestMapping(value = "/web/userFunction")
    public String userFunction() {
        return "userFunction";
    }

    @RequestMapping(value = "/web/canoeFunction")
    public String canoeFunction() {
        return "canoeFunction";
    }

    @RequestMapping(value = "/web/tripFunction")
    public String tripFunction() {
        return "tripFunction";
    }

    @RequestMapping(value = "/web/rentFunction")
    public String rentFunction() {
        return "rentFunction";
    }


    public void runEmail(Rent rent) throws Exception {

        log.info("Sending Email with JavaMailSender");

        Mail mail = new Mail();
        mail.setFrom("maciejswiderski261@gmail.com");
        mail.setTo(rent.getUser().getEmail());
        mail.setSubject("New rent of canoes - please make a payment");
        mail.setContent(emailContentToSend(rent));

        emailService.sendSimpleMessage(mail);
    }

    public void runEmailForTrips(RentOfTrips rentOfTrips) throws Exception {

        log.info("Sending Email with JavaMailSender");

        Mail mail = new Mail();
        mail.setFrom("maciejswiderski261@gmail.com");
        mail.setTo(rentOfTrips.getUser().getEmail());
        mail.setSubject("New rent of canoes - please make a payment");
        mail.setContent(emailContentToSendRentOfTrips(rentOfTrips));

        emailService.sendSimpleMessage(mail);
    }
}
