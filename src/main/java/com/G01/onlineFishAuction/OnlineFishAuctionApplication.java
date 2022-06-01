package com.G01.onlineFishAuction;

import com.G01.onlineFishAuction.entities.Code;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

@RestController
@SpringBootApplication
public class OnlineFishAuctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineFishAuctionApplication.class, args);
	}


	@GetMapping("/")
	public String documentation() throws FileNotFoundException {
		try {
			File myObj = new File("src/main/resources/templates/index.html");
			Scanner myReader = new Scanner(myObj);
			StringBuilder FinelString= new StringBuilder();
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				FinelString.append(data);
			}
			myReader.close();
			return FinelString.toString();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			return "Hello world!";
		}

	}

}
