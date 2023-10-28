package com.aninfo;

import com.aninfo.model.Account;
import com.aninfo.model.BankTransaction;
import com.aninfo.service.AccountService;
import com.aninfo.service.BankTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2
public class Memo1BankApp {

	@Autowired
	private AccountService accountService;

	@Autowired
	private BankTransactionService transactionService;

	public static void main(String[] args) {
		SpringApplication.run(Memo1BankApp.class, args);
	}

	@PostMapping("/accounts")
	@ResponseStatus(HttpStatus.CREATED)
	public Account createAccount(@RequestBody Account account) {
		return accountService.createAccount(account);
	}

	@GetMapping("/accounts")
	public Collection<Account> getAccounts() {
		return accountService.getAccounts();
	}

	@GetMapping("/accounts/{cbu}")
	public ResponseEntity<Account> getAccount(@PathVariable Long cbu) {
		Optional<Account> accountOptional = accountService.findById(cbu);
		return ResponseEntity.of(accountOptional);

	}

	@PutMapping("/accounts/{cbu}")
	public ResponseEntity<Account> updateAccount(@RequestBody Account account, @PathVariable Long cbu) {
		Optional<Account> accountOptional = accountService.findById(cbu);

		if (!accountOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		account.setCbu(cbu);
		accountService.save(account);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/accounts/{cbu}")
	public void deleteAccount(@PathVariable Long cbu) {
		accountService.deleteById(cbu);
	}

	public void createTransaction(Long cbu, Double sum, String type) {
		transactionService.createBankTransaction(cbu, sum, type);
	}

	@PostMapping("/accounts/transactions/withdraw/{cbu}")
	@ResponseStatus(HttpStatus.CREATED)
	public Account withdraw(@PathVariable Long cbu, @RequestParam Double sum) {
		this.createTransaction(cbu, sum, "Extraction");
		return accountService.withdraw(cbu, sum);
	}

	@PostMapping("/accounts/transactions/deposit/{cbu}")
	@ResponseStatus(HttpStatus.CREATED)
	public Account deposit(@PathVariable Long cbu, @RequestParam Double sum) {
		Double firstBalance = accountService.findById(cbu).get().getBalance();
		Account account = accountService.deposit(cbu, sum);
		Double newBalance = account.getBalance();
		this.createTransaction(cbu, newBalance - firstBalance, "Deposit");
		return account;
	}

	@GetMapping("/transactions/{id}")
	public ResponseEntity<BankTransaction> getTransactionById(@PathVariable Integer id) {
		Optional<BankTransaction> transactionOptional = transactionService.findById(id);
		return ResponseEntity.of(transactionOptional);
	}

	@GetMapping("/accounts/transactions/{cbu}")
	public Collection<BankTransaction> getTransactionsByAccount(@PathVariable Long cbu) {
		return transactionService.findAllByAccount(cbu);
	}

	@DeleteMapping("/accounts/transactions/{id}")
	public void deleteTransaction(@PathVariable Integer id) {
		transactionService.deleteById(id);
	}

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any())
			.build();
	}
}
