package com.ashraafcode.customer;

import com.example.clients.fraud.FraudCheckResponse;
import com.example.clients.fraud.FraudClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

//    @Autowired
//    RestTemplate restTemplate;

    private final FraudClient fraudClient;

    public void registerCustomer(CustomerRegistrationRequest request){
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();

        //todo: check if email is valid
        //todo: check if email not taken
        //todo: store in db

        //todo: check if fraudster

        customerRepository.saveAndFlush(customer);

//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://localhost:8081/api/v1/fraud-check/{customerId}", FraudCheckResponse.class, customer.getId());

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse.getIsFraudster()){
            throw new IllegalArgumentException("fraudster");
        }

        //todo: send notification

        customerRepository.save(customer);
    }

}
