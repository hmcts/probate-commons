# Probate Commons

<!--[![Build Status](https://travis-ci.org/hmcts/probate-commons.svg?branch=master)](https://travis-ci.org/hmcts/probate-commons) -->
[![Download](https://api.bintray.com/packages/hmcts/hmcts-maven/probate-commons/images/download.svg) ](https://bintray.com/hmcts/hmcts-maven/probate-commons/_latestVersion)

This is a library for creating and using shared DTOs across microservices in probate.

## Getting started

### Prerequisites

- [JDK 11](https://www.oracle.com/java)

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
Branch tags should be based off the **current** tag version but with the JIRA appended. 

The JIRA reference will need to be **omitted** and the tag version **updated** when applying the tag to **master**.

The *build.gradle* does **not** need to be updated as the tag version will be taken from the *git tag* once pushed to the remote.

The example below uses version '1.0.0' as the current release and '1.0.1' as the updated master release.

If master is not tagged, a release will not be created and therefore will be unavailable to other components.

#### Updating branch tag

Follow the steps below to tag a branch (possibly once the code review has completed) 
- Ensure build is successful: 
    - ./gradlew clean build
    - git add . 
    - git commit -m  "DTSPB-2231: updating commons for will condition v13"
    - git push
  
- Create the tag in git with the new version and JIRA reference.
    - git tag -a DTSPB-2231-will-condition-v1.0.12 -m "Update version of commons for will condtion"
- Push the new tag to the git remote server.
    - git push origin DTSPB-2231-will-condition-v1.0.12

To delete a tag from local

git tag --delete DTSPB-2231-will-condition-v1.0.0

To delete from server

git push origin --delete DTSPB-2231-will-condition-v1.0.0

#### Updating master tag

Perform the necessary validation steps to merge the code to master. Ensure the branch build succeeds, tagging in git though shows up as errors in travis-ci.
https://travis-ci.org/hmcts/probate-commons/

Once the JIRA has been merged follow the steps below to tag master. 
- Locally get latest of master.
    - git checkout master
    - git pull
- Ensure build is successful: 
    - ./gradew clean build
- Create the tag in git with the updated version:
    - git tag -a 1.0.1 -m "Update to README.md file."
- Push the new tag to the git remote server.
    - git push origin 1.0.1

You can verify the tag is correct in master by viewing the download link at the top of the readme, it should reflect the latest tag.

### Branch from a previous tag

To revert code to a previous tag you can checkout the tag to a new branch as follows.

```bash
    git checkout tags/1.0.0 -b PRO-1235-Reverting-Back-To-Baseline 
```

### Releasing
The Probate-commons is now has been moved to jitpack.

To check which versions is available on jitpack:
https://jitpack.io/com/github/hmcts/probate-commons/

To check on the build log on the version:
https://jitpack.io/com/github/hmcts/probate-commons/{version}/build.log


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE.md) file for details.
