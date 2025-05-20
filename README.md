# Probate Commons
[![](https://github.com/hmcts/probate-commons/actions/workflows/gradle.yml/badge.svg)](https://github.com/hmcts/probate-commons/actions/workflows/gradle.yml)

This is a library for creating and using shared DTOs across microservices in probate.

## Getting started

### Prerequisites

- [JDK 17](https://www.oracle.com/java)

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

To push the changes to your local repository execute the following command.

```bash
    ./gradlew publishToMavenLocal
```

### Releasing
Releases are created on [github](https://github.com/hmcts/probate-commons/releases/new) and are internally available through azure artifacts [hmcts-lib](https://dev.azure.com/hmcts/Artifacts/_artifacts/feed/hmcts-lib) feed

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE.md) file for details.
