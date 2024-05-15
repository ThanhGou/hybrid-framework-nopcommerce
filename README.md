# Test Automation Project for nopCommerce Demo

This repository contains test automation scripts for the [nopCommerce demo website](https://demo.nopcommerce.com/). The tests are written in Java and utilize Selenium WebDriver for browser automation and TestNG as the testing framework.

## Table of Contents

- [Project Structure](#project-structure)
- [Tech Stack](#tech-stack)
- [Setup and Installation](#setup-and-installation)
- [Run Test](#run-test)
- [Reporting](#reporting)
- [Test example](#test-example)

## Project Structure

```plaintext
hybrid-framework-nopcommerce/
│
│   ├── actions/
│   │   └── commons/
│   │   └── pageObjects/                  # Page Object Model classes
│   │   └── utilities/  
│   └── browserDrivers/
│   └── interfaces/
│   └── libSelenium/
│   └── resources/                        # TestNG configuration file
│   └── testData/
│   └── testcases/
│       └── com.nopcommerce.account/
│
└── README.md                             # Project README file

```
## Tech stack
- **Java**: Programming language for writing tests.
- **Selenium WebDriver**: Browser automation tool.
- **TestNG**: Testing framework for organizing and running tests.

## Setup and Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/ThanhGou/hybrid_framework_nopcommerce
   cd test-automation-nopcommerce
   ```
2. **Install dependencies**
   - Download the latest version of Selenium and TestNG, then add to libraries. For example: selenium-server-4.20.0.jar and testng-7.9.0.jar.
3. **Config Webdriver**
   - Ensure you have the appropriate WebDriver for your browser installed and set in your system PATH. For example, for ChromeDriver:
     - Download ChromeDriver from [here](https://chromedriver.chromium.org/downloads).
     - Add the ChromeDriver location to your system PATH.

## Run test
- Run file `runTestcases.xml` on folder `resources`
## Reporting
- Note: this part will be update later.
  
## Test Example

**Scenario**: User registers with email and password successfully.

- Go to [nopCommerce demo website](https://demo.nopcommerce.com/)
- Open register page URL
- Click on REGISTER button
- Enter email, password, and confirm password
- Click SUBMIT button
- Redirected to homepage with a message noticed "Your account has been registered successfully!"
  
