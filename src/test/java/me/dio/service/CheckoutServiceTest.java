package me.dio.service;

import me.dio.repository.CheckoutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {

    @Mock
    private CartService cartService;
    @Mock
    private CheckoutRepository checkoutRepository;

    @InjectMocks
    private CheckoutService checkoutService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void roundToTwoDecimalPlacesRoundsCorrectly() {
        double result = checkoutService.roundToTwoDecimalPlaces(10.456);
        assertEquals(10.46, result);
    }
}
