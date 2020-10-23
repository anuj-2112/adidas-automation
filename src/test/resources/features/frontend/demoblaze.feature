Feature: Demo Blaze

  Scenario: Test Demo Blaze
    Given Demo Blaze URL is open
    When Navigate to Phones
    When Navigate to Monitors
    When Navigate to Laptops
    When Product Sony vaio i5 is clicked
    When Product is added To Cart
    And Accept Pop Up
    When Navigate to Home
    When Product Dell i7 8gb is clicked
    When Product is added To Cart
    And Accept Pop Up
    When Navigate to Cart
    And Delete Dell i7 8gb from the cart
    Then Place Order
    And Enter Details into the Form

