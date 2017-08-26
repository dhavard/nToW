This is a command line Java application intended to demonstrate basic algorithms for converting integer numbers to English word equivalents.

## Preparing

You can clone this repository in order to prepare to build and run it. No pre-built application is available at this time.

## Prerequisites

Java 8 Runtime Environment is require to run this application. See more information [here](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)

Operating System runtime permissions may also be required to be changed. This can be done in the console in the parent directory where the project was cloned.

    chmod -R +rwx ./nToW

## Building

This application can be built using Gradle. See [here](https://gradle.org/docs/current/userguide/gradle_wrapper.html) for more information.

To build the entire project, you should run the following in the root of the checkout.

    ./gradlew build

### Running the Unit Tests

To test the project manually, you can use the gradle test command. The test command is run automatically as part of the build command, so this is not necessary if not making changes.

    ./gradlew test

## Running the application

To run the application, you can use the gradle run command.

    ./gradlew run

Once the application is running it will prompt the user for input. There are four different commands available:

  Command  | Description
---------- | -------------
  --q      | Quit the application
  --h      | View the help text
  --n      | Convert an integer to English word equivalent
  --v      | Convert an integer to English word equivalent using commas and hyphens where appropriate

Example inputs:

    --n 435

    --v 92378692378

The application will only handle numbers between -2^63 and 2^63 exclusively.