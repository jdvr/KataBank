package com.bankkata;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestingAccountServiceControl {
    @Mock
    private Console console;
    @Mock private Clock clock;
    private AccountServiceControl accountServiceControl;

    @Before
    public void SetUp() {
        accountServiceControl = new AccountServiceControl(clock, new ArrayListRepository(), console);
    }

    @Test
    public void should_not_print_anything_if_printStatement_is_not_invoked() throws Exception{
        when(clock.now()).thenReturn("01/04/2014");
        accountServiceControl.deposit(100);
        verify(clock).now();
        verifyNoMoreInteractions(clock);
        verifyNoMoreInteractions(console);
    }

}
