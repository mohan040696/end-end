Feature: Login AirAsia Application

  Background: 
    Given User launch the AirAsia Application

  Scenario Outline: AirAsia application launch
    When User login into air asia application with "<MOBILENO>","<PASSWORD>"

    Examples: 
      | URL | MOBILENO | PASSWORD | homepage |
      | URL | MOBILENO | PASSWORD | homepage |
