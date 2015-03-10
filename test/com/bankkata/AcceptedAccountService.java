package com.bankkata;


import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AcceptedAccountService {

    @Mock private Console console;
    @Mock private Clock clock;
    private AccountService accountService;

    @Before
    public void SetUp() throws NoSuchFieldException, IllegalAccessException {
        accountService = new AccountService();
        inject(new AccountServiceControl(clock, new ArrayListRepository(), console));
    }

    private void inject(AccountServiceControl accountServiceControl) throws
                                                                     NoSuchFieldException,
                                                                     IllegalAccessException {
        Field control = accountService.getClass().getDeclaredField("control");
        control.setAccessible(true);
        control.set(accountService, accountServiceControl);
    }

    @Test
    public void should_print_only_the_header_when_there_are_no_transactions() throws Exception{
        accountService.printStatement();
        verify(console).printLine("DATE | AMOUNT | BALANCE");
        verifyNoMoreInteractions(console);
    }

    @Test
    public void should_print_a_single_row_statement_with_a_deposit() throws Exception{
        when(clock.now()).thenReturn("01/04/2014");
        accountService.deposit(1000);
        accountService.printStatement();
        verify(console).printLine("DATE | AMOUNT | BALANCE");
        verify(console).printLine("01/04/2014 | 1000 | 1000");
        verifyNoMoreInteractions(console);
    }

    @Test
    public void should_print_a_single_row_statement_with_a_deposit_in_a_different_day() throws Exception{
        when(clock.now()).thenReturn("01/01/2015");
        accountService.deposit(800);
        accountService.printStatement();
        verify(console).printLine("DATE | AMOUNT | BALANCE");
        verify(console).printLine("01/01/2015 | 800 | 800");
        verifyNoMoreInteractions(console);
    }



    @Test
    public void should_print_two_row_statement_with_two_deposit_in_different_days() throws Exception{
        when(clock.now()).thenReturn("01/01/2015", "02/01/2015");
        accountService.deposit(1000);
        accountService.deposit(800);
        accountService.printStatement();
        verify(console).printLine("DATE | AMOUNT | BALANCE");
        verify(console).printLine("02/01/2015 | 800 | 1800");
        verify(console).printLine("01/01/2015 | 1000 | 1000");
        verifyNoMoreInteractions(console);
    }


    public void should_print_statement_containing_all_transactions() {
        when(clock.now()).thenReturn("01/04/2014", "02/04/2014", "10/04/2014");
        accountService.deposit(1000);
        accountService.withdraw(100);
        accountService.deposit(500);

        accountService.printStatement();

        verify(console).printLine("DATE | AMOUNT | BALANCE");
        verify(console).printLine("10/04/2014 | 500 | 1400");
        verify(console).printLine("02/04/2014 | -100 | 900");
        verify(console).printLine("01/04/2014 | 1000 | 1000");
    }

}
