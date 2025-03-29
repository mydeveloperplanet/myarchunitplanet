package com.mydeveloperplanet.myarchunitplanet.example5;

import static com.tngtech.archunit.core.domain.JavaModifier.FINAL;
import static com.tngtech.archunit.core.domain.JavaModifier.PRIVATE;

import java.util.List;

import com.enofex.taikai.Taikai;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

class MyArchitectureTest {

    @Test
    void shouldFulfillConstraints() {
        Taikai.builder()
                .namespace("com.mydeveloperplanet.myarchunitplanet.example5")
                .java(java -> java
                        .noUsageOfDeprecatedAPIs()
                        .methodsShouldNotDeclareGenericExceptions()
                        .utilityClassesShouldBeFinalAndHavePrivateConstructor()
                        .imports(imports -> imports
                                .shouldHaveNoCycles()
                                .shouldNotImport("..shaded..")
                                .shouldNotImport("org.junit.."))
                        .naming(naming -> naming
                                .classesShouldNotMatch(".*Impl")
                                .methodsShouldNotMatch("^(foo$|bar$).*")
                                .fieldsShouldNotMatch(".*(List|Set|Map)$")
                                .fieldsShouldMatch("com.enofex.taikai.Matcher", "matcher")
                                .constantsShouldFollowConventions()
                                .interfacesShouldNotHavePrefixI()))
                .logging(logging -> logging
                        .loggersShouldFollowConventions(Logger.class, "logger", List.of(PRIVATE, FINAL)))
                .test(test -> test
                        .junit5(junit5 -> junit5
                                .classesShouldNotBeAnnotatedWithDisabled()
                                .methodsShouldNotBeAnnotatedWithDisabled()))
                .spring(spring -> spring
                        .noAutowiredFields()
                        .boot(boot -> boot
                                .springBootApplicationShouldBeIn("com.mydeveloperplanet.myarchunitplanet.example5"))
                        .configurations(configuration -> configuration
                                .namesShouldEndWithConfiguration())
                        .controllers(controllers -> controllers
                                .shouldBeAnnotatedWithRestController()
                                .namesShouldEndWithController()
                                .shouldNotDependOnOtherControllers()
                                .shouldBePackagePrivate())
                        .services(services -> services
                                .shouldBeAnnotatedWithService()
                                .shouldNotDependOnControllers()
                                .namesShouldEndWithService())
                        .repositories(repositories -> repositories
                                .shouldBeAnnotatedWithRepository()
                                .shouldNotDependOnServices()
                                .namesShouldEndWithRepository()))
                .build()
                .check();
    }

    @Test
    void shouldFulfillConstraintsFailOnEmpty() {
        Taikai.builder()
                .namespace("com.mydeveloperplanet.myarchunitplanet.example5")
                .failOnEmpty(true)
                .java(java -> java
                        .noUsageOfDeprecatedAPIs()
                        .methodsShouldNotDeclareGenericExceptions()
//                        .utilityClassesShouldBeFinalAndHavePrivateConstructor()
                        .imports(imports -> imports
                                .shouldHaveNoCycles()
                                .shouldNotImport("..shaded..")
                                .shouldNotImport("org.junit.."))
                        .naming(naming -> naming
                                .classesShouldNotMatch(".*Impl")
                                .methodsShouldNotMatch("^(foo$|bar$).*")
                                .fieldsShouldNotMatch(".*(List|Set|Map)$")
//                                .fieldsShouldMatch("com.enofex.taikai.Matcher", "matcher")
//                                .constantsShouldFollowConventions()
                                .interfacesShouldNotHavePrefixI()))
                .logging(logging -> logging
                        .loggersShouldFollowConventions(Logger.class, "logger", List.of(PRIVATE, FINAL)))
                .test(test -> test
                        .junit5(junit5 -> junit5
                                .classesShouldNotBeAnnotatedWithDisabled()
                                .methodsShouldNotBeAnnotatedWithDisabled()))
                .spring(spring -> spring
                        .noAutowiredFields()
//                        .boot(boot -> boot
//                                .springBootApplicationShouldBeIn("com.mydeveloperplanet.myarchunitplanet.example5"))
//                        .configurations(configuration -> configuration
//                                .namesShouldEndWithConfiguration())
                        .controllers(controllers -> controllers
                                .shouldBeAnnotatedWithRestController()
                                .namesShouldEndWithController()
                                .shouldNotDependOnOtherControllers()
                                .shouldBePackagePrivate())
                        .services(services -> services
                                .shouldBeAnnotatedWithService()
                                .shouldNotDependOnControllers()
                                .namesShouldEndWithService())
                        .repositories(repositories -> repositories
                                .shouldBeAnnotatedWithRepository()
                                .shouldNotDependOnServices()
                                .namesShouldEndWithRepository()))
                .build()
                .check();
    }

}
