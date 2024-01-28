package com.keshavfinance.accounts.service.impl;

import com.keshavfinance.accounts.constants.AccountsConstants;
import com.keshavfinance.accounts.dto.CustomerDto;
import com.keshavfinance.accounts.entity.Accounts;
import com.keshavfinance.accounts.entity.Customer;
import com.keshavfinance.accounts.exception.CustomerAlreadyExistException;
import com.keshavfinance.accounts.mapper.CustomerMapper;
import com.keshavfinance.accounts.repository.AccountsRepository;
import com.keshavfinance.accounts.repository.CustomerRepository;
import com.keshavfinance.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistException("Customer already registered with given mobileNumber: " +
                    customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDate.from(LocalDateTime.now()));
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDate.from(LocalDateTime.now()));
        newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }
}
