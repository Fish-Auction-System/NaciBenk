package com.G01.onlineFishAuction.restApi;

import com.G01.onlineFishAuction.DTO.CooperativeMemberDTO;
import com.G01.onlineFishAuction.entities.*;
import com.G01.onlineFishAuction.exceptions.CodeNotFoundException;
import com.G01.onlineFishAuction.exceptions.FishermanAuctionNotExists;
import com.G01.onlineFishAuction.exceptions.UsernameAlreadyInUse;
import com.G01.onlineFishAuction.exceptions.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.G01.onlineFishAuction.business.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class Controller {
    // Declaration of Service Layers.
    // FIXME Cagatay buraya bak -> Buradaki sorun su tamamen customerService deklare edip injektlemisiz ama
    //  hic kullanmamısız.
    // FIXME Extra recommendation -> Intellij isterseniz final yapın diyor cunku
    //  üstünde baska tekrar atama vs yapmıyorsunuz diyor asagıdaki private variable'ları
    private ICustomerService customerService;
    private IUserService userService;
    private ICooperativeMemberService cooperativeMemberService;

    // Dependecy Injection of Some Objects.
    @Autowired
    public Controller(ICustomerService customerService, IUserService userService,
                      ICooperativeMemberService cooperativeMemberService) {
        // Those object provides data Access to DB server.
        // !! Consider this a warning -> Do not change those if you are examining this code.
        this.customerService = customerService;
        this.userService = userService;
        this.cooperativeMemberService = cooperativeMemberService;
    }

    @GetMapping("/customers")
    // There is no necessary exception part of getAll parts of this controller.
    // Basically this is only for testing and monitoring the system via backend.
    public List<Customer> getCustomer() {
        // returns all customer objects for monitoring.
        return userService.getAllCustomers();
    }

    @GetMapping("/fisherman")
    // There is no necessary exception part of getAll parts of this controller.
    // Basically this is only for testing and monitoring the system via backend.
    public List<Fisherman> getFisherman() {
        // returns all fisherman objects for monitoring.
        return userService.getFisherman();
    }

    @GetMapping("/admin")
    // There is no necessary exception part of getAll parts of this controller.
    // Basically this is only for testing and monitoring the system via backend.
    public List<CooperativeHead> getCooperativeHead() {
        // returns all admin( Cooperative Head) objects for monitoring.
        // Note -> There is only one cooperative head in our Design.
        return userService.getAdmin();
    }

    @GetMapping("/members")
    // There is no necessary exception part of getAll parts of this controller.
    // Basically this is only for testing and monitoring the system via backend.
    public List<CooperativeMember> getMembers() {
        // returns all member( Cooperative member) objects for monitoring.
        return userService.getAllMembers();
    }


    @GetMapping("/loginget/{username}/{password}")
    // Basically generic type login function for backend
    public ResponseEntity<LoginResponseJson> login(@PathVariable String username, @PathVariable String password) throws UsernameNotFoundException {
        String userType = "";
        // returning user type for front-end programmer.
        try {
            if ((userType = userService.login(username, password)) != null) {
                // Http status 2**
                return new ResponseEntity<>(new LoginResponseJson(200, userType, "/api/login", userType), HttpStatus.OK);
            }
            else {
                // Http status 4**
                return new ResponseEntity<>(new LoginResponseJson(400, "Invalid Requests", "/api/login", userType), HttpStatus.BAD_REQUEST);
            }
        }
        catch (UsernameNotFoundException exception){
            return new ResponseEntity<>(new LoginResponseJson(400, "Username does not exists!", "/api/login", userType), HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping("signup/customer")
    // Customer Sign up Post mapping url
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer) throws UsernameAlreadyInUse {
        try {
            userService.customerRegister(customer);
            // response object returns http 2**
            return new ResponseEntity<>("Successful Registration!", HttpStatus.OK);
        }
        // If not understandable error is thrown which is probably server error.
        catch (UsernameAlreadyInUse genericEx) {
            genericEx.printStackTrace();
            // If an object other than Customer object is sent, spring automatically throws bad request already.
            return new ResponseEntity<String>(genericEx.getMessage(), HttpStatus.BAD_REQUEST);

        } catch (SQLException e) {
            return new ResponseEntity<String>("You can not add this data! it is not unique or not valid", HttpStatus.BAD_REQUEST);

        }
        catch (Exception err) {
            return new ResponseEntity<String>("Not unique data! mail or sth in SYSTEM!", HttpStatus.BAD_REQUEST);
        }
    }



    // This method is basically adding cooperative member to website.
    @PostMapping("signup/member")
    // Returns Response entity.
    public ResponseEntity<String> registerCooperativeMember(@RequestBody CooperativeMemberDTO memberDTO) throws UsernameAlreadyInUse{
        try {
            // Try block tries to add customer To website database.
            CooperativeMember newCoopObject = new CooperativeMember(memberDTO.getUsername(), memberDTO.getMail(), memberDTO.getPassword());
            userService.cooperativeMemberRegister(newCoopObject, memberDTO.getCode());
            // User service is layer for accessing the database which is based on Repository system.
            return new ResponseEntity<String>("Successful Login...", HttpStatus.OK);
            // Http Status 2**
        } catch (CodeNotFoundException error) {
            // If generated code by Cooperative Head is not in db system -> this catch block finds and disables the user.
            // Bad Request...
            error.printStackTrace();
            // Console Log.
            return new ResponseEntity<String>("Unsuccessful Login... -> Check Code got from Coop. Head", HttpStatus.BAD_REQUEST);

        }
        catch (UsernameAlreadyInUse error) {
            // If generated code by Cooperative Head is not in db system -> this catch block finds and disables the user.
            // Bad Request...
            error.printStackTrace();
            return new ResponseEntity<String>("Unsuccessful Login... -> Check username", HttpStatus.BAD_REQUEST);

        }
        catch (Exception err) {
            return new ResponseEntity<String>("Not unique data! mail or sth in SYSTEM!", HttpStatus.BAD_REQUEST);
        }
        // If an object other than CooperativeMemberDTO object is sent, spring automatically throws bad request already.

    }

    // Registering the fisherman Url post mapping.
    @PostMapping("/register-fisherman")
    public ResponseEntity<String> registerFisherman(@RequestBody Fisherman fisherman) throws UsernameAlreadyInUse{
        try {
            // Succes HTTP 2**
            cooperativeMemberService.registerFisherman(fisherman);
            return new ResponseEntity<>("Succesful atempt to add fisherman", HttpStatus.OK);
        } catch (UsernameAlreadyInUse generic) {
            // There is a reason it was registered backend.
            // If an object other than Fisherman object is sent, spring automatically throws bad request.
            return new ResponseEntity<>(generic.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception err) {
            return new ResponseEntity<>("Not unique data! mail or sth in SYSTEM!", HttpStatus.BAD_REQUEST);
        }
    }


}
