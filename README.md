This is a demo project for practice UI test automation.
Tech stack: Java, Selenium, TestNG
Test automation framework has been designed following Page Object Model pattern.

Tools:
- Selenium server version: 4.20.0
- TestNG version: 7.9.0
- IDE tool: IntelliJ IDEA

Test automation framework structure :
1. Init a Java project
2. Create directory following Page Object Model structure:</br>
  a. **testcases**: store files where test cases are designed following product business. For example: it contains test cases with end-to-end steps of Login fucntion:
    - Open register page URL
    - Click on REGISTER button
    - Enters email, password and confirm password
    - Click SUBMIT button
    - Redirected to homepage with a message noticed "Your account has been registered successfully!" </br>
    
  b. **actions**: store files where implement methods which specify for some pre-defined purposes.
    - **commons**: contains methods which can be called from other pages. For example: openURL of a page, getTitle of the page
    - **pageObjects**: contains methods which is called for a specifice page. For example: LoginPageObject.java file contains only methods used for login page: enterToEmail(),                enterPassword(), clickToLogInButton().
    </br>
  c. **interfaces**: initiate the UI elements of a specific page, make them constant variables and allow them to be accessible from any class.
  </br>
  d. **resources**: contain configuration files, sucha as TestNG XML file: define various parameters and settings for running test and config report.
  </br>
  e. **libSelenium**:
  </br>
  f. **testData**:
  </br>
