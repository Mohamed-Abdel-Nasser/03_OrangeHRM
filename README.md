# OrangeHRM Application

<img src="https://opensource-demo.orangehrmlive.com/web/images/ohrm_branding.png?v=1721393199309" alt="OrangeHRM" width="300">

## Table of Contents
- [Introduction](#introduction)
- [Release Notes](#release-notes)
- [Project Structure](#project-structure)
- [Tools and Technologies](#tools-and-technologies)
- [Contributing](#contributing)

---
## Introduction
OrangeHRM is a comprehensive Human Resource Management (HRM) System that captures all the essential functionalities required for any enterprise. Copyright (C) 2006 OrangeHRM Inc., http://www.orangehrm.com/

OrangeHRM is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
                  
OrangeHRM is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

---
## Release Notes
## Version 1.0
This marks the first release of the AutoFramework-2 project.
I am committed to continually enhancing and evolving this framework to ensure it remains updated, robust, and professional.
With each iteration, the project will integrate modern features, improved functionality, and industry best practices to meet the dynamic needs of test automation.

Future updates will focus on:
- Adding advanced automation features.
- Enhancing performance and efficiency.
- Ensuring compatibility with emerging tools and technologies.
- Maintaining alignment with industry standards to deliver a consistently professional and dependable solution.

---
## Project Structure
```
OrangeHRM-Automation/
├── src/main/java/common/                # Common utilities (e.g., DriverFactory, ConfigReader, Constants)
├── src/main/java/LOGGER/                # Logging setup (e.g., LogManager, log4j2.xml)
├── src/main/java/pages/                 # Page Object Model (POM) classes
├── src/main/java/utility/               # Utility functions (e.g., ScreenshotUtil, TestDataReader)
├── src/test/java/listeners/             # TestNG listeners (e.g., Listener, RetryAnalyzer)
├── src/test/java/TestBase/              # Base test setup and configurations
├── src/test/java/TestClass/             # Test cases for individual modules
├── src/test/resources/                  # Test data and configuration files
├── pom.xml                              # Maven dependencies and project build configuration
└── README.md                            # Project documentation
```
---
## Tools and Technologies
- **Java**: Programming language
    - java 23.0.1 2024-10-15
- **Selenium WebDriver**: For browser automation
    - Selenium 4.27.1
- **IntelliJ IDEA**: A powerful IDE for efficient Java development with advanced features and intelligent code assistance.
    - IntelliJ IDEA 2024.3 (Community Edition)
- **Maven Dependencies**: For dependency management

---
## Contributing
Contributions are welcome!
We welcome contributions from QA Automation Testing Engineers! To contribute:

1. Fork the repository and clone it to your local machine.
2. Create a new branch for your test enhancements or fixes (`git checkout -b test-enhancement-name`).
3. Write clear, maintainable test scripts and ensure thorough test coverage.
4. Run all tests and confirm they pass successfully.
5. Commit your changes with meaningful messages (`git commit -m 'Add new test or fix'`).
6. Push your branch to your fork (`git push origin test-enhancement-name`).
7. Open a pull request, including a description of your improvements or fixes.

We look forward to your contributions and appreciate your attention to quality!

---
