package service;

import com.financial.transaction.accountsystem.dtos.request.AccountRequestDto;
import com.financial.transaction.accountsystem.dtos.response.AccountResponseDto;
import com.financial.transaction.accountsystem.entity.Account;
import com.financial.transaction.accountsystem.enums.AccountType;
import com.financial.transaction.accountsystem.enums.CurrencyType;
import com.financial.transaction.accountsystem.repository.AccountRepository;
import com.financial.transaction.accountsystem.service.AccountService;
import com.financial.transaction.accountsystem.service.UserServiceClient;
import com.financial.transaction.accountsystem.utils.AccountUtilsManager;
import com.financial.transaction.system.response.UserResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserServiceClient userServiceClient;

    @InjectMocks
    private AccountService accountService;

    private AccountRequestDto accountRequestDto;
    private UserResponseDto userResponseDto;

    @BeforeEach
    void setUp() {
        accountRequestDto = new AccountRequestDto();
        accountRequestDto.setUserId("user123");
        accountRequestDto.setAccountNumber("ACC123");
        accountRequestDto.setAccountType(AccountType.SAVINGS);
        accountRequestDto.setCurrencyType(CurrencyType.USD);
        accountRequestDto.setBalance(1000L);

        userResponseDto = new UserResponseDto();
        userResponseDto.setUserId("user123");
    }

    @Test
    void create_ShouldThrowException_WhenUserDoesNotExist() {
        when(userServiceClient.getUserByUserId(anyString())).thenReturn(new UserResponseDto());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> accountService.create(accountRequestDto));

        assertEquals("User does not exists for userId: user123", exception.getMessage());
    }

    @Test
    void create_ShouldCreateNewAccount_WhenAccountDoesNotExist() {
        when(userServiceClient.getUserByUserId("user123")).thenReturn(userResponseDto);
        when(accountRepository.findByAccountNumber("ACC123")).thenReturn(null);

        // Mocking AccountUtilsManager static method if needed
        Mockito.mockStatic(AccountUtilsManager.class).when(AccountUtilsManager::createAccountId).thenReturn("ACC_ID_123");

        Account account = new Account();
        account.setAccountId("ACC_ID_123");
        account.setUserId("user123");
        account.setAccountNumber("ACC123");
        account.setAccountType(AccountType.SAVINGS);
        account.setCurrencyType(CurrencyType.USD);
        account.setBalance(1000L);
        account.setCreated(new Date());
        account.setUpdated(new Date());

        AccountResponseDto accountResponseDto = accountService.create(accountRequestDto);

        assertNotNull(accountResponseDto);
        assertEquals("ACC123", accountResponseDto.getAccountNumber());
        assertEquals("user123", accountResponseDto.getUserId());
        assertEquals(1000L, accountResponseDto.getBalance());
    }

    @Test
    void create_ShouldUpdateExistingAccount_WhenAccountExists() {
        when(userServiceClient.getUserByUserId("user123")).thenReturn(userResponseDto);

        Account existingAccount = new Account();
        existingAccount.setAccountId("ACC_ID_123");
        existingAccount.setUserId("user123");
        existingAccount.setAccountNumber("ACC123");
        existingAccount.setBalance(500L);
        existingAccount.setCreated(new Date());

        when(accountRepository.findByAccountNumber("ACC123")).thenReturn(existingAccount);

        AccountResponseDto accountResponseDto = accountService.create(accountRequestDto);

        assertNotNull(accountResponseDto);
        assertEquals(1000L, accountResponseDto.getBalance());
        assertEquals("user123", accountResponseDto.getUserId());
        assertEquals("ACC123", accountResponseDto.getAccountNumber());
    }
}
