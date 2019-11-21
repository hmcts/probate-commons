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
    ./gradlew build
```

## Developing

### Coding style tests

To run all checks (including unit tests) please execute the following command:

```bash
    ./gradlew check
```

## Versioning

We use [SemVer](http://semver.org/) for versioning.
For the versions available, see the tags on this repository.

## Versioning
- Update version number  is based on tags.
- Locally get latest of master
- Creat a branch. push the changes code in branch.
- Create tag `git tag -a 0.0.75_PRO_gradel_work_flow_test_1 -m "using gradel tage workflow" `
- Push tags: `git push origin 0.0.75_PRO_gradel_work_flow_test_1`
- It create a new version

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE.md) file for details.
