# Probate Commons

<!--[![Build Status](https://travis-ci.org/hmcts/probate-commons.svg?branch=master)](https://travis-ci.org/hmcts/probate-commons) -->
[![Download](https://api.bintray.com/packages/hmcts/hmcts-maven/probate-commons/images/download.svg) ](https://bintray.com/hmcts/hmcts-maven/probate-commons/_latestVersion)

This is a library for creating and using shared DTOs across microservices in probate.

## Getting started

### Prerequisites

- [JDK 8](https://www.oracle.com/java)

## Usage

Just include the library as your dependency and you will be to use the DTO classes.

## Building

The project uses [Gradle](https://gradle.org) as a build tool but you don't have install it locally since there is a
`./gradlew` wrapper script.  

To build project please execute the following command:

```bash
    ./gradlew clean build
```

## Developing

### Coding style tests

To run all checks (including unit tests) please execute the following command:

```bash
    ./gradlew check
```

To push the changes to your local repoository execute the following command.

```bash
    ./gradlew publishToMavenLocal
```


## Versioning

For the versions available, see the tags on this repository. Always pull from master so that you've captured the latest update so all files are accurate.

```bash
    git tag
```

All release versions follow the format MAJOR.MINOR.PATCH and from November 2019 have been baselined at '1.0.0'.

### Applying a version tag

Tags are applied to the branch and then to master. 
Branch tags should include the JIRA. The JIRA reference will need to be removed BEFORE merging code with master.
The example below uses version '1.0.1' as an example.

#### Updating branch tags

Follow the steps below to tag a branch once the code review has completed. 
- Ensure build is successful: 
    - ./gradew clean build
- Update version number in build.gradle and attach the JIRA reference.
    - version '1.0.1_PRO-1234'
- Create the tag in git:
    - git tag -a 1.0.1_PRO-1234 -m "Update to README.md file."
- Push the new tag to the git remote server.
    - git push origin 1.0.1_PRO-1234

#### Updating master

On the branch **before** merge.
- Update version number in build.gradle and remove the JIRA reference
- Create tag: 
    - git tag 1.0.1

Perform the necessary validation steps to merge the code to master. Ensure the branch build succeeds, tagging in git though shows up as errors in travis-ci.
https://travis-ci.org/hmcts/probate-commons/

Once the JIRA has been merged follow the steps below to tag master. 
- Locally get latest of master.
    - git checkout master
    - git pull
- Ensure build is successful: "./gradew clean build"
- Check the version in build.gradle is correct.
- Create the tag in git:
    - git tag -a 1.0.1 -m "Update to README.md file."
- Push tags: 
    - git push origin 1.0.1

### Branch from a previous tag

To revert code to a previous tag you can checkout the tag to a new branch as follows.

```bash
    git checkout tags/<tag_name> -b <branch_name> 
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE.md) file for details.
